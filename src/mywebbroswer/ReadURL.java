package mywebbroswer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadURL {
    public static void main(String args[]) throws Exception {
        try {

            URL url = new URL(("http://localhost:8081/3"));
          //  File f =new File("localhost.html");

            InputStreamReader isr = new InputStreamReader(url.openStream());

        //    FileWriter fw = new FileWriter(f);
            
            BufferedReader br = new BufferedReader(isr);


            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
         //       fw.write(str+'\n');
            }
            br.close();
            isr.close();
          //  fw.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
