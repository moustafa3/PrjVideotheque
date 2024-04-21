package SpringBt.service;

import SpringBt.model.Actor;
import SpringBt.repository.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ActorServiceImplTest {

    @InjectMocks
    ActorServiceImpl actorService;

    @Mock
    ActorRepository actorRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Actor actor = new Actor();
        actor.setId(1L);
        when(actorRepository.findById(1L)).thenReturn(Optional.of(actor));

        Optional<Actor> result = actorService.findById(1L);

        assertEquals(actor, result.orElse(null));
        verify(actorRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        when(actorRepository.findAll()).thenReturn(Arrays.asList(actor1, actor2));

        List<Actor> result = actorService.findAll();

        assertEquals(2, result.size());
        verify(actorRepository, times(1)).findAll();
    }

    @Test
    public void testInsert() {
        Actor actor = new Actor();
        when(actorRepository.save(actor)).thenReturn(actor);

        Actor result = actorService.insert(actor);

        assertEquals(actor, result);
        verify(actorRepository, times(1)).save(actor);
    }

   @Test
    public void testUpdate() {
    Actor actor = new Actor();
    actor.setId(1L);
    actor.setName("Test");
    when(actorRepository.findById(1L)).thenReturn(Optional.of(actor));

    Actor newActor = new Actor();
    newActor.setName("Updated");

    Actor updatedActor = new Actor();
    updatedActor.setId(1L);
    updatedActor.setName("Updated");
    when(actorRepository.save(any(Actor.class))).thenReturn(updatedActor);

    Actor result = actorService.update(1L, newActor);

    assertEquals("Updated", result.getName());
    verify(actorRepository, times(1)).findById(1L);
    verify(actorRepository, times(1)).save(any(Actor.class));
}

    @Test
    public void testDelete() {
        Actor actor = new Actor();
        actor.setId(1L);
        when(actorRepository.findById(1L)).thenReturn(Optional.of(actor));

        actorService.delete(1L);

        verify(actorRepository, times(1)).findById(1L);
        verify(actorRepository, times(1)).delete(actor);
    }

    @Test
    public void testUpdateActorNotFound() {
        Actor newActor = new Actor();
        newActor.setName("Updated");
        when(actorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> actorService.update(1L, newActor));
        verify(actorRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteActorNotFound() {
        when(actorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> actorService.delete(1L));
        verify(actorRepository, times(1)).findById(1L);
    }
}