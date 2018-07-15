package practice.spring.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String streetNo;
	private String city;
	private String state;
	private String country;
	private String contactNo;

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String toString() {
		return "AddressDao [streetNo=" + streetNo + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", contactNo=" + contactNo + "]";
	}

}
