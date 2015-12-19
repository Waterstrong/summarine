package org.summarine.core.definition;

import org.summarine.core.util.ReflectionUtil;

public class BeanDefinition {
    private String name;
    private String type;
    private Object value;

    private BeanDefinition() {
    }

    private BeanDefinition(String name, String type) {
        this.name = name;
        this.type = type;
    }

    private BeanDefinition(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public static BeanDefinition createByType(String name, String type) {
        return new BeanDefinition(name, type);
    }

    public static BeanDefinition createByValue(String name, Object value) {
        return new BeanDefinition(name, value);
    }


    public Object getBeanInstance() {
        if(value == null) {
            value = ReflectionUtil.getInstance(type);
        }
        return value;
    }

    public String getName() {
        return name;
    }
}
