package base.logic;

import base.data.Scene;
import base.data.SceneObject;
import base.data.Sphere;
import base.data.util.Vector3;
import base.presentation.Display;
import com.jogamp.opencl.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.logging.Level;
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

        //CLBuffer<ByteBuffer> isectBuffer = context.createByteBuffer(, CLMemory.Mem.WRITE_ONLY);
        //CLBuffer<FloatBuffer> sphereBuffer = context.createFloatBuffer(Sphere.NUM_FLOATS, CLMemory.Mem.READ_ONLY);

        Sphere test1 = new Sphere();
        test1.setPosition(new Vector3(1, 2, 3));
        Sphere test2 = new Sphere();
        test2.setPosition(new Vector3(10, 20, 30));

        int elementCount = 1;

        int localWorkSize = Math.min(device.getMaxWorkGroupSize(), 256);
        int globalWorkSize = roundUp(localWorkSize, elementCount);

        CLBuffer<FloatBuffer> in = context.createFloatBuffer(globalWorkSize, CLMemory.Mem.READ_ONLY);
        CLBuffer<FloatBuffer> out = context.createFloatBuffer(globalWorkSize, CLMemory.Mem.WRITE_ONLY);

        for (float f : test1.toFloatList()) {
            in.getBuffer().put(f);
        }
        for (float f : test2.toFloatList()) {
            in.getBuffer().put(f);
        }
        in.getBuffer().rewind();

        kernel.putArgs(in).putArg(2).putArg(0).putArg(0).putArgs(out);

        queue.putWriteBuffer(in, false)
                .put1DRangeKernel(kernel, 0, globalWorkSize, localWorkSize)
                .putReadBuffer(out, true);

        for (int i = 0; i < 6; i++) {
            System.out.println(out.getBuffer().get());
        }
    }

    private static int roundUp(int groupSize, int globalSize) {
        int r = globalSize % groupSize;
        if (r == 0) {
            return globalSize;
        } else {
            return globalSize + groupSize - r;
        }
    }

    public void render() throws IOException {

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
