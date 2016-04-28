/**
 * <br>项目名: csvo
 * <br>文件名: EventController.java
 * <br>Copyright 2016
 */
package com.id.test.event;

import java.util.ArrayList;
import java.util.List;

/** 
 * <br>类 名: EventController 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月9日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class EventController {
	private List<Event> list = new ArrayList<Event>();
	
	public void addEvent(Event e) {
		list.add(e);
	}
	
	public void run() {
		while(list.size() > 0) {
			for(Event e : new ArrayList<Event>(list)) {
				if(e.ready()) {
					System.out.println(e);
					e.action();
					list.remove(e);
				}
			}
		}
	}
}
