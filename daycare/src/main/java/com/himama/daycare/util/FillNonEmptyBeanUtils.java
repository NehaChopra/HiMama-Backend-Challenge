package com.himama.daycare.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;

/**
 * 
 * @author nchopra
 *
 */
public class FillNonEmptyBeanUtils extends BeanUtilsBean {

	@Override
	public void copyProperty(Object dest, String name, Object source)
			throws IllegalAccessException, InvocationTargetException {
		if (source == null)
			return;
		else {
			if (source instanceof Number) {
				if (((Number) source).intValue() == 0) {
					return;
				}
			}
			super.copyProperty(dest, name, source);
		}
	}
}