package ch6n6;

public class PrimitiveVsWrapperPerformance {
    // 반복 횟수: 오버헤드를 누적시켜 차이를 명확하게 보기 위해 큰 값을 사용한다.
    private static final int ITERATIONS = 100_000_000; 

    public static void main(String[] args) {
        System.out.println("반복 횟수: " + ITERATIONS + "회\n");

        // 1. 기본 타입 성능 측정
        long startTimePrimitive = System.currentTimeMillis();
        long sumPrimitive = 0L; // long 기본 타입 변수를 선언한다.
        
        for (int i = 0; i < ITERATIONS; i++) {
            sumPrimitive += i; // 기본 타입 간의 단순 덧셈 연산을 실시한다.
        }
        
        long endTimePrimitive = System.currentTimeMillis();
        long durationPrimitive = endTimePrimitive - startTimePrimitive;

        System.out.println("--- 기본 타입 (long) 연산 ---");
        System.out.println("최종 합계 (더미): " + sumPrimitive);
        System.out.println("소요 시간: " + durationPrimitive + " ms");
        
        System.out.println("\n----------------------------\n");
        
        // 2. 래퍼 클래스 성능 측정(자동 박싱과 언박싱 발생)
        long startTimeWrapper = System.currentTimeMillis();
        Long sumWrapper = 0L; // Long 래퍼 클래스 객체
        
        for (int i = 0; i < ITERATIONS; i++) {
            // 이 줄에서 자동 언박싱과 자동 박싱이 연속적으로 발생한다.
            // 1) sumWrapper (Long 객체)가 long (기본 타입)으로 언박싱된다.
            // 2) 언박싱된 값에 i(기본 타입)가 더해진다.
            // 3) 결과값(기본 타입)이 다시 sumWrapper(Long 객체)로 박싱되어 저장된다.
            sumWrapper += i; 
        }
        
        long endTimeWrapper = System.currentTimeMillis();
        long durationWrapper = endTimeWrapper - startTimeWrapper;

        System.out.println("--- 래퍼 클래스 (Long) 연산 ---");
        System.out.println("최종 합계 (더미): " + sumWrapper);
        System.out.println("소요 시간: " + durationWrapper + " ms");
        
        System.out.println("\n----------------------------");
        System.out.println("성능 차이 (배수): " + (double)durationWrapper / durationPrimitive);
    }
}
