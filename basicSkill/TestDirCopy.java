package Test.design;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** 文件拷贝与文件夹拷贝 */
public class TestDirCopy {
	public static void main(String[] args) {
		String srcPath = "e:/a/bb";
		String destPath = "e:/a/aa";
		File src = new File(srcPath);
		File dest = new File(destPath);
		if (src.isDirectory()) {
			dest = new File(destPath, src.getName());
		}
		copyDirDetil(src, dest);
	}

	// 文件夹拷贝----------------------------------------------------------------------------
	private static void copyDirDetil(File src, File dest) {
		if (src.isFile()) {
			TestDirCopy fc = new TestDirCopy();
			fc.copyFile(src, dest);
		} else if (src.isDirectory()) {
			dest.mkdirs();
			for (File temp : src.listFiles()) {
				copyDirDetil(temp, new File(dest, temp.getName()));
			}
		}
	}

	// 文件拷贝----------------------------------------------------------------------------
	public void copyFile(File src, File dest) {
		if (!src.isFile()) {
			System.out.println("这不是文件！");
		}
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(src);
			os = new FileOutputStream(dest, true);// true是可以继续写，flase则覆盖
			byte[] flush = new byte[1024];
			int len;
			while (-1 != (len = is.read(flush))) {
				os.write(flush, 0, flush.length);
			}
			os.flush();// 记得手动flush一下
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}