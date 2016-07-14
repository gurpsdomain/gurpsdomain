package org.gurpsdomain.integration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

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

class Pipeline {

    public static Pipeline flow(Reader reader) {
        return new Pipeline(reader);
    }

    private final Reader reader;
    private final Map<String, Object> data;

    private Pipeline(Reader reader) {
        this.reader = reader;
        Yaml yaml = new Yaml();
        Map<String, Object> data = (Map<String, Object>)yaml.load(reader);
        this.data = data;

    }

    public void into(Writer writer) {
        Gson gson = new Gson();
        Sheet sheet = new Sheet((Integer)data.get("basepoints"));
        gson.toJson(sheet, writer);

    }
}

class Sheet {
    private final Points points;

    public Sheet(int earned) {
        this.points = new Points(earned);
    }
}

class Points {
    private int earned = 250;

    public Points(int earned) {
        this.earned = earned;
    }
}