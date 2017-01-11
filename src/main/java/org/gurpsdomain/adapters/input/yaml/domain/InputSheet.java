package org.gurpsdomain.adapters.input.yaml.domain;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import java.beans.IntrospectionException;
import java.util.Collections;
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
    public Integer age;
    public String birthday;
    public String height;
    public String weight;
    public String size;
    public Integer TL;
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

    public String birthday() {
        if (birthday != null) {
            return birthday;
        } else {
            return "";
        }
    }

    public String campaign() {
        if (campaign != null) {
            return campaign;
        } else {
            return "";
        }
    }

    public String createdOn() {
        if (createdOn != null) {
            return createdOn;
        } else {
            return "";
        }

    }

    public String eyes() {
        if (eyes != null) {
            return eyes;
        } else {
            return "";
        }
    }

    public String gender() {
        if (gender != null) {
            return gender;
        } else {
            return "";
        }
    }

    public String hair() {
        if (hair != null) {
            return hair;
        } else {
            return "";
        }
    }

    public String hand() {
        if (hand != null) {
            return hand;
        } else {
            return "";
        }
    }

    public String height() {
        if (height != null) {
            return height;
        } else {
            return "";
        }
    }

    public String player() {
        if (player != null) {
            return player;
        } else {
            return "";
        }
    }

    public String race() {
        if (race != null) {
            return race;
        } else {
            return "";
        }
    }

    public String religion() {
        if (religion != null) {
            return religion;
        } else {
            return "";
        }
    }

    public String size() {
        if (size != null) {
            return size;
        } else {
            return "";
        }
    }

    public String skin() {
        if (skin != null) {
            return skin;
        } else {
            return "";
        }
    }

    public String title() {
        if (title != null) {
            return title;
        } else {
            return "";
        }
    }

    public String weight() {
        if (weight != null) {
            return weight;
        } else {
            return "";
        }
    }

    public List<InputAdvantage> advantages() {
        if (advantages != null) {
            return advantages;
        } else {
            return Collections.emptyList();
        }
    }

    public List<InputAdvantage> disadvantages() {
        if (disadvantages != null) {
            return disadvantages;
        } else {
            return Collections.emptyList();
        }
    }
}
