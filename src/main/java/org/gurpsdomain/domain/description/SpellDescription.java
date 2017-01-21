package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.DifficultyLevel;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.Spell;

import javax.xml.bind.annotation.*;

import static org.gurpsdomain.domain.DifficultyLevel.*;

@XmlRootElement(name = "spell")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellDescription implements Registerable<SpellDescription> {
    @XmlAttribute(name = "very_hard")
    private String veryHardString;
    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "reference")
    private String reference;
    @XmlElement(name = "college")
    private String colleges;
    @XmlElement(name = "power_source")
    private String powerSource;
    @XmlElement(name = "spell_class")
    private String spellClasses;
    @XmlElement(name = "maintenance_cost")
    private String maintenanceCost;
    @XmlElement(name = "casting_time")
    private String castingTime;
    @XmlElement(name = "duration")
    private String duration;


    private SpellDescription() {
        /* needed for JAXB */
    }

    public SpellDescription(String name, String pageReference, String veryHardString, String colleges, String powerSource, String spellClasses, String maintenanceCost, String castingTime, String duration) {
        this.name = name;
        this.reference = pageReference;
        this.veryHardString = veryHardString;
        this.colleges = colleges;
        this.powerSource = powerSource;
        this.spellClasses = spellClasses;
        this.maintenanceCost = maintenanceCost;
        this.castingTime = castingTime;
        this.duration = duration;
    }

    public Spell createSpell(int points) {
        DifficultyLevel difficultyLevel = HARD;
        if ("yes".equals(veryHardString)) {
            difficultyLevel = VERY_HARD;
        }
        return new Spell(name, points, reference, difficultyLevel, colleges, powerSource, spellClasses, maintenanceCost, castingTime, duration);
    }

    @Override
    public void registerIn(Repository<SpellDescription> repository) {
        repository.register(name, this);
    }
}
