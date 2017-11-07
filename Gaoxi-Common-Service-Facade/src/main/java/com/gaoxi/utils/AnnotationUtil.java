package com.gaoxi.utils;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 上午10:28
 * @description 注解工具包
 */
public class AnnotationUtil {

    /**
     * 获取指定类上的指定注解
     * @param clazz 目标类
     * @param annotationClazz 注解类的Class对象
     * @param <T> 注解的类型
     * @return 注解对象
     */
    public static <T> T getAnnotationValueByClass(Class clazz, Class annotationClazz) {
        return (T) clazz.getAnnotation(annotationClazz);
    }

    /**
     * 获取指定方法上的指定注解
     * @param method 目标方法
     * @param annotationClazz 注解类的Class对象
     * @param <T> 注解类型
     * @return 注解对象
     */
    public static <T> T getAnnotationValueByMethod(Method method, Class annotationClazz) {
        return (T) method.getAnnotation(annotationClazz);
    }

    /**
     * 获取指定Field上的指定注解
     * @param field 目标 成员变量
     * @param annotationClazz 注解类的Class对象
     * @param <T> 注解类型
     * @return 注解对象
     */
    public static <T> T getAnnotationValueByField(Field field, Class annotationClazz) {
        return (T) field.getAnnotation(annotationClazz);
    }
}
