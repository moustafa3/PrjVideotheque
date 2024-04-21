package SpringBt.service;

import SpringBt.model.Category;
import SpringBt.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Category category = new Category();
        category.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.findById(1L);

        verify(categoryRepository, times(1)).findById(1L);
        assertEquals(category.getId(), result.get().getId());
    }

    @Test
    public void testFindAll() {
        Category category1 = new Category();
        Category category2 = new Category();
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        assertEquals(2, categoryService.findAll().size());
    }

    @Test
    public void testInsert() {
        Category category = new Category();
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.insert(category);

        assertEquals(category, result);
    }

    @Test
    public void testUpdate() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Drama");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category newCategory = new Category();
        newCategory.setName("Comedy");
        when(categoryRepository.save(any(Category.class))).thenReturn(newCategory);

        Category result = categoryService.update(1L, newCategory);

        assertEquals(newCategory.getName(), result.getName());
    }

    @Test
    public void testDelete() {
        Category category = new Category();
        category.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        categoryService.delete(1L);

        verify(categoryRepository, times(1)).delete(category);
    }
}