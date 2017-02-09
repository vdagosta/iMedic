import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class AddEmpl extends JFrame implements ActionListener{
	
	/* The AddEmpl class allows to read the input from the GUI's JTextFields and JComboBox and add a new Employee(User)
	 * with the same properties as the input to its linked list. Considering the nature of the .add(..) method
	 * of the Userlist class, the employees will be added in alphabetical order based on their username property. The first
	 * User added to a new system with non existing .ser file must be an Admin so that at least one user will be able to 
	 * enter the system again after quitting . */
	
	// ==== PROPERTIES ====
	
	// DATA ELEMENTS
	
	// Screen dimensions
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	// Create an empty UserList class that will be loaded with the data from the .ser file
	private UserList data;
	
	// Properties that will store the data read from the JTextFields and JComboBox
	private String fullName;
	private String username;
	private String password;
	private String accType;
	private String email;
	private String salary;
	
	// Options for JComboBox
	private String[] accOpt = {"Admin", "Doctor", "Receptionist"};
	
	
	// GUI ELEMENTS
	
	// Panels
	private JPanel right;
	private JPanel top;
	private JPanel left;
	private JPanel bottom;
	
	// Labels
	private JLabel titleLbl;
	private JLabel accLbl;
	private JLabel userLbl;
	private JLabel passLbl;
	private JLabel fullLbl;
	private JLabel emailLbl;
	private JLabel salaryLbl;
	private JLabel imageLbl;
	
	// Icons
	private Icon image;
	
	// Input Fields
	private JComboBox<String> accTxt;
	private JTextField userTxt;
	private JTextField passTxt;
	private JTextField fullTxt;
	private JTextField emailTxt;
	private JTextField salaryTxt;
	
	
	// Buttons
	private JButton cancelBtn;
	private JButton saveBtn;

	
	// ==== CONSTRUCTOR ====

	AddEmpl(){
		
		// Panels 
		
		top = new JPanel();
		
		left = new JPanel();
		left.setPreferredSize(new Dimension(150, 150));
		
		right = new JPanel();
		right.setLayout(new GridLayout(6,2));
		right.setPreferredSize(new Dimension(400, 250));
		
		bottom = new JPanel();
		
		
		// Labels
		
		titleLbl = new JLabel("Add new employee");
		accLbl = new JLabel("User Type :");
		userLbl = new JLabel("Username :");
		passLbl = new JLabel("Password :");
		fullLbl = new JLabel("Full Name :");
		emailLbl = new JLabel("Email :");
		salaryLbl = new JLabel("Salary :");
		
		
		titleLbl.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		accLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		userLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18)); 
		passLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18)); 
		fullLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		emailLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18)); 
		salaryLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		
		// Icons
		
		image = new ImageIcon("add.png");
		imageLbl = new JLabel(image);
		
		// Buttons
	
		cancelBtn = new JButton("Cancel");
		saveBtn = new JButton("Save  ");
		cancelBtn.setFocusPainted(false);
		saveBtn.setFocusPainted(false);
		
		// Buttons ActionListeners 
		saveBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		
		// Input Fields
		Font font = new Font("Century Gothic", Font.PLAIN, 16);
		
		accTxt = new JComboBox<String>(accOpt);
		userTxt = new JTextField(25);
		passTxt = new JTextField(25);
		fullTxt = new JTextField(25);
		emailTxt = new JTextField(25);
		salaryTxt = new JTextField(25);
		
		
		accTxt.setFont(font);
		userTxt.setFont(font);
		passTxt.setFont(font);
		fullTxt.setFont(font);
		emailTxt.setFont(font);
		salaryTxt.setFont(font);

		
		// Add Components to JPanels
		
		right.add(userLbl);
		right.add(userTxt);
		right.add(passLbl);
		right.add(passTxt);
		right.add(fullLbl);
		right.add(fullTxt);
		right.add(accLbl);
		right.add(accTxt);
		right.add(emailLbl);
		right.add(emailTxt);
		right.add(salaryLbl);
		right.add(salaryTxt);
		
		top.add(titleLbl);
		left.add(imageLbl);
		bottom.add(saveBtn);
		bottom.add(cancelBtn);
		
		
		
		// Define Container 
		
		Container pane = this.getContentPane();   
		pane.setLayout(new BorderLayout());
		pane.add(top, BorderLayout.PAGE_START);
		pane.add(right, BorderLayout.CENTER);
		pane.add(left, BorderLayout.LINE_START);
		pane.add(bottom, BorderLayout.PAGE_END);
		
		// GUI properties
		
		this.setSize(650,350);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation((screen.width - 500)/2,((screen.height-500)/2));	
		

		// Exit system when exit JFrame
		
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
		
		
	} // END CONSTRUCTOR

	
	
	
	// ===== CLASS METHODS =====
	
	
	// The read() method reads the user inputs from the JTextFields and JComboBox and assigns those inputs to the class' private properties 
	public void read() {
		
		// Read from JComboBox and assign Strings to accType property based on the selected index
		
		int type = accTxt.getSelectedIndex();
    	if(type == 0){
    		this.accType = "Admin";
    	}
    	else if(type == 1){
    		this.accType = "Doctor";
    	}
    	else {
    		this.accType = "Receptionist";
    	}
			
    	
    	// Read from JTextFields and assign Strings to the class' private variables
    	
		this.username = userTxt.getText().trim();
		this.password = passTxt.getText().trim();
		this.fullName = fullTxt.getText().trim();
		this.email = emailTxt.getText().trim();
		this.salary = salaryTxt.getText().trim();
		
		
	}
	

	
	// The loadData() method instantiates the Userlist data property and loads the data of the serialized file into the newly instantiated Class 
	public void loadData(){
		
		// Instantiate data using default Userlist constructor
		this.data = new UserList();					/* =====> data is empty at this point */
		
		 
		File f = new File("user.ser");				/* ====> Create an abstract representation of user.ser */
		 
		
		// Check if the file exists

		if(f.exists() && !f.isDirectory()) { 
			data.load();					/* ====> if file exists use the .load() method from Userlist class to load existing data */
		}
		    	
		else {
		    try {
		    	f.createNewFile();		/* ====> if file doesn't exists create a new file with that name and save on it a default(empty) Userlist */
		    	data.save();
		    }
		    catch(Exception a){
		    	System.out.println("Error creating File");
		    }	
		
		}
	}
		
		
	
	
	// The saveData() method is used after the loadData() to add the user input to the existing .ser file 
	public void saveData(){
		
	
		// Use the Userlist .add(..) method to add the user input to the Userlist that was previously loaded
		data.add(fullName, username, password, accType, email, salary);
		
		// User the Userlist .save() method to save the Linked list to the user.ser file
		data.save();
		
	}
	
	
	// ==== ACTION EVENT READER ====
	
	public void actionPerformed(ActionEvent e){
	    if (e.getSource() == saveBtn){					/* ====> If user clicks on saveBtn */
	    	
	    	
	    	// Read the data from the GUI using the read() method 
			read();
			
	    	if(accType.equals("Admin")){				/* ====> Check if the User that is being added is an Admin */
	    	loadData();									/* ====> Load existing data */
	    	saveData();									/* ====> Save new data */
	    	
	    	// Success message dialog
	    	JOptionPane.showMessageDialog(this, "Employee was added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    	
	    	else if((!accType.equals("Admin"))){		/* ====> Check if the User that is being added is an Admin */
	    		
	    		/* ==== LOAD DATA IF .SER EXISTS WITHOUT CREATING THE FILE IF DOESN'T EXIST ==== */
	    		
	    		// Instantiate data using default Userlist constructor
	    		this.data = new UserList();					/* =====> data is empty at this point */
	    		
	    		 
	    		File f = new File("user.ser");				/* ====> Create an abstract representation of user.ser */
	    		 
	    		
	    		// Check if the file exists
	    		
	    		if(f.exists() && !f.isDirectory()) { 
	    			data.load();					/* ====> if file exists use the .load() method from Userlist class to load existing data */
	    		}
	    		
	    		
	    		/* ==== CHECK IF THERE ARE ADMINS IN THE LIST ==== */
	    		
	    		if(data.getAdmSize() == 0 ){					/* ====> If there are no Admins in UserList show message error */
	    			JOptionPane.showMessageDialog(this, "Error! \n" + "The first User added must be an Admin.", "Error", JOptionPane.ERROR_MESSAGE);
	    		}
	    		else{
	    			loadData();									/* ====> Load existing data */
	    	    	saveData();									/* ====> Save new data */
	    	    	JOptionPane.showMessageDialog(this, "Employee was added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
	    	    	
	    		}
	    	}
	    	// Go to Admin menu
	    	JFrame adm = new AdmMenu();
    	    adm.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    	
	    }
	    
	    else if(e.getSource() == cancelBtn){			/* ====> If user clicks on cancelBtn */
	    	
	    	// Go to Admin menu
	    	JFrame adm = new AdmMenu();
    	    adm.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
    	    
	    }
	}
	
	
} // END CLASS