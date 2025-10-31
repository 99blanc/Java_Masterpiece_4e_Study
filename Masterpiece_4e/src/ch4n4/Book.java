package ch4n4;

public class Book {
	String title;
	String author;
	
	public Book(String t) { // 생성자 1
		title = t;
		author = "작자 미상";
	}
	
	public Book(String t, String a) { // 생성자 1 오버로딩
		title = t;
		author = a;
	}
	
	public static void main(String[] args) {
		Book littlePrince = new Book("어린 왕자", "생텍쥐페리"); // 생성자 1 호출
		Book loveStory = new Book("춘향전"); // 생성자 1 오버로딩 호출
		System.out.println(littlePrince.title + " " + littlePrince.author);
		System.out.println(loveStory.title + " " + loveStory.author);
	}
}
