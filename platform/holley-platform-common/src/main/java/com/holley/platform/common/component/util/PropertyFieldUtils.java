package com.holley.platform.common.component.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.holley.platform.common.util.DateUtil;

@SuppressWarnings("unchecked")
public class PropertyFieldUtils {

    private Map        classprops;
    private Object     objInstance;
    // 缓存
    private static Map propCache = new HashMap();

    /**
     * 构造函数
     * 
     * @param c
     */
    public PropertyFieldUtils(Class c) {
        classprops = (HashMap) propCache.get(c);
        if (classprops == null) {
            classprops = getClassPropertys(c);
            propCache.put(c, classprops);
        }
    }

    /**
     * 构造函数
     * 
     * @param obj
     */
    public PropertyFieldUtils(Object obj) {
        this(obj.getClass());
        objInstance = obj;
    }

    private PropertyField getPropertyFieldDefine(String name) throws Exception {
        PropertyFieldDescriptor desc = (PropertyFieldDescriptor) this.classprops.get(name);
        Object obj = this.getInstance();

        if (desc == null) {
            String[] propertyNames = name.split("\\.");
            if (propertyNames.length > 1) {
                for (String propertyName : propertyNames) {
                    obj = getProperty(obj, propertyName);
                    if (obj == null) {
                        return null;
                    }
                }
                PropertyFieldUtils fieldProp = new PropertyFieldUtils(obj);
                desc = (PropertyFieldDescriptor) fieldProp.classprops.get(propertyNames[propertyNames.length - 1]);
            }
        }

        return desc == null ? null : new PropertyField(obj, desc);
    }

