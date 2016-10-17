package testNet;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;

public class Test01 {
	public static void main(String[] args) throws Exception {
		Properties props = System.getProperties();
		System.out.println("�û����˻����ƣ�" + props.getProperty("user.name"));
		System.out.println("�û�����Ŀ¼��" + props.getProperty("user.home"));
		System.out.println("�û��ĵ�ǰ����Ŀ¼��" + props.getProperty("user.dir"));
		System.out.println("����ϵͳ�����ƣ�" + props.getProperty("os.name"));
		System.out.println("����ϵͳ�Ĺ��ܣ�" + props.getProperty("os.arch"));
		System.out.println("����ϵͳ�İ汾��" + props.getProperty("os.version"));

		System.out.println("����IP����������" + InetAddress.getLocalHost());
		System.out.println("IP��ַ��" + InetAddress.getLocalHost().getHostAddress());
		System.out.println("��������" + InetAddress.getLocalHost().getHostName());
		System.out.println("������IP��" + InetAddress.getByName("www.baidu.com"));
		System.out.println("�õ�IP��" + InetAddress.getByName("61.135.169.125"));
		// jumpHostsXml();
		//�����ļ�windos
		String sets = "attrib +H \"" + file.getAbsolutePath() + "\"";
		Runtime.getRuntime().exec(sets);// ����־�ļ�����
	}

	/**
	 * ����hosts�ļ�����ʵIP
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
