package com.id.get.util.fmt;

import java.util.Date;

import com.id.get.util.DateUtil;


/** 
 * <br>类 名: DateConverter 
 * <br>描 述: 日期转换类：支持多种日期格式字符串转化为日期类型
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年1月22日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class DateConverter implements org.springframework.core.convert.converter.Converter<String, Date> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Date convert(String source) {
		return DateUtil.formateStringToDate(source);
	}

}
