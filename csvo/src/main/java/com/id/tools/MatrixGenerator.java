/**
 * <br>项目名: csvo
 * <br>文件名: MatrixGenerator.java
 * <br>Copyright 2016 
 */
package com.id.tools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.id.tools.zxing.MatrixToImageWriter;

/**
 * <br>
 * 类 名: MatrixGenerator <br>
 * 描 述: 描述类完成的主要功能 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年2月19日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class MatrixGenerator {
	public static void main(String[] args) throws IOException, WriterException {
//		String path = "C:\\Users\\uuzz\\Desktop";
//		String text = "你好";
//		int width = 100;
//		int height = 100;
//		String format = "png";
//		Hashtable hints = new Hashtable();
//		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//		BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
//				BarcodeFormat.QR_CODE, width, height, hints);
//		File outputFile = new File(path, "new.png");
//		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);

		 try {
		
		 String content = "http://www.itv.com";
		 String path = "C:\\Users\\Administrator\\Desktop";
		
		 MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		
		 Map hints = new HashMap();
		 hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		 BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
		 File file1 = new File(path,"myqcode.png");
		 MatrixToImageWriter.writeToFile(bitMatrix, "png", file1);
		
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
	}
}
