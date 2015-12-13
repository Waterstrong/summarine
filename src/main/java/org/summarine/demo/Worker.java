package org.summarine.demo;

import org.summarine.core.annotation.Autowired;
import org.summarine.core.annotation.Component;

@Component
public class Worker {

    @Autowired
    private ITool bigMachine;

    public String weed() {
        return "I'm using the " + bigMachine.using() + ", the weed is " + bigMachine.weed() + ".";
    }
}
