package org.gurpsdomain.integration;

import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.description.SpellDescription;
import org.gurpsdomain.domain.repositories.InMemoryRepository;
import org.gurpsdomain.domain.repositories.xml.SpellDescriptions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SpellRepositoryTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{ "Enlarge"});
        data.add(new Object[]{ "Sense Emotion"});
        return data;
    }

    private final String spellName;
    private Repository<SpellDescription> repository;

    public SpellRepositoryTest(String spellName) {
        this.spellName = spellName;
    }

    @Before
    public void createRepository() {
        repository = InMemoryRepository.loadedWith(SpellDescriptions.class, Arrays.asList("src/main/resources/data/spells.magic-set.xml"));
    }

    @Test
    public void shouldGetSpellByName() {
        SpellDescription spellDescription = repository.getByName(spellName);

        assertThat(spellDescription, is(not(nullValue())));
    }
}
