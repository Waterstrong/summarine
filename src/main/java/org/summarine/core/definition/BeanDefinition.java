package org.summarine.core.definition;

public class BeanDefinition {
    private String name;
    private String type;
    private Object value;

    public BeanDefinition(String name, String type) {
        this.name = name;
        this.type = type;
    }


    public String getType() {
        return type;
    }
}
