package ch5nC;

import java.util.Scanner;

public class Bear extends GameObject {
    private Scanner scanner;
    
    public Bear(int startX, int startY, int distance) {
        super(startX, startY, distance);
        this.scanner = new Scanner(System.in);
    }

    @Override
    protected void move() {
        System.out.print("Bear의 이동 방향 (a:왼쪽, s:아래, d:위, f:오른쪽) >> ");
        String direction = scanner.next();
        
        int newX = x;
        int newY = y;

        switch (direction) {
            case "a": newX -= distance; break; // 왼쪽
            case "f": newX += distance; break; // 오른쪽
            case "d": newY -= distance; break; // 위
            case "s": newY += distance; break; // 아래
            default: 
                System.out.println("잘못된 입력입니다. 제자리에 머뭅니다.");
                return;
        }

        // 경계 검사 (10행 20열 격자판 가정: X=0~19, Y=0~9)
        if (newX >= 0 && newX < 20) {
            x = newX;
        } else {
            System.out.println("벽에 부딪혔습니다. (X 경계)");
        }
        
        if (newY >= 0 && newY < 10) {
            y = newY;
        } else {
            System.out.println("벽에 부딪혔습니다. (Y 경계)");
        }
    }

    @Override
    protected char getShape() {
        return 'B';
    }
}