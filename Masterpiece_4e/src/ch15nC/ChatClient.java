package ch15nC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

// GUI 기반 클라이언트 채팅 프로그램
public class ChatClient extends JFrame implements ActionListener {
    private JTextArea displayArea;
    private JTextField inputField;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private final String SERVER_IP = "localhost";
    private final int PORT = 9999;

    public ChatClient() {
        super("클라이언트 채팅 프로그램");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 직접 종료 처리

        // 1. UI 구성
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        inputField = new JTextField(30);
        inputField.addActionListener(this); // Enter 키 입력 시 액션 처리

        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        setSize(400, 350);
        setVisible(true);
        
        // 2. 윈도우 닫기 이벤트 처리(리소스 정리 및 종료)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    // 창을 닫을 때 "bye" 메시지를 서버에게 전송
                    sendMessage("bye"); 
                    closeResources();
                } catch (IOException ex) {
                    // 무시
                }
                System.exit(0);
            }
        });

        // 3. 클라이언트 네트워크 시작
        new Thread(() -> startClient()).start();
    }
    
    // 메시지 전송 처리
    @Override
    public void actionPerformed(ActionEvent e) {
        String message = inputField.getText();
        if (message.isEmpty()) return;

        try {
            sendMessage(message);
            inputField.setText(""); // 전송 후 입력창 초기화
            
            if (message.equalsIgnoreCase("bye")) {
                closeResources();
                System.exit(0);
            }
        } catch (IOException ex) {
            displayArea.append(">> 메시지 전송 오류: " + ex.getMessage() + "\n");
            tryCloseAndExit();
        }
    }
    
    private void sendMessage(String message) throws IOException {
        if (out != null) {
            out.write("클라이언트: " + message + "\n");
            out.flush();
            displayArea.append("나: " + message + "\n");
        }
    }

    private void startClient() {
        try {
            socket = new Socket(SERVER_IP, PORT); // 서버에 연결 시도
            displayArea.append(">> 서버에 연결되었습니다.\n");
            
            // 스트림 설정
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            // 수신 전용 스레드 시작
            new ReceiverThread().start();
        } catch (IOException e) {
            displayArea.append(">> 서버 연결 오류: " + e.getMessage() + "\n");
            tryCloseAndExit();
        }
    }

    // 통신 자원 정리
    private void closeResources() {
        try {
            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();
        } catch (IOException e) {
            System.err.println("자원 해제 오류: " + e.getMessage());
        }
    }
    
    // 오류 발생 시 종료 시도
    private void tryCloseAndExit() {
        closeResources();
        System.exit(0);
    }

    // 서버로부터 메시지를 수신하는 전용 스레드
    class ReceiverThread extends Thread {
        @Override
        public void run() {
            String inputMessage;
            try {
                while (true) {
                    inputMessage = in.readLine(); // 서버로부터 메시지 수신 대기
                    
                    if (inputMessage == null || inputMessage.equalsIgnoreCase("서버: bye")) {
                        displayArea.append(">> 상대방이 'bye'를 입력하여 연결을 종료합니다.\n");
                        break;
                    }
                    
                    displayArea.append(inputMessage + "\n");
                }
            } catch (IOException e) {
                // readLine() 메소드에서 오류가 발생하면 연결 끊김으로 판단
                displayArea.append(">> 서버 연결이 끊어졌습니다: " + e.getMessage() + "\n");
            } finally {
                tryCloseAndExit();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatClient::new);
    }
}