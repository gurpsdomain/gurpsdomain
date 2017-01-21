package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Skill;
import org.gurpsdomain.domain.SkillBonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static org.gurpsdomain.domain.SkillBonus.skillBonus;

@XmlRootElement(name = "skill_bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillBonusDescription {
    @XmlElement(name = "name")
    private String name;
    private AmountDescription amount;

    public SkillBonusDescription() {
        /* needed by JAXB */
    }

    //TODO take comparing and specialization into account
    public SkillBonusDescription(String name, String bonus) {
        this.name = name;
        this.amount = new AmountDescription(bonus);
    }

    public SkillBonus createSkillBonus() {
            return skillBonus(name, amount.bonus);
    }

}
