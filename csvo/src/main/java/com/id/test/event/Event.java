/**
 * <br>项目名: csvo
 * <br>文件名: Event.java
 * <br>Copyright 2016
 */
package com.id.test.event;

/** 
 * <br>类 名: Event 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月9日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public abstract class Event {
	private long eventTime;
	protected final long delayTime;
	
	public Event(long delayTime) {
		this.delayTime = delayTime;
	}
	
	public void start() {
		eventTime = System.nanoTime() + this.delayTime;
	}
	
	public boolean ready() {
		return System.nanoTime() >= eventTime;
	}
	
	public abstract void action();
}
