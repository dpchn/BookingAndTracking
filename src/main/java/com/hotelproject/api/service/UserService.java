package com.hotelproject.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hotelproject.api.dao.UserDao;
import com.hotelproject.api.model.Hotel;
import com.hotelproject.api.model.User;

@Service
public class UserService {

	public Map<String, String> addUser(String name, String email, HttpServletRequest request) {
		User user = new User(name, email);
		UserDao dao = new UserDao();
		int id = dao.addUser(user);
		Map map = new HashMap<String, String>();
		if (id == 0)
			return null;
		else {
			request.getSession().setAttribute("userId", id);
			System.out.println("Register");
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			map.put("lastLogin", new Date());
			return map;
		}
	}

	public Map<String, String> login(String email, HttpServletRequest request) {
		UserDao dao = new UserDao();
		User user = dao.login(email);
		if (user == null) {
			return null;
		} else {
			Date lastLogin = user.getLastlogintime();
			user.setLastlogintime(new Date());
			dao.updateUser(user);
			request.getSession().setAttribute("userId", user.getId());
			
			List<Map<String, String>> list = getAllBookedHotels(user);
	
			request.getSession().setAttribute("history", list);
			Map map = new HashMap<String, String>();
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			map.put("lastLogin", lastLogin);
			return map;
		}
	}
	

	public List<Map<String, String>> getAllBookedHotels(User user){
		List<Map<String, String>> list = new ArrayList<>();
		user.getBookedHotel().forEach(x -> {
			Map<String, String> bookingHistory = new HashMap<String, String>();
			bookingHistory.put("startDate", x.getStartDate());
			bookingHistory.put("endDate", x.getEndDate());
			bookingHistory.put("name", x.getHotelName());
			list.add(bookingHistory);
		});
		return list;
	}
	public List<Map<String, String>> getBookedHotel(int id) {
		User user = new User();
		UserDao dao = new UserDao();
		user = dao.getUser(id);
		List<Hotel> hotels = user.getBookedHotel();
		List<Map<String, String>> bookedHotels = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		hotels.forEach((hotel) -> {
			map.put("hotelName", hotel.getHotelName());
			map.put("startDate", hotel.getStartDate());
			map.put("endDate", hotel.getEndDate());
			bookedHotels.add(map);
		});
		return bookedHotels;
	}

	public static void main(String[] args) {
		HttpServletRequest request;
		// new UserService().login("dp20101@gmail.com", re);
	}

}
