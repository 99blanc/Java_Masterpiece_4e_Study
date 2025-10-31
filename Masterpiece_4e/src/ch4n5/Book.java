package ch4n5;

public class Book {
	String title;
	String author;
	
	void show() {
		System.out.println(title + " " + author);
	}
	
	public Book() {
		this("", "");
		System.out.print("생성자 호출");
	}
	
	public Book(String title) {
		this(title, "작자 미상");
	}
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public static void main(String[] args) {
		Book littlePrince = new Book("어린 왕자", "생텟쥐페리");
		Book loveStory = new Book("춘향전");
		Book emptyBook = new Book();
		loveStory.show();
	}
}
