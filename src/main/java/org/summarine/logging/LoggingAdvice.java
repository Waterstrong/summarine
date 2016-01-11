package org.summarine.logging;


import org.summarine.core.aop.IAdvice;

public class LoggingAdvice implements IAdvice {
//    private static Logger LOGGER = Logger.getLogger(LoggingAdvice.class);

    @Override
    public void before() {
        System.out.println("-----This is before-----");
    }

    @Override
    public void after() {
        System.out.println("-----This is after-----");
    }
}
