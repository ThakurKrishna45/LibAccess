package controller;

import java.io.IOException;
import java.util.List;

import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BookIssue;

@WebServlet("/userIssuedBook")
public class UserIssuedBook extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int userId= Integer.parseInt(req.getParameter("userId"));
		UserDao ud= new UserDao();
		List<BookIssue> bki= ud.userIssuedBooks(userId);
		req.setAttribute("IssuedBooks", bki);
		req.getRequestDispatcher("/user/myIssuedBook.jsp").forward(req,res);
	}
}
