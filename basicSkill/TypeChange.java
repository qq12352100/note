package testNet;

public class TypeChange {
	public static void main(String[] args) {
		java.sql.Date day;
		day = TypeChange.stringToDate("2003-11-3");
		String strday = TypeChange.dateToString(day);
		System.out.println(strday);
	}

	// string --> int
	public static int stringToInt(String intstr) {
		Integer integer;
		integer = Integer.valueOf(intstr);
		return integer.intValue();
	}

	// int --> string
	public static String intToString(int value) {
		Integer integer = new Integer(value);
		return integer.toString();
	}

	// string --> float
	public static float stringToFloat(String floatstr) {
		Float floatee;
		floatee = Float.valueOf(floatstr);
		return floatee.floatValue();
	}

	// float --> string
	public static String floatToString(float value) {
		Float floatee = new Float(value);
		return floatee.toString();
	}

	// string --> sqlDate
	public static java.sql.Date stringToDate(String dateStr) {
		return java.sql.Date.valueOf(dateStr);
	}

	// sqlDate --> string
	public static String dateToString(java.sql.Date datee) {
		return datee.toString();
	}

}
