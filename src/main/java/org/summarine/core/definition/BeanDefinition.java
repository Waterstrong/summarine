package org.summarine.core.definition;

public class BeanDefinition {
    private String name;
    private String type;
    private Object value;

    public BeanDefinition(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public BeanDefinition(String simpleName, String typeName, Object value) {
        this(simpleName, typeName);
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
