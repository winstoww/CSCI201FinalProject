import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        DBUserLogin userLogin = new DBUserLogin();
         
        try {
            Profile user = userLogin.tryLogin(email, password);
            String destPage = "login.html";
             
            if (user == null) {
                request.setAttribute("message", "Invalid email/password");
            } else { 
                HttpSession session = request.getSession(); //HttpSession is used to store the user's information until logged out
                session.setAttribute("user", user); //***** TODO: use later when you need to access user information -> Profile user = session.getAttribute("user");
                destPage = "dashboard.html";
            }
             
            RequestDispatcher dispatch = request.getRequestDispatcher(destPage);
            dispatch.forward(request, response);
            
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
}
