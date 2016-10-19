public class TestEnCodeTime {
	public static void main(String[] args) {
		System.out.println(unCode(enCode(System.currentTimeMillis())));
	}

	public static long unCode(String str) {
		System.out.println("密文:" + str);
		String[] numArr = str.split("[a-z]");
		byte[] wb = str.replaceAll("[0-9]", "").getBytes();
		for (int i = 0; i < numArr.length; i++) {
			if (Long.parseLong(numArr[numArr.length - 1 - i]) * 2 != wb[i])// 验证
				return 0;
		}
		for (int i = 0; i < wb.length; i++) {
			wb[i] = (byte) ((wb[i] - 8) / 2);
		}
		return Long.parseLong(new String(wb)) / 8;
	}

	/** 传入时间 ,扩大8倍,获取每个数的二进制码乘以2加8变成字母二进制码,字母正序数字逆序组合成密文 */
	public static String enCode(long longNum) {
		System.out.println("原文:" + longNum);
		String nowStr = longNum * 8 + "";// 时间扩大
		byte[] nowByte = nowStr.getBytes();
		int num = 0 ;
		for (int i = 0; i < nowByte.length; i++) {
			nowByte[i] = (byte) (nowByte[i] * 2 + 8);// 单位数扩大两倍加1边字母
			num+=nowByte[i];
		}
		System.out.println(num);
		String[] wordArr = new String(nowByte).split("");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nowByte.length; i++) {
			sb.append(nowByte[nowByte.length - 1 - i] / 2);
			sb.append(wordArr[i]);
		}
		return sb.toString();
	}

}
