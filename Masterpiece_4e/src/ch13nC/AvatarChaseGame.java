package ch13nC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AvatarChaseGame extends JFrame {

    private GamePanel gamePanel;

    public AvatarChaseGame() {
        setTitle("아바타 추격 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500); // 패널 크기 설정
        setLocationRelativeTo(null); // 중앙 배치

        gamePanel = new GamePanel();
        setContentPane(gamePanel);

        // GamePanel 객체가 키 이벤트를 받을 수 있도록 포커스를 설정한다.
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        // 괴물 스레드를 시작한다.
        MonsterThread monsterThread = new MonsterThread(gamePanel);
        monsterThread.start();

        setVisible(true);
    }

    /**
     * 게임 화면을 표시하고 아바타 이동을 처리하는 패널
     **/
    class GamePanel extends JPanel implements KeyListener {
        private final JLabel avatar; // 아바타
        private final JLabel monster; // 괴물
        private final int AVATAR_MOVE_STEP = 10;
        private final int MONSTER_SIZE = 15; // 아바타/괴물 크기

        public GamePanel() {
            // 배치관리자 삭제하여 자유로운 위치를 설정한다.
            setLayout(null);
            setBackground(Color.BLACK);
            addKeyListener(this);

            // 아바타 설정: 15x15 크기, "@" 문자열
            avatar = new JLabel("@");
            avatar.setFont(new Font("Monospaced", Font.BOLD, MONSTER_SIZE));
            avatar.setForeground(Color.GREEN);
            avatar.setHorizontalAlignment(SwingConstants.CENTER);
            avatar.setVerticalAlignment(SwingConstants.CENTER);
            avatar.setBounds(100, 100, MONSTER_SIZE, MONSTER_SIZE); // 초기 위치 및 크기 설정
            add(avatar);

            // 괴물 설정: 15x15 크기, "M" 문자열
            monster = new JLabel("M");
            monster.setFont(new Font("Monospaced", Font.BOLD, MONSTER_SIZE));
            monster.setForeground(Color.RED);
            monster.setHorizontalAlignment(SwingConstants.CENTER);
            monster.setVerticalAlignment(SwingConstants.CENTER);
            monster.setBounds(300, 300, MONSTER_SIZE, MONSTER_SIZE); // 초기 위치 및 크기 설정
            add(monster);
        }

        // 아바타와 괴물의 JLabel 인스턴스를 외부에서 접근할 수 있도록 Getter 제공
        public JLabel getAvatar() {
            return avatar;
        }

        public JLabel getMonster() {
            return monster;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // 'q' 키 입력 시 프로그램 종료
            if (e.getKeyChar() == 'q') {
                System.out.println("게임 종료: 'q' 키 입력");
                System.exit(0);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int newX = avatar.getX();
            int newY = avatar.getY();
            int keyCode = e.getKeyCode();

            // 상/하/좌/우 키를 이용한 아바타 이동 (10 픽셀씩)
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    newY -= AVATAR_MOVE_STEP;
                    break;
                case KeyEvent.VK_DOWN:
                    newY += AVATAR_MOVE_STEP;
                    break;
                case KeyEvent.VK_LEFT:
                    newX -= AVATAR_MOVE_STEP;
                    break;
                case KeyEvent.VK_RIGHT:
                    newX += AVATAR_MOVE_STEP;
                    break;
            }

            // 패널 경계를 벗어나지 않도록 조정한다.
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int compSize = avatar.getWidth();

            newX = Math.max(0, Math.min(newX, panelWidth - compSize));
            newY = Math.max(0, Math.min(newY, panelHeight - compSize));

            // 아바타 위치 업데이트
            avatar.setLocation(newX, newY);

            // 움직임 후 화면 다시 그리기 요청
            repaint();
        }

        @Override
		public void keyReleased(KeyEvent e) {
			// 사용하지 않는 메소드이다.
			
		}
        
        // --- 충돌 감지 로직 ---
        public boolean checkCollision() {
            // 두 컴포넌트의 경계를 가져온다.
            Rectangle avatarBounds = avatar.getBounds();
            Rectangle monsterBounds = monster.getBounds();

            // 충돌 여부를 확인한다.
            return avatarBounds.intersects(monsterBounds);
        }
    }

    /**
     * 괴물의 자동 추적 이동을 담당하는 스레드
     **/
    class MonsterThread extends Thread {
        private final GamePanel panel;
        private final JLabel avatar;
        private final JLabel monster;
        private final int MONSTER_MOVE_STEP = 5; // 5 픽셀씩 이동한다.
        private final int DELAY_MS = 200; // 200ms 마다 이동한다.

        public MonsterThread(GamePanel panel) {
            this.panel = panel;
            this.avatar = panel.getAvatar();
            this.monster = panel.getMonster();
            setDaemon(true); // 프로그램 종료 시 자동으로 스레드를 종료한다.
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(DELAY_MS); // 200ms 만큼 대기한다.

                    int avatarX = avatar.getX();
                    int avatarY = avatar.getY();
                    int monsterX = monster.getX();
                    int monsterY = monster.getY();

                    int newMonsterX = monsterX;
                    int newMonsterY = monsterY;

                    // X축 추적
                    if (avatarX < monsterX) {
                        // 아바타가 괴물의 왼쪽에 있다. (괴물 왼쪽 이동)
                        newMonsterX -= MONSTER_MOVE_STEP;
                    } else if (avatarX > monsterX) {
                        // 아바타가 괴물의 오른쪽에 있다. (괴물 오른쪽 이동)
                        newMonsterX += MONSTER_MOVE_STEP;
                    }

                    // Y축 추적
                    if (avatarY < monsterY) {
                        // 아바타가 괴물의 위쪽에 있다. (괴물 위쪽 이동)
                        newMonsterY -= MONSTER_MOVE_STEP;
                    } else if (avatarY > monsterY) {
                        // 아바타가 괴물의 아래쪽에 있다. (괴물 아래쪽 이동)
                        newMonsterY += MONSTER_MOVE_STEP;
                    }

                    // 괴물 위치 업데이트
                    monster.setLocation(newMonsterX, newMonsterY);

                    // 위치 변경 후 GamePanel 객체를 다시 그린다.
                    // monster.getParent().repaint() 또는 panel.repaint() 메소드를 호출한다.
                    panel.repaint();

                    // 충돌 감지
                    if (panel.checkCollision()) {
                        monster.setForeground(Color.YELLOW); // 잡혔을 때 색상을 변경한다.
                    } else {
                        monster.setForeground(Color.RED);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        // Swing GUI 작업은 이벤트 디스패치 스레드(EDT)에서 실행하는 것이 안전하다.
        SwingUtilities.invokeLater(AvatarChaseGame::new);
    }
}