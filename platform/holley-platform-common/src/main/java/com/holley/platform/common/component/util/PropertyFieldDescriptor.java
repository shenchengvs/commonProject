package com.holley.platform.common.component.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 属性描述bean
 * 
 * @author Administrator
 */
public class PropertyFieldDescriptor {

    private Class<Object> propertyType;
    private Method        readMethod;
    private Method        writeMethod;
    private Field         field;
    private String        propertyName;

    @SuppressWarnings("unchecked")
    public Class getPropertyType() {
        return propertyType;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    @SuppressWarnings("unchecked")
    public PropertyFieldDescriptor(Field field) {
        readMethod = null;
        writeMethod = null;
        this.field = field;
        this.propertyName = field.getName();
        propertyType = (Class) field.getType();
    }

    @SuppressWarnings("unchecked")
    public PropertyFieldDescriptor(String propertyName, Method readMethod, Method writeMethod) {
        this.readMethod = readMethod;
        this.writeMethod = writeMethod;
        this.field = null;
        this.propertyName = propertyName;
        propertyType = (Class<Object>) readMethod.getReturnType();
    }

    @SuppressWarnings("unchecked")
    public PropertyFieldDescriptor(Method readMethod, Field field) {
        this.readMethod = readMethod;
        this.writeMethod = null;
        this.field = field;
        this.propertyName = field.getName();
        propertyType = (Class<Object>) field.getType();
    }

    @SuppressWarnings("unchecked")
    public PropertyFieldDescriptor(Field field, Method writeMethod) {
        this.readMethod = null;
        this.writeMethod = writeMethod;
        this.field = field;
        this.propertyName = field.getName();
        propertyType = (Class<Object>) field.getType();
    }

    public Object get(Object obj) {
        try {
            if (readMethod != null) {
                return readMethod.invoke(obj, new Object[] {});
            } else {
                return field.get(obj);
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void set(Object obj, Object value) {
        try {
            Object prop = PropertyFieldUtils.coverTo(value, this.propertyType);
            if (writeMethod != null) {
                writeMethod.invoke(obj, new Object[] { prop });
            } else if (field != null) {
                field.set(obj, prop);
            } else throw new RuntimeException("Property protected!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
