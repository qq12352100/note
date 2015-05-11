import java.util.ArrayList;
import java.util.List;

/**
 * �������װ5��ƻ����һ��������ţ�һ���������á�ƻ�����ޡ�
 * 
 * @author Administrator
 */
public class ProductAndCostom {
	public static void main(String[] args) {
		// ������Դ
		Production pro = new Production();
		Custom custom = new Custom(pro);
		Producer producer = new Producer(pro);
		new Thread(producer).start();
		new Thread(custom).start();
	}
}

// ��Դ
class Production {
	private String src;
	private List<String> box = new ArrayList<String>();// ����
	private boolean flag1 = false;// ������ʼ
	private boolean flag2 = true;// ���ѵȴ�

	public synchronized void product(String src) throws Exception {
		if (flag1) {// true �����ȴ�
			this.wait();
		}
		// ��ʼ����
		Thread.sleep(300);
		// �������
		this.src = src;
		// ��������
		box.add(src);
		System.out.println("������----------->" + src);
		System.out.println("��������" + box.size() + "��ƻ��");
		// ��ʼ����
		this.flag2 = false;
		// ֪ͨ����
		this.notify();
		if (box.size() >= 5) {
			// ֹͣ����
			this.flag1 = true;
		}
	}

	public synchronized void custom() throws Exception {
		if (flag2) {// true ���ѵȴ�
			this.wait();
		}
		// ��ʼ����
		Thread.sleep(200);
		src = box.get(0);
		System.out.println("������--->" + src);
		box.remove(0);
		// �������
		this.flag1 = false;
		// ֪ͨ����
		this.notifyAll();
		if (box.size() <= 0) {
			// ֹͣ����
			this.flag2 = true;
		}
	}
}

// ����
class Producer implements Runnable {
	Production p;

	public Producer(Production p) {
		this.p = p;
	}

	public void run() {
		int i = 1;
		while (true) {
			try {
				p.product("��" + i + "��ƻ��");
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

// ����
class Custom implements Runnable {
	Production p;

	public Custom(Production p) {
		this.p = p;
	}

	public void run() {
		while (true) {
			try {
				p.custom();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}