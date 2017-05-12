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

    private final CLContext context;
    private final CLDevice device;
    private final CLProgram program;
    private final CLCommandQueue queue;
    private final CLKernel kernel;

    private Scene scene;
    private Display display;

    public PathTracer(Scene scene, Display display) throws IOException {
        this.scene = scene;
        this.display = display;

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
        kernel.setArg(4, out);
        kernel.rewind();

        queue.putWriteBuffer(in, true);
        queue.put1DRangeKernel(kernel, 0, globalWorkSize, localWorkSize);
        queue.putReadBuffer(out, false);
        queue.finish();

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

        display.getjFrame().setVisible(true);
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
}
