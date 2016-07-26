package org.gurpsdomain;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
    "src/test/resources/cucumber-test.feature"
}, glue = { "org.gurpsdomain" })
public class CucumberTest {
}

