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
	 * Scaner����
	 */
	public static void standardInput() {
		// ����Scanner���� ���ܴӿ���̨����
		Scanner input = new Scanner(System.in);
		System.out.println("����������:");
		// ����String��
		String name = input.next();
		System.out.println("������ѧ��");
		// ����int��
		int id = input.nextInt();// ʲô����next����ͽ�ʲô ע���Сд
		// ������
		System.out.println("����Ϊ:" + name + "\tѧ��Ϊ:" + id);
	}

	/**
	 * InputStream����
	 */
	public static void samples() throws IOException {
		// 1. ���ǴӼ��̶���һ������,���ص���һ���ַ���
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter a line:");
		System.out.println(stdin.readLine());
		stdin.close();

		// 2. ���Ǵ��ļ������ж�������
		// BufferedReader in = new BufferedReader(new FileReader("d:/abc.sql"));//��������
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("d:/abc.sql"), "UTF-8"));
		String s, s2 = new String();
		while ((s = in.readLine()) != null)
			s2 += s + "\n";
		System.out.println(s2);
		in.close();

		// 3. ���Ǵ�һ���ַ�������������ֽ�
		// StringReader in1 = new StringReader(s2);
		// int c;
		// while ((c = in1.read()) != -1)
		// System.out.print((char) c);

		// 4. ���ǽ�һ���ַ���д���ļ�
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
