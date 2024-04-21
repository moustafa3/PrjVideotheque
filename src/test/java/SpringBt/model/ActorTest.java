package SpringBt.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActorTest {

    private Actor actor;

    @BeforeEach
    public void setUp() {
        actor = new Actor();
    }

    @Test
    public void testSetName() {
        String name = "Test Name";
        actor.setName(name);
        assertEquals(name, actor.getName());
    }

    @Test
    public void testSetBio() {
        String bio = "Test Bio";
        actor.setBio(bio);
        assertEquals(bio, actor.getBio());
    }

    @Test
    public void testAddFilm() {
        Film film = Mockito.mock(Film.class);
        actor.addFilm(film);
        assertEquals(1, actor.getFilms().size());
    }

    @Test
    public void testRemoveFilm() {
        Film film = Mockito.mock(Film.class);
        actor.addFilm(film);
        actor.removeFilm(film);
        assertEquals(0, actor.getFilms().size());
    }
}