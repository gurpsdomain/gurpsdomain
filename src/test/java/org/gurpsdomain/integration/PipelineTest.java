package org.gurpsdomain.integration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.gurpsdomain.Pipeline;
import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.output.SheetOutput;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

import static org.gurpsdomain.adapters.input.yaml.YamlSheetInput.fromYaml;
import static org.gurpsdomain.adapters.output.json.JsonSheetOutput.toJson;
import static org.gurpsdomain.matchers.MapOfMapMatcher.hasPath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

public class PipelineTest {
    private Reader reader;
    private Writer writer;
    private String expectedJson;

    @Before
    public void createReader() throws FileNotFoundException {
        reader = new FileReader(new File("src/test/resources/sheets/dai-blackthorn.yml"));
    }

    @Before
    public void createWriter() {
        writer = new StringWriter();
    }

    @Before
    public void readExpectedJson() throws FileNotFoundException {
        BufferedReader jsonReader = new BufferedReader(new FileReader(new File("src/test/resources/json/dai-blackthorn.json")));
        expectedJson = jsonReader.lines().collect(Collectors.joining());
    }

    @Test
    public void shouldProduceCorrectJson() throws FileNotFoundException {
        SheetInput input = fromYaml(reader);
        SheetOutput output = toJson(writer);

        Pipeline.flow(input).into(output);
        
        String json = writer.toString();
        assertThat(json, sameJSONAs(expectedJson));
    }
}

