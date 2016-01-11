package org.summarine.demo;

import org.summarine.core.annotation.MyAspect;
import org.summarine.core.annotation.MyComponent;
import org.summarine.logging.LoggingAspect;

@MyComponent
public class AOPTester {

    @MyAspect(advice = LoggingAspect.class)
    public void action() {
        System.out.println("Main Body: This is a test!");
    }
}
