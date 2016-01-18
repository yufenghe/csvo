package com.id.get.util.fmt;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.id.get.util.DateUtil;

public class SimpleDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		if (value == null)
			return;
		SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_DASH_FORMAT);
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	}

}