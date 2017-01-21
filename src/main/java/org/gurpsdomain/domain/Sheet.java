package org.gurpsdomain.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sheet {
    private final Points points;
    private final List<Note> notes = new ArrayList<>();
    private final List<Advantage> advantages = new ArrayList<>();
    private final List<Skill> skills = new ArrayList<>();
    private final List<Spell> spells = new ArrayList<>();
    private final Map<String, Object> metaData = new HashMap<>();
    private final Attributes attributes;

    public static Sheet from(Map<String[], String> metaData, List<Integer> awards, List<Advantage> advantages, List<Skill> skills, List<Spell> spells, List<Note> notes) {
        Sheet sheet = new Sheet();
        for (Map.Entry<String[], String> entry : metaData.entrySet()) {
            sheet.setMetaDataProperty(entry.getKey(), entry.getValue());
        }
        for (int award : awards) {
            sheet.award(award);
        }
        for (Advantage advantage : advantages) {
            sheet.addAdvantage(advantage);
        }
        for (Skill skill : skills) {
            sheet.addSkill(skill);
        }
        for (Spell spell : spells) {
            sheet.addSpell(spell);
        }
        for (Note note : notes) {
            sheet.addNote(note);
        }
        return sheet;
    }

    private Sheet() {
        this.points = new Points(0);
        this.attributes = new Attributes();
    }

    public void setSizeModifier(int amount) {
        attributes.addSizeModifierBonus(amount);
    }

    private void award(int amount) {
        points.award(amount);
    }

    private void addNote(Note note) {
        notes.add(note);
    }

    private void addAdvantage(Advantage advantage) {
        advantage.payCost(points);
        advantage.updateAttributes(attributes);
        advantage.updateSkills(skills);
        advantages.add(advantage);
    }

    private void addSkill(Skill skill) {
        skill.payCost(points);
        skill.updateFromAdvantages(advantages);
        skills.add(skill);
    }

    private void addSpell(Spell spell) {
        spell.payCost(points);
        spells.add(spell);
    }

    private void setMetaDataProperty(String[] keys, String value) {
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
