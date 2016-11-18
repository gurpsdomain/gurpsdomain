package org.gurpsdomain.repository;

import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.domain.description.CostDescription;
import org.gurpsdomain.domain.description.CostType;
import org.gurpsdomain.domain.description.ModifierDescription;
import org.gurpsdomain.repositories.InMemoryRepository;
import org.gurpsdomain.repositories.ItemNotFoundException;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryRepositoryTest {
    private static final String NOT_REGISTERED_ADVANTAGE_NAME = "Not Registered Advantage Name";
    private static final String REGISTERED_ADVANTAGE_NAME = "Registered Advantage Name";
    private static final AdvantageDescription ANY_ADVANTAGE = new AdvantageDescription("test", 10, "B128", Arrays.asList(new ModifierDescription("ModifierDescription 1", new CostDescription(10, CostType.points), "B129")));

    @Test
    public void shouldNotKnowAnItemBeforeItIsRegistered() {
        InMemoryRepository<AdvantageDescription> repository = new InMemoryRepository<AdvantageDescription>();

        assertThat(repository.exists(NOT_REGISTERED_ADVANTAGE_NAME), is(false));
    }

    @Test
    public void shouldReturnRegisteredItem() {
        InMemoryRepository<AdvantageDescription> repository = new InMemoryRepository<AdvantageDescription>();
        repository.register(REGISTERED_ADVANTAGE_NAME, ANY_ADVANTAGE);

        AdvantageDescription advantage = repository.getByName(REGISTERED_ADVANTAGE_NAME);

        assertThat(advantage, is(ANY_ADVANTAGE));
    }

    @Test(expected = ItemNotFoundException.class)
    public void shouldThrowAnExceptionWhenGettingAnUnregisteredItem() {
        InMemoryRepository<AdvantageDescription> repository = new InMemoryRepository<AdvantageDescription>();

        repository.getByName(NOT_REGISTERED_ADVANTAGE_NAME);
    }
}
