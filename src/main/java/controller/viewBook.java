package controller;

import java.io.IOException;
import java.util.List;

import dao.BookDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;

@WebServlet("/viewBook")
public class viewBook extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int page = 1;
        int pageSize = 5; 
        String query = req.getParameter("searchQuery"); 

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        BookDao dao = new BookDao();
        
        List<Book> books = dao.getBooksPaginated(query, page, pageSize);
        
        long totalBooks = dao.getBookCount(query);
        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

        req.setAttribute("books", books);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("searchQuery", query);
    
//        System.out.println("DEBUG: Books found in DAO: " + (books != null ? books.size() : "NULL"));

 
//        System.out.println("DEBUG: Attribute 'books' has been set.");
        req.getRequestDispatcher("/viewBook.jsp").forward(req, res);
	}
}
