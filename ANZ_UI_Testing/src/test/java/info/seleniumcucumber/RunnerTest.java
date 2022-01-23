package info.seleniumcucumber;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        glue = {"info.seleniumcucumber.steps"},
        features = {"classpath:features/my_first.feature"},
        dryRun = false,
        monochrome = true,
        plugin = {
                "html:target/LoginReport", "json:target/cucumber.json",
                "pretty:target/cucumber-pretty.txt",
                "usage:target/cucumber-usage.json","junit:target/cucumber-results.xml"}
)

public class RunnerTest {
}