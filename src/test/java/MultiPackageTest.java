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
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MultiPackageTest {

    private String packageName = "base";
    private List<PojoClass> pojoClasses;

    @Before
    public void setup() {
        FilterChain filter = new FilterChain(new FilterClassName("Display"), new FilterClassName("PathTracer"));

        pojoClasses = PojoClassFactory.getPojoClassesRecursively(packageName, filter);
    }

    @Test
    public void validate() {
        for (PojoClass pojoClass : pojoClasses) {
            Validator validator = ValidatorBuilder.create()
                    .with(new SetterMustExistRule(), new GetterMustExistRule())
                    .with(new SetterTester(), new GetterTester())
                    .build();
            validator.validate(pojoClass);
        }
    }

}