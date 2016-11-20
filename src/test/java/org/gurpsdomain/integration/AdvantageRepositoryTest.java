package org.gurpsdomain.integration;

import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.repositories.InMemoryRepository;
import org.gurpsdomain.repositories.xml.AdvantageDescriptions;
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
public class AdvantageRepositoryTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{ "Combat Reflexes"});
        data.add(new Object[]{ "Flexibility"});
        return data;
    }

    private final String advantageName;
    private Repository<AdvantageDescription> repository;

    public AdvantageRepositoryTest(String advantageName) {
        this.advantageName = advantageName;
    }

    @Before
    public void createRepository() {
        repository = InMemoryRepository.loadedWith(AdvantageDescriptions.class, "src/main/resources/data/advantages.basic-set.xml");
    }

    @Test
    public void shouldGetAdvantageByName() {
        AdvantageDescription advantageDescription = repository.getByName(advantageName);

        assertThat(advantageDescription, is(not(nullValue())));
    }
}
