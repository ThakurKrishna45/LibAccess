package util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Book;
import model.BookIssue;
import model.Role;
import model.User;

public class HibernateUtil {
	private static SessionFactory factory;
	static {
		try {
			Configuration cfg = new Configuration().configure();

			cfg.addAnnotatedClass(Book.class);
			cfg.addAnnotatedClass(BookIssue.class);
			cfg.addAnnotatedClass(Role.class);
			cfg.addAnnotatedClass(User.class);

			factory = cfg.buildSessionFactory();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}
}
