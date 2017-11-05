package com.gaoxi.utils;

import com.gaoxi.enumeration.BaseEnum;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/4 下午8:02
 * @description
 */
public class EnumUtil {

    public static <E extends Enum<?> & BaseEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}