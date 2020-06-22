package mywebserver.jsp;

import mywebserver.HttpServer;

import java.io.File;

public class JspTranslation {
    public JspTranslation(){};

   public void ofThe(String jspName){
        String jsp = HttpServer.readFiletoString("web/site/"+jspName);
        String  fileName = jspName.substring(0, jspName.indexOf(".")) + "_jsp";

        File file = new File("src/jsp/class/" + fileName + ".java");
        int begin = 0;
       int end = jsp.indexOf("<%",begin);
       if(end!=-1)
        {
           // file = jsp.substring(begin,end);

        }
    }
}
