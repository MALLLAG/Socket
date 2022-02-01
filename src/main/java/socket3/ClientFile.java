package socket3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientFile {

    Socket socket;
    BufferedWriter bw;
    BufferedReader keyboard;
    BufferedReader br;

    public ClientFile() {
        try {
            System.out.println("클라이언트 소켓 시작");
            socket = new Socket("localhost", 10000);

            System.out.println("buffer(write) 연결");
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println("키보드 스트림, buffer 연결 ");
            keyboard = new BufferedReader(new InputStreamReader(System.in));

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 새로운 스레드 역할
            ReadThread rt = new ReadThread();
            Thread t1 = new Thread(rt);
            t1.start();

            // 메인 스레드 역할
           while (true) {
                System.out.println("키보드 입력 대기중");
                String keyboardMsg = keyboard.readLine();
                bw.write(keyboardMsg + "\n ");
                bw.flush();
            }

        } catch (Exception e) {
            System.out.println("서버 소켓 에러 발생 : " + e.getMessage());
        }
    }

    class ReadThread implements Runnable{

        @Override
        public void run() {
            while (true) {
                try {
                    String msg = br.readLine();
                    System.out.println("서버로부터 받은 메시지 : " + msg);
                } catch (Exception e) {
                    System.out.println("클라이언트 소켓쪽에서 서버 소켓 메시지를 입력 받는 중 오류 발생 : " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new ClientFile();
    }
}
