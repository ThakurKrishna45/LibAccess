package controller;

import java.io.IOException;

import dao.BookDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("admin/deleteBook")
public class deleteBook extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp)
            throws IOException {

        int id=Integer.parseInt(req.getParameter("id"));

        new BookDao().delete(id);

        resp.sendRedirect("admin/books");
    }
}
