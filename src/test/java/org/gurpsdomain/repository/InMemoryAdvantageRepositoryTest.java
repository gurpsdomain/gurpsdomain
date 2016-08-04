package org.gurpsdomain.repository;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.repositories.AdvantageNotFoundException;
import org.gurpsdomain.repositories.InMemoryAdvantageRepository;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryAdvantageRepositoryTest {
    private static final String NOT_REGISTERED_ADVANTAGE_NAME = "Not Registered Advantage Name";
    private static final String REGISTERED_ADVANTAGE_NAME = "Registered Advantage Name";
    private static final Advantage ANY_ADVANTAGE = new Advantage("test", 10, "B128");

    @Test
    public void shouldNotKnowAnAdvantageBeforeItIsRegistered() {
        InMemoryAdvantageRepository repository = new InMemoryAdvantageRepository();

        assertThat(repository.exists(NOT_REGISTERED_ADVANTAGE_NAME), is(false));
    }

    @Test
    public void shouldReturnRegisteredAdvantages() {
        InMemoryAdvantageRepository repository = new InMemoryAdvantageRepository();
        repository.register(REGISTERED_ADVANTAGE_NAME, ANY_ADVANTAGE);

        Advantage advantage = repository.getByName(REGISTERED_ADVANTAGE_NAME);

        assertThat(advantage, is(ANY_ADVANTAGE));
    }

    @Test(expected = AdvantageNotFoundException.class)
    public void shouldThrowAnExceptionWhenGettingAnUnregisteredAdvantage() {
        InMemoryAdvantageRepository repository = new InMemoryAdvantageRepository();

        repository.getByName(NOT_REGISTERED_ADVANTAGE_NAME);
    }
}
