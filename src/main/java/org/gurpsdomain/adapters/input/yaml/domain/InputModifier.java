package org.gurpsdomain.adapters.input.yaml.domain;

import org.gurpsdomain.domain.description.predicate.ModifierDescriptionPredicate;
import org.gurpsdomain.domain.description.predicate.Name;

import static org.gurpsdomain.domain.description.predicate.And.and;
import static org.gurpsdomain.domain.description.predicate.Note.note;

public class InputModifier {
    public String name;
    public String variation;

    public boolean isAVariation() {
        return variation != null && !variation.isEmpty();
    }

    public ModifierDescriptionPredicate descriptionPredicate() {
        ModifierDescriptionPredicate predicate;
        if (this.isAVariation()) {
            predicate = and(Name.name(name), note(variation));
        } else {
            predicate = Name.name(name);
        }
        return predicate;
    }
}
