package controller;

import java.io.IOException;

import dao.BookDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;

@WebServlet("/addNewBook")
public class addNewBook extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String isbn= req.getParameter("isbn");
		String title= req.getParameter("title");
		String author= req.getParameter("author");
		int copies= Integer.parseInt(req.getParameter("copies"));
		BookDao bd= new BookDao();
		Book bk=bd.addNewBook(isbn, title, author);
		bd.addCopies(copies, bk);
		res.setContentType("text/plain");
		res.getWriter().write("Book added successfully!");
	}
}
