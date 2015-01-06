package com.bsu.sed.utils;

import javax.persistence.Column;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Convenience Tools for getting entity metadata.
 *
 * @author gbondarchuk
 */
public abstract class EntityUtils {
    private EntityUtils() {
    }

    /**
     * Get max length of the entity field using the metainformation specified by @Column
     *
     * @param clazz entity class
     * @param field name of field
     * @return max length of the field
     * @throws IllegalArgumentException if entity class doesn't have field
     */
    public static int getFieldMaxLength(Class clazz, String field) {
        try {
            PropertyDescriptor[] descriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                if (descriptor.getName().equals(field)) {
                    Method readMethod = descriptor.getReadMethod();
                    Column annotation = readMethod.getAnnotation(Column.class);
                    if (annotation == null) {
                        throw new IllegalArgumentException("Property [" + field + "] of the class " + clazz.getName() + " is not mapped");
                    }
                    return annotation.length();
                }
            }
            throw new IllegalArgumentException("Class " + clazz.getName() + " doesn't have property [" + field + "]");
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }
}
