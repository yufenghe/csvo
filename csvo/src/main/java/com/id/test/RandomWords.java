/**
 * <br>项目名: csvo
 * <br>文件名: RandomWords.java
 * <br>Copyright 2016 
 */
package com.id.test;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/** 
 * <br>类 名: RandomWords 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月8日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class RandomWords implements Readable {
	private static Random rand = new Random(47);
	private static final char[] capitals = "ABCDEFGHIGKLMNOPQRSTUVWXYZ".toCharArray();
	private static final char[] lowers = "abcdefghigklmnopqrstuvwxyz".toCharArray();
	private static final char[] vowels = "aeiou".toCharArray();
	private int count;
	
	public RandomWords(int count) {
		this.count = count;
	}

	/* (non-Javadoc)
	 * @see java.lang.Readable#read(java.nio.CharBuffer)
	 */
	@Override
	public int read(CharBuffer cb) throws IOException {
		if(count == 0) return -1;
		cb.append(capitals[rand.nextInt(capitals.length)]);
		for(int i=0; i<4; i++) {
			cb.append(vowels[rand.nextInt(vowels.length)]);
			cb.append(lowers[rand.nextInt(lowers.length)]);
		}
		
		cb.append(" ");
//		cb.flip();
		if(cb.length() == cb.capacity()) {
			cb.clear();
		}
		return cb.length();
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(new RandomWords(10));
		while(scanner.hasNext()) {
			System.out.println(scanner.next());
		}
	}

}
