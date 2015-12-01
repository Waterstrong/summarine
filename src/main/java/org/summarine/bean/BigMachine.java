package org.summarine.bean;

import org.summarine.core.annotation.Bean;

@Bean(name = "bigMachine")
public class BigMachine implements ITool {
    @Override
    public int weed() {
        return 10;
    }

    @Override
    public String using() {
        return "big machine";
    }
}
