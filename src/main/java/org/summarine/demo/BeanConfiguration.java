package org.summarine.demo;

import org.summarine.core.annotation.Bean;
import org.summarine.core.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ITool buildTool() {
        return new BigMachine();
    }

}
