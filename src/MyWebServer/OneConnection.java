package mywebserver;

import java.io. *;
import java.net.Socket;
import mywebserver.http.HttpRequest;
import mywebserver.http.HttpResponse;
import mywebserver.sender.HttpSender;
import mywebserver.sender.HttpServletSender;
import mywebserver.servlet.HttpServletRequest;
import mywebserver.servlet.HttpServletResponse;

import java.util.ArrayList;

class OneConnection implements Runnable {

    //public static HttpRequest request;
    //public static HttpResponse response;

    private Socket sock;
    private DataOutputStream out = null;
    ArrayList<String> header = null;

    OneConnection(Socket sock) throws Exception {
        this.sock = sock;
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new DataOutputStream(sock.getOutputStream());
    }

    public void run() {
        try {
            HttpRequest request = new HttpRequest(sock);
            String url = request.getUrl();

            if(HttpServer.servletMap.containsKey(url)|| url.endsWith(".jsp")) {
                   HttpServletSender hss = new HttpServletSender(new HttpServletResponse(request));
                   new Thread(hss).start();
            }
            else {
                    HttpSender hs = new HttpSender(new HttpResponse(request));
                    new Thread(hs).start();
            }
            } catch (IOException ex) {
            ex.printStackTrace();
    } catch (Exception e) {
            System.out.println("Except: " + e);
            e.printStackTrace();
        }
    }
}