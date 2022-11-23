package de.olivergierke.ninjector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration tests for {@link DisableFieldInjection}.
 *
 * @author Oliver Gierke
 */
@SpringJUnitConfig
public class NinjectorApplicationTest {

    @Configuration
    @ComponentScan
    @DisableFieldInjection
    static class Config {
    }

    @SuppressWarnings("resource")
    @Test
    public void rejectsFieldInjected() {
        assertThrows(BeanCreationException.class, () -> new AnnotationConfigApplicationContext(Config.class));
    }

    @Component
    static class MyComponent {
        @Autowired
        MyOtherComponent myOtherComponent;
    }

    @Component
    static class MyOtherComponent {
    }
}
