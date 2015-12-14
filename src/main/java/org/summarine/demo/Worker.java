package org.summarine.demo;

import org.summarine.core.annotation.Autowired;
import org.summarine.core.annotation.Component;
import org.summarine.core.annotation.Qualifier;

@Component
public class Worker {

    @Autowired
    @Qualifier("bigMachine")
    private ITool tool;

    public String weed() {
        return "I'm using the " + tool.using() + ", the weed is " + tool.weed() + ".";
    }
}
