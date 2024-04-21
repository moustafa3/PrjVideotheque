package SpringBt.repository;

import SpringBt.model.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ActorRepositoryTest {

    @Mock
    ActorRepository actorRepository;

    Actor actor1, actor2;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        actor1 = new Actor();
        actor1.setId(1L);
        actor1.setName("Actor One");

        actor2 = new Actor();
        actor2.setId(2L);
        actor2.setName("Actor Two");
    }

    @Test
    public void testFindByNameContainingIgnoreCase() {
        when(actorRepository.findByNameContainingIgnoreCase("Actor")).thenReturn(Arrays.asList(actor1, actor2));

        List<Actor> result = actorRepository.findByNameContainingIgnoreCase("Actor");
        assertEquals(2, result.size());
        verify(actorRepository, times(1)).findByNameContainingIgnoreCase("Actor");
    }
    @Test
    public void testSaveActor() {
        when(actorRepository.save(any(Actor.class))).thenAnswer(i -> i.getArguments()[0]);

        Actor result = actorRepository.save(actor1);
        assertEquals(actor1, result);
        verify(actorRepository, times(1)).save(actor1);
    }

    @Test
    public void testFindById() {
        when(actorRepository.findById(1L)).thenReturn(Optional.of(actor1));

        Optional<Actor> result = actorRepository.findById(1L);
        assertEquals(actor1, result.get());
        verify(actorRepository, times(1)).findById(1L);
    }
}