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
    @XmlElement(name = "cost", required=false)
    private CostDescription cost;
    @XmlElement(name = "notes", required=false)
    private String note;
    @XmlElement(name = "reference")
    private String pageReference;

    public ModifierDescription(){
        /* needed by JAXB */
    }

    public ModifierDescription(String name, CostDescription cost, String pageReference, String note) {
        this.name = name;
        this.cost = cost;
        this.pageReference = pageReference;
        this.note = note;
    }

    public boolean matchesName(String wantedName) {
        return this.name.equals(wantedName);
    }

    public boolean matchesNote(String wantedNote) {
        return this.note.equals(wantedNote);
    }

    public Modifier createModifier() {
        return new Modifier(name, cost.createCost(), pageReference);
    }
}
