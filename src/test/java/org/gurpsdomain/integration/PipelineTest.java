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

import static org.gurpsdomain.adapters.input.YamlSheetInput.fromYaml;
import static org.gurpsdomain.adapters.output.JsonSheetOutput.toJson;
import static org.gurpsdomain.integration.JsonAsserter.in;
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

        in(outputAsMap(writer)).path("points.earned").shouldEqual(250.0);
    }

    private Map<String, Object> outputAsMap(Writer writer) {
        String result = writer.toString();
        Gson gson = new Gson();
        Type stringObjectMap = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(result, stringObjectMap);
    }
}

class JsonAsserter {
    public static JsonAsserter in (Map<String, Object> data) {
        return new JsonAsserter(data);
    }

    private final Map<String, Object> data;

    private JsonAsserter(Map<String, Object> data) {
        this.data = data;
    }

    public Assertion path(String propertyPath) {
        String[] properties = propertyPath.split("\\.");
        Object current = data;
        for (int index = 0; index < properties.length; index++) {
            String property = properties[index];
            current = ((Map<String, Object>) current).get(property);
        }
        return new Assertion(current);
    }
}

class Assertion {
    private Object target;
    public Assertion(Object target) {
        this.target = target;
    }

    public void shouldEqual(double value) {
        assertThat(target, is(value));
    }
}