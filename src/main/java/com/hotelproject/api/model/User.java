package com.hotelproject.api.model;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name="USER", catalog="hotelproject")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID",unique=true,nullable=false)
	int id;
	@Column(name="NAME", nullable=false)
	String name;
	@Column(name="EMAIL", nullable=false)
	String email;
	@Column(name="LAST_LOGIN_AT", nullable=true)
	Date lastlogintime;

	@ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL,targetEntity=Hotel.class)
	@JoinTable(name = "hotel_user_mapping", catalog="hotelproject", joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName="ID") }, inverseJoinColumns = { @JoinColumn(name = "HOTEL_ID", referencedColumnName="ID") })
	private List<Hotel> bookedHotel =  new ArrayList<Hotel>();
	public User(){
		
	}
	
	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public List<Hotel> getBookedHotel() {
		return bookedHotel;
	}

	public void setBookedHotel(List<Hotel> bookedHotel) {
		this.bookedHotel = bookedHotel;
	}
	
	

}
