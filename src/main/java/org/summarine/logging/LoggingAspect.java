package org.summarine.logging;


import org.summarine.core.aop.IAspect;

public class LoggingAspect implements IAspect {

    @Override
    public void before() {
        System.out.println("-----This is before-----");
    }

    @Override
    public void after() {
        System.out.println("-----This is after-----");
    }
}
