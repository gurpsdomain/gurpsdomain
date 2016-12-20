package tools;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
    "src/test/resources/tools/cucumber-test.feature"
}, glue = { "tools" })
public class CucumberTest {
}

