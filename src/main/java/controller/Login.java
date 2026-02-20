package controller;

import java.io.IOException;

import dao.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/login")
public class Login extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String email= req.getParameter("email");
		String password= req.getParameter("password");
		UserDao dao= new UserDao();
		User user= dao.userLogin(email,password);
		if(user!=null) {
			HttpSession session= req.getSession();
			session.setAttribute("user",user);
			session.setAttribute("userRole",user.getRole().getRoleName());
			res.sendRedirect("user/dashboard.jsp");
//			 if ("ADMIN".equals(user.getRole().getRoleName()))
//	                res.sendRedirect("jsp/adminDashboard.jsp");
//	            else
//	                res.sendRedirect("jsp/studentDashboard.jsp");
		}else {
			res.sendRedirect("login.jsp");
		}
	}
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.sendRedirect("login.jsp");
    }
}
