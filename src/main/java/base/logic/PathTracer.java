package base.logic;

import base.data.Scene;
import base.presentation.Display;
import com.jogamp.opencl.*;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.logging.Logger;

public class PathTracer {

  private static final Logger LOGGER = Logger.getLogger(PathTracer.class.getName());
  private static final String KERNEL_SRC = "/opencl/PathTracer.cl";
  private static final String KERNEL = "path_trace";
  // OpenCL variables
  private CLContext context;
  private CLDevice device;
  private CLProgram program;
  private CLCommandQueue queue;
  private CLKernel kernel;
  // Work sizes of the device
  private long localWorkSize;
  private long globalWorkSize;

  private Scene scene;
  private Display display;

  private boolean hasInit;

  private CLBuffer<FloatBuffer> input;
  private CLBuffer<FloatBuffer> output;

  public PathTracer(Scene scene, Display display) {
    this.scene = scene;
    this.display = display;

    hasInit = false;
  }

  public void init() throws IOException {
    context = CLContext.create();
    LOGGER.info(context.toString());

    device = context.getMaxFlopsDevice();
    LOGGER.info(device.toString());

    queue = device.createCommandQueue();
    LOGGER.info(queue.toString());

    program = context.createProgram(getClass().getResourceAsStream(KERNEL_SRC)).build();
    LOGGER.info(program.toString());

    kernel = program.createCLKernel(KERNEL);
    LOGGER.info(kernel.toString());

    prepKernel();

    hasInit = true;
  }

  private void prepKernel() {
    localWorkSize = Math.min(256, device.getMaxWorkGroupSize());
    globalWorkSize = (long) display.getWidth() * (long) display.getHeight();
    if (globalWorkSize % localWorkSize != 0) {
      globalWorkSize = (globalWorkSize / localWorkSize + 1) * localWorkSize;
    }

    input = scene.createFloatBuffer(context);
    output = context.createFloatBuffer(display.getWidth() * display.getHeight() * 4,
        CLMemory.Mem.WRITE_ONLY);

    kernel.setArg(0, input)
        .setArg(1, scene.getSceneObjects().size())
        .setArg(2, display.getWidth())
        .setArg(3, display.getHeight())
        .setArg(5, output)
        .rewind();

    LOGGER.info("Ready to render...");
  }

  public void render(boolean renderAgain) throws IllegalStateException {
    if (hasInit) {
      long frame = 1;
      do {
        LOGGER.info("Rendering pass " + frame);

        kernel.setArg(4, frame++)
            .rewind();

        queue.putWriteBuffer(input, true)
            .put1DRangeKernel(kernel, 0, globalWorkSize, localWorkSize)
            .putReadBuffer(output, false)
            .finish();

        int x = 0;
        int y = 0;
        while (output.getBuffer().hasRemaining()) {
          float r = output.getBuffer().get();
          float g = output.getBuffer().get();
          float b = output.getBuffer().get();
          output.getBuffer().get();

          display.setPixel(x, y, r, g, b);

          if (++x >= display.getWidth()) {
            x = 0;
            y++;
          }
        }
        display.repaint();

        output.getBuffer().rewind();
      } while (renderAgain);
    } else {
      throw new IllegalStateException("init() must be called prior to render()");
    }
  }

  public Scene getScene() {
    return scene;
  }

  public void setScene(Scene scene) {
    this.scene = scene;
  }

  public Display getDisplay() {
    return display;
  }

  public void setDisplay(Display display) {
    this.display = display;
  }

  public CLContext getContext() {
    return context;
  }

  public void setContext(CLContext context) {
    this.context = context;
  }

  public CLDevice getDevice() {
    return device;
  }

  public void setDevice(CLDevice device) {
    this.device = device;
  }

  public CLProgram getProgram() {
    return program;
  }

  public void setProgram(CLProgram program) {
    this.program = program;
  }

  public CLCommandQueue getQueue() {
    return queue;
  }

  public void setQueue(CLCommandQueue queue) {
    this.queue = queue;
  }

  public CLKernel getKernel() {
    return kernel;
  }

  public void setKernel(CLKernel kernel) {
    this.kernel = kernel;
  }
}
