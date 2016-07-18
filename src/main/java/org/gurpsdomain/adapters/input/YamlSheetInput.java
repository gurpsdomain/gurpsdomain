package org.gurpsdomain.adapters.input;

import org.gurpsdomain.domain.Sheet;
import org.gurpsdomain.domain.SheetBuilder;
import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import static org.gurpsdomain.domain.SheetBuilder.builder;

public class YamlSheetInput implements SheetInput {
    public static YamlSheetInput fromYaml(Reader reader) {
        return new YamlSheetInput(reader);
    }

    private Reader reader;
    private SheetBuilder sheetBuilder;

    private YamlSheetInput(Reader reader) {
        this.reader = reader;
    }

    @Override
    public Sheet produce() {
        if (sheetBuilder == null) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = (Map<String, Object>) yaml.load(reader);
            this.sheetBuilder = builder((Integer) data.get("basepoints"));
            List<Integer> rewards = (List<Integer>) data.get("rewards");
            for (Integer reward: rewards) {
                sheetBuilder.award(reward);
            }
        }
        return sheetBuilder.build();
    }
}
