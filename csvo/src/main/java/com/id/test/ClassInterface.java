/**
 * <br>项目名: csvo
 * <br>文件名: ClassInterface.java
 * <br>Copyright 2016 
 */
package com.id.test;


/** 
 * <br>类 名: ClassInterface 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月8日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public interface ClassInterface {
	void read();
	
	class MyInner implements ClassInterface {

		/* (non-Javadoc)
		 * @see com.id.test.ClassInterface#read()
		 */
		@Override
		public void read() {
			System.out.println("MyInner read()");
		}
		
		public static void main(String[] args) {
			MyInner inner = new MyInner();
			inner.read();
		}
	}
}

