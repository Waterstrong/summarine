package org.summarine.bean;

import org.summarine.core.annotation.Bean;

@Bean(name = "worker")
public class Worker {
    @Bean(name = "smallShovel")
    private ITool tool;

    public String weed() {
        return "I'm using the " + tool.using() + ", the weed is " + tool.weed() + ".";
    }
}
