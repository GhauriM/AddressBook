import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import java.awt.Desktop;

/*
 * This main class starts the address book program by calling the book class constructor 
 * and creating a book object
 */
public class Main implements ActionListener {

	JFrame mainFrame = new JFrame();					//creates a frame for the board
	JFrame inputFrame = new JFrame();					//creates the frame for the input window
	JFrame errorFrame = new JFrame();					//creates the frame for the error window
	
	JPanel titlePanel = new JPanel();					//creates the title panel 
	JPanel infoTitlePanel = new JPanel();				//creates the title panel for info frame
	JPanel buttonPanel = new JPanel();					//creates the button panel for info frame	
	JPanel infoPanel = new JPanel();					//creates the info panel 
	
	JLabel titleTextField = new JLabel();				//creates the title text field
	JLabel infoTitleTextField = new JLabel();			//creates the title text field
	JLabel firstNameLabel = new JLabel();				//creates the first name Label
	JLabel lastNameLabel = new JLabel();				//creates the phone number Label
	JLabel streetLabel = new JLabel();					//creates the street Label
	JLabel cityLabel = new JLabel();					//creates the city Label
	JLabel stateLabel = new JLabel();					//creates the state Label
	JLabel zipcodeLabel = new JLabel();					//creates the zipcode Label
	JLabel phoneNumberLabel = new JLabel();				//creates the phone number Label
	JLabel errorMessage = new JLabel();				//creates the phone number Label

	JTextField firstNameTextField = new JTextField("");	//creates the first name text field
	JTextField lastNameTextField = new JTextField("");	//creates the phone number text field
	JTextField streetTextField = new JTextField("");		//creates the street text field
	JTextField cityTextField = new JTextField("");		//creates the city text field
	JTextField stateTextField = new JTextField("");		//creates the state text field
	JTextField zipcodeTextField = new JTextField("");		//creates the zipcode text field
	JTextField phoneNumberTextField = new JTextField("");	//creates the phone number text field
	
	JButton addButton = new JButton();					//creates a add button
	JButton updateButton = new JButton();				//creates a update button
	JButton deleteButton = new JButton();				//creates a delete button
	JButton enterButton = new JButton();				//creates an enter button
	JButton exitButton = new JButton();					//creates an exit button
	JButton logButton = new JButton();					//creates an log button
	
	JTextPane addressList = new JTextPane();			//creates a box with list of addresses 
	
	Book addressBook; 									//create book object
	
	//Variable hold values entered in by user
	private String message;			//return message from addressbook class method calls
	private String firstName;		//user entered first name 						
	private String lastName;		//user entered last name 		
	private String street;			//user entered street name 	
	private String city;			//user entered city name 	
	private String state;			//user entered state name 	
	private String zipcode;			//user entered zipcode 	
	private String phoneNumber;		//user entered phone number	
	
	//Constructor creates the object to start the program and create the windows
	public Main() {
		
		addressBook = new Book();		//creates a book object 
		buildMainFrame();				//builds the main frame
	}
	
