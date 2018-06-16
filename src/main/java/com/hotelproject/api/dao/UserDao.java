package com.hotelproject.api.dao;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotelproject.api.config.HibernateSessionUtil;
import com.hotelproject.api.model.User;



public class UserDao {
	
	/**
	 * @param user
	 * @return
	 */
	public int addUser(User user ){
		Transaction transaction;
		Session session = HibernateSessionUtil.getSessionFactory().openSession();
		try{
		transaction = session.beginTransaction();
		int id = (Integer)session.save(user);
		transaction.commit();
		session.close();
		return id;
		}catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public User login(String email){
		Transaction transaction;
		Session session = HibernateSessionUtil.getSessionFactory().openSession();
		String sql = "from com.hotelproject.api.model.User where EMAIL=:email";
		Query query = session.createQuery(sql);
		query.setParameter("email",email);
		return (User) query.uniqueResult();
	}
	
	public User getUser(int id){
		Session session = HibernateSessionUtil.getSessionFactory().openSession();
		String sql = "from User where ID=:id";
		Query query= session.createQuery(sql);
		query.setParameter("id", id);
		return (User) query.uniqueResult();
	}
	
	public void updateUser(User user){
		Session session = HibernateSessionUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
	}
	
	/*public static void main(String[] args) {
		User user=new UserDao().getUser(1);
		System.out.println(user.getLastlogintime());
	}*/
}
