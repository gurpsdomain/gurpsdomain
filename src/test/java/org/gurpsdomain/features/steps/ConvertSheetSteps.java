package org.gurpsdomain.features.steps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.gurpsdomain.Pipeline;
import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.input.yaml.YamlSheetInput;
import org.gurpsdomain.adapters.output.SheetOutput;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

import static org.gurpsdomain.adapters.input.yaml.YamlSheetInput.sheetInputBuilder;
import static org.gurpsdomain.adapters.output.json.JsonSheetOutput.toJson;
import static org.gurpsdomain.matchers.MapOfMapMatcher.hasPath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConvertSheetSteps {
    public static final String SHEETS_LOCATION = "src/test/resources/sheets/";
    public static final String INITIAL_SHEET = "initial.yml";
    private Reader reader;
    private Writer writer;

    @Given("^a sheet file (.+\\.yml)$")
    public void a_sheet_file(String file) throws Throwable {
        reader = new FileReader(new File(SHEETS_LOCATION + file));
    }

    @Given("^an initial sheet$")
    public void an_initial_sheet() throws Throwable {
        reader = new FileReader(new File(SHEETS_LOCATION + INITIAL_SHEET));
    }


    @And("^I add an advantage named \"([^\"]*)\"$")
    public void i_add_an_advantage_named(String advantage) throws Throwable {
        reader = updateYaml(reader, "advantages:", "- name: " + advantage);
    }

    @And("^I add a skill named \"([^\"]*)\" for (\\d+) points$")
    public void i_add_a_skill_named_for_points(String skill, int points) throws Throwable {
        reader = updateYaml(reader, "skills:", "- name: " + skill + "\n points: " + points);
    }


    @When("^I convert it to json$")
    public void i_convert_it_to_yaml() throws Throwable {
        YamlSheetInput.Builder read = sheetInputBuilder();
        read.advantagesFrom("src/main/resources/data/advantages.basic-set.xml");
        read.skillsFrom("src/main/resources/data/skills.basic-set.xml");
        read.spellsFrom("src/main/resources/data/spells.magic-set.xml");
        read.equipmentFrom("src/main/resources/data/equipments.basic-set.xml");

        SheetInput input = read.fromYaml(reader);
        writer = new StringWriter();
        SheetOutput output = toJson(writer);
        Pipeline.flow(input).into(output);
    }

    @Then("^I expect a handedness that is \"([^\"]*)\"$")
    public void i_expect_a_handedness_that_is(String handedness) throws Throwable {
        Map<String, Object> data = outputAsMap(writer);

        assertThat(data, hasPath("metaData.description.hand", is(handedness)));
    }

    @Then("^I expect a point total of (\\d+)$")
    public void i_expect_a_point_total_of(double pointTotal) throws Throwable {
        Map<String, Object> data = outputAsMap(writer);

        assertThat(data, hasPath("points.total", is(pointTotal)));
    }

    @Then("^I expect (?:a|an) (.*) points total of (\\d+)$")
    public void i_expect_an_X_points_total_of(String category, double pointsTotal) throws Throwable {
        Map<String, Object> data = outputAsMap(writer);

        assertThat(data, hasPath("points." + category, is(pointsTotal)));
    }


    private Map<String, Object> outputAsMap(Writer writer) {
        String result = writer.toString();
        Gson gson = new Gson();
        Type stringObjectMap = new TypeToken<Map<String, Object>>() {
        }.getType();
        return gson.fromJson(result, stringObjectMap);
    }

    private Reader updateYaml(Reader reader, String marker, String data) throws Throwable {
        File tempFile = File.createTempFile("temp-from-empty-sheet-", ".yml");
        tempFile.deleteOnExit();

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(reader);
            bw = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line + "\n");
                if (line.contains(marker)) {
                    bw.write(data + "\n");
                }
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                //
            }
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                //
            }
        }
        return new FileReader(tempFile);
    }
}
