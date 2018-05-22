package com.jsure.datacenter.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/9
 * @Time: 13:15
 * I am a Code Man -_-!
 */
public class ObjectUtils {

    public ObjectUtils() {
    }

    public static HashMap<String, Object> objectToHashMap(Object obj) {
        HashMap hashMap = new HashMap();
        Class clazz = obj.getClass();
        ArrayList clazzs = new ArrayList();

        do {
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();
        } while (!clazz.equals(Object.class));

        Iterator var4 = clazzs.iterator();

        while (var4.hasNext()) {
            Class iClazz = (Class) var4.next();
            Field[] fields = iClazz.getDeclaredFields();
            Field[] var7 = fields;
            int var8 = fields.length;

            for (int var9 = 0; var9 < var8; ++var9) {
                Field field = var7[var9];
                Object objVal = null;
                field.setAccessible(true);

                try {
                    objVal = field.get(obj);
                } catch (IllegalAccessException var13) {
                    var13.printStackTrace();
                }

                hashMap.put(field.getName(), objVal);
            }
        }

        return hashMap;
    }

    public static Object hashMapToBean(Class type, Map map) {
        try {
            BeanInfo e = Introspector.getBeanInfo(type);
            Object obj = type.newInstance();
            PropertyDescriptor[] propertyDescriptors = e.getPropertyDescriptors();

            for (int i = 0; i < propertyDescriptors.length; ++i) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    Object value = map.get(propertyName);
                    Object[] args = new Object[]{value};

                    try {
                        descriptor.getWriteMethod().invoke(obj, args);
                    } catch (IllegalAccessException var11) {
                        var11.printStackTrace();
                    } catch (InvocationTargetException var12) {
                        var12.printStackTrace();
                    }
                }
            }

            return obj;
        } catch (Exception var13) {
            var13.printStackTrace();
            return null;
        }
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else if (!(obj instanceof Object[])) {
            return false;
        } else {
            Object[] object = (Object[]) ((Object[]) obj);
            if (object.length == 0) {
                return true;
            } else {
                boolean empty = true;

                for (int i = 0; i < object.length; ++i) {
                    if (!isNullOrEmpty(object[i])) {
                        empty = false;
                        break;
                    }
                }

                return empty;
            }
        }
    }

    public static boolean isNotNullAndEmpty(Object obj) {
        return !isNullOrEmpty(obj);
    }


    /**
     *
     * <功能详细描述>判断传入的参数是否含有空或者null
     * @param val
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNull(String... val)
    {
        for (String str : val)
        {
            if (!isNullOrEmpty(str))
            {
                return true;
            }
        }
        return false;
    }

}
