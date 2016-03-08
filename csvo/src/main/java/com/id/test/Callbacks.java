/**
 * <br>项目名: csvo
 * <br>文件名: Callbacks.java
 * <br>Copyright 2016
 */
package com.id.test;

/** 
 * <br>类 名: Callbacks 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月8日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class Callbacks {
	public static void main(String[] args) {
		Callee1 callee1 = new Callee1();
		Callee2 callee2 = new Callee2();
		
		MyIncrement.f(callee2);
		
		Caller caller1 = new Caller(callee1);
		Caller caller2 = new Caller(callee2.getCallbackReference());
		
		caller1.go();
		caller1.go();
		
		caller2.go();
		caller2.go();
	}
}

interface Incrementable {
	void increment();
}

class Callee1 implements Incrementable {
	
	private int i = 0;

	/* (non-Javadoc)
	 * @see com.id.test.Incrementable#increment()
	 */
	@Override
	public void increment() {
		// TODO Auto-generated method stub
		i++;
		System.out.println(i);
	}
	
}

class MyIncrement {
	public void increment() {
		System.out.println("other operation");
	}
	
	static void f(MyIncrement mi) {
		mi.increment();
	}
}

class Callee2 extends MyIncrement {
	private int i = 0;
	
	public void increment() {
		super.increment();
		i++;
		System.out.println(i);
	}
	
	private class Closure implements Incrementable {

		/* (non-Javadoc)
		 * @see com.id.test.Incrementable#increment()
		 */
		@Override
		public void increment() {
			Callee2.this.increment();
		}
		
	}
	
	Incrementable getCallbackReference() {
		return new Closure();
	}
}

class Caller {
	private Incrementable callerReference;
	Caller(Incrementable cbh) {
		this.callerReference = cbh;
	}
	
	void go() {
		callerReference.increment();
	}
}
