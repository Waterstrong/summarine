package org.summarine.core.util;

public class ReflectionUtil {
    public static Object getInstance(String beanType) {
        Object instance = null;
        try {
            instance = Class.forName(beanType).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
