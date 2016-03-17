/**
 * <br>项目名: csvo
 * <br>文件名: Protocol.java
 * <br>Copyright 2016
 */
package com.id.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * <br>
 * 类 名: Protocol <br>
 * 描 述: 描述类完成的主要功能 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年3月17日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public interface Protocol {
	/**
	 * 接收一个SocketChannel的处理
	 * 
	 * @param key
	 * @throws IOException
	 */
	void handleAccept(SelectionKey key) throws IOException;

	/**
	 * 从一个SocketChannel读取信息的处理
	 * 
	 * @param key
	 * @throws IOException
	 */
	void handleRead(SelectionKey key) throws IOException;

	/**
	 * 向一个SocketChannel写入信息的处理
	 * 
	 * @param key
	 * @throws IOException
	 */
	void handleWrite(SelectionKey key) throws IOException;
}
