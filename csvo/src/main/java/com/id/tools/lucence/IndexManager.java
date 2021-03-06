/**
 * <br>项目名: csvo
 * <br>文件名: IndexUtil.java
 * <br>Copyright 2016
 */
package com.id.tools.lucence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

/**
 * <br>
 * 类 名: IndexManager <br>
 * 描 述: 描述类完成的主要功能 <br>
 * 作 者: yufenghe <br>
 * 创 建： 2016年6月21日 <br>
 * 版 本：v1.0.0 <br>
 * <br>
 * 历 史: (版本) 作者 时间 注释
 */
public class IndexManager {
	private static String content = "";

	private static String INDEX_DIR = "D:\\Demo\\luceneIndex";
	private static String DATA_DIR = "D:\\Demo\\luceneData";
	private static Analyzer analyzer = null;
	private static Directory directory = null;
	private static IndexWriter indexWriter = null;

	private static class Singleton {
		private static IndexManager indexManager = new IndexManager();
	}

	private IndexManager() {

	}

	/**
	 * 创建索引管理器
	 * 
	 * @return 返回索引管理器对象
	 */
	public static IndexManager getManager() {
		return Singleton.indexManager;
	}
	
	/**
	 * 创建当前文件目录的索引
	 * 
	 * @param path
	 *            当前文件目录
	 * @return 是否成功
	 */
	public static boolean createIndex(String path) {
		Date date1 = new Date();
		List<File> fileList = getFileList(path);
		if (fileList == null || fileList.isEmpty()) {
			return false;
		}

		try {
			analyzer = new SmartChineseAnalyzer(Version.LUCENE_CURRENT);
			directory = FSDirectory.open(new File(INDEX_DIR));

			File indexFile = new File(INDEX_DIR);
			if (!indexFile.exists()) {
				indexFile.mkdirs();
			}
			IndexWriterConfig config = new IndexWriterConfig(
					Version.LUCENE_CURRENT, analyzer);
			indexWriter = new IndexWriter(directory, config);

			for (File file : fileList) {
				content = "";
				// 获取文件后缀
				String type = file.getName().substring(
						file.getName().lastIndexOf(".") + 1);
				if ("txt".equalsIgnoreCase(type)) {

					content += txt2String(file);

				} else if ("doc".equalsIgnoreCase(type)) {

					content += doc2String(file);

				} else if ("xls".equalsIgnoreCase(type)) {

					content += xls2String(file);

				}

				System.out.println("name :" + file.getName());
				System.out.println("path :" + file.getPath());
				// System.out.println("content :"+content);
				System.out.println();

				try {
					// analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
					// analyzer = new
					// SmartChineseAnalyzer(Version.LUCENE_CURRENT);
					// directory = FSDirectory.open(new File(INDEX_DIR));

					Document document = new Document();
					document.add(new TextField("filename", file.getName(),
							Store.YES));
					document.add(new TextField("content", content, Store.YES));
					document.add(new TextField("path", file.getPath(),
							Store.YES));
					indexWriter.addDocument(document);
					// closeWriter();
				} catch (Exception e) {
					e.printStackTrace();
				}
				content = "";
			}

			indexWriter.commit();
			closeWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		Date date2 = new Date();
		System.out.println("创建索引-----耗时：" + (date2.getTime() - date1.getTime())
				+ "ms\n");
		return true;
	}

	/**
	 * 读取txt文件的内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String txt2String(File file) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result = result + "\n" + s;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取doc文件内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String doc2String(File file) {
		String result = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			HWPFDocument doc = new HWPFDocument(fis);
			Range rang = doc.getRange();
			result += rang.text();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取xls文件内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String xls2String(File file) {
		String result = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			StringBuilder sb = new StringBuilder();
			jxl.Workbook rwb = Workbook.getWorkbook(fis);
			Sheet[] sheet = rwb.getSheets();
			for (int i = 0; i < sheet.length; i++) {
				Sheet rs = rwb.getSheet(i);
				for (int j = 0; j < rs.getRows(); j++) {
					Cell[] cells = rs.getRow(j);
					for (int k = 0; k < cells.length; k++)
						sb.append(cells[k].getContents());
				}
			}
			fis.close();
			result += sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 查找索引，返回符合条件的文件
	 * 
	 * @param text
	 *            查找的字符串
	 * @return 符合条件的文件List
	 */
	public static void searchIndex(String text) {
		Date date1 = new Date();
		try {
			directory = FSDirectory.open(new File(INDEX_DIR));
			// analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
			analyzer = new SmartChineseAnalyzer(Version.LUCENE_CURRENT);
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);

			/* 多条件查询 begin */
			// String queryStr1 = "life";// 搜索关键字
			// BooleanQuery booleanQuery = new BooleanQuery();
			// // 条件一内容中必须要有life内容
			// QueryParser queryParser = new QueryParser(VERSION, "content", new
			// StandardAnalyzer(VERSION));// 实例查询条件类
			// Query query1 = queryParser.parse(queryStr1);
			// // 条件二评分大于等于80
			// Query query2 = NumericRangeQuery.newIntRange("score", 80, null,
			// true, false);
			// booleanQuery.add(query1, BooleanClause.Occur.MUST);
			// booleanQuery.add(query2, BooleanClause.Occur.MUST);
			/* 多条件查询 end */

			QueryParser parser = new QueryParser(Version.LUCENE_CURRENT,
					"content", analyzer);
			Query query = parser.parse(text);

			ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
			// 高亮
			Formatter formatter = new SimpleHTMLFormatter("<font color='red'>",
					"</font>");// 高亮html格式
			Scorer score = new QueryScorer(query);// 检索评份
			Fragmenter fragmenter = new SimpleFragmenter(100);// 设置最大片断为100
			Highlighter highlighter = new Highlighter(formatter, score);// 高亮显示类
			highlighter.setTextFragmenter(fragmenter);// 设置格式
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				System.out.println("____________________________");

				System.out.println(hitDoc.get("filename"));
				String content = hitDoc.get("content");
				if (content != null) {
					TokenStream tokenStream = analyzer.tokenStream("content",
							new StringReader(content));
					String str = highlighter.getBestFragment(tokenStream,
							content);// 得到高亮显示后的内容
					System.out.println(str);
				}
				System.out.println(hitDoc.get("path"));
				System.out.println("____________________________");
			}
			ireader.close();
			directory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date date2 = new Date();
		System.out.println("查看索引-----耗时：" + (date2.getTime() - date1.getTime())
				+ "ms\n");
	}
	/**
	 * 过滤目录下的文件
	 * 
	 * @param dirPath
	 *            想要获取文件的目录
	 * @return 返回文件list
	 */
	public static List<File> getFileList(String dirPath) {
		File file = new File(dirPath);

		File[] files = null;
		if (file.isDirectory()) {
			files = file.listFiles();
		} else if (file.isFile()) {
			files = new File[]{file};
		}

		if (files == null)
			return null;

		List<File> fileList = new ArrayList<File>();
		for (File item : files) {
			if (isTxtFile(item.getName())) {
				fileList.add(item);
			}
		}
		return fileList;
	}
	/**
	 * 判断是否为目标文件，目前支持txt xls doc格式
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 如果是文件类型满足过滤条件，返回true；否则返回false
	 */
	public static boolean isTxtFile(String fileName) {
		if (fileName.lastIndexOf(".txt") > 0) {
			return true;
		} else if (fileName.lastIndexOf(".xls") > 0) {
			return true;
		} else if (fileName.lastIndexOf(".doc") > 0) {
			return true;
		}
		return false;
	}

	public static void closeWriter() throws Exception {
		if (indexWriter != null) {
			indexWriter.close();
		}
	}
	/**
	 * 删除文件目录下的所有文件
	 * 
	 * @param file
	 *            要删除的文件目录
	 * @return 如果成功，返回true.
	 */
	public static boolean deleteDir(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteDir(files[i]);
			}
		}
		file.delete();
		return true;
	}
	public static void main(String[] args) {
		File fileIndex = new File(INDEX_DIR);
		if (deleteDir(fileIndex)) {
			fileIndex.mkdir();
		} else {
			fileIndex.mkdir();
		}

		boolean hasIndex = createIndex(DATA_DIR);
		if (hasIndex) {
			searchIndex("常用");
		}
	}
}
