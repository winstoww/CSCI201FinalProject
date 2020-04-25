import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String slatitude = request.getParameter("latitude");
        String slongitude = request.getParameter("longitude");
        String destPage = "survey.jsp"; //or .html?

        if (email == null || name == null || password == null || slatitude == null || slongitude == null || email.length() == 0 || 
        		password.length() == 0 || slatitude.length() == 0 || name.length() == 0 || slongitude.length() == 0) {
            request.setAttribute("register_message", "Fill in all boxes");
        }
        else if (!email.contains("@")) {
            request.setAttribute("register_message", "Email is missing @");
        }
        else if (!email.contains(".")) {
            request.setAttribute("register_message", "Email is missing .com, .net, .edu, etc.");
        }
        else {
	    try {
            double latitude = Double.parseDouble(slatitude);
            double longitude = Double.parseDouble(slongitude);
              
	    Profile user = DBUserLogin.tryLogin(email, password);
	    if (user != null) {
		request.setAttribute("register_message", "Account already exists");
	    }
	    else { 
		Profile new_user = DBCreateUser.createNewUser(email, password, name, latitude, longitude);
		HttpSession session = request.getSession(); //HttpSession is used to store the user's information until logged out
		session.setAttribute("user", new_user);
		session.setAttribute("connection", connection);
		destPage = "survey.html";
	    }
        }
        
        RequestDispatcher dispatch = request.getRequestDispatcher(destPage);
        dispatch.forward(request, response);
    }
}
