package com.id.tools.dict;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

/**
 * 不可变枚举常量字典Action类
 * 
 * @author yzhou
 * @date 2011-10-22 下午4:39:38
 * @version 3.0
 */
@Service
public class FixedDictService {
	private static final String	ENUM_LIST_METHOD	= "values";
	@Autowired
	FixedDictPostProcessor		fixedDictProcessor;

	/**
	 * 加载枚举字典
	 * 
	 * @param simpleName
	 *            对应自定义枚举类的类名(不包括包名)
	 * @param excludes
	 * @return
	 * @throws Exception
	 */
	public List loadFixedDicts(String simpleName, String excludes) throws Exception {
		List dicts = new ArrayList();
		Map<String, Class<?>> dictClass = fixedDictProcessor.getFixedDictClass();
		//获取simpleName对应的枚举类
		Class<?> clazz = dictClass.get(simpleName);
		if (clazz != null) {
			FixedDict dictAnnotation = AnnotationUtils.findAnnotation(clazz, FixedDict.class);

			List<String> lstExcludes = StringUtils.isNotEmpty(excludes)?Arrays.asList(excludes.split(",")):null;
			Method methodValues = clazz.getMethod(ENUM_LIST_METHOD, null);
			//通过枚举类获取枚举对象列表
			List lstEO = Arrays.asList(clazz.getEnumConstants());
			for (Object item : lstEO) {
				if (lstExcludes != null && lstExcludes.size() > 0 && lstExcludes.contains(item + "")) {
					continue;
				}
				Method mLabel = clazz.getMethod(dictAnnotation.label(), null);
				Method mValue = clazz.getMethod(dictAnnotation.value(), null);
				dicts.add(new NameValue(mLabel.invoke(item, null) + "", mValue.invoke(item, null) + ""));
			}
		}
		return dicts;
	}

	private class NameValue {
		private String	name;
		private String	value;

		public NameValue(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}

	}
}