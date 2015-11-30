package org.summarine.bean;

import org.summarine.core.annotation.Bean;

@Bean(name = "worker")
public class Worker {
    private ITool tool;

    public int weed() {
        return tool.weed();
    }
}
