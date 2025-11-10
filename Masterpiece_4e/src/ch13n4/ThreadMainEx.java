package ch13n4;

public class ThreadMainEx {
	public static void main(String[] args) {
		long id = Thread.currentThread().threadId(); // 스레드 ID를 반환한다.
		String name = Thread.currentThread().getName(); // 스레드 이름을 반환한다.
		int priority = Thread.currentThread().getPriority(); // 스레드 우선 순위 값을 반환한다.
		Thread.State s = Thread.currentThread().getState(); // 스레드 상태 값을 반환한다.
		
		System.out.println("현재 스레드 이름 = " + name);
		System.out.println("현재 스레드 ID = " + id);
		System.out.println("현재 스레드 우선 순위 값 = " + priority);
		System.out.println("현재 스레드 상태 = " + s);
	}
}
