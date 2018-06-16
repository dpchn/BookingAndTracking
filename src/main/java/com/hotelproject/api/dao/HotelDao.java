package com.hotelproject.api.dao;

import org.hibernate.Transaction;

import org.hibernate.Session;

import com.hotelproject.api.config.HibernateSessionUtil;
import com.hotelproject.api.model.Hotel;


public class HotelDao {

	public int book(Hotel hotel){
		Transaction transaction;
		Session session = HibernateSessionUtil.getSessionFactory().openSession();
		
		int id =0;
		try {
			transaction = session.beginTransaction();
			id  = (int) session.save(hotel);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
