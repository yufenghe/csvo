/**
 * <br>项目名: csvo
 * <br>文件名: GreenHouseController.java
 * <br>Copyright 2016
 */
package com.id.test.event;

/** 
 * <br>类 名: GreenHouseController 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月9日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class GreenHouseController {
	public static void main(String[] args) {
		GreenHouseControls gc = new GreenHouseControls();
		
		Event[] eventList = {
				gc.new LightOn(0),
				gc.new LightOff(200),
				gc.new WaterOn(400),
				gc.new WaterOff(600)
		};
		
		gc.addEvent(gc.new Restart(800, eventList));
		
		if(args.length == 1) {
			gc.addEvent(new GreenHouseControls.Terminate(new Integer(args[0])));
		}
		
		gc.run();
	}
}
