package org.summarine.core.factory;

import org.summarine.core.definition.BeanDefinition;

public interface IBeanFactory {
    Object getBean(String beanName);

    void registerBeanDefinition(String resource, String handler);

    BeanDefinition getBeanDefinition(String beanName);
}
