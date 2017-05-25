package base.logic;

import base.data.Scene;
import base.presentation.Display;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

public class PathTracerTest extends TestCase {

    @Test
    public void testInit() {
        PathTracer pt = new PathTracer(new Scene(), new Display(100, 100));
        try {
            pt.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(pt.getContext());
        assertNotNull(pt.getDevice());
        assertNotNull(pt.getProgram());
        assertNotNull(pt.getQueue());
        assertNotNull(pt.getKernel());

    }

}