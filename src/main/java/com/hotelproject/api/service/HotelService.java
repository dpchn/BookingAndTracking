package com.hotelproject.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hotelproject.api.dao.HotelDao;
import com.hotelproject.api.dao.UserDao;
import com.hotelproject.api.model.Hotel;
import com.hotelproject.api.model.User;

@Service
public class HotelService {
	
	//Save Confirm  Hotel deatils 
	public boolean bookHotel(String hotelName, String hotelId, String startDate, String endDate, int noOfPeople, String city, int userId){
		Hotel bookedHotel = new Hotel(hotelId, hotelName, city, noOfPeople, endDate, startDate);
		HotelDao dao = new HotelDao();
		UserDao userDao = new UserDao();
		List list = new ArrayList<User>();
		User user = userDao.getUser(userId);
		list.add(userDao.getUser(userId));
		bookedHotel.setUsers(list);
		
		int id = dao.book(bookedHotel);
		if(id==0)
			return false;
		else
			return true;
	}
}
