package ch13n7;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

class MyLabel extends JLabel {
	private int barSize = 0; // 현재 그려져야할 바의 크기
	private int maxBarSize; // 바의 최대 크기
	
	public MyLabel(int maxBarSize) {
		this.maxBarSize = maxBarSize;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.MAGENTA);
		int width = (int)(((double)(this.getWidth())) / maxBarSize * barSize);
		
		if (width == 0) {
			return; // 크기가 0 값이기 때문에 바를 그릴 필요가 없다.
		}
		
		g.fillRect(0, 0, width, this.getHeight());
	}
	
	synchronized public void fill() {
		if (barSize == maxBarSize) {
			try  {
				wait(); // 바가 최대이면, ConsumerThread 객체에 의해 바가 줄어들 때까지 대기한다.
			} 
			catch (InterruptedException e) {
				return;
			}
		}
		
		++barSize;
		repaint(); // 바의 크기가 변했으니 다시 그린다.
		notify(); // 기다리는 ConsumerThread 스레드를 깨운다.
	}
	
	synchronized public void consume() {
		if (barSize == 0) {
			try {
				wait(); // 바의 크기가 0 값이면 바의 크기가 0 값보다 커질 때까지 대기한다.
			}
			catch (InterruptedException e) {
				return;
			}
		}
		
		--barSize;
		repaint(); // 바의 크기가 변했으니 다시 그린다.
		notify(); // 기다리는 이벤트 스레드를 깨운다.
	}
}

class ConsumerThread extends Thread {
	private MyLabel bar;
	
	public ConsumerThread(MyLabel bar) {
		this.bar = bar;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				sleep(200);
				bar.consume(); // 0.2초마다 바를 1 값 줄인다.
			}
			catch (InterruptedException e) {
				return;
			}
			
		}
	}
}

public class TabAndThreadEx extends JFrame{
	private MyLabel bar = new MyLabel(100); // 바의 최대 크기를 100 값으로 설정
	
	public TabAndThreadEx(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		bar.setBackground(Color.ORANGE);
		bar.setOpaque(true);
		bar.setLocation(20, 50);
		bar.setSize(300, 20); // 300 * 20 크기의 바
		c.add(bar);
		
		// 컨텐트팬에 키 이벤트 핸들러를 등록한다.
		c.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				bar.fill(); // 키를 누를 때마다 바가 1 값씩 증가한다.
			}
		});
		
		setSize(350, 200);
		setVisible(true);
		c.setFocusable(true);
		c.requestFocus(); // 컨텐츠팬에 키 처리권을 부여한다.
		ConsumerThread th = new ConsumerThread(bar); // 스레드를 생성한다.
		th.start(); // 스레드를 시작한다.
	}
	
	public static void main(String[] args) {
		new TabAndThreadEx("아무키나 빨리 눌러 바를 채우세요.");
	}
}
