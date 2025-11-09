package ch13n1;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

class TimerThread extends Thread {
	private JLabel timerLabel; // 타이머 값이 출력되는 레이블이다.
	
	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel; // 타이머 카운트를 출력할 레이블이다.
	}
	
	// 스레드 코드로 run() 메소드가 종료하면 스레드도 종료된다.
	@Override
	public void run() {
		int n = 0; // 타이머 카운트 값이다.
		
		while (true) {
			timerLabel.setText(Integer.toString(n)); // 레이블에 카운트 값 출력
			++n;
			
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				return; // 예외가 발생하면 스레드 종료
			}
		}
	}
}

public class ThreadTimerEx extends JFrame {
	public ThreadTimerEx() {
		setTitle("Thread 클래스를 상속받은 타이머 스레드 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 타이머 값을 출력할 레이블을 생성한다.
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		c.add(timerLabel);
		TimerThread th = new TimerThread(timerLabel);
		setSize(300, 170);
		setVisible(true);
		th.start();
	}
	
	public static void main(String[] args) {
		new ThreadTimerEx();
	}
}
