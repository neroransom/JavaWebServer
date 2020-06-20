package mywebserver;

import java.io. *;
import java.net.Socket;
import mywebserver.http.HttpRequest;
import mywebserver.http.HttpResponse;
import java.util.ArrayList;

class OneConnection implements Runnable {

    public static HttpRequest request;
    public static HttpResponse response;

    private Socket sock;
    private DataOutputStream out = null;
    ArrayList<String> header = null;

    OneConnection(Socket sock) throws Exception {
        this.sock = sock;
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new DataOutputStream(sock.getOutputStream());
    }

    void sendFile(String fname) throws Exception {
        fname = fname.substring(1);
        String where = "/site/" + fname; // create dir if necessary
        if (where.indexOf(".. ") > -1)
            throw new SecurityException("No access to parent dirs");
        System.out.println("looking for" + where);
        File f = new File(where);
        DataInputStream din = new DataInputStream(new FileInputStream(f));
        int len = (int) f.length();
        byte[] buf = new byte[len];
        din.readFully(buf);
        out.writeBytes("HTTP/1.1 200 OK\r\n");
        out.writeBytes("Content-Length: " + len + "\r\n");
        out.writeBytes("Content-Type: text/html\r\n\n");
        out.write(buf);
        out.flush();
        out.close();
    }

    public void run() {
        try {
            request = new HttpRequest(sock);
            String filename = request.getRequest();
            if(filename == null||filename.isEmpty()) {
                return;
            }
            response = new HttpResponse(request);

            if (request.getUrl() != null) {

            }

            response.sendFile(filename);
        } catch (Exception e) {
            System.out.println("Except: " + e);
            e.printStackTrace();
        }
    }
}