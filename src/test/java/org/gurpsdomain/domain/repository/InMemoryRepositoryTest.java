package org.gurpsdomain.domain.repository;

import org.gurpsdomain.domain.description.*;
import org.gurpsdomain.domain.repositories.InMemoryRepository;
import org.gurpsdomain.domain.repositories.ItemNotFoundException;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryRepositoryTest {
    private static final String NOT_REGISTERED_ADVANTAGE_NAME = "Not Registered Advantage Name";
    private static final String REGISTERED_ADVANTAGE_NAME = "Registered Advantage Name";
    private static final AdvantageDescription ANY_ADVANTAGE = new AdvantageDescription("testName", 10, 1, 5, "B128", Arrays.asList(new ModifierDescription("ModifierDescription 1", new CostDescription(10, "points"), "B129")),Arrays.asList(new AttributeBonusDescription("HT", "1")));

    @Test
    public void shouldNotKnowAnItemBeforeItIsRegistered() {
        InMemoryRepository<AdvantageDescription> repository = new InMemoryRepository<>();

        assertThat(repository.exists(NOT_REGISTERED_ADVANTAGE_NAME), is(false));
    }

    @Test
    public void shouldReturnRegisteredItem() {
        InMemoryRepository<AdvantageDescription> repository = new InMemoryRepository<>();
        repository.register(REGISTERED_ADVANTAGE_NAME, ANY_ADVANTAGE);

        AdvantageDescription advantage = repository.getByName(REGISTERED_ADVANTAGE_NAME);

        assertThat(advantage, is(ANY_ADVANTAGE));
    }

    @Test(expected = ItemNotFoundException.class)
    public void shouldThrowAnExceptionWhenGettingAnUnregisteredItem() {
        InMemoryRepository<AdvantageDescription> repository = new InMemoryRepository<>();

        repository.getByName(NOT_REGISTERED_ADVANTAGE_NAME);
    }
}
