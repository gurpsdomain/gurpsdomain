package org.gurpsdomain.features.steps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.gurpsdomain.Pipeline;
import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.output.SheetOutput;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

import static org.gurpsdomain.adapters.input.yaml.YamlSheetInput.fromYaml;
import static org.gurpsdomain.adapters.output.json.JsonSheetOutput.toJson;
import static org.gurpsdomain.matchers.MapOfMapMatcher.hasPath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConvertSheetSteps {
    public static final String SHEETS_LOCATION = "src/test/resources/sheets/";
    private Reader reader;
    private Writer writer;

    @Given("^a sheet file (.+\\.yml)$")
    public void a_sheet_file(String file) throws Throwable {
        reader = new FileReader(new File(SHEETS_LOCATION + file));
    }

    @When("^I convert it to json$")
    public void i_convert_it_to_yaml() throws Throwable {
        SheetInput input = fromYaml(reader);
        writer = new StringWriter();
        SheetOutput output = toJson(writer);
        Pipeline.flow(input).into(output);
    }



    @Then("^I expect a point total of (\\d+)$")
    public void i_expect_a_point_total_of(double pointTotal) throws Throwable {
        Map<String, Object> data = outputAsMap(writer);

        assertThat(data, hasPath("points.total", is(pointTotal)));
    }

    private Map<String, Object> outputAsMap(Writer writer) {
        String result = writer.toString();
        Gson gson = new Gson();
        Type stringObjectMap = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(result, stringObjectMap);
    }
}
