package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestNetWorkConnect implements Runnable {
	private static String osName;

	public static void main(String[] args) {
		Properties props = System.getProperties();
		osName = props.getProperty("os.name");
		System.out.println("操作系统：" + osName);
		TestNetWorkConnect ns = new TestNetWorkConnect();
		new Thread(ns).start();// 启动线程
	}

	// 判断网络状态
	public void isConnect() {
		Runtime runtime = Runtime.getRuntime();
		String ping;
		if (osName.indexOf("windos") > 0 || osName.indexOf("Windows") > 0) {
			ping = "ping " + "www.baidu.com";
		} else {
			ping = "ping -c 5 " + "www.baidu.com";
		}
		try {
			Process process = runtime.exec(ping);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
				// System.out.println("返回值为:"+line);
			}
			is.close();
			isr.close();
			br.close();

			if (null != sb && !sb.toString().equals("")) {
				String logString = "";
				if (sb.toString().indexOf("TTL") > 0 || sb.toString().indexOf("ttl") > 0) {
					// 网络畅通
					logString = "网络正常，时间 " + this.getCurrentTime();
					System.out.println(logString);
				} else {
					// 网络不畅通
					logString = "网络断开，时间 " + this.getCurrentTime();
					System.out.println(logString);
				}
				this.writeIntoLog(logString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得当前时间
	public String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		return time;
	}

	// 将信息写入日志文件
	public void writeIntoLog(String logString) {
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			file = new File("E:\\netWorkState.log");
			if (!file.exists()) {
				file.createNewFile();// 如果不存在该文件，则创建
			}
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			fw.append(logString + "\r\n");// 换行
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void run() {
		while (true) {
			this.isConnect();
			try {
				// 每隔3秒钟测试一次网络是否连通
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
