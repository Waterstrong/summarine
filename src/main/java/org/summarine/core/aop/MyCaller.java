package org.summarine.core.aop;

import java.lang.reflect.Method;

import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;
import org.summarine.core.annotation.MyAspect;

public class MyCaller implements MethodInterceptor {

    private IAspect advice;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (obj.getClass().getSuperclass().isAnnotationPresent(MyAspect.class) ||
                method.isAnnotationPresent(MyAspect.class)) {
            this.advice.before();
            Object result = proxy.invokeSuper(obj, args);
            this.advice.after();
            return result;
        }
        return proxy.invokeSuper(obj, args);
    }

    public void setAdvice(IAspect advice) {
        this.advice = advice;
    }
}
