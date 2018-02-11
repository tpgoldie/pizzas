package com.tpg.pjs.config;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = { ApplicationConfig.class },
    loader = AnnotationConfigContextLoader.class
)
public class ConfigurationTest {

    @Test
    @Ignore
    public void whenSpringContextIsInstantiated_thenNoExceptions() {}
}
