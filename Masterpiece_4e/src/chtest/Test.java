package chtest;

public class Test {
	public static void main(String[] args) {
		int n[][] = {{1}, {1, 2, 3}, {1}, {1, 2, 3, 4}, {1, 2}};
		
		for (int j = 0; j < n.length; ++j) {
			System.out.print(n[j].length + " ");
		}
	}
}
