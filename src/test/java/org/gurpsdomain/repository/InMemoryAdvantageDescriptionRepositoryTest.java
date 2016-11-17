package org.gurpsdomain.repository;

import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.domain.description.CostDescription;
import org.gurpsdomain.domain.description.CostType;
import org.gurpsdomain.repositories.AdvantageNotFoundException;
import org.gurpsdomain.repositories.InMemoryAdvantageDescriptionRepository;
import org.gurpsdomain.domain.description.ModifierDescription;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryAdvantageDescriptionRepositoryTest {
    private static final String NOT_REGISTERED_ADVANTAGE_NAME = "Not Registered Advantage Name";
    private static final String REGISTERED_ADVANTAGE_NAME = "Registered Advantage Name";
    private static final AdvantageDescription ANY_ADVANTAGE = new AdvantageDescription("test", 10, "B128", Arrays.asList(new ModifierDescription("ModifierDescription 1", new CostDescription(10, CostType.points), "B129")));

    @Test
    public void shouldNotKnowAnAdvantageBeforeItIsRegistered() {
        InMemoryAdvantageDescriptionRepository repository = new InMemoryAdvantageDescriptionRepository();

        assertThat(repository.exists(NOT_REGISTERED_ADVANTAGE_NAME), is(false));
    }

    @Test
    public void shouldReturnRegisteredAdvantages() {
        InMemoryAdvantageDescriptionRepository repository = new InMemoryAdvantageDescriptionRepository();
        repository.register(REGISTERED_ADVANTAGE_NAME, ANY_ADVANTAGE);

        AdvantageDescription advantage = repository.getByName(REGISTERED_ADVANTAGE_NAME);

        assertThat(advantage, is(ANY_ADVANTAGE));
    }

    @Test(expected = AdvantageNotFoundException.class)
    public void shouldThrowAnExceptionWhenGettingAnUnregisteredAdvantage() {
        InMemoryAdvantageDescriptionRepository repository = new InMemoryAdvantageDescriptionRepository();

        repository.getByName(NOT_REGISTERED_ADVANTAGE_NAME);
    }
}
