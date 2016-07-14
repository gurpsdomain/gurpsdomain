package org.gurpsdomain.adapters.input;

import org.gurpsdomain.domain.Sheet;
import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.util.Map;

public class YamlSheetInput implements SheetInput {
    public static YamlSheetInput fromYaml(Reader reader) {
        return new YamlSheetInput(reader);
    }

    private Reader reader;
    private Sheet sheet;

    private YamlSheetInput(Reader reader) {
        this.reader = reader;
    }

    @Override
    public Sheet produce() {
        if (sheet == null) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = (Map<String, Object>) yaml.load(reader);
            this.sheet = new Sheet((Integer) data.get("basepoints"));
        }
        return sheet;
    }
}
