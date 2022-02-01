package socket1;

import java.io.*;
import java.net.Socket;

public class ClientFile {

    Socket socket;
    BufferedWriter bw;
    BufferedReader br;

    public ClientFile() {
        try {
            System.out.println("클라이언트 소켓 시작");
            socket = new Socket("localhost", 10000);

            System.out.println("buffer(write) 연결");
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println("키보드 스트림, buffer 연결 ");
            br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("키보드 입력 대기중");
            String keyboardMsg = br.readLine();
            bw.write(keyboardMsg + "\n ");
            bw.flush();

        } catch (Exception e) {
            System.out.println("서버 소켓 에러 발생 : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ClientFile();
    }
}
