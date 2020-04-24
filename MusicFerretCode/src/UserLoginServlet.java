import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String destPage = "login.html";
        
        if (email == null || password == null || email.length() == 0 || password.length() == 0) {
            request.setAttribute("login_message", "Empty email/password");
        }
        else {
            try {
                Profile user = DBUserLogin.tryLogin(email, password);

                if (user == null) {
                    request.setAttribute("login_message", "Invalid email/password");
                } else { 
                    HttpSession session = request.getSession(); //HttpSession is used to store the user's information until logged out
                    session.setAttribute("user", user); //***** TODO: use later when you need to access user information -> Profile user = session.getAttribute("user");
                    destPage = "dashboard.html";
                }

            } catch (SQLException | ClassNotFoundException ex) {
                throw new ServletException(ex);
            }
        }
     
        RequestDispatcher dispatch = request.getRequestDispatcher(destPage);
        dispatch.forward(request, response);
    }
}
