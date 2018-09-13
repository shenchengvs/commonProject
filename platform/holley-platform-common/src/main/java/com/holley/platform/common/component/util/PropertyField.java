package com.holley.platform.common.component.util;

public class PropertyField {

    private Object                  instance;
    private PropertyFieldDescriptor desc;

    public PropertyField() {
    }

    public PropertyField(Object instance, PropertyFieldDescriptor desc) {
        this.instance = instance;
        this.desc = desc;
    }

    public PropertyFieldDescriptor getDesc() {
        return desc;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public void setDesc(PropertyFieldDescriptor desc) {
        this.desc = desc;
    }

    public Object getInstance() {
        return instance;
    }

    public Object get() {
        return desc.get(instance);
    }

    public void set(Object value) {
        desc.set(instance, value);
    }

}
