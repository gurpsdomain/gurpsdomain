package org.gurpsdomain.integration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.gurpsdomain.Pipeline;
import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.output.SheetOutput;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

import static org.gurpsdomain.adapters.input.YamlSheetInput.fromYaml;
import static org.gurpsdomain.adapters.output.JsonSheetOutput.toJson;
import static org.gurpsdomain.integration.MapOfMapMatcher.hasPath;
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

        assertThat(outputAsMap(writer), hasPath("points.earned", is(250.0)));
    }

    private Map<String, Object> outputAsMap(Writer writer) {
        String result = writer.toString();
        Gson gson = new Gson();
        Type stringObjectMap = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(result, stringObjectMap);
    }
}

class MapOfMapMatcher<T> extends TypeSafeMatcher<Map<String, Object>> {
    public static <T> MapOfMapMatcher hasPath(String path, Matcher<T> matcher) {
        return new MapOfMapMatcher(path, matcher);
    }

    private final String propertyPath;
    private final Matcher<T> matcher;

    public MapOfMapMatcher(String propertyPath, Matcher<T> matcher) {
        this.propertyPath = propertyPath;
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(Map<String, Object> start) {
        String[] properties = propertyPath.split("\\.");
        Object current = start;
        for (int index = 0; index < properties.length; index++) {
            String property = properties[index];
            if (current instanceof Map) {
                Map<String, Object> aMap = (Map<String, Object>) current;
                if (aMap.containsKey(property)) {
                    current = aMap.get(property);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return matcher.matches(current);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("to have path ");
        description.appendValue(propertyPath);
        description.appendText(" ");
        matcher.describeTo(description);

    }
}