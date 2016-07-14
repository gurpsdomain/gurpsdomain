package org.gurpsdomain.integration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.gurpsdomain.Pipeline;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PipelineTest {
    @Test
    public void shouldProduceCorrectJson() throws FileNotFoundException {
        Reader reader = new FileReader(new File("src/test/resources/sheets/dai-blackthorn.yml"));
        Writer writer = new StringWriter();

        Pipeline.flow(reader).into(writer);

        String output = writer.toString();
        Gson gson = new Gson();
        Type stringObjectMap = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> data = gson.fromJson(output, stringObjectMap);

        assertThat(((Map<String, Object>)data.get("points")).get("earned"), is(250.0));
    }
}

