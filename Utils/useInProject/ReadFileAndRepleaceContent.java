package Test.T;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class ReadFileAndRepleaceContent {
	public static void main(String[] args) {
		File file = new File("E:/a");// 目录
		readFile(file);
	}

	/** 读取文件夹 */
	public static void readFile(File file) {
		try {
			if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(file + "\\" + filelist[i]);
					if (readfile.isDirectory()) {
						readFile(readfile);
					} else {
						String absolutepath = readfile.getAbsolutePath();// 文件的绝对路径+后缀名
						if (absolutepath.endsWith(".txt")) {// 替换文件类型
							replaceContentToFile(absolutepath);
							System.out.println("替换完毕：" + absolutepath);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 替换内容 */
	public static void replaceContentToFile(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			StringBuilder content = new StringBuilder();

			while (br.ready() != false) {
				String line = br.readLine();
				content.append(line.replaceAll("ssa", "ss2"));// 替换的内容
				content.append("\r\n");
			}
			br.close();
			FileOutputStream fs = new FileOutputStream(path);
			fs.write(content.toString().getBytes());
			fs.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
