package org.summarine.core.factory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.summarine.core.annotation.Bean;
import org.summarine.core.definition.BeanDefinition;
import org.summarine.core.util.ReflectionUtil;

public class DefaultBeanFactory implements IBeanFactory {

    private Map<String, Object> beanCacheMap;
    private Map<String, Object> beanDefinitionMap;

    public DefaultBeanFactory() {
        this.beanCacheMap = new HashMap<>();
        this.beanDefinitionMap = null;
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

    private void registerBeanToCache(String beanName, Object beanInstance) {
        beanCacheMap.put(beanName, beanInstance);
    }

    private Object createBean(String beanName) {
        BeanDefinition beanDefinition = (BeanDefinition) beanDefinitionMap.get(beanName);
        Object instance = ReflectionUtil.getInstance(beanDefinition.getType());
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            Bean bean = field.getAnnotation(Bean.class);
            if(bean == null) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(instance, getBean(bean.name()));
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
