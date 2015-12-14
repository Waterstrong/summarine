package org.summarine.core.factory;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.summarine.core.definition.BeanDefinition;
import org.summarine.core.definition.HelloWorld;

public class DefaultBeanFactoryTest {

    private DefaultBeanFactory beanFactory;
    private BeanDefinition beanDefinition;
    private HelloWorld helloWorld;
    private Map<String, Object> beanDefMap;

    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        beanDefinition = mock(BeanDefinition.class);
        helloWorld = new HelloWorld();
        given(beanDefinition.getBeanInstance()).willReturn(helloWorld);
        beanDefMap = new HashMap<>();
        beanDefMap.put("helloWorld", beanDefinition);
        beanFactory.setBeanDefinitionMap(beanDefMap);
    }

    @Test
    public void should_get_bean_instance_when_given_bean_name() {
        Object instance = beanFactory.getBean("helloWorld");

        assertEquals(HelloWorld.class, instance.getClass());
    }

    @Test
    public void should_cache_bean_after_create_bean() {
        beanFactory.getBean("helloWorld");
        beanFactory.getBean("helloWorld");

        verify(beanDefinition, times(1)).getBeanInstance();
    }

    @Test
    public void should_register_bean_definition_when_given_bean_resource_and_handler() {
        String beanResource = "resource";
        String handlerName = "org.summarine.core.factory.MockHandler";

        beanFactory.registerBeanDefinition(beanResource, handlerName);

        assertEquals(BeanDefinition.class, beanFactory.getBeanDefinition("object").getClass());
    }

    @Test
    public void should_autowire_field_with_bean_when_given_field_with_autowired() {

    }
}