	//method builds the manin frame
	public void buildMainFrame() {
		//build frame
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(600, 250);
		mainFrame.setLayout(new BorderLayout());
		
		//build title panel 
		titlePanel.setLayout(new BorderLayout());
		//build title text Field
		titleTextField.setForeground(new Color(20, 153, 242));
		titleTextField.setFont(new Font("Serif", Font.BOLD, 20));
		titleTextField.setHorizontalAlignment(JLabel.CENTER);
		titleTextField.setText("Address Book");
		//add tile field to the title panel
		titlePanel.add(titleTextField, BorderLayout.CENTER);
		//add tile panel to mainFrame
		mainFrame.add(titlePanel, BorderLayout.NORTH);
		
		//build text area for list of addresses and add to main frame
		addressList.setFont(new Font("Serif", Font.BOLD, 12));
		JScrollPane scrollPane = new JScrollPane(addressList); 
		addressList.setEditable(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainFrame.add(scrollPane, BorderLayout.CENTER);
		//add current addresses to the text box
		addressList.setText(addressBook.toString()); 
		
		//build buttons panel
		buttonPanel.setLayout(new GridLayout(1,4));
		mainFrame.add(buttonPanel, BorderLayout.SOUTH);
		//build add button
		addButton.setFont(new Font("Serif", Font.BOLD, 12));
		addButton.setText("ADD NEW CONTACT");
		addButton.setFocusable(false);
		addButton.addActionListener(this);
		buttonPanel.add(addButton);
		//build update button
		updateButton.setFont(new Font("Serif", Font.BOLD, 12));
		updateButton.setText("UPDATE CONTACT");
		updateButton.setFocusable(false);
		updateButton.addActionListener(this);
		updateButton.setMaximumSize(new Dimension(30, 50));
		buttonPanel.add(updateButton);
		//build delete button
		deleteButton.setFont(new Font("Serif", Font.BOLD, 12));
		deleteButton.setText("DELETE CONTACT");
		deleteButton.setFocusable(false);
		deleteButton.addActionListener(this);
		buttonPanel.add(deleteButton);
		//build log button
		logButton.setFont(new Font("Serif", Font.BOLD, 12));
		logButton.setText("DISPLAY LOG");
		logButton.setFocusable(false);
		logButton.addActionListener(this);
		buttonPanel.add(logButton);

		//make the main frame visible
		mainFrame.setVisible(true);
	}
	
	//method builds the input frame
	public void buildInfoFrame(String frameTitle, String buttonLabel) {
		
		//build frame
		inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inputFrame.setSize(500,300);
		inputFrame.setLayout(new BorderLayout());
		
		//build title panel 
		infoTitlePanel.setLayout(new BorderLayout());
		//build title text Field
		infoTitleTextField.setForeground(new Color(20, 153, 242));
		infoTitleTextField.setFont(new Font("Serif", Font.BOLD, 25));
		infoTitleTextField.setHorizontalAlignment(JLabel.CENTER);
		infoTitleTextField.setText(frameTitle);
		//add tile field to the title panel
		infoTitlePanel.add(infoTitleTextField, BorderLayout.CENTER);
		//add tile panel to mainFrame
		inputFrame.add(infoTitlePanel, BorderLayout.NORTH);
		//builds a layout panel for info text fields; 
		infoPanel.setLayout(new GridLayout(7,2));
		inputFrame.add(infoPanel, BorderLayout.CENTER);
		
		//build first name label and text fields
		firstNameLabel.setText("First Name: ");
		firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		firstNameTextField.addActionListener(this);
		firstNameTextField.setText("");
		infoPanel.add(firstNameLabel, 0);
		infoPanel.add(firstNameTextField, 1);
		//build phone number label and text fields and add to panel
		lastNameLabel.setText("Last Name: ");
		lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lastNameTextField.addActionListener(this);
		lastNameTextField.setText("");
		infoPanel.add(lastNameLabel, 2);
		infoPanel.add(lastNameTextField, 3);
		//build street label and text fields and add to panel
		streetLabel.setText("Street: ");
		streetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		streetTextField.addActionListener(this);
		streetTextField.setText("");
		infoPanel.add(streetLabel, 4);
		infoPanel.add(streetTextField, 5);
		//build city label and text fields and add to panel
		cityLabel.setText("city: ");
		cityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cityTextField.addActionListener(this);
		cityTextField.setText("");
		infoPanel.add(cityLabel, 6);
		infoPanel.add(cityTextField, 7);
		//build state label and text fields and add to panel
		stateLabel.setText("State (abbreviation): ");
		stateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		stateTextField.addActionListener(this);
		stateTextField.setText("");
		infoPanel.add(stateLabel, 8);
		infoPanel.add(stateTextField, 9);
		//build zipcode label and text fields and add to panel
		zipcodeLabel.setText("ZIP Code: ");
		zipcodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		zipcodeTextField.addActionListener(this);
		zipcodeTextField.setText("");
		infoPanel.add(zipcodeLabel, 10);
		infoPanel.add(zipcodeTextField, 11);
		//build phone number label and text fields and add to panel
		phoneNumberLabel.setText("Phone Number (No dashs or Spaces): ");
		phoneNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneNumberTextField.addActionListener(this);
		phoneNumberTextField.setText("");
		infoPanel.add(phoneNumberLabel, 12);
		infoPanel.add(phoneNumberTextField, 13);
		
		//build function button
		enterButton.setFont(new Font("Serif", Font.BOLD, 12));
		enterButton.setText(buttonLabel);
		enterButton.setFocusable(false);
		enterButton.addActionListener(this);
		inputFrame.add(enterButton, BorderLayout.SOUTH);
		
		//make the main frame visible
		inputFrame.setVisible(true);
	}
	
	public void buildErrorFrame(String Message) {
		
		//build frame
		errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		errorFrame.setSize(500,150);
		errorFrame.setLayout(new BorderLayout());
		
		//build title text Field
		errorMessage.setForeground(new Color(0, 0, 0));
		errorMessage.setFont(new Font("Serif", Font.BOLD, 14));
		errorMessage.setHorizontalAlignment(JLabel.CENTER);
		errorMessage.setText(Message);
		//add tile field to the title panel
		errorFrame.add(errorMessage, BorderLayout.CENTER);

		//build function button
		exitButton.setFont(new Font("Serif", Font.BOLD, 16));
		exitButton.setText("Exit");
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
		errorFrame.add(exitButton, BorderLayout.SOUTH);
		
		//make the main frame visible
		errorFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//build the frame with add button is clicked
		if(e.getSource() == addButton) {	
			buildInfoFrame("Enter New Contact Information", "ADD CONTACT");			
		}
		//build the frame with update button is clicked
		if(e.getSource() == updateButton) {	
			buildInfoFrame("Enter Current Contact Information", "NEXT");	
		}
		//build the frame with delete button is clicked
		if(e.getSource() == deleteButton) {			
			buildInfoFrame("Enter Contact Information To Delete", "DELETE CONTACT");	
		}
		//open text file with log button is clicked
		if(e.getSource() == logButton) {
			try	{ 												//try/catch block to get file
				File file = new File("ChangeRecord.txt");		//create file object 
				Desktop desktop = Desktop.getDesktop();			//create desktop object to open file
				if(file.exists()) {								//checks file exists or not  
					desktop.open(file);							//opens the file  
				}
				else {											//error message if file not found
					buildErrorFrame("No Log File Available");
				}
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		//colse window when exit button is presses
		if(e.getSource() == exitButton) {	
			errorFrame.dispose();
		}
		//perform actions based on label given to the enter button
		if(e.getSource() == enterButton) {
			
			String message;											//local field to hold return message
			String firstName = firstNameTextField.getText();		//local field to hold user entered first name
			String lastName = lastNameTextField.getText();			//local field to hold user entered last name
			String street = streetTextField.getText();				//local field to hold user entered street name
			String city = cityTextField.getText();					//local field to hold user entered city name
			String state = stateTextField.getText();				//local field to hold user entered state name
			String zipcode = zipcodeTextField.getText();			//local field to hold user entered zipcode name
			String phoneNumber = phoneNumberTextField.getText();	//local field to hold user entered phoneNumber name
			
			//adds contact when button is clicked
			if(enterButton.getText() == "ADD CONTACT") {				
				//checks tat values are not null in text fields
				if(firstName.equals("") || lastName.equals("") || city.equals("") || state.equals("") || street.equals("") || 
						zipcode.equals("") || phoneNumber.equals("")) {			
					message = addressBook.addInfo(null, null, null, null, null, null, null);
					buildErrorFrame(message);		//error message is displayed if values are null
				}
				//calls add info method to add contact info to book
				else {			
					message = addressBook.addInfo(firstName, lastName, street, city, state, zipcode, phoneNumber);
					addressList.setText(addressBook.toString());
					//closes window once contact is added
					if(message == null) {
						inputFrame.dispose();
					}
					//shows error message if incorrect values entered
					else {
						buildErrorFrame(message);
					}
				}
			}
			
			if(enterButton.getText() == "DELETE CONTACT") {
				//checks tat values are not null in text fields
				if(firstName.equals("") || lastName.equals("") || city.equals("") || state.equals("") || street.equals("") || 
						zipcode.equals("") || phoneNumber.equals("")) {
					message = addressBook.deleteInfo(null, null, null, null, null, null, null);
					buildErrorFrame(message);
				}
				//calls add info method to delete contact info to book
				else {
					message = addressBook.deleteInfo(firstName, lastName, street, city, state, zipcode, phoneNumber);
					addressList.setText(addressBook.toString());
					//closes window once contact is deleted
					if(message == null) {
						inputFrame.dispose();
					}
					//shows error message if incorrect values entered
					else {
						buildErrorFrame(message);
					}
				}
			}
			
			if(enterButton.getText() == "NEXT") {
				//checks tat values are not null in text fields
				if(firstName.equals("") || lastName.equals("") || city.equals("") || state.equals("") || street.equals("") || 
						zipcode.equals("") || phoneNumber.equals("")) {
					message = addressBook.checkExists(null, null, null, null, null, null, null);
					buildErrorFrame(message);
				}
				//calls add info method to delete contact info to book
				else {
					message = addressBook.checkExists(firstName, lastName, street, city, state, zipcode, phoneNumber);
					//closes window once contact is added and sets values for use in updating contacts
					if(message == null) {
						
						this.firstName = firstName;			//holds first name to be updated
						this.lastName = lastName;			//holds last name to be updated
						this.street = street;				//holds street to be updated
						this.city = city;					//holds city to be updated
						this.state = state;					//holds state name to be updated
						this.zipcode = zipcode;				//holds zipcode to be updated
						this.phoneNumber = phoneNumber;		//holds phone number to be updated
						inputFrame.dispose();				//closes the window
						
						buildInfoFrame("Enter Updated Contact Information", "UPDATE CONTACT");	//builds new window to get updated info
						return;		//exits function after closing window
					}
					//shows error message if incorrect values entered
					else {
						buildErrorFrame(message);
					}
				}
			}
			if(enterButton.getText() == "UPDATE CONTACT") {
				//calls the update method in the book class to update info
				message = addressBook.updateInfo(this.firstName, this.lastName, this.street, this.city, this.state, this.zipcode, this.phoneNumber, firstName, 
						lastName, street, city, state, zipcode, phoneNumber);
				addressList.setText(addressBook.toString());
				//closes window once contact is updated
				if(message == null) {				
					inputFrame.dispose();
				}
				//shows error message if incorrect values entered
				else {		
					buildErrorFrame(message);
				}
			}		
		}
	}
	
	//Main method is used to run the program by creating object and calling constructor of main class
	public static void main(String[] args) {
		
		Main newBook = new Main();

	}


}
