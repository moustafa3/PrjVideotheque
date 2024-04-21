package SpringBt.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    public void testSetName() {
        String name = "Test Name";
        category.setName(name);
        assertEquals(name, category.getName());
    }

    @Test
    public void testAddFilm() {
        Film film = Mockito.mock(Film.class);
        category.addFilm(film);
        assertEquals(1, category.getFilms().size());
    }

    @Test
    public void testRemoveFilm() {
        Film film = Mockito.mock(Film.class);
        category.addFilm(film);
        category.removeFilm(film);
        assertEquals(0, category.getFilms().size());
    }
}