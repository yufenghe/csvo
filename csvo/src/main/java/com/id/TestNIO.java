/**
 * <br>项目名: csvo
 * <br>文件名: Test.java
 * <br>Copyright 2016
 */
package com.id;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * <br>
 * 类 名: Test <br>
 * 描 述: 描述类完成的主要功能 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年2月26日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class TestNIO {
	public static void main(String[] args) throws IOException {
		StringBuffer buffer = testChannelIn();
		
		testChannelOut(buffer);
	}

	public static void testBuffer() {
		// 分配新的int缓冲区，参数为缓冲区容量
		// 新缓冲区的当前位置将为零，其界限(限制位置)将为其容量。它将具有一个底层实现数组，其数组偏移量将为零。
		IntBuffer buffer = IntBuffer.allocate(8);

		for (int i = 0; i < buffer.capacity(); ++i) {
			int j = 2 * (i + 1);
			// 将给定整数写入此缓冲区的当前位置，当前位置递增
			buffer.put(j);
		}

		// 重设此缓冲区，将限制设置为当前位置，然后将当前位置设置为0
		buffer.flip();

		// 查看在当前位置和限制位置之间是否有元素
		while (buffer.hasRemaining()) {
			// 读取此缓冲区当前位置的整数，然后当前位置递增
			int j = buffer.get();
			System.out.print(j + "  ");
		}
	}

	public static StringBuffer testChannelIn() throws IOException {
		StringBuffer sbuffer = new StringBuffer();
		FileInputStream fin = new FileInputStream("d:\\linux命令.txt");

		// 获取通道
		FileChannel fc = fin.getChannel();
		Charset charset = Charset.forName("UTF-8");
		// 创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		// 读取数据到缓冲区
		while (fc.read(buffer) != -1) {
			buffer.flip();
			CharBuffer cb = charset.decode(buffer);
			sbuffer.append(cb);
			System.out.print(cb);
			buffer.clear();
		}

		fin.close();
		return sbuffer;
	}
	
	public static void testChannelOut(StringBuffer sbuffer) throws IOException {
		final byte[] message = sbuffer.toString().getBytes();
		FileOutputStream fout = new FileOutputStream( "d:\\test20160226.txt" );  
        FileChannel fc = fout.getChannel();  
        ByteBuffer buffer = ByteBuffer.allocate( 1024 );  
        
        for (int i=0; i<message.length; ++i) {  
            buffer.put( message[i] );  
            if(buffer.capacity() == buffer.position()) {
            	buffer.flip();  
            	fc.write( buffer );  
            	buffer.clear();
            }
        }  
        
        if(buffer.position() > -1) {
        	buffer.flip();  
        	fc.write( buffer ); 
//        	buffer.clear();
        }
          
        fout.close();  
	}
}
