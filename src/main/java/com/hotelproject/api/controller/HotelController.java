package com.hotelproject.api.controller;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotelproject.api.form.HotelForm;
import com.hotelproject.api.service.HotelService;

@Controller
@RequestMapping("hotel")
public class HotelController {

	@Autowired
	HotelService service;

	
	//To Get Liisted Hotel
	@RequestMapping(value = "/getHotelList", produces = MediaType.APPLICATION_JSON_VALUE)
	public static ModelAndView getHotelList(HttpServletRequest request) {
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = null;
		try {
			jsonArray = (JSONArray) parser.parse(new FileReader("G:/hotel.json"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView view = new ModelAndView("hotelListPage");
		view.addObject("hotelList", jsonArray);
		// return new ResponseEntity(jsonArray, HttpStatus.CONFLICT);
		return view;
	}

	
	//Get Specific Hotel Details
	@RequestMapping(value = "/getHotel")
	public ModelAndView getHotel(HttpServletRequest request, @RequestParam("hotelId") String id) {
		System.out.println("id : " + id);
		ModelAndView view = new ModelAndView("hotel");
		JSONObject jsonObject = getHotelDetailsById(id);
		view.addObject("data", jsonObject);
		request.getSession().setAttribute("hotelId", id);
		List recommendedHotels = SearchedHotelyByCity(jsonObject.get("city").toString()); //List hotels for show in recommendation
		System.out.println("Search Hotel : "+jsonObject.get("city").toString());
		request.getSession().setAttribute("searchedHotel",recommendedHotels );
		return view;
	}
	
	
	//Initiate for Booking
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ModelAndView requirectToConfirmPage(HttpServletRequest request,
			@ModelAttribute("HotelBookForm") HotelForm form) {
		System.out.println("Booked.....waiting");
		
		List list = new ArrayList<JSONObject>();
		String hotelId = (String) request.getSession().getAttribute("hotelId");
		JSONObject jsonObject = getHotelDetailsById(hotelId);
		list.add(jsonObject);
		if (request.getSession().getAttribute("draftedHotel") == null)
			request.getSession().setAttribute("draftedHotel", list);
		else{
			List l= (List) request.getSession().getAttribute("draftedHotel");
			System.out.println("L size "+l.size());
			list.addAll(l);
			request.getSession().setAttribute("draftedHotel", list);
		}
			
		System.out.println("Drafted Hotel : "+ list);
		
		form.setHotelId(hotelId);
		request.getSession().setAttribute("hoteForm", form);
		ModelAndView view = new ModelAndView("confirmPage");
		return view;
	}

	//Confirm Booking of Hotel
	@RequestMapping(value = "/confirmBooking")
	public ModelAndView confirmBooking(HttpServletRequest request) {
		HotelForm form = (HotelForm) request.getSession().getAttribute("hoteForm");
		System.out.println("Confirm............");
		int userId = (int) request.getSession().getAttribute("userId");
		String hotelId = (String) request.getSession().getAttribute("hotelId");
		JSONObject hotelDeatils = getHotelDetailsById(hotelId);
		boolean status = service.bookHotel(hotelDeatils.get("name").toString(), hotelId, form.getStartDate(),
				form.getEndDate(), form.getNoOfPeople(), hotelDeatils.get("city").toString(), userId);
		List<JSONObject> l= (List) request.getSession().getAttribute("draftedHotel");
		Iterator<JSONObject> iterator =  l.iterator();
		while(iterator.hasNext()){
			JSONObject jsonObject = iterator.next();
			if(jsonObject.get("id").equals(hotelId)){
				iterator.remove();
				System.out.println("REMOVE "+jsonObject);
				System.out.println("Available "+l);
				request.getSession().setAttribute("draftedHotel", l);
				break;
			}
		}
		request.getSession().removeAttribute("hotelForm");
		ModelAndView view = new ModelAndView("profile");
		return view;
	}
	
	
	
	//to show drafted Hotel on page
	@RequestMapping(value = "/draftedHotel")
	public String getDraftedHotel() {
		return "draftedHotel";
	}

	private JSONObject getHotelDetailsById(String id) {
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = null;
		try {
			jsonArray = (JSONArray) parser.parse(new FileReader("G:/hotel.json"));
			for (Object object : jsonArray) {
				JSONObject data = (JSONObject) object;

				if (data.get("id").equals(id)) {
					return data;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//To read json file
	private List<JSONObject> SearchedHotelyByCity(String city) {
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = null;
		List list = new ArrayList<JSONObject>();
		try {
			jsonArray = (JSONArray) parser.parse(new FileReader("G:/hotel.json"));
			for (Object object : jsonArray) {
				JSONObject jsonObject = (JSONObject) object;

				if (jsonObject.get("city").equals(city)) {
					list.add(jsonObject);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
