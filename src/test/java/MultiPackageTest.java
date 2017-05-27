import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterClassName;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;

public class MultiPackageTest extends TestCase {

  private static final Logger LOGGER = Logger.getLogger(MultiPackageTest.class.getName());
  private static final String PACKAGE_NAME = "base";

  @Test
  public void testPojo() {
    FilterClassName filter = new FilterClassName("PathTracer");

    List<PojoClass> pojoClasses = PojoClassFactory.getPojoClassesRecursively(PACKAGE_NAME, filter);

    for (PojoClass pojoClass : pojoClasses) {
      Validator validator = ValidatorBuilder.create()
          .with(new SetterTester(), new GetterTester())
          .build();

      try {
        validator.validate(pojoClass);
      } catch (Exception e) {
        LOGGER.info(e.getMessage());
      }
    }
  }

}