package ch6n7;

public class StringEx {
	public static void main(String[] args) {
		String a = new String(" C#");
		String b = new String(",C++ ");
		
		System.out.println(a + "의 길이는 " + a.length()); // 문자열의 길이(문자 개수)를 반환한다.
		System.out.println(a.contains("#")); // 문자열의 포함 관계를 반환한다.
		
		a = a.concat(b); // 문자열을 연결한다.
		System.out.println(a);
		
		a = a.trim(); // 문자열 앞뒤의 공백을 제거한다.
		System.out.println(a);
		
		a = a.replace("C#", "Java"); // 문자열을 대치한다.
		System.out.println(a);
		String s[] = a.split(",");
		
		for (int i = 0; i < s.length; ++i) {
			System.out.println("분리된 문자열" + i + ": " + s[i]);
		}
		
		a.substring(5); // 인덱스 5부터 끝까지 서브 문자열을 반환한다.
		System.out.println(a);
		
		char c = a.charAt(2); // 인덱스 2의 문자를 반환한다.
		System.out.println(c);
	}
}
