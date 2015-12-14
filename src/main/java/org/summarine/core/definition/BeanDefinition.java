package org.summarine.core.definition;

import org.summarine.core.util.ReflectionUtil;

public class BeanDefinition {
    private String name;
    private String type;
    private Object value;

    public BeanDefinition(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public BeanDefinition(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Object getBeanInstance() {
        if(value == null) {
            value = ReflectionUtil.getInstance(type);
        }
        return value;
    }
}
