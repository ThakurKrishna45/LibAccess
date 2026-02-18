package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Book;
import util.HibernateUtil;

public class BookDao {
	public Book addNewBook(String isbn, String title, String author) {
		Book book= new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setTotalCopies(0);
		book.setAvailableCopies(0);
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction trx= session.beginTransaction();
		session.persist(book);
		trx.commit();
		session.close();
		return book;
	}
	public void addCopies(int n, Book book) {
		int currentTotal= book.getTotalCopies();
		book.setAvailableCopies(currentTotal+n);
		book.setAvailableCopies(book.getAvailableCopies()+n);
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction trx= session.beginTransaction();
		session.merge(book);
		trx.commit();
		session.close();
	}
	public void removeCopies(int n, Book book) {
		int currentTotal= book.getTotalCopies();
		book.setTotalCopies(currentTotal-n);
		book.setAvailableCopies(book.getAvailableCopies()-n);
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction trx= session.beginTransaction();
		session.merge(book);
		trx.commit();
		session.close();
	}
	public List<Book> getBooksPaginated(String query, int page, int size) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        StringBuilder hql = new StringBuilder("FROM Book b");
	        boolean isSearch = (query != null && !query.trim().isEmpty());

	        if (isSearch) {
	            hql.append(" WHERE lower(b.title) LIKE :q OR lower(b.author) LIKE :q");
	        }
	        
	        Query<Book> q = session.createQuery(hql.toString(), Book.class);
	        if (isSearch) {
	            q.setParameter("q", "%" + query.toLowerCase() + "%");
	        }
	        
	        q.setFirstResult((page - 1) * size);
	        q.setMaxResults(size);
	        return q.list();
	    }
	}
	public long getBookCount(String query) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        StringBuilder hql = new StringBuilder("SELECT count(b) FROM Book b");
	        boolean isSearch = (query != null && !query.trim().isEmpty());

	        if (isSearch) {
	            hql.append(" WHERE lower(b.title) LIKE :q OR lower(b.author) LIKE :q");
	        }

	        Query<Long> q = session.createQuery(hql.toString(), Long.class);
	        if (isSearch) {
	            q.setParameter("q", "%" + query.toLowerCase() + "%");
	        }
	        return q.uniqueResult();
	    }
	}
}
