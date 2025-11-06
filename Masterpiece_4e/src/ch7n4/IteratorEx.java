package ch7n4;

import java.util.Iterator;
import java.util.Vector;

public class IteratorEx {
	public static void main(String[] args) {
		// 정수 값만 다루는 제네릭 벡터를 생성한다.
		Vector<Integer> v = new Vector<Integer>();
		v.add(5);
		v.add(4);
		v.add(-1);
		v.add(2, 100);
		
		// Iterator 인터페이스를 이용한 모든 정수를 출력한다.
		Iterator<Integer> it = v.iterator(); // Iterator 객체를 얻는다.
		while (it.hasNext()) {
			int n = it.next();
			System.out.println(n);
		}
		
		// Iterator 인터페이스를 이용하여 모든 정수를 더한다.
		int sum = 0;
		it = v.iterator();
		while (it.hasNext()) {
			int n = it.next();
			sum += n;
		}
		
		System.out.println("Vector 객체에 있는 정수 합 : " + sum);
	}
}
