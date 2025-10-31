package ch4n3;

public class Circle {
	final double PI = 3.141592;
	int radius;
	String name;
	
	public Circle() {
		radius = 1;
		name = "";
	}
	
	public Circle(int r, String n) {
		radius = r;
		name = n;
	}
	
	public double getArea( ) {
		return PI * radius * radius;
	}
	
	public static void main(String[] args) {
		Circle pizza = new Circle(10, "자바 피자");
		double area = pizza.getArea();
		System.out.println(pizza.name + "의 면적은 " + area);
		
		Circle donut = new Circle();
		donut.name = "도넛 피자";
		area = donut.getArea();
		System.out.println(donut.name + "의 면적은 " + area);
	}
}
