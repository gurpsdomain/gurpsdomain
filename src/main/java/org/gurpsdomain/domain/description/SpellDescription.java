package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.DifficultyLevel;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.Spell;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "spell")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellDescription implements Registerable<SpellDescription> {
    @XmlAttribute(name = "very_hard", required = false) //FIXME this doesn't seem to do what I hoped
    private String veryHardString;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "reference")
    private String reference;

    private SpellDescription() {
        /* needed for JAXB */
    }

    public SpellDescription(String name, String pageReference, String veryHardString) {
        this.name = name;
        this.reference = pageReference;
        this.veryHardString = veryHardString;
    }

    public Spell createSpell(int points) {
        DifficultyLevel difficultyLevel = DifficultyLevel.HARD;
        if ("yes".equals(veryHardString)) {
            difficultyLevel = DifficultyLevel.VERY_HARD;
        }
        return new Spell(name, points, reference, difficultyLevel);
    }

    @Override
    public void registerIn(Repository<SpellDescription> repository) {
        repository.register(name, this);
    }
}
