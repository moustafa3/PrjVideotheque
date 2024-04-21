package SpringBt;

import SpringBt.controller.ActorController;
import SpringBt.controller.CategoryController;
import SpringBt.controller.FilmController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class PrjVideothequeApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private PrjVideothequeApplication prjVideothequeApplication;
    @Autowired
    private FilmController filmController;
    @Autowired
    private ActorController actorController;
    @Autowired
    private CategoryController categoryController;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
        assertThat(filmController).isNotNull();
        assertThat(actorController).isNotNull();
        assertThat(prjVideothequeApplication).isNotNull();
        assertThat(prjVideothequeApplication).isInstanceOf(PrjVideothequeApplication.class);
        assertThat(categoryController).isNotNull();
    }
}