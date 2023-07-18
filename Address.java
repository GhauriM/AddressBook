/*
 * This class creates Address objects to store addresses and perform 
 * functions on them.
 */
public class Address {

	private String firstName; 		//first name of person
	private String lastName;		//last name of person
	private String street;			//name of the street
	private String city;			//name of the city
	private String state;			//name of the state
	private String zipcode;			//zip code 
	private String phoneNumber;		//persons phone number
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zipcode
	 * @param phoneNumber
	 * this constructor is called when creating a address object to store
	 * address information.
	 */
	public Address(String firstName, String lastName, String street, String city, String state, String zipcode,
			String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phoneNumber = phoneNumber;
	}

	//public method to get the first name field
	public String getFirstName() {
		return firstName;
	}

	//public method to set the first name field
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	//public method to get the last name field
	public String getLastName() {
		return lastName;
	}
	
	//public method to set the last name field
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	//public method to get the street name field
	public String getStreet() {
		return street;
	}

	//public method to set the street name field
	public void setStreet(String street) {
		this.street = street;
	}

	///public method to get the city name field
	public String getCity() {
		return city;
	}

	//public method to set the city name field
	public void setCity(String city) {
		this.city = city;
	}

	//public method to get the state name field
	public String getState() {
		return state;
	}

	//public method to set the state name field
	public void setState(String state) {
		this.state = state;
	}

	//public method to get the zip code field
	public String getZipcode() {
		return zipcode;
	}

	//public method to set the zip code field
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	//public method to get the phone number field
	public String getPhoneNumber() {
		return phoneNumber;
	}

	//public method to set the phone number field
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	//toString prints out a formated line with the address book entry
	@Override
	public String toString() { 
		
		String name = String.format("%1$-" + 30 + "s", firstName + " " + lastName);		//formats name to pad with spaces
		return (name + street + ", " + city + " " + state + " " + zipcode + " " + phoneNumber.substring(0,3) + "-" + phoneNumber.substring(3,6)+ "-" + phoneNumber.substring(6,10));
	}
}
