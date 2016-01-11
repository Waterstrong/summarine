package org.summarine.core.aop;

import java.lang.reflect.Method;

import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;
import org.summarine.core.annotation.MyAspect;

public class MyCallerProxy implements MethodInterceptor {

    private IAspect aspect;

    public MyCallerProxy(IAspect aspect) {
        this.aspect = aspect;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (obj.getClass().getSuperclass().isAnnotationPresent(MyAspect.class) ||
                method.isAnnotationPresent(MyAspect.class)) {
            this.aspect.before();
            Object result = proxy.invokeSuper(obj, args);
            this.aspect.after();
            return result;
        }
        return proxy.invokeSuper(obj, args);
    }

}
