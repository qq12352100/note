
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestJsoup {
	public static void main(String[] args) {
		try {
			openURL2();
//			parseDocumentFromUrl();
			// getDoc();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void openURL2() {
		Desktop desktop = Desktop.getDesktop();
		if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
			URI uri = URI.create("http://www.baidu.com/");
			try {
				desktop.browse(uri);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void parseDocumentFromUrl() throws Exception {
		Document doc = null;
		doc = Jsoup.connect("http://bukaikai.top/")
				// .data("query", "Java")// data(key,value)是该URL要求的参数
				.userAgent("Mozilla")// userAgent制定用户使用的代理类型
				// .cookie("auth", "token")//
				.timeout(3000)// 连接超时时间
				.get();// post或者get方法
		String title = doc.title();
		System.out.println(title);
		Elements all = doc.getAllElements();
		System.out.println(all.text());

	}

	public static void getDoc() throws Exception {
		File f = new File("E://imgs");
		if (!f.exists()) {
			f.mkdirs();
		}
		Document doc = Jsoup.connect("http://www.topit.me/?p=1").cookie("is_click", "true").get();
		// 获取后缀为png和jpg的图片的元素集合
		Elements a = doc.select("div[id=content] div[class=e m]");
		Elements pngs = a.select("img[src~=(?i)\\.(png|jpe?g)]");
		Elements b = a.select("a[target=_blank]");
		for (Element e : b) {
			System.out.println(e.attr("href"));
			Document doc1 = Jsoup.connect(e.attr("href")).cookie("is_click", "true").get();
			Elements a1 = doc1.select("div[id=content] a[id=item-tip] img");
			System.out.println(a1.attr("src"));
		}
		// 遍历元素
		for (Element e : pngs) {
			String src = e.attr("src");// 获取img中的src路径http://cuisuqiang.iteye.com/blog/1726173
			downLoad(src);
		}
	}

	private static void downLoad(String src) {
		try {
			String imageName = src.substring(src.lastIndexOf("/") + 1, src.length());
			URL url = new URL(src);
			URLConnection uri = url.openConnection();
			InputStream is = uri.getInputStream();
			OutputStream os = new FileOutputStream(new File("E://imgs", imageName));
			byte[] buf = new byte[1024];
			int l = 0;
			while ((l = is.read(buf)) != -1) {
				os.write(buf, 0, l);
			}
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}