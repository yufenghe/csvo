/**
 * <br>项目名: csvo
 * <br>文件名: BigEgg.java
 * <br>Copyright 2016
 */
package com.id.test.event;

/** 
 * <br>类 名: BigEgg 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月9日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class BigEgg extends Egg{
	public class Yolk {
		public Yolk() {
			System.out.println("BigEgg Yolk");
		}
	}
	
	public static void main(String[] args) {
		new BigEgg();
	}
}

class Egg {
	private Yolk yolk;
	
	protected class Yolk {
		public Yolk() {
			System.out.println("Egg Yolk");
		}
	}
	
	public Egg() {
		System.out.println("new Egg()");
		yolk = new Yolk();
	}
}
