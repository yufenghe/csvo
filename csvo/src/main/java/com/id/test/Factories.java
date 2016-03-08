/**
 * <br>项目名: csvo
 * <br>文件名: InnerService.java
 * <br>Copyright 2016 
 */
package com.id.test;

/** 
 * <br>类 名: InnerService 
 * <br>描 述: 工厂 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月8日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class Factories {
	public static void serviceConsumer(ServiceFactory factory) {
		Service service = factory.getService();
		service.read();
		service.write();
	}
	
	public static void main(String[] args) {
		serviceConsumer(ServiceImpl1.factory);
		serviceConsumer(ServiceImpl2.factory);
	}
}

interface Service {
	public void read();
	public void write();
}

interface ServiceFactory {
	public Service getService();
}

class ServiceImpl1 implements Service {

	private ServiceImpl1() {
		
	}
	
	public static ServiceFactory factory = new ServiceFactory() {
		
		@Override
		public Service getService() {
			return new ServiceImpl1();
		}
	};

	/* (non-Javadoc)
	 * @see com.id.test.Service#read()
	 */
	@Override
	public void read() {
		System.out.println("ServiceImpl1 read()");
	}


	/* (non-Javadoc)
	 * @see com.id.test.Service#write()
	 */
	@Override
	public void write() {
		System.out.println("ServiceImpl1 write()");
	}
}

class ServiceImpl2 implements Service {
	
	private ServiceImpl2() {
		
	}

	public static ServiceFactory factory = new ServiceFactory() {
		
		@Override
		public Service getService() {
			return new ServiceImpl2();
		}
	};
	
	/* (non-Javadoc)
	 * @see com.id.test.Service#read()
	 */
	@Override
	public void read() {
		System.out.println("ServiceImpl2 read()");
	}

	/* (non-Javadoc)
	 * @see com.id.test.Service#write()
	 */
	@Override
	public void write() {
		System.out.println("ServiceImpl2 write()");
	}
	
}
