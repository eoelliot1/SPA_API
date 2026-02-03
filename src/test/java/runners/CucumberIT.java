package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/login.feature",
                "src/test/resources/features/courses.feature",
                "src/test/resources/features/students.feature",
                "src/test/resources/features/trainers.feature",
        },
        glue = "steps",
        plugin = {"pretty"},
        monochrome = true
)
public class CucumberIT {
}
