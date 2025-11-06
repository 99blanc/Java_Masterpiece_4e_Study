package ch7n2;

import java.util.Vector;

class Point {
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}

public class PointVectorEx {
	public static void main(String[] args) {		
		Vector<Point> v = new Vector<Point>(); // Point 객체를 요소로 다루는 벡터를 생성한다.
		
		// 3개의 Point 객체들을 삽입한다.
		v.add(new Point(2, 3));
		v.add(new Point(-5, 20));
		v.add(new Point(30, -8));
		
		v.remove(1); // 인덱스 1의 Point(-5, 20) 객체를 삭제한다.
		
		for (int i = 0; i < v.size(); ++i) {
			Point p = v.get(i); // 벡터의 i 번째 Point 객체를 얻어낸다.
			System.out.println(p); // p.toString() 메소드를 이용하여 p 객체를 출력한다.
		}
	}
}
