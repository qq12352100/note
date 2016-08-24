package Test.design;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 序列化与反序列化 */
public class TestSerializable {
	public static void main(String[] args) throws Exception {
		write("E:/a/10.txt"); // 序列化
		read("E:/a/10.txt"); // 反序列化
	}

	public static void write(String destPath) throws Exception {
		Employee obj = new Employee("bkk", 100);

		File dest = new File(destPath);

		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dest)));

		oos.writeObject(obj);

		oos.flush();
		oos.close();
	}

	public static void read(String srcPath) throws Exception {
		File src = new File(srcPath);

		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(src)));

		Object obj = ois.readObject();

		if (obj instanceof Employee) {
			Employee emp = (Employee) obj;
			System.out.println(emp.getName());
			System.out.println(emp.getSalary());
		}

		ois.close();
	}
}

class Employee implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	int salary;

	public Employee(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}
