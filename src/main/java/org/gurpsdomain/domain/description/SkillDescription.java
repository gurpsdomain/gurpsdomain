package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.DifficultyLevel;
import org.gurpsdomain.domain.Skill;
import org.gurpsdomain.domain.Repository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="skill")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillDescription implements Registerable<SkillDescription> {
    private String name;
    private  int points;
    private String reference;
    private DifficultyLevel difficultyLevel;

    private SkillDescription() {
        /* needed for JAXB */
    }

    public SkillDescription(String name, int points, DifficultyLevel difficultyLevel,String pageReference) {
        this.name = name;
        this.points = points;
        this.difficultyLevel = difficultyLevel;
        this.reference = pageReference;

}

    public Skill createSkill() {
        Skill skill = new Skill(name, points, difficultyLevel ,reference);
        return skill;
    }


    @Override
    public void registerIn(Repository<SkillDescription> repository) {
        repository.register(name, this);
    }
}
