package org.gurpsdomain.repositories.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@XmlRootElement(name = "advantage_list")
@XmlAccessorType(XmlAccessType.FIELD)
public class Advantages implements Iterable<Advantage> {
    @XmlElement(name="advantage")
    private List<Advantage> advantages = new ArrayList<Advantage>();

    public Advantages() {

    }

    @Override
    public Iterator<Advantage> iterator() {
        return advantages.iterator();
    }
}
