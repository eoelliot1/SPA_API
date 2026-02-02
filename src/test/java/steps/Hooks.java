package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.HelperClass;

public class Hooks {

    @Before
    public static void setUp() {

        HelperClass.setUpDriver();
    }

    @After
    public static void tearDown(Scenario scenario) {
        HelperClass.tearDown();
    }
}