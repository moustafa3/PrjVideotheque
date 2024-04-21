package SpringBt.service;

import SpringBt.model.Film;
import SpringBt.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FilmServiceImplTest {

    @InjectMocks
    private FilmServiceImpl filmService;

    @Mock
    private FilmRepository filmRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Film film = new Film();
        film.setId(1L);
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        Optional<Film> returnedFilm = filmService.findById(1L);

        assertEquals(film, returnedFilm.orElse(null));
        verify(filmRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        Film film1 = new Film();
        Film film2 = new Film();
        when(filmRepository.findAll()).thenReturn(Arrays.asList(film1, film2));

        assertEquals(2, filmService.findAll("").size());
        verify(filmRepository, times(1)).findAll();
    }

    @Test
    public void testInsert() {
        Film film = new Film();
        when(filmRepository.save(any(Film.class))).thenReturn(film);

        Film returnedFilm = filmService.insert(film);

        assertEquals(film, returnedFilm);
        verify(filmRepository, times(1)).save(film);
    }

    @Test
    public void testUpdate() {
        Film film = new Film();
        film.setId(1L);
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(filmRepository.save(any(Film.class))).thenReturn(film);

        Film returnedFilm = filmService.update(1L, film);

        assertEquals(film, returnedFilm);
        verify(filmRepository, times(1)).findById(1L);
        verify(filmRepository, times(1)).save(film);
    }

    @Test
    public void testDelete() {
        Film film = new Film();
        film.setId(1L);
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        filmService.delete(1L);

        verify(filmRepository, times(1)).findById(1L);
        verify(filmRepository, times(1)).delete(film);
    }
}