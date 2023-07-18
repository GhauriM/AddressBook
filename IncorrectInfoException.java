/*
 * this exception class returns messages to the user based on the error found
 */

public class IncorrectInfoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	String errorMessage = null;		//holds  the error message to return
	
	//constructor takes in error id as parameter and saves error message based on error type to the error message field
	public IncorrectInfoException(int errorType) {
		
		if(errorType == 1) {
			errorMessage = "The entered name and address do not match any records.";
		}
		if(errorType == 2) {
			errorMessage = "The phone number provided is not in the correct foramt.";
		}
		if(errorType == 3) {
			errorMessage = "The zipcode is incorrect, enter 5 digits (0-9) only";
		}
		if(errorType == 4) {
			errorMessage = "The state is incorect. provide abbrevaited state name";
		}
		if(errorType == 5) {
			errorMessage = "values can not be null";
		}
	}
	
	//toString returns an error message if an exception is thrown
	@Override
	public String toString() {
		return errorMessage;
	}
}
