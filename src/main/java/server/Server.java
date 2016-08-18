package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 刘博文
 * @date 2016/8/18 0018 10:52
 */
public class Server {
    public static void main(String args[]){
        try {
            ServerSocket server = new ServerSocket(8080);
            //accept 为阻塞方式，执行到此处会等待请求，而不向下执行
            Socket socket = server.accept();
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = is.readLine();
            System.out.print("received from client : " + line);

            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("received data : " + line);
            pw.flush();
            pw.close();
            is.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
