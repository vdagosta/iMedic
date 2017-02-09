
/* Veronica D'Agosta
 * CSCI 24000 - Spring 2016
 * Final Project      */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class Interface extends JFrame implements ActionListener{

	/* The Interface class is the GUI interface that tests if the user is allowed to access the system based on if the user is present on 
	 * UserList linked list. If the file which UserList was serialized on is not in the directory (because no Users have ever been added to 
	 * the system) then it is possible to access the system using the Userlist's head's properties. 
	 * 
	 * FIRST LOGIN ONLY CREDENTIALS
	 * 
	 * User Type: Admin
	 * Username: username
	 * Password: password 
	 * 
	 * After one Admin has been added to the system than this option will not be available anymore.
	 * 
	 * If the user forgets his login credentials he will have the option to go to a Recover interface that will allow him to retrieve his data
	 * by having them sent to the email that was inserted in the system. */
	
	// ==== PROPERTIES ====
	
	// DATA ELEMENTS
	
	// Screen dimensions
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
	// Create an empty UserList class that will be loaded with the data from the .ser file
	private UserList data;
	
	// Properties that will store the data read from the JTextField, JPasswordField and JComboBox
	private String username;
	private String password;
	private String accType;
	
	// Options for JComboBox
	private String[] accOpt = {"Admin", "Doctor", "Receptionist"};
	
	// Options for credentials recovery mode 
	private String[] options = {"Try Again", "Forgot Credentials"};
	
	
	// GUI ELEMENTS
	
	// Panels
	private JPanel rightPnl;
	private JPanel leftPnl;
	
	// Labels
	private JLabel nameLbl;
	private JLabel imageLbl;
	private JLabel accTypeLbl;
	private JLabel userLbl;
	private JLabel passLbl;
	private JLabel fillLbl1;
	private JLabel fillLbl2;
	private JLabel fillLbl3;
	private JLabel fillLbl4;

	// Icons
	private Icon   image1;
	
	// Input fields
	private JComboBox<String> typeBox; 
	private JTextField userTxt;
	private JPasswordField passTxt;
	
	// Buttons
	private JButton loginBtn;
	private JButton exitBtn;
	
	
	
	// ==== CONSTRUCTOR ====
	
	Interface() {
	
		// Panels
		
		leftPnl = new JPanel();
		rightPnl = new JPanel();
		leftPnl.setLayout(new BorderLayout());
		rightPnl.setLayout(new GridLayout(6,2));
		
		// Labels
		
		
		// Left side
		nameLbl = new JLabel("i-Medic", SwingConstants.CENTER);
		nameLbl.setFont(new Font("Century Gothic", Font.PLAIN, 60));
		
		
		
		// Right side
		accTypeLbl = new JLabel("User Type ");
		userLbl = new JLabel("Username :");
		passLbl = new JLabel("Password :");
		accTypeLbl.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		userLbl.setFont(new Font("Century Gothic", Font.PLAIN, 22)); 
		passLbl.setFont(new Font("Century Gothic", Font.PLAIN, 22)); 
		
		// Fill labels
		fillLbl1 = new JLabel(" ");
		fillLbl2 = new JLabel(" ");
		fillLbl3 = new JLabel(" ");
		fillLbl4 = new JLabel(" ");
		fillLbl1.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		fillLbl2.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		fillLbl3.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		fillLbl4.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		
		
		// Icons
		
		image1 = new ImageIcon("image1.png");
		imageLbl = new JLabel(image1);
		
		// Buttons
		
		loginBtn = new JButton("Login");
		exitBtn = new JButton("Exit");
		
		// Buttons ActionListeners
	    loginBtn.addActionListener(this);
	    exitBtn.addActionListener(this);
		
	    
		// Input Fields
		
		typeBox = new JComboBox<String>(accOpt);
		userTxt = new JTextField(20);
		passTxt = new JPasswordField(20);
		
		
		// Add Components to JPanels
		
		leftPnl.add(nameLbl, BorderLayout.PAGE_START);
		leftPnl.add(imageLbl, BorderLayout.CENTER);
		
		rightPnl.add(fillLbl1);
		rightPnl.add(fillLbl2);
		rightPnl.add(accTypeLbl);
		rightPnl.add(typeBox);
		rightPnl.add(userLbl);
		rightPnl.add(userTxt);
		rightPnl.add(passLbl);
		rightPnl.add(passTxt);
		rightPnl.add(fillLbl3);
		rightPnl.add(fillLbl4);
		rightPnl.add(loginBtn);
		rightPnl.add(exitBtn);
		
		
		
		// Define Container 
		
		Container pane = this.getContentPane();   
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		pane.add(leftPnl);
		pane.add(rightPnl);
		

		// GUI properties
		
	    this.setSize(800,250);
	    this.setVisible(true);
	    this.setResizable(false);
	    this.setLocation((screen.width - 700)/2,((screen.height-400)/2));
	    
	    // Exit system when exit JFrame
	    
	    this.addWindowListener(new WindowAdapter(){
	            public void windowClosing(WindowEvent e){
	                System.exit(0);
	            }
	    });
			
	    
	} // END CONSTRUCTOR
	
	
	
	
	
	
	// ==== CLASS METHODS ====
	
	
	/* The read() method reads the user inputs from the JTextFields and JComboBox and assigns those inputs to the class' private properties 
	 * checking if the .ser file exists. If it exists than the head of the list, which has default username "username" and password "password",
	 * should not be a valid login so the Interface does not assign "username" to the class property. Whenever the check() method checks if the
	 * properties match the linked list then the function will always return a not-matched integer. If the file does not exists than the head of
	 * the list is a valid login. This is done so that the user will still be able to login even the .ser file has never been created. */  
	
	public void read() {
		
		int type;										/* ====> Stores the JComboBox index */
		char[] password;								/* ====> Stores the JPasswordField input in an array of characters */
		
		
		// Check if the user.ser file exists
		
		File f = new File("user.ser");					/* ====> Create an abstract representation of user.ser */
		
    	if(f.exists() && !f.isDirectory()) { 			/* ====> If the file exists than the Userlist head is not a valid user for login */
    		
    		// Read from JComboBox and assign Strings to accType property base on the selected index
    		type = typeBox.getSelectedIndex();
    		
    		if(type == 0){
    			this.accType = "Admin";
    		}
    		else if(type == 1){
    			this.accType = "Doctor";
    		}
    		else {
    			this.accType = "Receptionist";
    		}
    		
    		
    		
    		if(userTxt.getText().trim().equals("username")){	/* ====> If the input is "username" (same as head's username property) than */
    			this.username = "Not Valid";					/* ====> username is assigned "Not valid" */
    			this.password = "Not Valid";					/* ====> password is assigned "Not valid" */
    		}
    		
    		else {    	
    			this.username = userTxt.getText().trim();
   
    			// Read from JTextField and JPasswordField and assign Strings to the class' private variables
    			password = passTxt.getPassword();
    			this.password = new String(password); 		
    		}
    		
    	}
    	
    	else{											/* ====> If the file doesn't exists than the Userlist head is a valid user for login */
    		
    	// Read from JComboBox and assign Strings to accType property base on the selected index
    		
		type = typeBox.getSelectedIndex();
		
		if(type == 0){
			this.accType = "Admin";
		}
		else if(type == 1){
			this.accType = "Doctor";
		}
		else {
			this.accType = "Receptionist";
		}
		
		// Read from JTextField and JPasswordField and assign Strings to the class' private variables
		
		this.username = userTxt.getText().trim();
		password = passTxt.getPassword();
		this.password = new String(password);
		
    	}
	}
	
	
	/* The check() mehod verifies if the data stored in the class' properties matches any User on UserList and returns a different integer
	 * based on the User account type (Admin, Doctor, Receptionist).
	 * Retrieve username password and account type of a User who has same username as the input, if there is no User with same username 
	 * than the function findUser(..) returns the head of UserList. Based on the read() method if the file .ser exists the head is not a
	 * valid login so the class property username will be "Not Valid" and it wouldn't match the head's actual username which is "username"  */
	
	public int check(){
	

	String userCheck;
	String passCheck;
	String accTypeCheck;
	int result = 0;	
		
	// Retrieve username password and account type of a User who has same username as the input
	userCheck = data.findUser(this.username).getUsername();
	passCheck = data.findUser(this.username).getPassword();
	accTypeCheck = data.findUser(this.username).getAccType();
	
	// Check if the class properties username, password and accType match the User's properties
	if(username.equals(userCheck) && password.equals(passCheck) && accType.equals(accTypeCheck)){
		
		// Assign a different result based on which account type (Admin, Doctor, Receptionist) is logging in
		if(accType.equals("Admin")){
			result = 1;
		}
		else if(accType.equals("Doctor")){
			result = 2;
		}
		else {
			result = 3;
		}
		
	}
	
	else {
		result = 0; 						/* ====> If the data doesn't match the result gets 0*/
	}
	
	 return result;							/* ====> Return result of the check */ 
	 
	}
	

	
	
	// The loadData() method instantiates the Userlist data property and loads the data of the serialized file into the newly instantiated Class 
	public void loadData(){
		
		// Instantiate data using default Userlist constructor
		
		this.data = new UserList();					/* =====> data is empty at this point */
		
		File f = new File("user.ser");				/* ====> Create an abstract representation of user.ser */
	    
		if(f.exists() && !f.isDirectory()) { 		
			data.load();						/* ====> If file exists use the .load() method from Userlist class to load existing data */
		}
		else {									/* ====> If there are no existing Users */
	    	JOptionPane.showMessageDialog(this, "No employees in the database. Use default credentials.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	    	
	}
	
	
	

	
	// ==== ACTION EVENT READER ====
	
	public void actionPerformed(ActionEvent e){
		
	    if (e.getSource() == exitBtn){						/* ====> If user clicks on exitBtn */
	    	System.exit(0);									/* ====> Quit the program */
	    }
	    
	    
	    
	    else if(e.getSource() == loginBtn){					/* ====> If user clicks on loginBtn */
	    	
	    	read();											/* ====> Read input from GUI */
	    	
	    	int checked = check();							/* ====> Call check() function and store return value in a variable */
	    	
	    	
	    	/* Perform different tasks depending on the return value of check() and opens three different menu if the value
	    	 * is 1, 2 or 3. The check failed if the check() method returned 0.
	    	 */
	    	
	    	if(checked== 1) {																					/* ====> The user is an Admin */
	    		// Success message dialog
	    		JOptionPane.showMessageDialog(this, "Welcome", "Admin.", JOptionPane.PLAIN_MESSAGE);
	    		// Go to Admin menu
	    		JFrame adm = new AdmMenu();
	    	    adm.setVisible(true);
	    	    // Close current frame
	    	    this.dispose();
	    	} 
	    	
	    	else if(checked == 2) {																				/* ====> The user is a Doctor */
	    		// Success message dialog
	    		JOptionPane.showMessageDialog(this, "Welcome", "Doctor.", JOptionPane.PLAIN_MESSAGE);
	    		// Go to Doctor menu
	    		JFrame doc = new DocMenu();
	    	    doc.setVisible(true);
	    	    // Close current frame
	    	    this.dispose();
	    		}
	    	else if(checked == 3) {																				/* ====> The user is a Receptionist */
	    		// Success message dialog
	    		JOptionPane.showMessageDialog(this, "Welcome", "Receptionist.", JOptionPane.PLAIN_MESSAGE);
	    		// Go to Doctor menu
	    		JFrame rec = new RecMenu();
	    	    rec.setVisible(true);
	    	    // Close current frame
	    	    this.dispose();
	    		}
	    	
	    	
	    	else {																						/* ====> Username and password do not match */
	    		
	    		
	    		// Error option dialog
			    int n = JOptionPane.showOptionDialog(this, "Wrong username or password", "Error",
			    		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
	    		
			    // If user clicks on Try again 
			    if( n == 0){
			    	
			    	//Try Again 
			    }
			    // If user clicks on Forgot credentials goes to Recover interface
			    else if (n == 1){
			    	// Go to Recover interface
			    	Recover rec = new Recover();
			    	rec.setVisible(true);
			    }
	    	}
	    	
	    } // end if
	  } // end actionPerformed
	
	
	
	// ==== MAIN ====
	
	public static void main(String[] args){
	    Interface h = new Interface();
	    h.loadData();
	} // END MAIN 
	    
} // END CLASS



	
	
	
