package SpringBt.controller;

import SpringBt.model.Category;
import SpringBt.service.CategoryService;
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
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Test Category");
    }

    @Test
    public void getAllCategories() throws Exception {
        given(categoryService.findAll()).willReturn(Arrays.asList(category));

        mockMvc.perform(get("/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(category.getName())));
    }

    @Test
    public void getCategoryById() throws Exception {
        given(categoryService.findById(1L)).willReturn(Optional.of(category));

        mockMvc.perform(get("/categories/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(category.getName())));
    }

    @Test
    public void addCategory() throws Exception {
        given(categoryService.insert(Mockito.any(Category.class))).willReturn(category);

        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(category.getName())));
    }

    @Test
    public void updateCategory() throws Exception {
        given(categoryService.update(Mockito.anyLong(), Mockito.any(Category.class))).willReturn(category);

        mockMvc.perform(put("/categories/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(category.getName())));
    }

    @Test
    public void deleteCategory() throws Exception {
        mockMvc.perform(delete("/categories/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}