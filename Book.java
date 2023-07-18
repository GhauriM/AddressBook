import java.util.*;
/*
 * the book class manages all address objects that are created and allows for them to be added or removes or updated
 * also address book can be saved to a file
 */
public class Book {

	private List<Address> addressBook;		//list of address objects to store all addresses
	public List<String> changeLog;			//list of any updates and deletions form the address book
	
	//constructor is called to create a book object and initialize the address object list
	Book(){ 
		//the address list is initialized
		addressBook = new ArrayList<Address>();
		//change log list is initialized; 
		changeLog = new ArrayList<String>();
	}
	
	//addInfo method adds an address to the list and verifies the parameters passed in. return true if info was added
	public String addInfo(String firstName, String lastName, String street, String city, String state, String zipcode,
			String phoneNumber) throws IncorrectInfoException {
		//try/catch statement used to throw error if the contact info is not correct then method returns false
		try {
			//if statement checks for null values, throws exception if null value is found
			if(firstName != null && lastName != null && street != null && city != null && state != null && zipcode != null && phoneNumber != null) {
				//checks that the state uses 2 letter abbreviation 
				if(state.length() == 2) {
					//checks to see that the zipcode is the correct length and also a set of numbers
					if(zipcode.length() == 5 &&Integer.parseInt(zipcode) <= 99999) {
						//checks to see that the phone is the correct length and also a set of numbers
						if(phoneNumber.length() == 10 && Long.parseLong(phoneNumber) <= 9999999999L) {
							Address newAddress = new Address(firstName, lastName, street, city, state, zipcode, phoneNumber);	//create address object
							addressBook.add(newAddress);	//add object to address book list
							return null;
						}
						else {
							throw new IncorrectInfoException(2);		//throws an exception if the info is incorrect. parameter 2 = error with phone number
						}
					}
					else {
						throw new IncorrectInfoException(3);		//throws an exception if the info is incorrect. parameter 3 = error with zipcode
					}
				}
				else {
					throw new IncorrectInfoException(4);		//throws an exception if the info is incorrect. parameter 4 = error with state
				}
			}
			else {
				throw new IncorrectInfoException(5);		//throws an exception if the info is incorrect. parameter 5 = null value given
			}	
		}
		catch (Exception e){
			return e.toString();	//return false if error is thrown	
		}			
	}
	
	//deleteInfo method deletes an address from the list after it verifies the parameters passed in. return true if info was deleted
	public String deleteInfo(String firstName, String lastName, String street, String city, String state, String zipcode,
			String phoneNumber) throws IncorrectInfoException{
		
		//System.out.println(firstName + lastName + street +city + state + zipcode + phoneNumber);
		//try/catch statement used to throw error if the contact info is not found then method returns false
		try {
			if(firstName != null && lastName != null && street != null && city != null && state != null && zipcode != null && phoneNumber != null) {
				//loop iterates to find the contact to delete.
				for(int count = 0; count < addressBook.size(); count++){
					if(addressBook.get(count).getFirstName().equalsIgnoreCase(firstName) && addressBook.get(count).getLastName().equalsIgnoreCase(lastName) &&
							addressBook.get(count).getStreet().equalsIgnoreCase(street) && addressBook.get(count).getCity().equalsIgnoreCase(city) &&
							addressBook.get(count).getState().equalsIgnoreCase(state) && addressBook.get(count).getZipcode().equalsIgnoreCase(zipcode) &&
							addressBook.get(count).getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
						
						recordChange(count, "Record Deleted: "); //calls method to record the change
						addressBook.remove(count);		//removes the contact form the device
						return null;
					}
					else {
						throw new IncorrectInfoException(1);		//throws an exception if the info is not found. parameter 1 = incorrect info
					}
				}
			}
			else {
				throw new IncorrectInfoException(5);		//throws an exception if the info is incorrect. parameter 5 = null value given
			}
		}
		catch (Exception e){
			return e.toString();		
		}
		return null;	
	}
	
