package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputNote;
import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.domain.Note;
import org.gurpsdomain.domain.SheetBuilder;

import java.util.List;

public class AddNotesStep implements YamlBuildStep {

    @Override
    public void build(InputSheet data, SheetBuilder sheetBuilder) {
        List<InputNote> inputNotes = data.notes;
        if (!(inputNotes == null)) {
            for (InputNote inputNote : inputNotes) {
                Note note = new Note(inputNote.name, inputNote.note);
                sheetBuilder.addNote(note);
            }
        }
    }
}
