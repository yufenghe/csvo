/**
 * <br>项目名: csvo
 * <br>文件名: Server.java
 * <br>Copyright 2016
 */
package com.id.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * <br>
 * 类 名: Server <br>
 * 描 述: 描述类完成的主要功能 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年3月17日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class Server {
	// 缓冲区大小
	private static final int BUFFERSIZE = 1024;

	// 超时时间，单位毫秒
	private static final int TIMEOUT = 3000;

	// 监听端口
	private static final int LISTERN_PORT = 8080;

	public Server() throws IOException {
		new ServerListernThread();
	}

	public static void main(String[] args) throws IOException {
		new Server();
//		new Thread() {  
//            @Override  
//            public void run() {  
//                try {  
//                    while (true) {  
//                        Scanner scan = new Scanner(System.in);//键盘输入数据  
//                        String string = scan.next();  
////                        client.sendMsg(string);  
//                        Protocol protocol = new SocketProtocol(BUFFERSIZE);
//                    }  
//                } catch (Exception e) {  
//                   
//                    e.printStackTrace();  
//                } finally {  
//                }  
//                super.run();  
//            }  
//        }.start();
	}

	class ServerListernThread implements Runnable {
		private Selector selector;
		
		private Protocol protocol;

		public ServerListernThread() throws IOException {
			// 创建选择器
			selector = Selector.open();
			
			protocol = new SocketProtocol(BUFFERSIZE);

			// 打开监听信道
			ServerSocketChannel listernChannel = ServerSocketChannel.open();

			// 绑定socket对象，并与本地端口绑定
			listernChannel.socket().bind(
					new InetSocketAddress(LISTERN_PORT));

			// 设置为非阻塞模式
			listernChannel.configureBlocking(false);

			// 将选择器绑定到监听信道，只有非阻塞信道才可以注册选择器，并在注册过程中指定该信道可以进行accept操作
			listernChannel.register(selector, SelectionKey.OP_ACCEPT);
			
			new Thread(this).start();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try {
				// 反复循环，等待IO
				while (true) {
					// 监听注册通道，当其中有注册的 IO，操作可以进行时，该函数返回，并将对应的 SelectionKey 加入
					// selected-key set
					if (selector.select(TIMEOUT) == 0) {
						// System.out.println("独自等待");
						continue;
					}

					// 取得迭代器.selectedKeys()中包含了每个准备好某一I/O操作的信道的SelectionKey
					// Selected-key Iterator 代表了所有通过 select() 方法监测到可以进行 IO 操作的
					// channel,
					// 这个集合可以通过 selectedKeys() 拿到
					Iterator<SelectionKey> keyIter = selector.selectedKeys()
							.iterator();
					while (keyIter.hasNext()) {
						SelectionKey key = keyIter.next();

						try {
							// 有客户端连接请求时
							if (key.isAcceptable()) {
								protocol.handleAccept(key);
							}

							// 判断是否有数据发送过来，读取客户端发送的数据
							else if (key.isReadable()) {
								protocol.handleRead(key);
							}

							// 判断是否有效，发送给客户端数据
							else if (key.isValid() && key.isWritable()) {
								protocol.handleWrite(key);
							}
						} catch (IOException e) {
							System.out.println("发生异常");
						} finally {
							keyIter.remove();
						}
					}
				}
			} catch (IOException e) {
				System.out.println("服务端发生异常");
			}

		}
	}
}
