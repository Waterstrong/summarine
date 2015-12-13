package org.summarine.core.handler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.summarine.core.annotation.Bean;
import org.summarine.core.annotation.Component;
import org.summarine.core.annotation.Configuration;
import org.summarine.core.definition.BeanDefinition;
import org.summarine.core.util.ReflectionUtil;

public class AnnotationHandler implements IHandler {

    private static final char DOT_CHAR = '.';
    private static final char SLASH_CHAR = '/';

    @Override
    public Map<String, Object> convert(String beanPackage) {
        Map<String, Object> beanMap = new HashMap<>();
        String packageDirectory = beanPackage.replace(DOT_CHAR, SLASH_CHAR);
        try {
            Enumeration<URL> directories = Thread.currentThread().getContextClassLoader().getResources(packageDirectory);
            while (directories.hasMoreElements()) {
                URL url = directories.nextElement();
                File file = new File(url.getFile());
                String[] classNames = file.list();
                // TODO: use java8 feature
                for (String className : classNames) {
                    className = removeClassSuffix(className);
                    String fullName = buildFullClassName(beanPackage, className);
                    Class<?> clazz = ReflectionUtil.getClass(fullName);
                    // TODO: ignore interface component annotation
                    if(clazz.isInterface()) {
                       continue;
                    }
                    if (hasAnnotation(clazz)) {
                        String key = className.toLowerCase();
                        beanMap.put(key, new BeanDefinition(key, fullName));
                    } else if(clazz.isAnnotationPresent(Configuration.class)) { // TODO split the logic
                        Method[] methods = clazz.getDeclaredMethods();
                        for (Method method : methods) {
                                if(method.isAnnotationPresent(Bean.class)) {
                                try {
                                    Object instance = method.invoke(ReflectionUtil.getInstance(fullName));
                                    String methodName = method.getName().toLowerCase();
                                    beanMap.put(methodName, new BeanDefinition(methodName, instance.getClass().getTypeName(), instance));
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beanMap;
    }

    private String buildFullClassName(String beanPackage, String className) {
        return beanPackage + DOT_CHAR + className;
    }

    private String removeClassSuffix(String className) {
        return className.substring(0, className.lastIndexOf(DOT_CHAR));
    }

    // TODO: use interface to handle different annotations
    private boolean hasAnnotation(Class<?> clazz) {
        return clazz != null && clazz.isAnnotationPresent(Component.class);
    }
}
