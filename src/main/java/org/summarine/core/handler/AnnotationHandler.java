package org.summarine.core.handler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.summarine.core.annotation.Bean;
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
                    className = className.substring(0, className.lastIndexOf(DOT_CHAR));
                    String fullName = beanPackage + DOT_CHAR + className;
                    Object instance = ReflectionUtil.getInstance(fullName);
                    // TODO: use interface to handle different annotations
                    if (hasAnnotation(instance)) {
                        Bean bean = instance.getClass().getAnnotation(Bean.class);
                        beanMap.put(bean.name(), new BeanDefinition(bean.name(), fullName));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beanMap;
    }

    private boolean hasAnnotation(Object instance) {
        return instance != null && instance.getClass().isAnnotationPresent(Bean.class);
    }
}
