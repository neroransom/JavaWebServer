package mywebserver.jsp;

import mywebserver.HttpServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JspTranslation {
    public JspTranslation(){};

   public void ofThe(String jspName) throws IOException {
       jspName=jspName.substring(1);

        StringBuilder _jsp = new StringBuilder();
        String jsp = HttpServer.readFiletoString("web/site/"+jspName);
        String  fileName = jspName.substring(0, jspName.indexOf(".jsp")) + "_jsp";

        File file = new File("src/mywebserver/jsp/jspclass/" + fileName + ".java");

        if(file.exists()&&file.isFile()) //刷新
        {
            file.delete();
        }

       _jsp.append("package mywebserver.jsp.jspclass;" +
               "import mywebserver.jsp.HttpJspBase;" +
               "import mywebserver.servlet.HttpServletRequest;" +
               "import mywebserver.servlet.HttpServletResponse;" +
               "import java.io.*;"+

               "public final class "+fileName+" extends HttpJspBase {" +
               "public void _jspService(HttpServletRequest request, HttpServletResponse response) {" +
               "PrintWriter out = response.getWriter();");


       int begin = 0;
       int end;

       if(jsp.contains("<%@"))
       {
           begin = jsp.indexOf("%>",jsp.indexOf("<@"))+2;
       }

       while((end = jsp.indexOf("<%=",begin))!=-1)
        {
           _jsp.append("out.write(\""+jsp.substring(begin,end)+"\");");
            begin = end+3;
            end = jsp.indexOf("%>",begin);
            _jsp.append("out.println("+jsp.substring(begin,end)+");");
            begin = end+2;
        }
       _jsp.append("out.flush();\nout.close();}}");

       FileWriter fwriter = new FileWriter(file);
       fwriter.write(_jsp.toString());
       fwriter.flush();
       fwriter.close();
    }
}
