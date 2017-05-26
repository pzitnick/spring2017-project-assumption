package base.logic;

import base.data.Material;
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

    private CLContext context;
    private CLDevice device;
    private CLProgram program;
    private CLCommandQueue queue;
    private CLKernel kernel;

    private Scene scene;
    private Display display;

    public PathTracer(Scene scene, Display display) {
        this.scene = scene;
        this.display = display;
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
    }

    public void render() {
        long localWorkSize = Math.min(256, device.getMaxWorkGroupSize());
        long globalWorkSize = (long) display.getWidth() * (long) display.getHeight();
        if (globalWorkSize % localWorkSize != 0) {
            globalWorkSize = (globalWorkSize / localWorkSize + 1) * localWorkSize;
        }

        CLBuffer<FloatBuffer> in = context.createFloatBuffer(scene.getSceneObjects().get(0).getNumFloats() * scene.getSceneObjects().size(), CLMemory.Mem.READ_ONLY);
        CLBuffer<FloatBuffer> out = context.createFloatBuffer(display.getWidth() * display.getHeight() * 4, CLMemory.Mem.WRITE_ONLY);

        for (int i = 0; i < scene.getSceneObjects().size(); ++i) {
            for (float f : scene.getSceneObjects().get(i).toFloatList()) {
                in.getBuffer().put(f);
            }
            in.getBuffer().put(0f);
            in.getBuffer().put(0f);
            in.getBuffer().put(0f);
        }
        in.getBuffer().rewind();

        kernel.setArg(0, in);
        kernel.setArg(1, scene.getSceneObjects().size());
        kernel.setArg(2, display.getWidth());
        kernel.setArg(3, display.getHeight());
        kernel.setArg(4, 0);
        kernel.setArg(5, out);
        kernel.rewind();

        int frame = 1;

        while (true) {
            kernel.setArg(4, frame);
            kernel.rewind();

            queue.putWriteBuffer(in, true)
                    .put1DRangeKernel(kernel, 0, globalWorkSize, localWorkSize)
                    .putReadBuffer(out, false)
                    .finish();

            int x = 0;
            int y = 0;
            while (out.getBuffer().hasRemaining()) {
                float r = out.getBuffer().get();
                float g = out.getBuffer().get();
                float b = out.getBuffer().get();
                out.getBuffer().get();

                display.setPixel(x, y, r, g, b);

                if (++x >= display.getWidth()) {
                    x = 0;
                    y++;
                }
            }
            display.repaint();

            frame++;
            out.getBuffer().rewind();
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
