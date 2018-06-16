package com.hotelproject.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="hotel_booking",catalog="hotelproject")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID",unique=true,nullable=false)
	int id;
	@Column(name="HOTEL_ID", nullable=false)
	String hotelId;
	
	@Column(name="HOTEL_NAME", nullable=false)
	String hotelName;
	@Column(name="CITY", nullable=false)
	String city;
	@Column(name="NO_OF_PEOPLE", nullable=false)
	int noOfPeople;
	@Column(name="END_DATE", nullable=false)
	String endDate;
	@Column(name="START_DATE", nullable=false)
	String startDate;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL,targetEntity=User.class)
	@JoinTable(name = "hotel_user_mapping", catalog="hotelproject", joinColumns = { @JoinColumn(name = "HOTEL_ID", referencedColumnName="ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName="ID") })
	private List<User> users =  new ArrayList<User>();

	public Hotel(){
		
	}
	
	public Hotel(String hotelId, String hotelName, String city, int noOfPeople, String endDate, String startDate) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.city = city;
		this.noOfPeople = noOfPeople;
		this.endDate = endDate;
		this.startDate = startDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
	
}
