package com.holley.platform.common.component.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象序列化工具
 */
public class SerializableObject implements Serializable {

    private static final long serialVersionUID = 1L;
    public byte               data[];

    /**
     * 保存一个对象
     * 
     * @param obj
     * @return
     */
    public boolean SaveObject(Object obj) {
        if (obj == null) {
            return true;
        }
        Exception saveException = null;

        if (Serializable.class.isInstance(obj)) {
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream stream = new ObjectOutputStream(out);
                stream.writeObject(obj);
                stream.close();
                this.data = out.toByteArray();
                out.close();
            } catch (Exception e) {
                saveException = e;
            }
        } else {
            saveException = new Exception(obj.getClass().getName() + "  not implements java.io.Serializable");
        }

        if (saveException != null) {
            saveException.printStackTrace();
            return false;
        } else {
            return true;
        }
    }

    /**
     * 把一个可序列对象变为二进制数组
     * 
     * @param obj
     * @return
     */
    public static byte[] toByteArray(Object obj) {
        SerializableObject sol = new SerializableObject();
        if (obj != null) {
            sol.SaveObject(obj);
        }
        return sol.data;
    }

}
