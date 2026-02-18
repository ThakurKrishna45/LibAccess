package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.BookIssue;
import model.Role;
import model.User;
import util.HibernateUtil;

public class UserDao {
	public User userLogin(String email, String password) {
		User user=null;
		try(Session session= HibernateUtil.getSessionFactory().openSession()){
		Query<User> query= session.createQuery("From User Where email=:e and password=:p", User.class);
		query.setParameter("e",email);
		query.setParameter("p",password);
		user = query.uniqueResult();
		return user;
	}catch (Exception e) {
		
		e.printStackTrace();
	}
		return user;
	}
	public User userRegister(String name, String email, String password, int roleId) {
		User user= new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		try(Session session= HibernateUtil.getSessionFactory().openSession()){
		Transaction trx= session.beginTransaction();
		Role role=session.find(Role.class,roleId);
		user.setRole(role);
		if(role.getRoleName()=="STUDENT") {
			user.setActive(true);
		} else {
			user.setActive(false);
		}
		try {
			session.persist(user);
			trx.commit();
			session.close();
		} catch (Exception e) {
				if (trx != null) {
					trx.rollback();
				}
				e.printStackTrace();
		}
		}
		return user;
	}
	public boolean isEmailExist(String email) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        Query<Long> query = session.createQuery("select count(u) from User u where u.email = :email", Long.class);
	        query.setParameter("email", email);
	        return query.uniqueResult() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<BookIssue> userIssuedBooks(int userId){
		List<BookIssue> li =null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			StringBuilder hql= new StringBuilder("From BookIssue bi where bi.user= :uid ORDER BY bi.issueDate DESC");
			Query<BookIssue> query= session.createQuery(hql.toString(),BookIssue.class);
			query.setParameter("uid", userId);
			li= query.getResultList();
		}
		return li;
	}
}
