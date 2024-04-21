package SpringBt.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmTest {

    private Film film;

    @BeforeEach
    public void setUp() {
        film = new Film();
    }

    @Test
    public void testSetTitle() {
        String title = "Test Title";
        film.setTitle(title);
        assertEquals(title, film.getTitle());
    }

    @Test
    public void testSetYear() {
        Integer year = 2022;
        film.setYear(year);
        assertEquals(year, film.getYear());
    }

    @Test
    public void testSetDescription() {
        String description = "Test Description";
        film.setDescription(description);
        assertEquals(description, film.getDescription());
    }

    @Test
    public void testSetReleaseDate() {
        Date date = new Date();
        film.setReleaseDate(date);
        assertEquals(date, film.getReleaseDate());
    }

    @Test
    public void testSetDuration() {
        Integer duration = 120;
        film.setDuration(duration);
        assertEquals(duration, film.getDuration());
    }

    @Test
    public void testAddActor() {
        Actor actor = Mockito.mock(Actor.class);
        film.addActor(actor);
        assertEquals(1, film.getActors().size());
    }

    @Test
    public void testRemoveActor() {
        Actor actor = Mockito.mock(Actor.class);
        film.addActor(actor);
        film.removeActor(actor);
        assertEquals(0, film.getActors().size());
    }
}