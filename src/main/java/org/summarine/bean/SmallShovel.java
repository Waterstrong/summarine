package org.summarine.bean;

import org.summarine.core.annotation.Bean;

@Bean(name = "smallShovel")
public class SmallShovel implements ITool {
    @Override
    public int weed() {
        return 2;
    }

    @Override
    public String using() {
        return "small shovel";
    }
}
