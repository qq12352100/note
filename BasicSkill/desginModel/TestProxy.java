package Test.design;

/**
 * 动态代理 -实现共同的接口 -代理类持有真实类的引用
 */
public class TestProxy {
	public static void main(String[] a) {
		new WeddingCompany(new You()).marry();
	}
}

/* 接口 */
interface Marry {
	public abstract void marry();
}

/* 真实类 */
class You implements Marry {
	public void marry() {
		System.out.println("我要娶你！");
	}
}

/* 代理类 */
class WeddingCompany implements Marry {
	private You you;

	public WeddingCompany(You you) {
		this.you = you;
	}

	public void before() {
		System.out.println("布置中。。。。");
	}

	public void affter() {
		System.out.println("结束了。。。");
	}

	public void marry() {
		before();
		you.marry();
		affter();
	}
}
