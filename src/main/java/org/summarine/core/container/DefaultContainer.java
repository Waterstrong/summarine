package org.summarine.core.container;

import org.summarine.core.definition.BeanDefinition;
import org.summarine.core.factory.IBeanFactory;

public class DefaultContainer implements IContainer {

    private IBeanFactory beanFactory;

    @Override
    public Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }
}
