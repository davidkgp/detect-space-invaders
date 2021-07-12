package org.example.domain.spaceinvaders.core.port.outgoing;

import org.example.domain.spaceinvaders.core.model.Invader;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;
import org.example.domain.spaceinvaders.infrastructure.InvaderAdaptar;
import org.example.domain.spaceinvaders.infrastructure.repository.exceptions.InvaderPatternNotinDataStoreException;
import org.example.domain.spaceinvaders.infrastructure.repository.interfaces.InvaderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvaderDataStoreTest {

    InvaderRepository repo = mock(InvaderRepository.class);

    @Test
    public void whenInvadersNotFoundProvideEmptyList() {

        InvaderDataStore invaderDataStore = new InvaderAdaptar(repo);
        when(repo.get()).thenReturn(Collections.emptyList());

        assertEquals(invaderDataStore.get().size(), 0);

    }

    @Test
    public void whenInvadersRetrivalThrowsExecptionProvideEmptyList() {

        InvaderDataStore invaderDataStore = new InvaderAdaptar(repo);
        when(repo.get()).thenThrow(new InvaderPatternNotinDataStoreException("No Invader pattern Found"));

        assertEquals(invaderDataStore.get().size(), 0);

    }

    @Test
    public void whenInvadersFoundProvideNonEmptyList() {

        InvaderDataStore invaderDataStore = new InvaderAdaptar(repo);
        when(repo.get()).thenReturn(Arrays.asList(new Invader("1", Matrix.EMPTY_MATRIX),
                new Invader("2", Matrix.EMPTY_MATRIX)));

        assertEquals(invaderDataStore.get().size(), 2);

    }

}