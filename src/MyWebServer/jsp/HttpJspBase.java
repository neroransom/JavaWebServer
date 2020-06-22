package mywebserver.jsp;

import mywebserver.servlet.HttpServlet;
import mywebserver.servlet.HttpServletRequest;
import mywebserver.servlet.HttpServletResponse;
import mywebserver.servlet.ServletException;

import java.io.IOException;

/**
 33  * This is the super class of all JSP-generated servlets.
 34  *
 35  * @author nero_ransom CUG
 36  */

public abstract class HttpJspBase extends HttpServlet {
       protected HttpJspBase() {
       }

       public final void init()
        {
           super.init();
        }


        public final void destroy() {
        jspDestroy();
        _jspDestroy();
        }

   /**
   * Entry point into service.
   * */
        public final void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
        {
         _jspService(request, response);
        }

        public void jspDestroy() {
        }

        protected void _jspDestroy() {
        }

        public abstract void _jspService(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}
