package base;

import com.nativelibs4java.opencl.*;
import com.nativelibs4java.util.IOUtils;
import org.bridj.Pointer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    private Application() {
        throw new UnsupportedOperationException();
    }

    public static void main(String args[]) throws IOException {
        CLContext context = JavaCL.createBestContext();
        final CLQueue queue = context.createDefaultQueue();

        int dataSize = 128;

        final Pointer<Float> inputData = Pointer.allocateFloats(dataSize).order(context.getByteOrder());
        inputData.set(0, 1.0f);
        inputData.set(1, 1.1f);
        inputData.set(2, 1.2f);

        CLBuffer input = context.createFloatBuffer(CLMem.Usage.Input, inputData, true);
        input.write(queue, inputData, true);

        final CLBuffer<Float> output = context.createFloatBuffer(CLMem.Usage.Output, dataSize);
        float multFactor = 2f;

        String source = IOUtils.readText(new File("src/main/opencl/MultFloats.cl"));

        CLProgram program = context.createProgram(source).build();

        // Create kernel and set arguments
        CLKernel kernel = program.createKernel("mult_floats", input, output, multFactor);

        CLEvent kernelCompletion = kernel.enqueueNDRange(queue, new int[]{dataSize});

        kernelCompletion.setCompletionCallback(new CLEvent.EventCallback() {

            public void callback(int executionStatus) {
                // Read the whole buffer
                Pointer<Float> newResults = output.read(queue);

                System.out.println("Data " + newResults.get(0));
                System.out.println("Data " + newResults.get(1));
                System.out.println("Data " + newResults.get(2));
            }
        });

        kernelCompletion.waitFor();
    }

}
