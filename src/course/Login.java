package course;

import mywebserver.servlet.HttpServlet;
import mywebserver.servlet.HttpServletRequest;
import mywebserver.servlet.HttpServletResponse;
import mywebserver.servlet.ServletException;

import java.io.IOException;



/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		
		System.out.println("userName:"+userName+"\t password:"+password);
		if("admin".equals(userName) && "admin".equals(password))
				  request.getSession().setAttribute("username","admin");
		else
			 request.getSession().setAttribute("username","Unknown User");
		
		response.sendRedirect("test6.jsp");
			
	//	doGet(request, response);
	}

}
