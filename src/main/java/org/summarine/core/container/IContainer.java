package org.summarine.core.container;

import org.summarine.core.definition.BeanDefinition;

public interface IContainer {
    Object getBean(String beanName);

    BeanDefinition getBeanDefinition(String beanName);
}
