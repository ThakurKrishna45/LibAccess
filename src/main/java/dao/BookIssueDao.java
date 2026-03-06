package dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Book;
import model.BookIssue;
import model.User;
import util.HibernateUtil;

public class BookIssueDao {
	public void issue(int userId, String isbn) {
    LocalDate currentDate = LocalDate.now();
    
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Transaction trx = session.beginTransaction();
        
        try {
            String hql = "FROM Book b WHERE b.isbn = :isbn";
            Book book = session.createQuery(hql, Book.class)
                               .setParameter("isbn", isbn)
                               .uniqueResult();

            if (book == null) {
            		throw new Exception("Book not found with ISBN: " + isbn);
            }

            if (book.getAvailableCopies() <= 0) {
            	throw new Exception("No copies available for: " + book.getTitle());
 
            }
            User user=session.find(User.class, userId);
            BookIssue bk = new BookIssue();
            bk.setBook(book);
            bk.setUser(user);
            bk.setIssueDate(currentDate);
            bk.setDueDate(currentDate.plusDays(15));

            session.persist(bk);

    
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            session.merge(book);

            trx.commit();
        

        } catch (Exception e) {
            if (trx != null && trx.getStatus().canRollback()) {
                trx.rollback();
            }
            e.printStackTrace();
        }
    }
}
public boolean returnBook(int userId, String isbn) {
    LocalDate currentDate = LocalDate.now();
 
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Transaction trx = session.beginTransaction();
        
        try {
            BookIssue bki = getIssuedBook(session, userId, isbn);
            
            if (bki == null || bki.getReturnDate() != null) {
                return false; 
            }


            bki.setReturnDate(currentDate);
            session.merge(bki);

            Book book = bki.getBook();
            if (book != null) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                session.merge(book);
            }

            trx.commit();
            return true;
            
        } catch (Exception e) {
            // Rollback happens HERE while session is still open
            if (trx != null && trx.getStatus().canRollback()) {
                trx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    } // Session closes automatically here
}
	
	public BookIssue getIssuedBook(Session session,int userId, String isbn) {
		String hql="from BookIssue bi where bi.user.userId = :uid and bi.book.isbn = :isbn";
		Query<BookIssue> query=session.createQuery(hql,BookIssue.class);
		query.setParameter("uid", userId);
		query.setParameter("isbn", isbn);
		return query.uniqueResult();
	}
	public List<BookIssue> getAllBookIssues() {
	    List<BookIssue> list = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        String hqlString = "FROM BookIssue"; 
	        Query<BookIssue> bkQuery = session.createQuery(hqlString, BookIssue.class);
	        
	        list = bkQuery.getResultList(); 
	        System.out.println("SQL Executed. Rows found: " + (list != null ? list.size() : 0));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
