package org.summarine.core.definition;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BeanDefinitionTest {
    @Test
    public void should_get_object_instance_when_given_bean_value() {
        HelloWorld helloWorld = new HelloWorld();
        BeanDefinition beanDefinition = new BeanDefinition("helloWorld", helloWorld);

        HelloWorld beanInstance = (HelloWorld) beanDefinition.getBeanInstance();

        assertEquals(helloWorld, beanInstance);
    }

    @Test
    public void should_get_object_instance_by_type_when_given_bean_type() {
        BeanDefinition beanDefinition = new BeanDefinition("helloWorld", "org.summarine.core.definition.HelloWorld");

        Object beanInstance = beanDefinition.getBeanInstance();

        assertEquals(HelloWorld.class, beanInstance.getClass());
    }

    @Test(expected = Exception.class)
    public void should_throw_exception_when_not_found_bean() {
        BeanDefinition beanDefinition = new BeanDefinition("notFound", "NotFound");

        beanDefinition.getBeanInstance();
    }
}
