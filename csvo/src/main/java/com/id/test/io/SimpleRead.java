/**
 * <br>项目名: csvo
 * <br>文件名: SimpleRead.java
 * <br>Copyright 2016
 */
package com.id.test.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.MatchResult;

/** 
 * <br>类 名: SimpleRead 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月10日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class SimpleRead {
	public static BufferedReader input = new BufferedReader(new StringReader("Sir Robin of Camelot\n22 1.67843"));
	public static BufferedReader br = new BufferedReader(new StringReader("Sir Robin of Camelot\n22 1.67843"));
	
	public static String threadData = "58.27.82.161@02/10/2005\n"
			+ "204.45.234.40@02/11/2005\n"
			+ "58.27.82.161@02/11/2005\n"
			+ "58.27.82.161@02/12/2005\n"
			+ "[next long section with different data format]";
	
	public static void main(String[] args) {
		String name;
		try {
			System.out.println("What is your name?");
			name = input.readLine();
			System.out.println(name);
			System.out.println("How old are you? What is your favorite double?");
			System.out.println("input:<age> <double>");
			String numbers = input.readLine();
			System.out.println(numbers);
			String[] numArr = numbers.split(" ");
			int age = Integer.parseInt(numArr[0]);
			double favorite = Double.parseDouble(numArr[1]);
			System.out.format("Hi %s.\n", name);
			System.out.format("In 5 years you will be %d.\n", age+5);
			System.out.format("My favorite double is %f.\n", favorite/2);
			
			System.out.println("-----------华丽的分割线------------");
			
			Scanner scan = new Scanner(br);
			System.out.println("What is your name?");
			name = scan.nextLine();
			System.out.println(name);
			System.out.println("How old are you? What is your favorite double?");
			System.out.println("input:<age> <double>");
			age = scan.nextInt();
			favorite = scan.nextDouble();
			System.out.format("Hi %s.\n", name);
			System.out.format("In 5 years you will be %d.\n", age+5);
			System.out.format("My favorite double is %f.\n", favorite/2);
			
			System.out.println("-----------华丽的分割线------------");
			
			Scanner scanner = new Scanner("123, 23, 234, 343, 343");
			scanner.useDelimiter("\\s*,\\s*");
			while(scanner.hasNext()) {
				System.out.println(scanner.nextInt());
			}
			
			System.out.println("-----------华丽的分割线------------");
			
			Scanner threadScan = new Scanner(threadData);
			String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";
			while(threadScan.hasNext(pattern)) {
				threadScan.next(pattern);
				MatchResult result = threadScan.match();
				String ip = result.group(1);
				String date = result.group(2);
				System.out.format("Thread on %s from %s\n", date, ip);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
