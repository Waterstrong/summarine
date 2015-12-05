package org.summarine.core.factory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.summarine.core.annotation.Autowired;
import org.summarine.core.annotation.Bean;
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
        BeanDefinition beanDefinition = (BeanDefinition) beanDefinitionMap.get(beanName.toLowerCase());
        Object instance = ReflectionUtil.getInstance(beanDefinition.getType());
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            Autowired autowired = field.getAnnotation(Autowired.class);
            if(autowired == null) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(instance, getBean(autowired.value()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private Object getBeanFromCache(String beanName) {
        return beanCacheMap.get(beanName);
    }

    private boolean isBeanCached(String beanName) {
        return beanCacheMap.containsKey(beanName);
    }

}
