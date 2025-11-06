package ch7n3;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListEx {
	public static void main(String[] args) {
		// 문자열만 삽입 가능한 ArrayList 객체 생성
		ArrayList<String> a = new ArrayList<String>();
		
		// 키보드로부터 4개의 이름을 입력 받아 ArrayList 객체에 삽입한다.
		Scanner scanner = new Scanner(System.in); // Scanner 객체 생성
		for (int i = 0; i < 4; ++i) {
			System.out.print("이름을 입력하세요>>");
			String s =  scanner.next(); // 키보드로부터 이름을 입력받는다.
			a.add(s);
		}
		
		// ArrayList 객체에 들어 있는 모든 이름을 출력한다.
		for (int i = 0; i < a.size(); ++i) {
			String name = a.get(i); // ArrayList 객체에 i 번째 문자열을 얻어온다.
			System.out.print(name + " ");
		}
		
		// 가장 긴 이름을 출력한다.
		int logestIndex = 0; // 현재 가장 긴 이름이 있는 ArrayList 객체 내의 인덱스를 저장한다.
		for (int i = 1; i < a.size(); ++i) {
			// 이름 길이를 비교한다.
			if (a.get(logestIndex).length() < a.get(i).length()) {
				logestIndex = i; // i 번째 이름이 더 긴 이름이다.
			}
		}
		
		System.out.println("\n가장 긴 이름은 : " + a.get(logestIndex));
		scanner.close();
	}
}
