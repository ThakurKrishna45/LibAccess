package controller;

import java.io.IOException;

import dao.BookDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Book;

@WebServlet("/admin/addBook")
public class addNewBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.sendRedirect("addBook.jsp");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        
        try {
            String isbn = req.getParameter("isbn");
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String qtyStr = req.getParameter("qty");

            if (isbn == null || title == null || qtyStr == null || isbn.isEmpty() || title.isEmpty()) {
                throw new Exception("ISBN and Title are required fields.");
            }

            int copies = Integer.parseInt(qtyStr);

            BookDao bd = new BookDao();
            Book bk = bd.addNewBook(isbn, title, author);
            bd.addCopies(copies, bk);

            session.setAttribute("successMsg", "Book '" + title + "' added successfully!");

        } catch (NumberFormatException e) {
            session.setAttribute("errorMsg", "Invalid Quantity! Please enter a number.");
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = e.getMessage();
       
            if (errorMsg != null && errorMsg.contains("duplicate key value")) {
                session.setAttribute("errorMsg", "ISBN already exists!");
            } else {
  
                session.setAttribute("errorMsg", "Could not add book. Please try again.");
            }
        }

        res.sendRedirect("addBook.jsp");
    }
}