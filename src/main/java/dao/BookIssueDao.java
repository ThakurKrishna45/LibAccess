package dao;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Book;
import model.BookIssue;
import model.User;
import util.HibernateUtil;

public class BookIssueDao {
	public void issue(User user, Book book) {
		LocalDate currentDate = LocalDate.now();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction trx = session.beginTransaction();
			try {
				BookIssue bk = new BookIssue();
				bk.setBook(book);
				bk.setIssueDate(currentDate);
				bk.setUser(user);
				bk.setDueDate(currentDate.plusDays(15));
				session.persist(bk);
				int crrCopies = book.getAvailableCopies();
				if (crrCopies > 0) {
					book.setAvailableCopies(crrCopies - 1);
					session.merge(book);
				} else {
					throw new Exception("No copies available!");
				}
				trx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if (trx != null) {
					trx.rollback();
				}
				e.printStackTrace();
			}
		}
	}

	public void returnBook(BookIssue bki) {
		LocalDate currenDate = LocalDate.now();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction trx = session.beginTransaction();
			try {
				bki.setReturnDate(currenDate);
				session.merge(bki);

				Book book = bki.getBook();
				book.setAvailableCopies(book.getAvailableCopies() + 1);
				session.merge(book);
				trx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if (trx != null) {
					trx.rollback();
				}
				e.printStackTrace();
			}
		}
	}
}
