/**
 * <br>项目名: csvo
 * <br>文件名: Client.java
 * <br>Copyright 2016
 */
package com.id.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * <br>
 * 类 名: Client <br>
 * 描 述: 描述类完成的主要功能 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年3月17日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class Client {
	// 信道选择器
	private Selector selector;

	// 与服务器通信的信道
	private SocketChannel socketChannel;

	// 要连接的服务器Ip地址
	private String ip;

	// 要连接的远程服务器在监听的端口
	private int port;

	public Client(String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;

		initialize();
	}

	private void initialize() throws IOException {
		// 打开监听信道并设置为非阻塞模式
		socketChannel = SocketChannel.open(new InetSocketAddress(this.ip,
				this.port));
		socketChannel.configureBlocking(false);

		// 打开并注册选择器到信道
		selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_READ);

		new ClientReadThread(selector);
	}
	
	public void sendMsg(String message) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
		socketChannel.write(buffer);
	}
	
    static boolean mFlag = true;  
    public static void main(String[] args) throws IOException {  
    	final Client client = new Client("127.0.0.1", 8080);  
        new Thread() {  
            @Override  
            public void run() {  
                try {  
                    client.sendMsg("你好!Nio!醉里挑灯看剑,梦回吹角连营");  
                    while (mFlag) {  
                        Scanner scan = new Scanner(System.in);//键盘输入数据  
                        String string = scan.next();  
                        client.sendMsg(string);  
                    }  
                } catch (IOException e) {  
                    mFlag = false;  
                    e.printStackTrace();  
                } finally {  
                    mFlag = false;  
                }  
                super.run();  
            }  
        }.start();  
    }  
}

class ClientReadThread implements Runnable {
	private Selector selector;

	public ClientReadThread(Selector selector) {
		this.selector = selector;

		new Thread(this).start();
	}

	public void run() {
		try {
			while (selector.select() > 0) {// select()方法只能使用一次，用了之后就会自动删除,每个连接到服务器的选择器都是独立的
				// 遍历每个有可用IO操作Channel对应的SelectionKey
				for (SelectionKey sk : selector.selectedKeys()) {
					// 如果该SelectionKey对应的Channel中有可读的数据
					if (sk.isReadable()) {
						// 使用NIO读取Channel中的数据
						SocketChannel sc = (SocketChannel) sk.channel();// 获取通道信息
						ByteBuffer buffer = ByteBuffer.allocate(1024);// 分配缓冲区大小
						sc.read(buffer);// 读取通道里面的数据放在缓冲区内
						buffer.flip();// 调用此方法为一系列通道写入或相对获取 操作做好准备
						// 将字节转化为为UTF-8的字符串
						String receivedString = Charset.forName("UTF-8")
								.newDecoder().decode(buffer).toString();
						// 控制台打印出来
						System.out.println("接收到来自服务器"
								+ sc.socket().getRemoteSocketAddress() + "的信息:"
								+ receivedString);
						
						// 为下一次读取作准备
						sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
					}
					
					// 删除正在处理的SelectionKey
					selector.selectedKeys().remove(sk);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
