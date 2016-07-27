package org.gurpsdomain.features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "src/test/resources/features" },
        glue = { "org.gurpsdomain.features.steps" }
)
public class CucumberTest {
}
