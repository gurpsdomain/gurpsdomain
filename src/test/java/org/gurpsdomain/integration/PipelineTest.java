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

import static org.gurpsdomain.adapters.input.yaml.YamlSheetInput.fromYaml;
import static org.gurpsdomain.adapters.output.json.JsonSheetOutput.toJson;
import static org.gurpsdomain.matchers.MapOfMapMatcher.hasPath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PipelineTest {
    private Reader reader;
    private Writer writer;

    @Before
    public void createReader() throws FileNotFoundException {
        reader = new FileReader(new File("src/test/resources/sheets/dai-blackthorn.yml"));
    }

    @Before
    public void createWriter() {
        writer = new StringWriter();
    }

    @Test
    public void shouldProduceCorrectJson() throws FileNotFoundException {
        SheetInput input = fromYaml(reader);
        SheetOutput output = toJson(writer);

        Pipeline.flow(input).into(output);

        Map<String, Object> data = outputAsMap(writer);
        assertThat(data, hasPath("points.total", is(258.0)));
    }

    private Map<String, Object> outputAsMap(Writer writer) {
        String result = writer.toString();
        Gson gson = new Gson();
        Type stringObjectMap = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(result, stringObjectMap);
    }
}

