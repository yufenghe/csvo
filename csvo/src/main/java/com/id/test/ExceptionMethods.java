/**
 * <br>项目名: csvo
 * <br>文件名: ExceptionMethods.java
 * <br>Copyright 2016
 */
package com.id.test;


/** 
 * <br>类 名: ExceptionMethods 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月9日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class ExceptionMethods {
	public static void main(String[] args) {
		try {
			throw new Exception("my exception");
		} catch(Exception e) {
			System.out.println("Caught Exception:");
			System.out.println("getMessage():" + e.getMessage());
			System.out.println("getLocalizedMessage():" + e.getLocalizedMessage());
			System.out.println("toString():" + e);
			System.out.println("printStackTrace:");
			e.printStackTrace();
			for(StackTraceElement ste : e.getStackTrace()) {
				System.out.println(ste.getLineNumber() + "------" + ste.getMethodName());
				System.out.println(ste.getLineNumber() + "------" + ste.getClassName());
				System.out.println(ste.getLineNumber() + "------" + ste.getFileName());
			}
		}
	}
}
