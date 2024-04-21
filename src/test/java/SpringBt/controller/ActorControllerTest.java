package SpringBt.controller;

import SpringBt.model.Actor;
import SpringBt.service.ActorService;
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

@SpringBootTest
@AutoConfigureMockMvc
public class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActorService actorService;

    private Actor actor;

    @BeforeEach
    public void setUp() {
        actor = new Actor();
        actor.setId(1L);
        actor.setName("Test Actor");
    }

    @Test
    public void testGetAllActors() throws Exception {
        given(actorService.findAll()).willReturn(Arrays.asList(actor));

        mockMvc.perform(get("/actors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetActorById() throws Exception {
        given(actorService.findById(1L)).willReturn(Optional.of(actor));

        mockMvc.perform(get("/actors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddActor() throws Exception {
        given(actorService.insert(Mockito.any(Actor.class))).willReturn(actor);

        mockMvc.perform(post("/actors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(actor)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateActor() throws Exception {
        given(actorService.update(Mockito.anyLong(), Mockito.any(Actor.class))).willReturn(actor);

        mockMvc.perform(put("/actors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(actor)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteActor() throws Exception {
        Mockito.doNothing().when(actorService).delete(Mockito.anyLong());

        mockMvc.perform(delete("/actors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}