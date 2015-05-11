package testNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class TestAddr {
	/**
	 * ��ȡ��������IP��MAC��ַ
	 */
	public static void main(String[] args) {
		TestAddr.getAllLocalHostIP();
		System.out.println("LocalIP:" + TestAddr.getLocalIP());
		System.out.println("getWinLocalIP:" + TestAddr.getWinLocalIP());
		System.out.println(TestAddr.getMacAddress());
	}

	/**
	 * ��ȡ��������IP
	 */
	private static String[] getAllLocalHostIP() {
		List<String> res = new ArrayList<String>();
		Enumeration<?> netInterfaces;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				System.out.println("---Name---:" + ni.getName());
				Enumeration<InetAddress> nii = ni.getInetAddresses();
				while (nii.hasMoreElements()) {
					ip = (InetAddress) nii.nextElement();
					if (ip.getHostAddress().indexOf(":") == -1) {
						res.add(ip.getHostAddress());
						System.out.println("������ip=" + ip.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return (String[]) res.toArray(new String[0]);
	}

	public static String getLocalIP() {
		String ip = "";
		try {
			Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
			while (e1.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				System.out.println("getLocalIP--nic.getDisplayName ():" + ni.getDisplayName());
				System.out.println("getLocalIP--nic.getName ():" + ni.getName());
				if (!ni.getName().equals("eth0")) {
					continue;
				} else {
					Enumeration<?> e2 = ni.getInetAddresses();
					while (e2.hasMoreElements()) {
						InetAddress ia = (InetAddress) e2.nextElement();
						if (ia instanceof Inet6Address)
							continue;
						ip = ia.getHostAddress();
					}
					break;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return ip;
	}

	public static String getWinLocalIP() {
		String ip = "";
		try {
			Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
			while (e1.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				System.out.println("getWinLocalIP--nic.getDisplayName ():" + ni.getDisplayName());
				System.out.println("getWinLocalIP--nic.getName ():" + ni.getName());
				Enumeration<?> e2 = ni.getInetAddresses();
				while (e2.hasMoreElements()) {
					InetAddress ia = (InetAddress) e2.nextElement();
					ip = ia.getHostAddress();
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return ip;
	}

	/**
	 * ��ȡ�������������ַ
	 * 
	 * @return
	 */
	public static String getMacAddress() {
		String mac = "";
		String line = "";

		String os = System.getProperty("os.name");

		if (os != null && os.startsWith("Windows")) {
			try {
				String command = "cmd.exe /c ipconfig /all";
				Process p = Runtime.getRuntime().exec(command);

				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

				while ((line = br.readLine()) != null) {
					if (line.indexOf("Physical Address") > 0) {
						int index = line.indexOf(":") + 2;
						mac = line.substring(index);
						break;
					}
				}
				br.close();
			} catch (IOException e) {
			}
		}
		return mac;
	}
}