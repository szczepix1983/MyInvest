package com.szczepix.myinvest.services.requestService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class Callback {

    protected String invokeMethod;
    private Object scope;

    public Callback() {
    }

    public Callback(Object scope, String invokeMethod) {
        this.invokeMethod = invokeMethod;
        this.scope = scope;
    }

    public Object invoke(Object... parameters) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Object callableObject = Objects.isNull(scope) ? this : scope;
        Method method = callableObject.getClass().getMethod(Objects.isNull(invokeMethod) ? "emptyCallback" : invokeMethod, getParameterClasses(parameters));
        return method.invoke(callableObject, parameters);
    }

    public void emptyCallback(Object parameters) {

    }

    private Class[] getParameterClasses(Object... parameters) {
        Class[] classes = new Class[parameters.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = parameters[i].getClass();
        }
        return classes;
    }
}
