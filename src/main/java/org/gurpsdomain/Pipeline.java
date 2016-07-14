package org.gurpsdomain;

import com.google.gson.Gson;
import org.gurpsdomain.domain.Sheet;
import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.io.Writer;
import java.util.Map;

public class Pipeline {

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
