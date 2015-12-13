package org.summarine.core.container;

import org.summarine.core.definition.BeanDefinition;
import org.summarine.core.factory.IBeanFactory;
import org.summarine.core.util.ReflectionUtil;

public class DefaultContainer implements IContainer {

    private IBeanFactory beanFactory;

    public DefaultContainer() {
        beanFactory = (IBeanFactory) ReflectionUtil.getInstance("org.summarine.core.factory.DefaultBeanFactory");
        beanFactory.registerBeanDefinition("org.summarine.demo",  "org.summarine.core.handler.AnnotationHandler");
    }

    @Override
    public Object getBean(String beanName) {
        return beanFactory.getBean(beanName.toLowerCase());
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanFactory.getBeanDefinition(beanName);
    }
}
