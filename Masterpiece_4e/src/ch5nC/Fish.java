package ch5nC;

import java.util.Random;

public class Fish extends GameObject {
    private Random random;

    public Fish(int startX, int startY, int distance) {
        super(startX, startY, distance);
        this.random = new Random();
    }

    @Override
    protected void move() {
        // 0부터 4까지의 난수 생성 (총 5가지 경우)
        int moveChance = random.nextInt(5); 

        // 3/5 확률로 제자리에 머뭄 (0, 1, 2)
        if (moveChance < 3) {
            return; 
        }

        // 2/5 확률로 랜덤하게 이동 (3, 4)
        int direction = random.nextInt(4); // 0:왼쪽, 1:오른쪽, 2:위, 3:아래

        int newX = x;
        int newY = y;

        switch (direction) {
            case 0: newX -= distance; break; // 왼쪽
            case 1: newX += distance; break; // 오른쪽
            case 2: newY -= distance; break; // 위
            case 3: newY += distance; break; // 아래
        }

        // 경계 검사 (10행 20열 격자판 가정: X=0~19, Y=0~9)
        if (newX >= 0 && newX < 20) {
            x = newX;
        }
        
        if (newY >= 0 && newY < 10) {
            y = newY;
        }
    }

    @Override
    protected char getShape() {
        return 'F';
    }
}