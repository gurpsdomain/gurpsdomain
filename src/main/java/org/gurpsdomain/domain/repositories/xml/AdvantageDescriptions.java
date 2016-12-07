package org.gurpsdomain.domain.repositories.xml;


import org.gurpsdomain.domain.description.AdvantageDescription;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@XmlRootElement(name = "advantage_list")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdvantageDescriptions implements Iterable<AdvantageDescription> {
    @XmlElement(name="advantage")
    private List<AdvantageDescription> advantages = new ArrayList<AdvantageDescription>();

    @Override
    public Iterator<AdvantageDescription> iterator() {
        return advantages.iterator();
    }
}
