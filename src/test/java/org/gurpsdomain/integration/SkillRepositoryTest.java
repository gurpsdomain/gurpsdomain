package org.gurpsdomain.integration;

import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.description.SkillDescription;
import org.gurpsdomain.domain.repositories.InMemoryRepository;
import org.gurpsdomain.domain.repositories.xml.SkillDescriptions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SkillRepositoryTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{ "Accounting"});
        data.add(new Object[]{ "Zen Archery"});
        return data;
    }

    private final String skillName;
    private Repository<SkillDescription> repository;

    public SkillRepositoryTest(String skillName) {
        this.skillName = skillName;
    }

    @Before
    public void createRepository() {
        repository = InMemoryRepository.loadedWith(SkillDescriptions.class, "src/main/resources/data/skills.basic-set.xml");
    }

    @Test
    public void shouldGetSkillByName() {
        SkillDescription skillDescription = repository.getByName(skillName);

        assertThat(skillDescription, is(not(nullValue())));
    }
}
