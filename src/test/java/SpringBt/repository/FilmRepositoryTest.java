package SpringBt.repository;

import SpringBt.model.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FilmRepositoryTest {

    @Mock
    FilmRepository filmRepository;

    Film film1, film2;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        film1 = new Film();
        film1.setId(1L);
        film1.setTitle("Film One");

        film2 = new Film();
        film2.setId(2L);
        film2.setTitle("Film Two");
    }

    @Test
    public void testFindByTitleContaining() {
        when(filmRepository.findByTitleContaining("Film")).thenReturn(Arrays.asList(film1, film2));

        List<Film> result = filmRepository.findByTitleContaining("Film");
        assertEquals(2, result.size());
        verify(filmRepository, times(1)).findByTitleContaining("Film");
    }

    @Test
    public void testSaveFilm() {
        when(filmRepository.save(any(Film.class))).thenAnswer(i -> i.getArguments()[0]);

        Film result = filmRepository.save(film1);
        assertEquals(film1, result);
        verify(filmRepository, times(1)).save(film1);
    }

    @Test
    public void testFindById() {
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film1));

        Optional<Film> result = filmRepository.findById(1L);
        assertEquals(film1, result.get());
        verify(filmRepository, times(1)).findById(1L);
    }
}