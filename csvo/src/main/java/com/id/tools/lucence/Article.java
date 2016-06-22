/**
 * <br>项目名: csvo
 * <br>文件名: Article.java
 * <br>Copyright 2016
 */
package com.id.tools.lucence;


/**
 * <br>
 * 类 名: Article <br>
 * 描 述: 描述类完成的主要功能 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年6月21日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class Article {
	
	private String md;
	
	private String fileName;
	
	private String content;
	
	private String path;

	public String getMd() {
		return md;
	}

	public String getFileName() {
		return fileName;
	}

	public String getContent() {
		return content;
	}

	public String getPath() {
		return path;
	}

	public void setMd(String md) {
		this.md = md;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
