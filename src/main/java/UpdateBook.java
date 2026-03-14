import java.io.IOException;

import dao.BookDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;

@WebServlet("/admin/updateBook")
public class UpdateBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        BookDao dao = new BookDao();
        Book book = dao.getBook(id);

        if (book == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
            return;
        }

        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));

        int newQty = Integer.parseInt(req.getParameter("qty"));
        int diff = newQty - book.getTotalCopies();

        if (diff > 0) {
            dao.updateBook(book, diff, "ADD");
        } else if (diff < 0) {
            dao.updateBook(book, -diff, "REMOVE");
        } else {
            dao.updateBook(book, 0, "UPDATE");
        }

        resp.sendRedirect(req.getContextPath() + "/admin/books");
    }
}
