/**
 * <br>项目名: csvo
 * <br>文件名: PolyConstructors.java
 * <br>Copyright 2016 北京壹平台科技有限公司
 */
package com.id.test;

/** 
 * <br>类 名: PolyConstructors 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年3月8日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class PolyConstructors {
	public static void main(String[] args) {
		new RoundGlyph(5);
	}
	
}

class Glyph {
	void draw() {
		System.out.println("Glyph draw()");
	}
	
	Glyph() {
		System.out.println("Glyph() before draw()");
		draw();
		System.out.println("Glyph() after draw()");
	}
}

class RoundGlyph extends Glyph {
	private int radius = 1;
	private static int radiuss = 2;
	RoundGlyph(int i) {
		radius = i;
		System.out.println("RoundGlyph.RoundGlyph(),radius=" + radius);
	}
	
	void draw() {
		System.out.println("RoundGlyph.draw(), radius=" + radius);
		System.out.println("RoundGlyph.draw(), radiuss=" + radiuss);
	}
}
