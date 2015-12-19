package org.summarine.demo;

import org.summarine.core.annotation.MyComponent;

@MyComponent
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
