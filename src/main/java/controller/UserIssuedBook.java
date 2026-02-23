package controller;

import java.io.IOException;
import java.util.List;

import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BookIssue;
import model.User;

@WebServlet("/UserIssuedBook")
public class UserIssuedBook extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		User user=(User) session.getAttribute("user");
		int userId=user.getId();
		UserDao ud= new UserDao();
		List<BookIssue> bki= ud.userIssuedBooks(userId);
		req.setAttribute("IssuedBooks", bki);
		req.getRequestDispatcher("UserIssuedBook.jsp").forward(req,res);
	}
}
