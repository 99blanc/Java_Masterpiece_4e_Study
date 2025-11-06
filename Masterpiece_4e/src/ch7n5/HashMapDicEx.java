package ch7n5;

import java.util.HashMap;
import java.util.Scanner;

public class HashMapDicEx {
	public static void main(String[] args) {
		HashMap<String, String> dic = new HashMap<String, String>(); // 해시맵을 생성한다.
		dic.put("baby", "아기");
		dic.put("love", "사랑");
		dic.put("apple", "사과");
		
		// 사용자로부터 영어 단어를 입력받고 한글 단어를 검색한다.
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("찾고 싶은 단어는?");
			String eng = scanner.next();
			if (eng.equals("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			
			// eng 키의 kor 값을 검색한다.
			String kor = dic.get(eng);
			if (kor == null) {
				System.out.println(eng + "은(는) 없는 단어입니다.");
			}
			else {
				System.out.println(kor);
			}
		}
		
		scanner.close();
	}
}
