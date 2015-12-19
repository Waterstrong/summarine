package org.summarine.core.handler;

import org.summarine.core.annotation.MyBean;
import org.summarine.core.annotation.MyConfiguration;
import org.summarine.core.definition.HelloWorld;

@MyConfiguration
public class StubConfigurationAndBeanAnt {
    @MyBean
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }
}
