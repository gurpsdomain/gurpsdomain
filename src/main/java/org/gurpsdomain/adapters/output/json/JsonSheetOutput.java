package org.gurpsdomain.adapters.output.json;

import com.google.gson.Gson;
import org.gurpsdomain.adapters.output.SheetOutput;
import org.gurpsdomain.adapters.output.domain.SheetSheet;

import java.io.Writer;

public class JsonSheetOutput implements SheetOutput {
    public static JsonSheetOutput toJson(Writer writer) {
        return new JsonSheetOutput(writer);
    }

    private Writer writer;
    private JsonSheetOutput(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void export(SheetSheet sheet) {
        Gson gson = new Gson();
        gson.toJson(sheet, writer);

    }
}
