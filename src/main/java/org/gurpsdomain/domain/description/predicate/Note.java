package org.gurpsdomain.domain.description.predicate;

import org.gurpsdomain.domain.description.ModifierDescription;

public class Note implements ModifierDescriptionPredicate {
    public static Note note(String wantedNote) {
        return new Note(wantedNote);
    }
    private final String wantedNote;

    private Note(String wantedNote) {
        this.wantedNote = wantedNote;
    }

    @Override
    public boolean isFulfilledBy(ModifierDescription modifier) {
        return modifier.matchesNote(wantedNote);
    }
}
