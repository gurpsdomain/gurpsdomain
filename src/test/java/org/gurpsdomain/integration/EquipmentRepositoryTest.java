package org.gurpsdomain.integration;

import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.description.EquipmentDescription;
import org.gurpsdomain.domain.repositories.InMemoryRepository;
import org.gurpsdomain.domain.repositories.xml.EquipmentDescriptions;
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
public class EquipmentRepositoryTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{ "Studded Leather Skirt"});
        data.add(new Object[]{ "Reinforced Boots"});
        return data;
    }

    private final String equipmentName;
    private Repository<EquipmentDescription> repository;

    public EquipmentRepositoryTest(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    @Before
    public void createRepository() {
        repository = InMemoryRepository.loadedWith(EquipmentDescriptions.class, "src/main/resources/data/equipments.basic-set.xml");
    }

    @Test
    public void shouldGetEquipmentByName() {
        EquipmentDescription equipmentDescription = repository.getByName(equipmentName);

        assertThat(equipmentDescription, is(not(nullValue())));
    }
}
