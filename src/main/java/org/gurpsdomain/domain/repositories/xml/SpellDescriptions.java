package org.gurpsdomain.domain.repositories.xml;

import org.gurpsdomain.domain.description.SpellDescription;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Iterator;
import java.util.List;

@XmlRootElement(name = "spell_list")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellDescriptions implements Iterable<SpellDescription> {
    @XmlElement(name="spell")
    private List<SpellDescription> spells;

    @Override
    public Iterator<SpellDescription> iterator() {
        return spells.iterator();
    }
}
