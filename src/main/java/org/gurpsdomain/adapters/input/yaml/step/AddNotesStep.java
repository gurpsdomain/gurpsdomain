package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.Note;
import org.gurpsdomain.domain.SheetBuilder;

import java.util.List;
import java.util.Map;

public class AddNotesStep implements YamlBuildStep {

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        List<Map<String, Object>> inputNotes = (List<Map<String, Object>>) data.get("notes");
        if (!(inputNotes == null)) {
            for (Map<String, Object> inputNote : inputNotes) {
                Note note = new Note((String) inputNote.get("name"),(String) inputNote.get("note"));
                    sheetBuilder.addNote(note);
                }
            }
        }
    }
