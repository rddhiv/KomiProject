package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.*;

@RunWith(Cucumber.class) 
@CucumberOptions(
		features="src/test/resources/Features",
		glue={"StepDefinitions"},
		monochrome = true, 
		plugin = "html:target/cucumber-report.html"
		)
public class TestRunner {
}
