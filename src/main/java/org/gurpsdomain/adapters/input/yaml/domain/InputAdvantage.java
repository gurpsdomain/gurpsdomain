package org.gurpsdomain.adapters.input.yaml.domain;

import java.util.Collections;
import java.util.List;

public class InputAdvantage {
    public String name;
    public List<InputModifier> modifiers;
    public Integer levels;

    public Integer levels() {
        if (levels != null) {
            return levels;
        } else {
            return 0;
        }
    }

    public List<InputModifier> modifiers() {
        if (modifiers != null) {
            return modifiers;
        } else {
            return Collections.emptyList();
        }
    }
}
