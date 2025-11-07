package ch7nC;

import java.util.Vector;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

// 영어 단어와 한글 뜻을 저장하는 Word 클래스이다.
class Word {
    private String english;
    private String korean;

    public Word(String english, String korean) {
        this.english = english;
        this.korean = korean;
    }

    public String getEnglish() {
        return english;
    }

    public String getKorean() {
        return korean;
    }
}

public class WordQuiz {
    private Vector<Word> wordVector = new Vector<>();
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    // 퀴즈에 사용할 Word 객체들을 벡터에 삽입한다.
    private void loadWords() {
        // 예시에서 요구한 17개 단어보다 많은 단어를 삽입하여 다양한 오답 보기를 확보한다.
        wordVector.add(new Word("painting", "그림"));
        wordVector.add(new Word("bear", "곰"));
        wordVector.add(new Word("eye", "눈"));
        wordVector.add(new Word("picture", "사진"));
        wordVector.add(new Word("human", "인간"));
        wordVector.add(new Word("society", "사회"));
        wordVector.add(new Word("emotion", "감정"));
        wordVector.add(new Word("mistake", "오류"));
        wordVector.add(new Word("baby", "아기"));
        wordVector.add(new Word("deal", "거래"));
        wordVector.add(new Word("view", "보기"));
        wordVector.add(new Word("doll", "인형"));
        wordVector.add(new Word("statue", "조각상"));
        wordVector.add(new Word("water", "물"));
        wordVector.add(new Word("friend", "친구"));
        wordVector.add(new Word("sleep", "잠"));
        wordVector.add(new Word("music", "음악"));
        wordVector.add(new Word("time", "시간"));
        wordVector.add(new Word("story", "이야기"));
    }

    // 모든 단어의 한글 뜻 목록을 가져옵니다. 오답 보기를 만드는 데 사용된다.
    private List<String> getAllKoreanMeanings(String excludeMeaning) {
        // HashSet 객체를 사용하여 중복된 뜻을 제거한다.
        HashSet<String> allMeanings = new HashSet<>();
        for (Word word : wordVector) {
            allMeanings.add(word.getKorean());
        }
        
        // 현재 문제의 정답은 오답 목록에서 제외한다.
        allMeanings.remove(excludeMeaning);
        
        return new ArrayList<>(allMeanings);
    }
    
    // 퀴즈를 실행하는 메인 메소드이다.
    public void run() {
        loadWords();
        System.out.println("\"명품영어\"의 단어 테스트를 시작합니다.");
        System.out.println("-1을 입력하면 종료합니다.");
        System.out.printf("현재 %d개의 단어가 들어 있습니다.\n", wordVector.size());

        while (true) {
            // 1. 문제 출제할 단어 (정답) 랜덤 선택
            int quizIndex = random.nextInt(wordVector.size());
            Word correctWord = wordVector.get(quizIndex);
            String correctAnswer = correctWord.getKorean();

            // 2. 오답 보기(Distractors) 3개 선정
            List<String> allDistractors = getAllKoreanMeanings(correctAnswer);
            List<String> options = new ArrayList<>();
            options.add(correctAnswer);

            // 오답 풀이 크기가 3보다 작으면, 사용 가능한 모든 오답을 사용한다.
            int numDistractors = Math.min(3, allDistractors.size());
            
            // 오답 리스트를 섞고 앞에서부터 필요한 만큼 선택한다.
            Collections.shuffle(allDistractors);
            for (int i = 0; i < numDistractors; i++) {
                options.add(allDistractors.get(i));
            }
            
            // 3. (단어 수가 부족할 경우 대비)보기가 4개가 되지 않으면 루프를 건너뛴다.
            if (options.size() < 4) {
                // 단어 수 부족으로 4개의 보기를 만들 수 없는 경우, 다음 문제를 시도한다.
                continue; 
            }
            
            // 4. 보기 섞기 및 정답 위치 찾기
            Collections.shuffle(options);
            int correctOptionIndex = options.indexOf(correctAnswer) + 1; // 1부터 시작하는 보기 번호

            // 5. 문제 출력
            System.out.printf("%s?\n", correctWord.getEnglish());
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("(%d) %s ", i + 1, options.get(i));
            }
            System.out.print(">> ");

            // 6. 사용자 입력 처리
            try {
                String input = scanner.next();
                int userChoice = Integer.parseInt(input);

                if (userChoice == -1) {
                    System.out.println("\"명품영어\"를 종료합니다...");
                    break;
                }

                if (userChoice >= 1 && userChoice <= options.size()) {
                    // 7. 정답 확인 및 피드백을 출력한다.
                    if (userChoice == correctOptionIndex) {
                        System.out.println("Excellent !");
                    } else {
                        System.out.println("No .");
                    }
                } else {
                    System.out.println("잘못된 입력입니다. 1에서 " + options.size() + " 사이의 번호를 입력하거나 -1을 입력하세요.");
                }

            } catch (java.util.InputMismatchException | NumberFormatException e) {
                System.out.println("숫자만 입력해 주세요.");
                scanner.nextLine(); // 버퍼를 비운다.
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        WordQuiz quiz = new WordQuiz();
        quiz.run();
    }
}