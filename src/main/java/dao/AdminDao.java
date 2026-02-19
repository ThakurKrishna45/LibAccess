package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.User;
import util.HibernateUtil;

public class AdminDao {
	public List<User> adminList(){
		List<User> admins= new ArrayList<>();
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			String hql="From User U where U.role.roleName=:roleName";
			admins=session.createQuery(hql,User.class).setParameter("roleName","admin").getResultList();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return admins;
	}
	public boolean approveAdmin(int id) {
		boolean success=false;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			Transaction trx= session.beginTransaction();
			User user=session.find(User.class,id);
			try {
				user.setActive(true);
				session.merge(user);
				trx.commit();
				success=true;
			} catch (Exception e) {
				if(trx!=null) {
					trx.rollback();
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return success;
	}
}
