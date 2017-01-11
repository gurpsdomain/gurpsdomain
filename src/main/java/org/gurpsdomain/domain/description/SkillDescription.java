package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Attribute;
import org.gurpsdomain.domain.DifficultyLevel;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.Skill;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "skill")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillDescription implements Registerable<SkillDescription> {
    @XmlElement(name = "name", required = true)
    private String name;
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
        return new Skill(name, points, controllingAttribute(), difficultyLevel(), reference);
    }

    private Attribute controllingAttribute() {
        return Attribute.fromDescription(difficulty.split("/")[0]);
    }

    private DifficultyLevel difficultyLevel() {
        return DifficultyLevel.fromDescription(difficulty.split("/")[1]);
    }

    @Override
    public void registerIn(Repository<SkillDescription> repository) {
        repository.register(name, this);
    }
}
