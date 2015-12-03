package org.summarine.demo;

import org.summarine.core.annotation.Component;

@Component
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
