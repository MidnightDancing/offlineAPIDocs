package com.umpay.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author 作者 :miaoliyun
* @version 创建时间：2018年7月9日 下午2:33:05
* 类说明：
*/
public class AnnotationUtil {
	private static final Logger log = LoggerFactory.getLogger(AnnotationUtil.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object get(Class parent, Class in, String... inm) {
		// 获取类上的注解值
		Annotation anno = parent.getAnnotation(in);
		Object[] ret = new Object[inm.length];
		try {
			for(int i=0; i<inm.length; i++){
				Method me = anno.annotationType().getDeclaredMethod(inm[i]);
				if (!me.isAccessible()) {
					me.setAccessible(true);
				}
				ret[i] = me.invoke(anno);
			}
		} catch (Exception e) {
			log.error("parent={}, class={}, method={}", parent, in, inm, e);
		}
		return ret;
	}
}
