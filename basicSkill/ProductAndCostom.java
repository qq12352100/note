import java.util.ArrayList;
import java.util.List;

/**
 * 箱子最多装5个苹果。一个人往里放，一个人往外拿。苹果无限。
 * 
 * @author Administrator
 */
public class ProductAndCostom {
	public static void main(String[] args) {
		// 共享资源
		Production pro = new Production();
		Custom custom = new Custom(pro);
		Producer producer = new Producer(pro);
		new Thread(producer).start();
		new Thread(custom).start();
	}
}

// 资源
class Production {
	private String src;
	private List<String> box = new ArrayList<String>();// 箱子
	private boolean flag1 = false;// 生产开始
	private boolean flag2 = true;// 消费等待

	public synchronized void product(String src) throws Exception {
		if (flag1) {// true 生产等待
			this.wait();
		}
		// 开始生产
		Thread.sleep(300);
		// 生产完毕
		this.src = src;
		// 放入箱子
		box.add(src);
		System.out.println("放入了----------->" + src);
		System.out.println("箱子里有" + box.size() + "个苹果");
		// 开始消费
		this.flag2 = false;
		// 通知消费
		this.notify();
		if (box.size() >= 5) {
			// 停止生产
			this.flag1 = true;
		}
	}

	public synchronized void custom() throws Exception {
		if (flag2) {// true 消费等待
			this.wait();
		}
		// 开始消费
		Thread.sleep(200);
		src = box.get(0);
		System.out.println("拿走了--->" + src);
		box.remove(0);
		// 消费完毕
		this.flag1 = false;
		// 通知生产
		this.notifyAll();
		if (box.size() <= 0) {
			// 停止消费
			this.flag2 = true;
		}
	}
}

// 生产
class Producer implements Runnable {
	Production p;

	public Producer(Production p) {
		this.p = p;
	}

	public void run() {
		int i = 1;
		while (true) {
			try {
				p.product("第" + i + "个苹果");
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

// 消费
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