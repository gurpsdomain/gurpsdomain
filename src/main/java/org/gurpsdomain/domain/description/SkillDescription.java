package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Attribute;
import org.gurpsdomain.domain.DifficultyLevel;
import org.gurpsdomain.domain.Skill;
import org.gurpsdomain.domain.Repository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement(name = "skill")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillDescription implements Registerable<SkillDescription> {

    static final private Map<String, DifficultyLevel> difficultyLevelMap = new HashMap<>();

    static {
        difficultyLevelMap.put("E", DifficultyLevel.EASY);
        difficultyLevelMap.put("A", DifficultyLevel.AVERAGE);
        difficultyLevelMap.put("H", DifficultyLevel.HARD);
        difficultyLevelMap.put("VH", DifficultyLevel.VERY_HARD);
    }

    static final private Map<String, Attribute> controllingAttributeMap = new HashMap<>();

    static {
        for (Attribute attribute : Attribute.values()) {
            if (attribute.isControlling()) {
                controllingAttributeMap.put(attribute.getShorthand(), attribute);
            }
        }
    }

    private String name;
    private int points;
    private String reference;
    private String difficulty;

    private SkillDescription() {
        /* needed for JAXB */
    }

    public SkillDescription(String name, String difficulty, String pageReference) {
        this.name = name;
        this.difficulty = difficulty;
        this.reference = pageReference;

    }

    public Skill createSkill(int points) {
        Attribute controllingAttribute = controllingAttributeMap.get(difficulty.split("/")[0]);
        DifficultyLevel difficultyLevel = difficultyLevelMap.get(difficulty.split("/")[1]);

        Skill skill = new Skill(name, points, controllingAttribute, difficultyLevel, reference);
        return skill;
    }


    @Override
    public void registerIn(Repository<SkillDescription> repository) {
        repository.register(name, this);
    }
}
