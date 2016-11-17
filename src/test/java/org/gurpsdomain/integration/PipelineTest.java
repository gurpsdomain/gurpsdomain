package org.gurpsdomain.integration;

import org.gurpsdomain.Pipeline;
import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.output.SheetOutput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.gurpsdomain.adapters.input.yaml.YamlSheetInput.fromYaml;
import static org.gurpsdomain.adapters.output.json.JsonSheetOutput.toJson;
import static org.junit.Assert.assertThat;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

@RunWith(Parameterized.class)
public class PipelineTest {
    private static final File RESOURCES = Paths.get("src", "test", "resources").toFile();

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {

        Collection<Object[]> data = new ArrayList<>();
        data.add(dataFor("dai-blackthorn"));
        return data;
    }

    private static Object[] dataFor(String name) {
        return new Object[]{
            name, new File(RESOURCES, "sheets/" + name + ".yml"), new File(RESOURCES,  "json/" + name + ".json")
        };
    }

    private final File input;
    private final File expected;
    private Reader reader;
    private Writer writer;
    private String expectedJson;

    public PipelineTest(String _, File input, File expected) {
        this.input = input;
        this.expected = expected;
    }

    @Before
    public void createReader() throws FileNotFoundException {
        reader = new FileReader(input);
    }

    @Before
    public void createWriter() {
        writer = new StringWriter();
    }

    @Before
    public void readExpectedJson() throws FileNotFoundException {
        BufferedReader jsonReader = new BufferedReader(new FileReader(expected));
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

