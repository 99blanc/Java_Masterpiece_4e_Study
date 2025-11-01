package ch5n1;

class Point {
	private int x, y;
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void showPoint() {
		System.out.println("(" + x + ", " + y + ")");
	}
}

class ColorPoint extends Point { // Point 클래스를 상속받은 ColorPoint 선언
	private String color;
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void showColorPrint() { // 컬러 점의 좌표 출력
		System.out.print(color);
		showPoint(); // Point 클래스의 showPoint() 호출
	}
}
