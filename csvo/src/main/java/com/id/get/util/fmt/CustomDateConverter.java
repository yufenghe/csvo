package com.id.get.util.fmt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import com.id.get.util.DateUtil;

public class CustomDateConverter implements Converter<String, Date> {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomDateConverter.class);
	
	public Date convert(String source) {
		if (StringUtils.isEmpty(source))
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.DATE_DASH_FORMAT);
		if (source.length() > 10) {
			dateFormat.applyPattern(DateUtil.DATE_TIME_DASH_SECOND_FORMAT);
		}
		
		dateFormat.setLenient(false);
		
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			LOGGER.error("日期格式化异常", e);
		}
		
		return null;
	}

}
