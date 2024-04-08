package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"pretty","json:target/cucumber.json", "html:target/cucumber-html-report"},
        features="src/test/features/",
        tags= "@UITest",
        glue={"StepDefinition"})
public class Runner
{

}