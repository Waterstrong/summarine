package org.summarine.core.handler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.summarine.core.annotation.MyBean;
import org.summarine.core.annotation.MyComponent;
import org.summarine.core.annotation.MyConfiguration;
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
                beanMap.putAll(extractFromClsNames(beanPackage, Arrays.asList(file.list())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beanMap;
    }

//    protected abstract BeanDefinition generateBeanDefinition();

    private Map<String, Object> extractFromClsNames(String beanPackage, List<String> classNames) {
        Map<String, Object> beanMap = new HashMap<>();
        classNames.stream().forEach(className -> beanMap.putAll(handleClassName(beanPackage, className)));
        return beanMap;
    }

    private Map<String, Object> handleClassName(String beanPackage, String className) {
        Map<String, Object> beanMap = new HashMap<>();
        className = removeClassSuffix(className);
        String fullName = buildFullClassName(beanPackage, className);
        Class<?> clazz = ReflectionUtil.getClass(fullName);

        if (hasComponentAnnotation(clazz)) {
            BeanDefinition beanDefinition = BeanDefinition.createByType(className.toLowerCase(), fullName);
            beanMap.put(beanDefinition.getName(), beanDefinition);
        } else if (hasConfigurationAnnotation(clazz)) { // TODO split the logic
            List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
            methods.forEach(method -> beanMap.putAll(handleBeanMethod(fullName, method)));
        }
        return beanMap;
    }

    private Map<String, Object> handleBeanMethod(String fullName, Method method) {
        Map<String, Object> beanMap = new HashMap<>();
        if (hasBeanAnnotation(method)) {
            try {
                Object instance = method.invoke(ReflectionUtil.getInstance(fullName));
                BeanDefinition beanDefinition = BeanDefinition.createByValue(extractBeanName(method), instance);
                beanMap.put(beanDefinition.getName(), beanDefinition);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return beanMap;
    }

    private String extractBeanName(Method method) {
        String beanName = method.getAnnotation(MyBean.class).name();
        return (beanName.isEmpty() ? method.getName() : beanName).toLowerCase();
    }

    private boolean hasBeanAnnotation(Method method) {
        return method != null && method.isAnnotationPresent(MyBean.class);
    }

    private boolean hasConfigurationAnnotation(Class<?> clazz) {
        return clazz != null && clazz.isAnnotationPresent(MyConfiguration.class);
    }

    private String buildFullClassName(String beanPackage, String className) {
        return beanPackage + DOT_CHAR + className;
    }

    private String removeClassSuffix(String className) {
        return className.substring(0, className.lastIndexOf(DOT_CHAR));
    }

    // TODO: use interface to handle different annotations
    private boolean hasComponentAnnotation(Class<?> clazz) {
        return clazz != null && clazz.isAnnotationPresent(MyComponent.class);
    }
}