    /**
     * 得到class的属性
     * 
     * @param c Description of Parameter
     * @return The ClassPropertys value
     */
    private HashMap<String, PropertyFieldDescriptor> getClassPropertys(Class c) {
        Method[] methods = c.getMethods();
        Field[] fields = c.getFields();
        HashMap<String, PropertyFieldDescriptor> props = new HashMap<String, PropertyFieldDescriptor>();
        String setName = null;
        String propName;
        Method read = null;
        Method write = null;

        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())) {
                if ((method.getName().startsWith("get"))) {
                    setName = method.getName().replaceFirst("get", "set");
                } else if ((method.getName().startsWith("is"))) {
                    setName = method.getName().replaceFirst("is", "set");
                } else {
                    setName = null;
                }

                if (setName != null) {
                    read = method;
                    write = null;
                    propName = propertyName(setName.substring(3));
                    for (int j = 0; j < methods.length; j++) {
                        method = methods[j];
                        if (Modifier.isPublic(method.getModifiers()) && (method.getName().equals(setName))) {
                            write = method;
                        }
                    }

                    if (write != null) {
                        props.put(propName, new PropertyFieldDescriptor(propName, read, write));
                    } else {
                        boolean findField = false;
                        for (int j = 0; j < fields.length; j++) {
                            Field f = fields[j];
                            if (Modifier.isPublic(f.getModifiers()) && f.getName().equals(propName)) {
                                findField = true;
                                props.put(propName, new PropertyFieldDescriptor(read, f));
                                break;
                            }
                        }
                        if (!findField) {
                            props.put(propName, new PropertyFieldDescriptor(propName, read, write));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            propName = f.getName();
            setName = "set" + capitalize(propName);

            if (Modifier.isPublic(f.getModifiers()) && (props.get(propName) == null)) {
                write = null;
                for (Method method : methods) {
                    if (Modifier.isPublic(method.getModifiers()) && (method.getName().equals(setName))) {
                        write = method;
                    }

                    if (write != null) {
                        props.put(propName, new PropertyFieldDescriptor(f, write));
                    } else {
                        props.put(propName, new PropertyFieldDescriptor(f));
                    }
                }
            }
        }
        return props;
    }

    public void setPropertyField(String name, Object value) throws Exception {
        PropertyField field = getPropertyFieldDefine(name);
        if (field == null) {
            throw new Exception(name + " is null");
        }

        field.set(value);
    }

    public PropertyFieldDescriptor[] getFieldDescriptor() {
        PropertyFieldDescriptor[] props = new PropertyFieldDescriptor[classprops.size()];
        props = (PropertyFieldDescriptor[]) classprops.values().toArray(props);
        return props;
    }

    public PropertyFieldDescriptor getFieldDescriptor(String name) throws Exception {
        PropertyFieldDescriptor desc = (PropertyFieldDescriptor) this.classprops.get(name);
        if (desc == null) {
            throw new Exception(name + " is error");
        }

        return desc;
    }

    public Object getPropertyField(String name) throws Exception {
        PropertyField field = getPropertyFieldDefine(name);
        if (field == null) {
            throw new Exception(name + " is error");
        }

        return field.get();
    }

    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        PropertyFieldDescriptor[] props = getFieldDescriptor();
        for (PropertyFieldDescriptor prop : props) {
            sbuf.append(prop.getPropertyName());
            sbuf.append("=\"");
            sbuf.append(prop.get(objInstance));
            sbuf.append("\";");
        }

        return sbuf.toString();
    }

    public static void setProperty(Object obj, String name, Object value) throws Exception {
        PropertyFieldUtils prop = new PropertyFieldUtils(obj);
        prop.setPropertyField(name, value);
    }

    public static boolean isByteAray(Class c) {
        if (c.isArray()) {
            Class pc = c.getComponentType();
            if (pc.equals(Byte.TYPE)) {
                return true;
            }
        }
        return false;
    }

    public static Object getProperty(Object obj, String name) throws Exception {
        if (obj == null) {
            return null;
        }
        PropertyFieldUtils prop = new PropertyFieldUtils(obj);
        return prop.getPropertyField(name);
    }

    public static String objectToString(Object obj) {
        PropertyFieldUtils prop = new PropertyFieldUtils(obj);
        return prop.toString();
    }

    /**
     * 把名字的第一个支付大写
     * 
     * @param s
     * @return
     */
    public static String capitalize(String s) {
        if (s.length() == 0) {
            return s;
        }
        char chars[] = s.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    /**
     * 把名字的第一个支付小写
     * 
     * @param s
     * @return
     */
    public static String propertyName(String s) {
        if (s.length() == 0) {
            return s;
        }
        char chars[] = s.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    public static Object coverTo(Object obj, Class toType) {

        String s;
        if (obj == null) {
            return null;
        } else if (toType.equals(Byte.TYPE) || (toType.equals(Byte.class))) {
            if (Byte.class.isInstance(obj)) {
                return obj;
            }
            s = obj.toString();
            return new Byte(s);
        } else if (toType.equals(Character.TYPE) || (toType.equals(Character.class))) {
            if (Character.class.isInstance(obj)) {
                return obj;
            }
            s = obj.toString();
            return new Character((char) (Integer.parseInt(s)));
        }

        else

        if (toType.equals(Short.TYPE) || (toType.equals(Short.class))) {
            if (Short.class.isInstance(obj)) {
                return obj;
            }
            s = obj.toString();
            return new Short(s);
        }

        else if (toType.equals(Long.TYPE) || (toType.equals(Long.class))) {
            if (Long.class.isInstance(obj)) {
                return obj;
            }
            s = obj.toString();
            return new Long(s);
        } else if (toType.equals(Integer.TYPE) || (toType.equals(Integer.class))) {
            if (Integer.class.isInstance(obj)) {
                return obj;
            }
            s = obj.toString();
            return new Integer(s);
        } else if (toType.equals(Float.TYPE) || (toType.equals(Float.class))) {
            if (Float.class.isInstance(obj)) {
                return obj;
            }
            s = obj.toString();
            return new Float(s);
        } else if (toType.equals(Double.TYPE) || (toType.equals(Double.class))) {
            if (Double.class.isInstance(obj)) {
                return obj;
            }
            s = obj.toString();
            return new Double(s);
        } else if (toType.equals(String.class)) {
            if (String.class.isInstance(obj)) {
                return obj;
            }
            if (Date.class.isInstance(obj)) {
                return DateUtil.DateToLongStr((Date) obj);
            }
            return obj.toString();
        } else if (toType.equals(Boolean.TYPE) || (toType.equals(Boolean.class))) {
            if (Boolean.class.isInstance(obj)) {
                return obj;
            }
            s = obj.toString();
            return new Boolean(s);
        } else if (toType.equals(Date.class)) {
            if (Date.class.isInstance(obj)) {
                return new Date(((long) (((Date) obj).getTime() / 1000)) * 1000);
            }
            ;
            if (String.class.isInstance(obj)) {
                return DateUtil.LongStrToDate((String) obj);
            }
            return null;
        } else if (toType.equals(java.sql.Timestamp.class)) {
            if (Date.class.isInstance(obj)) {
                return new java.sql.Timestamp(((long) (((Date) obj).getTime() / 1000)) * 1000);
            }
            ;
            if (String.class.isInstance(obj)) {
                return new java.sql.Timestamp((DateUtil.LongStrToDate((String) obj).getTime() / 1000) * 1000);
            }
            return null;
        } else if (toType.isInstance(obj)) {
            return obj;
        }

        return null;
    }

    public static Object assign(Object src, Object dest) {
        PropertyFieldUtils psrc = new PropertyFieldUtils(src);
        PropertyFieldUtils pdest = new PropertyFieldUtils(dest);
        PropertyFieldDescriptor[] props = psrc.getFieldDescriptor();

        for (PropertyFieldDescriptor prop : props) {
            PropertyFieldDescriptor desc = (PropertyFieldDescriptor) pdest.getClassPropMap().get(prop.getPropertyName());
            if (desc != null) {
                desc.set(dest, prop.get(src));
            }
        }
        return dest;
    }

    public Map getClassPropMap() {
        return classprops;
    }

    public void setInstance(Object obj) {
        objInstance = obj;
    }

    public Object getInstance() {
        return objInstance;
    }

    public static void main(String[] args) throws Exception {

        System.out.print(PropertyFieldUtils.propertyName("州立"));
        // PropertyField dd = pro.getPropertyFieldDefine("java.util.HashMap");
    }
}
