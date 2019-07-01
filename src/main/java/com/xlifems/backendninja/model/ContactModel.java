package com.xlifems.backendninja.model;

public class ContactModel {

	private int id;
	private String firtname;
	private String lastname;
	private String telephone;
	private String city;
	
	public ContactModel() {
		
	}

	public ContactModel(int id, String firtname, String lastname, String telephone, String city) {
		super();
		this.id = id;
		this.firtname = firtname;
		this.lastname = lastname;
		this.telephone = telephone;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirtname() {
		return firtname;
	}

	public void setFirtname(String firtname) {
		this.firtname = firtname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "ContactModel [id=" + id + ", firtname=" + firtname + ", lastname=" + lastname + ", telephone="
				+ telephone + ", city=" + city + "]";
	}
	
	

}
