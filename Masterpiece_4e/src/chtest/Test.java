package chtest;

public class Test {
	public static void main(String[] args) {
		int option = 1;
		
		switch (option) {
			case 1:
				System.out.println("옵션 1");
			case 2:
				System.out.println("옵션 2");
			case 3:
				System.out.println("옵션 3");
				break;
			default:
				System.out.println("해당 없음");
		}
	}
}
