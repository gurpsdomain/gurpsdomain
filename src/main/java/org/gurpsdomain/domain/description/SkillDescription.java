package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Repository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="skill")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillDescription implements Registerable<SkillDescription> {
    private String name;

    @Override
    public void registerIn(Repository<SkillDescription> repository) {
        repository.register(name, this);
    }
}
