package org.gurpsdomain.domain.repositories.xml;


import org.gurpsdomain.domain.description.EquipmentDescription;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@XmlRootElement(name = "equipment_list")
@XmlAccessorType(XmlAccessType.FIELD)
public class EquipmentDescriptions implements Iterable<EquipmentDescription> {
    @XmlElement(name = "equipment")
    private List<EquipmentDescription> equipments = new ArrayList<>();

    @Override
    public Iterator<EquipmentDescription> iterator() {
        return equipments.iterator();
    }
}
