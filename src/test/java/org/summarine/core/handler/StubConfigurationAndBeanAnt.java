package org.summarine.core.handler;

import org.summarine.core.annotation.Bean;
import org.summarine.core.annotation.Configuration;
import org.summarine.core.definition.HelloWorld;

@Configuration
public class StubConfigurationAndBeanAnt {
    @Bean
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }
}
