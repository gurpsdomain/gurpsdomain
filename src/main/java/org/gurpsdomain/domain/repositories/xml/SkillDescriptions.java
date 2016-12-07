package org.gurpsdomain.domain.repositories.xml;

import org.gurpsdomain.domain.description.SkillDescription;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Iterator;
import java.util.List;

@XmlRootElement(name = "skill_list")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillDescriptions implements Iterable<SkillDescription> {
    @XmlElement(name="skill")
    private List<SkillDescription> skills;

    @Override
    public Iterator<SkillDescription> iterator() {
        return skills.iterator();
    }
}
