package main.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone_number;
	private String address;
	private String birthday;
	private int isLogin;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public User() {}
	
	public User(int id, String name, String email, String password, String phoneNumber, String address, String brithday,
			int isLogin, Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone_number = phoneNumber;
		this.address = address;
		this.birthday = brithday;
		this.isLogin = isLogin;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public User(String name, String email, String password, String phoneNumber, String address, String brithday,
			int isLogin) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone_number = phoneNumber;
		this.address = address;
		this.birthday = brithday;
		this.isLogin = isLogin;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phone_number;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phone_number = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public int getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}
	
	
}
