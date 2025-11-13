package ch15n3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEx {
	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		Socket socket = null;
		Scanner scanner = new Scanner(System.in); // 키보드에서 읽은 scanner 객체를 생성한다.
		
		try {
			socket = new Socket("localhost", 9999); // 클라이언트 소켓을 생성하고 서버에 연결한다.
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while (true) {
				System.out.print("보내기>>"); // 프롬프트
				String outputMessage = scanner.nextLine(); // 키보드에서 한 행을 읽는다.
				
				if (outputMessage.equalsIgnoreCase("bye")) {
					out.write(outputMessage + "\n");
					out.flush();
					break; // 사용자가 "bye"를 입력한 경우 서버로 전송 후 실행을 종료한다.
				}
				
				out.write(outputMessage + "\n"); // 키보드에서 읽은 문자열을 전송한다.
				out.flush(); // out 객체의 스트림 버퍼에 있는 모든 문자열을 전송한다.
				String inputMessage = in.readLine(); // 서버로부터 한 행을 수신한다.
				System.out.println("서버: " + inputMessage); // 서버로부터 받은 메세지를 화면에 출력한다.
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				scanner.close();
				
				if (socket != null) {
					socket.close(); // 클라이언트 소켓을 닫는다.
				}
			}
			catch (IOException e) {
				System.out.println("서버와 채팅 중 오류가 발생하였습니다.");
			}
		}
	}
}
