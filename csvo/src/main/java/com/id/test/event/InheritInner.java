/**
 * <br>项目名: csvo
 * <br>文件名: InheritInner.java
 * <br>Copyright 2016
 */
package com.id.test.event;

/** 
 * <br>类 名: InheritInner 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月9日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class InheritInner extends WithInner.Inner{
	InheritInner(WithInner wi) {
		wi.super();
	}
	
	public static void main(String[] args) {
		WithInner wi = new WithInner();
		InheritInner ii = new InheritInner(wi);
	}
}

class WithInner {
	class Inner {
		
	}
}