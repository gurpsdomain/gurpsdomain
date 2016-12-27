package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Modifier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "modifier")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModifierDescription {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "cost", required = false)
    private CostDescription cost;
    @XmlElement(name = "notes", required = false)
    private String note;
    @XmlElement(name = "reference")
    private String pageReference;

    public ModifierDescription() {
        /* needed by JAXB */
    }

    public ModifierDescription(String name, CostDescription cost, String pageReference, String note) {
        this.name = name;
        this.cost = cost;
        this.pageReference = pageReference;
        this.note = note;
    }

    public ModifierDescription(String name, CostDescription cost, String pageReference) {
        this(name, cost, pageReference, "");
    }

    public boolean matchesName(String wantedName) {
        return name != null && name.equals(wantedName);
    }

    public boolean matchesNote(String wantedNote) {
        return note != null && note.equals(wantedNote);
    }

    public Modifier createModifier() {
        if (note == null) {
            return new Modifier(name, cost.createCost(), pageReference);
        } else {
            return new Modifier(name, cost.createCost(), pageReference, note);
        }

    }
}
