package controller;

import org.hibernate.Session;

import dao.BookIssueDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;
import model.User;
import util.HibernateUtil;

@WebServlet("/admin/issueBook")
public class BookIssue extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String action= req.getParameter("action");
		BookIssueDao issueDao = new BookIssueDao();
		
		if("issue".equals(action)) {
			try(Session session=HibernateUtil.getSessionFactory().openSession()){
				int bookId=Integer.parseInt(req.getParameter("bookId"));
				int studentId=Integer.parseInt(req.getParameter("studentId"));
				User user=session.find(User.class, studentId);
				Book book=session.find(Book.class,bookId);
				issueDao.issue(user, book);
//				res.sendRedirect("dashboard.jsp?msg=issued");
			}
		}else if ("return".equals(action)) {
            int issueId = Integer.parseInt(req.getParameter("issueId"));
            
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
            model.BookIssue bki= session.find(model.BookIssue.class,issueId);
            
            issueDao.returnBook(bki);
//            res.sendRedirect("dashboard.jsp?msg=returned");
            }
        }
	}
}
