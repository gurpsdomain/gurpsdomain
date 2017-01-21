package org.gurpsdomain.domain.repository;

import org.gurpsdomain.domain.description.*;
import org.gurpsdomain.domain.repositories.InMemoryRepository;
import org.gurpsdomain.domain.repositories.ItemNotFoundException;
import org.junit.Test;

import static org.mockito.Mockito.mock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryRepositoryTest {
    private static final String NOT_REGISTERED_ADVANTAGE_NAME = "Not Registered Advantage Name";
    private static final String REGISTERED_ADVANTAGE_NAME = "Registered Advantage Name";
    private static final AdvantageDescription ANY_ADVANTAGE = mock(AdvantageDescription.class);


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
