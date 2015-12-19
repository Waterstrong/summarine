package org.summarine.demo;

import org.summarine.core.annotation.Inject;
import org.summarine.core.annotation.MyComponent;
import org.summarine.core.annotation.MyQualifier;

@MyComponent
public class Worker {

    @Inject
    @MyQualifier("bigMachine")
    private ITool tool;

    public String weed() {
        return "I'm using the " + tool.using() + ", the weed is " + tool.weed() + ".";
    }
}
