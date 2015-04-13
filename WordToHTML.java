package org.bkk.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;


public class WordToHTML {
	public static void main(String args[]) throws Exception {
		T1();
		T2();
	}
	/**
	 * 简单的word转html
	 * 需要导入poi-scratchpad-3.12-beta1-20150228.jar
	 * @throws Exception
	 */
	public static void T1() throws Exception {
		final String path = "d:/";
		// 文件路径
		final String file = "1.doc";
		InputStream input = new FileInputStream(path + file);
		HWPFDocument wordDocument = new HWPFDocument(input);
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance()//
				.newDocumentBuilder().newDocument());

		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
				File file = new File(path + suggestedName);
				try {
					OutputStream os = new FileOutputStream(file);
					os.write(content);
					os.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return path + suggestedName;
			}
		});

		wordToHtmlConverter.processDocument(wordDocument);
		Document htmlDocument = wordToHtmlConverter.getDocument();
		// 输出路径
		File htmlFile = new File(path + "1.html");
		OutputStream outStream = new FileOutputStream(htmlFile);
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(outStream);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		outStream.close();
		System.out.println("OK!");
	}
	/**
	 * 读取文档输出内容，换行也会输出
	 * 需要导入poi-3.12-beta1-20150228.jar
	 * @throws Exception
	 */
	public static void T2() throws Exception {
		FileInputStream in = new FileInputStream("D:\\1.doc");
		WordExtractor extractor = new WordExtractor(in);
		String str = extractor.getText();
		System.out.println(str);
	}
}
