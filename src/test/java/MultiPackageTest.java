import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterChain;
import com.openpojo.reflection.filters.FilterClassName;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class MultiPackageTest extends TestCase {

    private static final String PACKAGE_NAME = "base";

    @Test
    public void testPojo() {
        FilterChain filter = new FilterChain(new FilterClassName("Display"), new FilterClassName("PathTracer"));

        List<PojoClass> pojoClasses = PojoClassFactory.getPojoClassesRecursively(PACKAGE_NAME, filter);

        for (PojoClass pojoClass : pojoClasses) {
            Validator validator = ValidatorBuilder.create()
                    .with(new SetterMustExistRule(), new GetterMustExistRule())
                    .with(new SetterTester(), new GetterTester())
                    .build();
            validator.validate(pojoClass);
        }
    }

}