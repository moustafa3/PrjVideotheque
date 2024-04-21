package SpringBt.controller;

import SpringBt.model.Film;
import SpringBt.service.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    private Film film;

    @BeforeEach
    public void setUp() {
        film = new Film();
        film.setId(1L);
        film.setTitle("Test Film");
        film.setYear(2021);
        film.setDuration(120);
    }

    @Test
    public void getAllFilms() throws Exception {
        given(filmService.findAll("")).willReturn(Arrays.asList(film));

        mockMvc.perform(get("/films")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(film.getTitle())));
    }

    @Test
    public void getFilmById() throws Exception {
        given(filmService.findById(1L)).willReturn(Optional.of(film));

        mockMvc.perform(get("/films/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(film.getTitle())));
    }

    @Test
    public void addFilm() throws Exception {
        given(filmService.insert(Mockito.any(Film.class))).willReturn(film);

        mockMvc.perform(post("/films")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(film)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(film.getTitle())));
    }

    @Test
    public void updateFilm() throws Exception {
        given(filmService.update(Mockito.anyLong(), Mockito.any(Film.class))).willReturn(film);

        mockMvc.perform(put("/films/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(film)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(film.getTitle())));
    }

    @Test
    public void deleteFilm() throws Exception {
        mockMvc.perform(delete("/films/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}