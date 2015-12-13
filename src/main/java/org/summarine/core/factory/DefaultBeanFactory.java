package org.summarine.core.factory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.summarine.core.annotation.Autowired;
import org.summarine.core.definition.BeanDefinition;
import org.summarine.core.handler.IHandler;
import org.summarine.core.util.ReflectionUtil;

public class DefaultBeanFactory implements IBeanFactory {

    private Map<String, Object> beanCacheMap;
    private Map<String, Object> beanDefinitionMap;

    public DefaultBeanFactory() {
        this.beanCacheMap = new HashMap<>();
        this.beanDefinitionMap = new HashMap<>();
    }

    @Override
    public Object getBean(String beanName) {
        if (isBeanCached(beanName)) {
            return getBeanFromCache(beanName);
        }
        Object beanObject = createBean(beanName);
        registerBeanToCache(beanName, beanObject);
        return beanObject;
    }

    @Override
    public void registerBeanDefinition(String beanResource, String handlerName) {
        IHandler handler = (IHandler) ReflectionUtil.getInstance(handlerName);
        beanDefinitionMap.putAll(handler.convert(beanResource));
        loadBeanConfiguration();
    }

    private void loadBeanConfiguration() {

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return (BeanDefinition) beanDefinitionMap.get(beanName);
    }

    private void registerBeanToCache(String beanName, Object beanInstance) {
        beanCacheMap.put(beanName, beanInstance);
    }

    private Object createBean(String beanName) {
        BeanDefinition beanDefinition = (BeanDefinition) beanDefinitionMap.get(beanName);
        Object beanValue = beanDefinition.getValue();
        Object instance = beanValue != null ? beanValue : ReflectionUtil.getInstance(beanDefinition.getType());
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            autowireField(instance, field);
        }
        return instance;
    }

    private void autowireField(Object instance, Field field) {
        if (hasAnnotation(field)) {
            field.setAccessible(true);
            try {
                String fieldName = field.getName().toLowerCase();
                field.set(instance, getBean(fieldName));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean hasAnnotation(Field field) {
        return field.isAnnotationPresent(Autowired.class);
    }

    private Object getBeanFromCache(String beanName) {
        return beanCacheMap.get(beanName);
    }

    private boolean isBeanCached(String beanName) {
        return beanCacheMap.containsKey(beanName);
    }

}
