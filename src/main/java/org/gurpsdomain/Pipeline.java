package org.gurpsdomain;

import com.google.gson.Gson;
import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.output.SheetOutput;
import org.gurpsdomain.domain.Sheet;
import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.io.Writer;
import java.util.Map;

public class Pipeline {
    public static Pipeline flow(SheetInput input) {
        return new Pipeline(input);
    }

    private final Sheet sheet;

    private Pipeline(SheetInput input) {
        this.sheet = input.produce();
    }

    public void into(SheetOutput writer) {
        writer.export(sheet);
    }
}
