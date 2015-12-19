package org.summarine.core.factory;

import java.util.HashMap;
import java.util.Map;

import org.summarine.core.definition.BeanDefinition;
import org.summarine.core.handler.IHandler;

public class MockHandler implements IHandler {
    @Override
    public Map<String, Object> convert(String resource) {
        Map<String, Object> map = new HashMap<>();
        if(resource == "resource")
        map.put("object", BeanDefinition.createByType("object", "type"));
        return map;
    }
}
