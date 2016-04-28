/**
 * <br>项目名: csvo
 * <br>文件名: GreenHouseControls.java
 * <br>Copyright 2016
 */
package com.id.test.event;

/** 
 * <br>类 名: GreenHouseControls 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月9日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class GreenHouseControls extends EventController {

	private boolean light = false;
	
	public class LightOn extends Event {

		/**
		 * @param delayTime
		 */
		public LightOn(long delayTime) {
			super(delayTime);
		}

		/* (non-Javadoc)
		 * @see com.id.test.event.Event#action()
		 */
		@Override
		public void action() {
			light = true;
		}
		
		public String toString() {
			return "Light is on";
		}
	}
	
	public class LightOff extends Event {

		/**
		 * @param delayTime
		 */
		public LightOff(long delayTime) {
			super(delayTime);
		}

		/* (non-Javadoc)
		 * @see com.id.test.event.Event#action()
		 */
		@Override
		public void action() {
			light = false;
		}
		
		public String toString() {
			return "Light is off";
		}
	}
	
	private boolean water = false;
	
	public class WaterOn extends Event {

		/**
		 * @param delayTime
		 */
		public WaterOn(long delayTime) {
			super(delayTime);
		}

		/* (non-Javadoc)
		 * @see com.id.test.event.Event#action()
		 */
		@Override
		public void action() {
			water = true;
		}
		
		public String toString() {
			return "Water is on";
		}
	}
	
	public class WaterOff extends Event {
		
		/**
		 * @param delayTime
		 */
		public WaterOff(long delayTime) {
			super(delayTime);
		}
		
		/* (non-Javadoc)
		 * @see com.id.test.event.Event#action()
		 */
		@Override
		public void action() {
			water = false;
		}
		
		public String toString() {
			return "Water is off";
		}
	}
	
	public class Restart extends Event {
		
		private Event[] eventList;

		/**
		 * @param delayTime
		 */
		public Restart(long delayTime, Event[] eventList) {
			super(delayTime);
			this.eventList = eventList;
			for(Event e : eventList) {
				addEvent(e);
			}
		}

		/* (non-Javadoc)
		 * @see com.id.test.event.Event#action()
		 */
		@Override
		public void action() {
			for(Event e : eventList) {
				e.start();
				addEvent(e);
			}
			
			start();
			addEvent(this);
		}
		
		public String toString() {
			return "ReStarting system";
		}
		
	}
	
	public static class Terminate extends Event {

		/**
		 * @param delayTime
		 */
		public Terminate(long delayTime) {
			super(delayTime);
		}

		/* (non-Javadoc)
		 * @see com.id.test.event.Event#action()
		 */
		@Override
		public void action() {
			System.exit(0);
		}
		
		public String toString() {
			return "Terminating";
		}
	}
}
