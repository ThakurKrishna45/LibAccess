package controller;

import java.io.IOException;

import dao.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class Registration extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			String name= req.getParameter("name");
			String email= req.getParameter("email");
			String password= req.getParameter("password");
			int roleId= Integer.parseInt(req.getParameter("roleId"));
			
			if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
		        try {
		            res.sendRedirect("register.jsp?error=empty");
		            return;
		        } catch (IOException e) { e.printStackTrace(); }
		    }
			
			
			UserDao user= new UserDao();
			if (user.isEmailExist(email)) {
		        try {
		            res.sendRedirect("register.jsp?error=exists");
		            return;
		        } catch (IOException e) { e.printStackTrace(); }
		    }
			user.userRegister(name, email, password, roleId);
			res.sendRedirect("login.jsp");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
