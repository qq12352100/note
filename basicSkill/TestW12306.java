package Test.design;

/** 线程安全 */
class W12306 implements Runnable {
	private boolean flag = true;
	private int num = 10;

	public void run() {
		while (flag) {
			test6();
//			test5();
//			test4();
//			test3();//安全
//			test2();//安全
//			test1();
		}
	}

	// 线程不安全，锁定不正确，一般锁定对象
	public void test6() {
		if(num<=0){flag = false;return;}
		synchronized (this) {
			try{Thread.sleep(500);}catch(Exception e){}// 模拟网络延时，最后取到了0和-1
			System.out.println(Thread.currentThread().getName() + "抢到了" + num--);
		}

	}

	// 线程不安全，锁定资源不正确
	public void test5() {
		synchronized ((Integer) num) {
			if(num<=0){flag = false;return;}
			try{Thread.sleep(500);}catch(Exception e){}// 模拟网络延时，最后取到了0和-1
			System.out.println(Thread.currentThread().getName() + "抢到了" + num--);
		}

	}

	// 线程不安全，锁定范围过小
	// a b c
	public void test4() {
		synchronized (this) {
			// b
			if(num<=0){flag = false;return;}
		}
		// b
		try{Thread.sleep(500);}catch(Exception e){}// 模拟网络延时，最后取到了0和-1
		System.out.println(Thread.currentThread().getName() + "抢到了" + num--);
		// a-->1

	}

	// 线程安全，锁定方法块
	public void test3() {
		synchronized (this) {
			if(num<=0){flag = false;return;}
			try{Thread.sleep(500);}catch(Exception e){}// 模拟网络延时
			System.out.println(Thread.currentThread().getName() + "抢到了" + num--);
		}

	}

	// 线程安全，锁定方法
	public synchronized void test2() {
		if(num<=0){flag = false;return;}
		try{Thread.sleep(500);}catch(Exception e){}// 模拟网络延时
		System.out.println(Thread.currentThread().getName() + "抢到了" + num--);

	}

	// 线程不安全
	public void test1() {
		if(num<=0){flag = false;return;}
		try{Thread.sleep(500);}catch(Exception e){}// 模拟网络延时，最后取到了0和-1
		System.out.println(Thread.currentThread().getName() + "抢到了" + num--);

	}
}

public class TestW12306 {
	public static void main(String[] args) {
		W12306 w = new W12306();

		Thread t1 = new Thread(w, "甲");
		Thread t2 = new Thread(w, "已");
		Thread t3 = new Thread(w, "丁");

		t2.start();
		t1.start();
		t3.start();
	}
}