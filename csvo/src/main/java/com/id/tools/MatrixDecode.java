/**
 * <br>项目名: csvo
 * <br>文件名: MatrixDecode.java
 * <br>Copyright 2016 
 */
package com.id.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.id.tools.zxing.BufferedImageLuminanceSource;

/**
 * <br>
 * 类 名: MatrixDecode <br>
 * 描 述: 描述类完成的主要功能 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年2月19日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class MatrixDecode {
	public static void main(String[] args) {
		try {
//			QRCodeReader reader = new QRCodeReader();
			
			MultiFormatReader formatReader = new MultiFormatReader();
			String filePath = "C:\\Users\\Administrator\\Desktop\\myqcode.png";
//			String filePath = "D:\\test.png";
			File file = new File(filePath);
			BufferedImage image = ImageIO.read(file);;
//			BufferedImage cropedImage = image.getSubimage(0, 0, 400, 400);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			
			Map<DecodeHintType, Object> tmpHintsMap = new EnumMap<DecodeHintType, Object>(
			        DecodeHintType.class);
			tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
			tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
			tmpHintsMap.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
			tmpHintsMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			Result result = formatReader.decode(binaryBitmap, tmpHintsMap);

			System.out.println("result = " + result.toString());
			System.out.println("resultFormat = " + result.getBarcodeFormat());
			System.out.println("resultText = " + result.getText());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
