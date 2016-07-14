package org.gurpsdomain.integration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.gurpsdomain.Pipeline;
import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.output.SheetOutput;
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
    @Test
    public void shouldProduceCorrectJson() throws FileNotFoundException {
        Reader reader = new FileReader(new File("src/test/resources/sheets/dai-blackthorn.yml"));
        SheetInput input = fromYaml(reader);
        Writer writer = new StringWriter();
        SheetOutput output = toJson(writer);

        Pipeline.flow(input).into(output);

        String result = writer.toString();
        Gson gson = new Gson();
        Type stringObjectMap = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> data = gson.fromJson(result, stringObjectMap);

        in(data).path("points.earned").shouldEqual(250.0);
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