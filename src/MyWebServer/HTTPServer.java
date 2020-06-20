package mywebserver;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String a[]) throws Exception {
        final int httpd = 8081;
        ServerSocket ssock = new ServerSocket(httpd);
        System.out.println("Listening the port 8081 locally...");
        while (true) {
            Socket sock = ssock.accept();
            System.out.println("client has made socket connection");

            OneConnection client = new OneConnection(sock);
            new Thread(client).start();

        }

//        try {
//            ssock.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("HttpServer close");
    }
}
