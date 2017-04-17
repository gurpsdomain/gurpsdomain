package org.gurpsdomain.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sheet {
    private final Points points;
    private final List<Note> notes = new ArrayList<>();
    private final List<Message> messages = new ArrayList<>();
    private final List<Advantage> advantages = new ArrayList<>();
    private final List<Skill> skills = new ArrayList<>();
    private final List<Spell> spells = new ArrayList<>();
    private final List<Equipment> equipments = new ArrayList<>();
    private final Map<String, Object> metaData = new HashMap<>();
    private final Attributes attributes;
    private final DamageResistances damageResistances;

    public static Sheet from(Map<String[], String> metaData, List<Integer> awards, List<Advantage> advantages, List<Skill> skills, List<Spell> spells, List<Equipment> equipments, List<Note> notes, List<Message> messages) {
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
        for (Equipment equipment : equipments) {
            sheet.addEquipment(equipment);
        }
        for (Note note : notes) {
            sheet.addNote(note);
        }
        for (Message message : messages) {
            sheet.addMessage(message);
        }
        return sheet;
    }

    private Sheet() {
        this.points = new Points(0);
        this.attributes = new Attributes();
        this.damageResistances = new DamageResistances();
    }

    public void setSizeModifier(int amount) {
        attributes.addSizeModifierBonus(amount);
    }

    private void award(int amount) {
        points.award(amount);
    }

    private void addNote(Note note) {notes.add(note);}

    private void addMessage(Message message){messages.add(message);}

    private void addAdvantage(Advantage advantage) {
        advantage.payCost(points);
        advantage.updateAttributes(attributes);
        advantage.updateSkills(skills);
        advantage.updateDamageResistances(damageResistances);
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

    private void addEquipment(Equipment equipment) {
        equipment.updateDamageResistances(damageResistances);
        equipments.add(equipment);
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
