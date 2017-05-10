package base;

import com.nativelibs4java.opencl.*;
import com.nativelibs4java.util.IOUtils;
import org.bridj.Pointer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class KernelTest {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    private KernelTest() {
        throw new UnsupportedOperationException();
    }

    public static void test() throws IOException {
        CLContext context = JavaCL.createBestContext();
        CLQueue queue = context.createDefaultQueue();

        int dataSize = 128;

        Pointer<Float> inputData = Pointer.allocateFloats(dataSize).order(context.getByteOrder());
        for (int i = 0; i < 128; i++) {
            inputData.set(i, 1.0f);
        }

        CLBuffer input = context.createFloatBuffer(CLMem.Usage.Input, inputData, true);
        input.write(queue, inputData, true);

        CLBuffer<Float> output = context.createFloatBuffer(CLMem.Usage.Output, dataSize);
        float multFactor = 2f;

        String source = IOUtils.readText(new File("src/main/opencl/MultFloats.cl"));

        CLProgram program = context.createProgram(source).build();

        // Create kernel and set arguments
        CLKernel kernel = program.createKernel("mult_floats", input, output, multFactor);

        CLEvent kernelCompletion = kernel.enqueueNDRange(queue, new int[]{dataSize});

        kernelCompletion.setCompletionCallback(executionStatus -> {
            // Read the whole buffer
            Pointer<Float> newResults = output.read(queue);

            System.out.println("Num data: " + newResults.getValidElements());
        });

        kernelCompletion.waitFor();
    }

}
