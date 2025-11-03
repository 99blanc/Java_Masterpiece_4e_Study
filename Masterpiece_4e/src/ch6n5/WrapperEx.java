package ch6n5;

public class WrapperEx {
	public static void main(String[] args) {
		System.out.println(Character.toLowerCase('A')); // 'A'를 소문자로 변환한다.
		
		char c1 = '4', c2 = 'F';
		
		if (Character.isDigit(c1)) { // 문자 c1이 숫자이면 true를 반환한다.
			System.out.println(c1 + " 숫자");
		}
		
		if (Character.isAlphabetic(c2)) { // 문자 c2가 영문자이면 true를 반환한다.
			System.out.println(c2 + " 영문자");
		}
		
		System.out.println(Integer.parseInt("-123")); // "-123"을 10진수로 변환한다.
		System.out.println(Integer.toHexString(28)); // 정수 28을 2진수 문자열로 변환한다.
		System.out.println(Integer.toBinaryString(28)); // 28을 16진수 문자열로 변환한다.
		System.out.println(Integer.bitCount(28)); // 28에 대한 2진수의 1의 개수를 반환한다.
		
		Double d = Double.valueOf(3.14);
		System.out.println(d.toString()); // Double을 문자열 "3.14"로 변환한다.
		System.out.println(Double.parseDouble("3.14")); // 문자열을 실수 3.14로 변환한다.
		
		boolean b = (4 > 3); // b는 true로 변환된다.
		System.out.println(Boolean.toString(b)); // true를 문자열 "true"로 변환한다.
		System.out.println(Boolean.parseBoolean("false")); // 문자열을 false로 변환한다.
	}
}
