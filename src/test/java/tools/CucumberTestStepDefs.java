package tools;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class CucumberTestStepDefs {
    private Feature feature;

    @Given("^a feature$")
    public void arrange() {
        feature = new Feature();
    }

    @When("^I run tests$")
    public void act() {
        feature.run();
    }

    @Then("^I want to see results$")
    public void asserts() {
        assertTrue(feature.ran());
    }

}

class Feature {
    private boolean ran = false;

    public void run() {
        this.ran = true;
    }

    public boolean ran() {
        return ran;
    }
}