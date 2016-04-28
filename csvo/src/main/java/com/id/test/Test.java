/**
 * <br>项目名: csvo
 * <br>文件名: Test.java
 * <br>Copyright 2016 北京壹平台科技有限公司
 */
package com.id.test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br>
 * 类 名: Test <br>
 * 描 述: 描述类完成的主要功能 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年4月5日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class Test {
	private String name;
	private int population;
	public Test(String name, int population) {
		this.name = name;
		this.population = population;
	}
	public String getName() {
		return this.name;
	}

	public int getPopulation() {
		return this.population;
	}
	public String toString() {
		return getName() + " - " + getPopulation();
	}
	public static void main(String args[]) {
		Comparator<Test> OrderIsdn = new Comparator<Test>() {
			public int compare(Test o1, Test o2) {
				int numbera = o1.getPopulation();
				int numberb = o2.getPopulation();
				if (numberb > numbera) {
					return -1;
				} else if (numberb < numbera) {
					return 1;
				} else {
					return 0;
				}

			}

		};
		Queue<Test> priorityQueue = new PriorityQueue<Test>(11, OrderIsdn);

		Test t1 = new Test("t1", 1);
		Test t3 = new Test("t3", 3);
		Test t2 = new Test("t2", 2);
		Test t4 = new Test("t4", 0);
		priorityQueue.add(t1);
		priorityQueue.add(t3);
		priorityQueue.add(t2);
		priorityQueue.add(t4);
		for(Test test : priorityQueue) {
			System.out.println(test.getName() + "---" +  test.getPopulation());
		}
		
		System.out.println("----------------");
		while(!priorityQueue.isEmpty()) {
			System.out.println(priorityQueue.poll().toString());
			
		}
		
		
		String str = "EMPROLE_ORM_[1231231adsfasd]";
		Pattern p = Pattern.compile("/^\\w+|_|\\w+|_|\\[");
		Matcher m = p.matcher(str);
		if(m.find()) {
			System.out.println("================" + m.group());
		}
	}
}