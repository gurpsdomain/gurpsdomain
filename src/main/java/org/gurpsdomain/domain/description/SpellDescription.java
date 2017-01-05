package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.Spell;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "spell")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellDescription implements Registerable<SpellDescription> {
    private String name;
    private String reference;

    private SpellDescription() {
        /* needed for JAXB */
    }

    public SpellDescription(String name, String pageReference) {
        this.name = name;
        this.reference = pageReference;
    }

    public Spell createSpell(int points) {
        return new Spell(name, points, reference);
    }

    @Override
    public void registerIn(Repository<SpellDescription> repository) {
        repository.register(name, this);
    }
}
