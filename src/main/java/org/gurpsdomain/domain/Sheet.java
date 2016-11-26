package org.gurpsdomain.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sheet {
    private final Points points;
    private List<Note> notes = new ArrayList<Note>();
    private final List<Advantage> advantages = new ArrayList<Advantage>();
    private final List<Skill> skills = new ArrayList<Skill>();
    private final Map<String, Object> metaData = new HashMap<String, Object>();
    private final Attributes attributes;

    public Sheet() {
        this.points = new Points(0);
        this.attributes = new Attributes(10);
    }

    public void award(int amount) {
        points.award(amount);
    }

    public void addNote(Note note) {
        notes.add(note);
    }


    public void addAdvantage(Advantage advantage) {
        advantage.payCost(points);
        advantage.updateAttributes(attributes);
        advantages.add(advantage);
    }

    public void addSkill(Skill skill) {
        skill.payCost(points);
        skills.add(skill);
    }

    public void setMetaDataProperty(String[] keys, String value) {
        Map<String, Object> current = metaData;
        for (int index = 0; index < keys.length - 1; index++) {
            if (!current.containsKey(keys[index])) {
                current.put(keys[index], new HashMap<String, Object>());
            }
            current = (Map<String, Object>) current.get(keys[index]);
        }
        current.put(keys[keys.length - 1], value);
    }
}
