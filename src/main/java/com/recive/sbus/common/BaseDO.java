package com.recive.sbus.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.util.Assert;

/**
 * @author 所有实体类的基类 主要是为了实现一些共有的方法
 *
 */
public class BaseDO {

	/**
	 * 根据原对象(source)更新目标对象(target)的属性值，该方法的产生主要是Convert类无法满足现有需求：
	 * 将source对象的所有非空属性传递给target
	 * 
	 * @param source
	 * @param target
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws BeansException
	 */
	public void updateProperties(Object source, Object target) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Assert.notNull(source, "source can not be null");
		Assert.notNull(target, "target can not be null");

		// 获取原对象的所有属性以及属性值
		Map<String, Object> sourceMap = getFiledMap(source);

		// 获取目标对象的所有属性以及属性值
		Map<String, Object> targetMap = getFiledMap(target);

		// 遍历进行值更新
		for (Map.Entry<String, Object> entry : sourceMap.entrySet()) {
			if (null != entry.getValue()) {// 值不为null,则需要床底给target
				String key = entry.getKey();
				if (targetMap.containsKey(key)) {
					Object[] tv = (Object[]) entry.getValue();
					Method method = target.getClass().getMethod(key, tv[0].getClass());
					method.invoke(target, tv[1]);
				}
			}
		}

	}

	private Map<String, Object> getFiledMap(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			Object[] tv = new Object[2];
			Class<?> fieldType = fields[i].getType();
			tv[0] = fieldType;
			Object filedVal = getFieldValueByName(fieldName, o);
			tv[1] = filedVal;

			map.put(fieldName, tv);
		}
		return map;
	}

	private Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

}
