package org.gurpsdomain.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.gurpsdomain.domain.Util.slice;

public class SheetBuilder {
    public static SheetBuilder builder() {
        return new SheetBuilder();
    }

    private List<Integer> awards = new ArrayList<Integer>();
    private List<Note> notes = new ArrayList<Note>();
    private List<Skill> skills = new ArrayList<Skill>();
    private List<Advantage> advantages = new ArrayList<Advantage>();
    private Map<String[], String> metaData = new HashMap<String[], String>();

    public void award(int amount) {
        awards.add(amount);
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public  void addSkill(Skill skill) {
       skills.add(skill);
    }

    public  void addAdvantage(Advantage advantage) {
        advantages.add(advantage);
    }


    public  void addMetaData(String... keys) {
        String value = keys[keys.length - 1];
        keys = slice(keys, 0, keys.length - 1);
        metaData.put(keys, value);
    }

    public Sheet build() {
        return Sheet.from(metaData, awards, advantages, skills, notes);
    }
}

class Util {
    public static String[] slice(String[] original, int startInclusive, int finishExclusive) {
        String[] slice = new String[finishExclusive - startInclusive];
        for (int index = startInclusive; index < finishExclusive; index++) {
            slice[index - startInclusive] = original[index];
        }
        return slice;
    }
}
