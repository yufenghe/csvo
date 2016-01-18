package com.id.get.util.fmt;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.id.get.util.DateUtil;

/**
 * 
 * <br>类 名: TimestampSerializer 
 * <br>描 述: Jackson格式输出时格式化日期 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年1月13日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class TimestampSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		if (value == null)
			return;
		SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_TIME_DASH_SECOND_FORMAT);
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	}
}