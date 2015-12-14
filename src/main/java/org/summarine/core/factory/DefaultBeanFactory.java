package org.summarine.core.factory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.summarine.core.annotation.Autowired;
import org.summarine.core.annotation.Qualifier;
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
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return (BeanDefinition) beanDefinitionMap.get(beanName);
    }

    private void registerBeanToCache(String beanName, Object beanInstance) {
        beanCacheMap.put(beanName, beanInstance);
    }

    private Object createBean(String beanName) {
        Object instance = ((BeanDefinition) beanDefinitionMap.get(beanName)).getBeanInstance();
        handleField(instance);
        return instance;
    }

    private void handleField(Object instance) {
        List<Field> fields = Arrays.asList(instance.getClass().getDeclaredFields());
        fields.stream().forEach(field -> autowireField(instance, field));
    }

    private void autowireField(Object instance, Field field) {
        if (hasAutowiredAnnotation(field)) {
            field.setAccessible(true);
            try {
                field.set(instance, getBean(extractQualifierValue(field)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private String extractQualifierValue(Field field) {
        return (field.isAnnotationPresent(Qualifier.class) ?
                field.getAnnotation(Qualifier.class).value() : field.getName()).toLowerCase();
    }

    private boolean hasAutowiredAnnotation(Field field) {
        return field.isAnnotationPresent(Autowired.class);
    }

    private Object getBeanFromCache(String beanName) {
        return beanCacheMap.get(beanName);
    }

    private boolean isBeanCached(String beanName) {
        return beanCacheMap.containsKey(beanName);
    }

    public void setBeanDefinitionMap(Map<String, Object> beanDefinitionMap) {
        this.beanDefinitionMap = beanDefinitionMap;
    }
}
