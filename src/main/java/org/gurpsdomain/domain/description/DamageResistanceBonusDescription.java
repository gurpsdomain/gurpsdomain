package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.DamageResistance;
import org.gurpsdomain.domain.DamageResistanceBonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static org.gurpsdomain.domain.DamageResistanceBonus.damageResistanceBonus;
import static org.gurpsdomain.domain.DamageResistanceBonus.leveledDamageResistanceBonus;

@XmlRootElement(name = "dr_bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class DamageResistanceBonusDescription {
    @XmlElement(name = "location")
    private String location;
    private AmountDescription amount;

    public DamageResistanceBonusDescription() {
        /* needed by JAXB */
    }

    public DamageResistanceBonusDescription(String location, String bonus) {
        this.location = location;
        this.amount = new AmountDescription(bonus);
    }

    public DamageResistanceBonus createDamageResistanceBonus() {
        if (amount.isLeveled()) {
            return leveledDamageResistanceBonus(DamageResistance.fromDescription(location), amount.bonus);
        } else {
            return damageResistanceBonus(DamageResistance.fromDescription(location), amount.bonus);
        }
    }
}
