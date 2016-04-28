/**
 * <br>项目名: csvo
 * <br>文件名: $Proxy2.java
 * <br>Copyright 2016 
 */
package com.id.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/** 
 * <br>类 名: $Proxy2 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月16日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class $Proxy2 extends Proxy implements SimpleProxy {

	/**
	 * @param h
	 */
	protected $Proxy2(InvocationHandler h) {
		super(h);
	}

	/** **/
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.id.test.proxy.SimpleProxy#simpleMethod1()
	 */
	@Override
	public void simpleMethod1() {

	}

	/* (non-Javadoc)
	 * @see com.id.test.proxy.SimpleProxy#simpleMethod2()
	 */
	@Override
	public void simpleMethod2() {

	}

}
