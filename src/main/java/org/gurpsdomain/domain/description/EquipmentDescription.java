package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@XmlRootElement(name = "equipment")
@XmlAccessorType(XmlAccessType.FIELD)
public class EquipmentDescription implements Registerable<EquipmentDescription> {
    @XmlElement(name = "description", required = true)
    private String name;
    private String reference;
    @XmlElement(name = "dr_bonus")
    private List<DamageResistanceBonusDescription> damageResistanceBonusDescriptions;

    private EquipmentDescription() {
        /* needed for JAXB */
    }

    public EquipmentDescription(String name, String pageReference, List<DamageResistanceBonusDescription> damageResistanceBonusDescriptions) {
        this.name = name;
        this.reference = pageReference;
        this.damageResistanceBonusDescriptions = damageResistanceBonusDescriptions != null ? damageResistanceBonusDescriptions : Collections.emptyList();

    }

    public Equipment createEquipment() {
        List<DamageResistanceBonus> damageResistanceBonuses = damageResistanceBonusDescriptions()
                .stream()
                .map(DamageResistanceBonusDescription::createDamageResistanceBonus)
                .collect(Collectors.toList());
        return new Equipment(name, reference, damageResistanceBonuses);
    }

    public void registerIn(Repository<EquipmentDescription> repository) {
        repository.register(name, this);
    }

    private List<DamageResistanceBonusDescription> damageResistanceBonusDescriptions() {
        if (damageResistanceBonusDescriptions != null) {
            return damageResistanceBonusDescriptions;
        } else {
            return Collections.emptyList();
        }
    }
}
