package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Book;
import util.HibernateUtil;

public class BookDao {
	public Book addNewBook(String isbn, String title, String author, int copy) {
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setTotalCopies(copy);
		book.setAvailableCopies(copy);
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction trx = session.beginTransaction();
			try {
				session.persist(book);
				trx.commit();
			} catch (Exception e) {
				if (trx != null) {
					trx.rollback();
				}
				throw e;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return book;
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

	public void delete(int id) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Book b = session.find(Book.class, id);
			session.remove(b);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	public Book getBook(int id) {
		Book bk = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			bk = session.find(Book.class, id);
		}
		return bk;
	}

	public void updateBook(Book book, int n, String operation) {
    Transaction tx = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        tx = session.beginTransaction();

        if ("ADD".equalsIgnoreCase(operation)) {
            book.setTotalCopies(book.getTotalCopies() + n);
            book.setAvailableCopies(book.getAvailableCopies() + n);

        } else if ("REMOVE".equalsIgnoreCase(operation)) {
            if (book.getAvailableCopies() < n) {
                throw new RuntimeException("Not enough copies to remove");
            }
            book.setTotalCopies(book.getTotalCopies() - n);
            book.setAvailableCopies(book.getAvailableCopies() - n);

        } 
        // else → NORMAL UPDATE (no copy change)

        session.merge(book);
        tx.commit();

    } catch (Exception e) {
        if (tx != null) {
			tx.rollback();
		}
        e.printStackTrace();
        throw e;
    }
}


}
