package org.gurpsdomain.adapters.input.yaml.domain;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import java.beans.IntrospectionException;
import java.util.List;

public class InputSheet {
    public static Constructor constructor() {
        Constructor constructor = new Constructor(InputSheet.class);
        TypeDescription description = new TypeDescription(InputSheet.class);
        description.putListPropertyType("rewards", Integer.class);
        description.putListPropertyType("advantages", InputAdvantage.class);
        description.putListPropertyType("disadvantages", InputAdvantage.class);
        description.putListPropertyType("skills", InputSkill.class);
        description.putListPropertyType("spells", InputSkill.class);
        description.putListPropertyType("notes", InputNote.class);
        constructor.setPropertyUtils(new PropertyUtils(){
            @Override
            public Property getProperty(Class<?> type, String name) throws IntrospectionException {
                if (name.equals("created on")) {
                    return super.getProperty(type, "createdOn");
                }
                return super.getProperty(type, name);
            }
        });
        return constructor;
    }

    public String player;
    public String campaign;
    public String createdOn;
    public String name;
    public String title;
    public String religion;
    public String race;
    public String gender;
    public int age;
    public String birthday;
    public String height;
    public String weight;
    public String size;
    public int TL;
    public String hair;
    public String eyes;
    public String skin;
    public String hand;
    public int basepoints;
    public List<Integer> rewards;
    public List<InputAdvantage> advantages;
    public List<InputAdvantage> disadvantages;
    public List<InputSkill> skills;
    public List<InputSkill> spells;
    public List<InputNote> notes;
}
