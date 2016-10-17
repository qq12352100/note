package testNet;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;

public class Test01 {
	public static void main(String[] args) throws Exception {
		Properties props = System.getProperties();
		System.out.println("用户的账户名称：" + props.getProperty("user.name"));
		System.out.println("用户的主目录：" + props.getProperty("user.home"));
		System.out.println("用户的当前工作目录：" + props.getProperty("user.dir"));
		System.out.println("操作系统的名称：" + props.getProperty("os.name"));
		System.out.println("操作系统的构架：" + props.getProperty("os.arch"));
		System.out.println("操作系统的版本：" + props.getProperty("os.version"));

		System.out.println("本机IP和主机名：" + InetAddress.getLocalHost());
		System.out.println("IP地址：" + InetAddress.getLocalHost().getHostAddress());
		System.out.println("主机名：" + InetAddress.getLocalHost().getHostName());
		System.out.println("域名和IP：" + InetAddress.getByName("www.baidu.com"));
		System.out.println("得到IP：" + InetAddress.getByName("61.135.169.125"));
		// jumpHostsXml();
		//隐藏文件windos
		String sets = "attrib +H \"" + file.getAbsolutePath() + "\"";
		Runtime.getRuntime().exec(sets);// 将日志文件隐藏
	}

	/**
	 * 跳过hosts文件拿真实IP
	 */
	public static void jumpHostsXml() {
		StringBuilder IFCONFIG = new StringBuilder();
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
						IFCONFIG.append(inetAddress.getHostAddress().toString() + "\n");
					}

				}
			}
		} catch (SocketException ex) {
		}
		System.out.println(IFCONFIG);
	}
}
