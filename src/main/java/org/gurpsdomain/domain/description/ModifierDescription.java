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

    public boolean matchesName(String wantedName) {
        try {
            return this.name.equals(wantedName);
        }
        catch (NullPointerException e){
            return false;
        }
    }

    public boolean matchesNote(String wantedNote) {
        try {
            return this.note.equals(wantedNote);
        }
        catch (NullPointerException e){
            return false;
        }
    }

    public Modifier createModifier() {
        //TODO here construct a name as concat with a note when required? In that case nullify the note?
        //TODO From this point on it is only for output purposes?
        return new Modifier(name, cost.createCost(), pageReference, note);
    }
}
