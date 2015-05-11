package testScanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Scanner;

public class Test01 {
	public static void main(String[] args) throws Exception {
		// standardInput();
		// samples();

	}

	/**
	 * Scaner输入
	 */
	public static void standardInput() {
		// 创建Scanner对象 接受从控制台输入
		Scanner input = new Scanner(System.in);
		System.out.println("请输入名字:");
		// 接受String型
		String name = input.next();
		System.out.println("请输入学号");
		// 接受int型
		int id = input.nextInt();// 什么类型next后面就接什么 注意大小写
		// 输出结果
		System.out.println("名字为:" + name + "\t学号为:" + id);
	}

	/**
	 * InputStream输入
	 */
	public static void samples() throws IOException {
		// 1. 这是从键盘读入一行数据,返回的是一个字符串
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter a line:");
		System.out.println(stdin.readLine());
		stdin.close();

		// 2. 这是从文件中逐行读入数据
		// BufferedReader in = new BufferedReader(new FileReader("d:/abc.sql"));//中文乱码
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("d:/abc.sql"), "UTF-8"));
		String s, s2 = new String();
		while ((s = in.readLine()) != null)
			s2 += s + "\n";
		System.out.println(s2);
		in.close();

		// 3. 这是从一个字符串中逐个读入字节
		// StringReader in1 = new StringReader(s2);
		// int c;
		// while ((c = in1.read()) != -1)
		// System.out.print((char) c);

		// 4. 这是将一个字符串写入文件
		try {
			BufferedReader in2 = new BufferedReader(new StringReader(s2));
			PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("d:/IODemo.out")));
			int lineCount = 1;
			while ((s = in2.readLine()) != null)
				out1.println(lineCount++ + ": " + s);
			out1.close();
			in2.close();
		} catch (EOFException e) {
			System.err.println("End of stream");
		}
	}
}
