package socket1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {

    ServerSocket serverSocket; // 클라이언트 연결을 받는 소켓
    Socket socket; // 실제 통신을 하는 소켓
    BufferedReader br;

    public ServerFile() {
        System.out.println("서버 소켓 시작");
        try {
            serverSocket = new ServerSocket(10000);
            System.out.println("서버 소켓 생성 완료");
            socket = serverSocket.accept(); // 클라이언트 접속 대기중
            System.out.println("클라이언트 연결 완료 (buffer 연결)");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg = br.readLine();
            System.out.println("클라이언트로부터 받은 메시지 : " + msg);
        } catch (Exception e) {
            System.out.println("서버 소켓 에러 발생 : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ServerFile();

    }
}
