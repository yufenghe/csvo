/**
 * <br>项目名: csvo
 * <br>文件名: SocketProtocol.java
 * <br>Copyright 2016
 */
package com.id.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Scanner;

/** 
 * <br>类 名: SocketProtocol 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月17日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class SocketProtocol implements Protocol {
	
	private int bufferSize;  
	  
    public SocketProtocol(int bufferSize) {  
        this.bufferSize = bufferSize;  
    }  

	/* (non-Javadoc)
	 * @see com.id.nio.Protocol#handleAccept(java.nio.channels.SelectionKey)
	 */
	@Override
	public void handleAccept(SelectionKey key) throws IOException {
		// 返回创建此键的通道，接受客户端建立连接的请求，并返回 SocketChannel 对象 
		SocketChannel clientChannel = ((ServerSocketChannel)key.channel()).accept();
		
		// 非阻塞式
		clientChannel.configureBlocking(false);
		
		// 注册到selector
		clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
	}

	/* (non-Javadoc)
	 * @see com.id.nio.Protocol#handleRead(java.nio.channels.SelectionKey)
	 */
	@Override
	public void handleRead(SelectionKey key) throws IOException {
		// 获得与客户端通信的信道
		SocketChannel clientChannel = (SocketChannel) key.channel();
		
		// 获得绑定的缓冲区并清空
		ByteBuffer buffer = (ByteBuffer) key.attachment();
		buffer.clear();
		
		// 读取信息
		long byteRead = clientChannel.read(buffer);
		
		// 没有读取到内容则关闭通信信道
		if(byteRead == -1) {
			clientChannel.close();
		}
		else {
			// 将缓冲区中的数据转为待输出状态
			buffer.flip();
			
			// 将数据转化为UTF-8的数据
			String recivedString = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
			System.out.println("接收到来自" + clientChannel.socket().getRemoteSocketAddress() + "的信息：" + recivedString);
			
			String sendString = "你好，客户端.@" + new Date().toString() + "，已经收到你的信息：" + recivedString;
			
			buffer = ByteBuffer.wrap(sendString.getBytes("UTF-8"));
			clientChannel.write(buffer);
			
			// 设置为下一次读取或者写做准备
			key.interestOps(SelectionKey.OP_WRITE);
		}
	}

	/* (non-Javadoc)
	 * @see com.id.nio.Protocol#handleWrite(java.nio.channels.SelectionKey)
	 */
	@Override
	public void handleWrite(SelectionKey key) throws IOException {
		// 获得与客户端通信的信道
		SocketChannel clientChannel = (SocketChannel) key.channel();
		
		// 获得绑定的缓冲区并清空
		ByteBuffer buffer = (ByteBuffer) key.attachment();
		buffer.clear();
		
		Scanner scan = new Scanner(System.in);//键盘输入数据  
        String message = scan.next();  
        buffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
        clientChannel.write(buffer);
        
	    // 设置为下一次读取或者写做准备
	    key.interestOps(SelectionKey.OP_READ);

	}

}
