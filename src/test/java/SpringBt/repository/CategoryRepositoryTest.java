package SpringBt.repository;

import SpringBt.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setName("Test Category");
        entityManager.persistAndFlush(category);
    }

    @Test
    public void whenFindByNameContainingIgnoreCase_thenReturnCategory() {
        // when
        List<Category> found = categoryRepository.findByNameContainingIgnoreCase(category.getName());

        // then
        assertThat(found.get(0).getName()).isEqualTo(category.getName());
    }
}