	//updateInfo method updates an address in the list after it verifies the parameters passed in. return true if info was updated
	public String updateInfo(String firstName, String lastName, String street, String city, String state, String zipcode,
			String phoneNumber, String newFirstName, String newLastName, String newStreet, String newCity, String newState, String newZipcode, String newPhoneNumber) throws IncorrectInfoException {
		//try/catch statement used to throw error if the contact info is not found then method returns false
		try {
			//checks that non of the values passed in are null
			if(firstName != null && lastName != null && street != null && city != null && state != null && zipcode != null && phoneNumber != null && 
					newStreet != null && newCity != null && newState != null && newZipcode != null && newPhoneNumber!= null && newFirstName != null && newLastName != null) {
				//checks that the state uses 2 letter abbreviation 
				if(newState.length() == 2) {
					//checks to see that the zipcode is the correct length and also a set of numbers
					if(newZipcode.length() == 5 &&Integer.parseInt(newZipcode) <= 99999) {
						//checks to see that the phone is the correct length and also a set of numbers
						if(newPhoneNumber.length() == 10 && Long.parseLong(newPhoneNumber) <= 9999999999L) {

							//loop iterates to find the contact to delete.
							for(int count = 0; count < addressBook.size(); count++){
								if(addressBook.get(count).getFirstName().equalsIgnoreCase(firstName) && addressBook.get(count).getLastName().equalsIgnoreCase(lastName) &&
										addressBook.get(count).getStreet().equalsIgnoreCase(street) && addressBook.get(count).getCity().equalsIgnoreCase(city) &&
										addressBook.get(count).getState().equalsIgnoreCase(state) && addressBook.get(count).getZipcode().equalsIgnoreCase(zipcode) &&
										addressBook.get(count).getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
									
									recordChange(count, "Record Updated: "); //calls method to record the change
									addressBook.get(count).setFirstName(newFirstName);		//changes the first name to a new first name
									addressBook.get(count).setLastName(newLastName);		//changes the last name to a new last name
									addressBook.get(count).setStreet(newStreet);		//changes the street name to a new street name
									addressBook.get(count).setCity(newCity);			//changes the city to a new city
									addressBook.get(count).setState(newState);			//changes the state to a new state
									addressBook.get(count).setZipcode(newZipcode);		//changes the zipcode to a new zipcode
									addressBook.get(count).setPhoneNumber(newPhoneNumber);	//changes the phone number to a new phone number
									
									return null;
								}	
							}
						}
						else {
							throw new IncorrectInfoException(2);		//throws an exception if the info is incorrect. parameter 2 = error with phone number
						}
					}
					else {
						throw new IncorrectInfoException(3);		//throws an exception if the info is incorrect. parameter 3 = error with zipcode
					}
				}
				else {
					throw new IncorrectInfoException(4);		//throws an exception if the info is incorrect. parameter 4 = error with state
				}
				throw new IncorrectInfoException(1);		//throws an exception if the info is not found. parameter 1 = incorrect info
			}
			else {
				throw new IncorrectInfoException(5);		//throws an exception if the info is incorrect. parameter 5 = null value given
			}
		}
		catch (Exception e){
			return e.toString();		
		}	
	}

	public String checkExists(String firstName, String lastName, String street, String city, String state, String zipcode,
			String phoneNumber) throws IncorrectInfoException {
		//try/catch statement used to throw error if the contact info is not found then method returns error
		try {
			if(firstName != null && lastName != null && street != null && city != null && state != null && zipcode != null && phoneNumber != null) {
				//loop iterates to find the contact to delete.
				for(int count = 0; count < addressBook.size(); count++){
					if(addressBook.get(count).getFirstName().equalsIgnoreCase(firstName) && addressBook.get(count).getLastName().equalsIgnoreCase(lastName) &&
							addressBook.get(count).getStreet().equalsIgnoreCase(street) && addressBook.get(count).getCity().equalsIgnoreCase(city) &&
							addressBook.get(count).getState().equalsIgnoreCase(state) && addressBook.get(count).getZipcode().equalsIgnoreCase(zipcode) &&
							addressBook.get(count).getPhoneNumber().equalsIgnoreCase(phoneNumber)) {

						return null;
					}
					else {
						throw new IncorrectInfoException(1);		//throws an exception if the info is not found. parameter 1 = incorrect info
					}
				}
			}
			else {
				throw new IncorrectInfoException(5);		//throws an exception if the info is incorrect. parameter 5 = null value given
			}
		}
		catch (Exception e){
			return e.toString();		
		}
		return null;	
	}
	//this method adds the changed made to the book to the log
	public void recordChange(int index, String changeType) {
		
		changeLog.add(changeType + addressBook.get(index).toString());	//change is added to the change log
		
	}
	
	//returns the address book object
	public List<Address> getAddressBook() {
		return addressBook;
	}

	//toString method writes the contents of the address Book in a formated string
	@Override
	public String toString() {
		
		String bookList = "";	//holds all of the addresses in a single string to return
		//iterates through the address book list and adds contents to the return string
		for(int count = 0; count < addressBook.size(); count++){
			//removes next line from the first address and prints all addresses on a separate line
			if(count == 0) {
				bookList = bookList + addressBook.get(count).toString();	
			}
			else {
				bookList = bookList + "\n" + addressBook.get(count).toString();
			}
		}
		return bookList;
	}
}
