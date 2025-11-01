package ch5nC;

public class FishHuntingMinigame {
    
    private static final int MAP_ROWS = 10;
    private static final int MAP_COLS = 20;
    
    private GameObject bear;
    private GameObject fish;
    
    public FishHuntingMinigame() {
        // 곰(B)과 물고기(F) 객체 생성 및 초기 위치 설정
        // (X, Y, distance) -> (열, 행, 이동거리)
        bear = new Bear(0, 0, 1);     // (0열, 0행)에서 시작
        fish = new Fish(19, 9, 1);    // (19열, 9행)에서 시작
    }

    private void drawMap() {
        // 10행 x 20열 지도 출력
        char[][] map = new char[MAP_ROWS][MAP_COLS];

        // 맵 초기화
        for (int i = 0; i < MAP_ROWS; i++) {
            for (int j = 0; j < MAP_COLS; j++) {
                map[i][j] = '-';
            }
        }

        // 객체 배치: Y는 행, X는 열
        map[bear.getY()][bear.getX()] = bear.getShape();
        map[fish.getY()][fish.getX()] = fish.getShape();

        // 맵 출력
        System.out.println("\n--- 물고기 사냥 미니 게임 (10행 x 20열) ---");
        for (int i = 0; i < MAP_ROWS; i++) {
            for (int j = 0; j < MAP_COLS; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    
    public void run() {
        System.out.println("물고기 사냥 게임을 시작합니다.");
        
        while (true) {
            drawMap();
            
            // 1. Bear 이동
            bear.move();
            
            // 2. Bear와 Fish의 충돌 검사
            if (bear.collide(fish)) {
                System.out.println("Bear가 Fish를 잡았습니다! 게임 성공!");
                drawMap();
                break;
            }
            
            // 3. Fish 이동
            fish.move();
            
            // 4. Fish 이동 후 충돌 검사 (Fish가 Bear가 있던 자리로 움직일 수도 있음)
            if (bear.collide(fish)) {
                System.out.println("Bear가 Fish를 잡았습니다! 게임 성공!");
                drawMap();
                break;
            }
        }
    }

    public static void main(String[] args) {
        FishHuntingMinigame game = new FishHuntingMinigame();
        game.run();
    }
}