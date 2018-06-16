package com.hotelproject.api.controller;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotelproject.api.dao.UserDao;
import com.hotelproject.api.form.UserForm;
import com.hotelproject.api.model.User;
import com.hotelproject.api.service.UserService;

@Controller

@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/enrollpage", method = RequestMethod.GET)
	public String register() {
		System.out.println("Hello");
		return "register";
	}

	
	/**
	 * 
	 * @param form
	 * @param request
	 * @return
	 * Register User by email
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public ModelAndView userRegister(@ModelAttribute("UserForm") @Valid UserForm form, HttpServletRequest request) {
		Map user = service.addUser(form.getName(), form.getEmail(), request);
		ModelAndView view = new ModelAndView("profile");
		request.getSession().setAttribute("data", user);
		if (user != null) {
			view.addObject("data", user);
		}
		return view;
	}
	
	@RequestMapping(value = "/getLoginPage", method = RequestMethod.GET)
	public String getLoginPage(){
		return "login";
	}
	
	
	//Login user using email
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView userLogin(HttpServletRequest request,@RequestParam("email") String email){
		System.out.println(email);
		Map map = service.login(email, request);
		
		if(map==null){
			return new ModelAndView("error");
		}else{
			request.getSession().setAttribute("data", map);
			ModelAndView view = new ModelAndView("profile");
			view.addObject("data",map);
			return view;
		}
	}
	
	
	//Got to profile WIth new booked hotels
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String userLogin(HttpServletRequest request){
		int id = (int) request.getSession().getAttribute("useId");
		UserDao userDao = new UserDao();
		User user = userDao.getUser(id);
		List<Map<String, String>> list = new UserService().getAllBookedHotels(user);
		request.getSession().setAttribute("history", list);
		return "profile";
	}

}
