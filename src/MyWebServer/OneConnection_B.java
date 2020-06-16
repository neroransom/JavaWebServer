package MyWebServer;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

class OneConnection_B extends OneConnection_A {
    OneConnection_B(Socket sock) throws Exception {
        super(sock);
    }

    void sendFile(String fname) throws Exception {
        String where = "/site/" + fname; // create dir if necessary
        if (where.indexOf(".. ") > -1)
            throw new SecurityException("No access to parent dirs");
        System.out.println("looking for" + where);
        File f = new File(where);
        DataInputStream din = new DataInputStream(new FileInputStream(f));
        int len = (int) f.length();
        byte[] buf = new byte[len];
        din.readFully(buf);
        out.writeBytes("HTTP/1.0 200 OK\r\n");
        out.writeBytes("Content-Length: " + len + "\r\n");
        out.writeBytes("Content-Type: text/html\r\n\n");
        out.write(buf);
        out.flush();
        out.close();
    }
}
