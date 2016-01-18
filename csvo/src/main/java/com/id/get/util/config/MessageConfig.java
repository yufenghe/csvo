/**
 * 项目名: idget<br/>
 * 文件名: MessageConfig.java<br/>
 */
package com.id.get.util.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 类 名: MessageConfig<br/>
 * 描 述: spring加载property文件属性<br/>
 * 版 本：<br/>
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
@Component
public class MessageConfig {

	public static String BUSINESS_CODE;
	public static String ACCESS_KEY;
	public static String SYSTEM_MARK;
	
	@Value("#{message[business_code]}")
	public static void setBUSINESS_CODE(String bUSINESS_CODE) {
		BUSINESS_CODE = bUSINESS_CODE;
	}
	
	@Value("#{message[access_key]}")
	public static void setACCESS_KEY(String aCCESS_KEY) {
		ACCESS_KEY = aCCESS_KEY;
	}
	
	@Value("#{message[system_mark]}")
	public static void setSYSTEM_MARK(String sYSTEM_MARK) {
		SYSTEM_MARK = sYSTEM_MARK;
	}
}
