import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class AddPat extends JFrame implements ActionListener{
	
	/* The AddPat class allows to read the input from the GUI's JTextFields and JComboBox and add a new Patient
	 * with the same properties as the input to its linked list. Considering the nature of the .add(..) method
	 * of the PatientList class, the patients will be added in alphabetical order based on their fullName property. */
	
	// ==== PROPERTIES ====
	
	// DATA ELEMENTS
	
	// Screen dimensions
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	// Create an empty PatientList class that will be loaded with the data from the .ser file
	private PatientList data;
	
	// Properties that will store the data read from the JTextFields and JComboBox
	private String fullName;
	private String dob;
	private String phone;
	private String gender;
	private String email;
	private String bill;
	
	// Options for JComboBox
	private String[] genderOpt = {"Male", "Female", "Other"};
	
	
	// GUI ELEMENTS
	
	// Panels
	private JPanel right;
	private JPanel top;
	private JPanel left;
	private JPanel bottom;
	
	// Labels
	private JLabel titleLbl;
	private JLabel genderLbl;
	private JLabel dobLbl;
	private JLabel phoneLbl;
	private JLabel fullLbl;
	private JLabel emailLbl;
	private JLabel billLbl;
	private JLabel imageLbl;
	
	// Icons
	private Icon image;
	
	// Input Fields
	private JComboBox<String> genderTxt;
	private JTextField dobTxt;
	private JTextField phoneTxt;
	private JTextField fullTxt;
	private JTextField emailTxt;
	private JTextField billTxt;
	
	
	// Buttons
	private JButton cancelBtn;
	private JButton saveBtn;

	
	// ==== CONSTRUCTOR ====

	AddPat(){
		
		// Panels 
		
		top = new JPanel();
		
		left = new JPanel();
		left.setPreferredSize(new Dimension(150, 150));
		
		right = new JPanel();
		right.setLayout(new GridLayout(6,2));
		right.setPreferredSize(new Dimension(400, 250));
		
		bottom = new JPanel();
		
		
		// Labels
		
		titleLbl = new JLabel("Add new patient");
		genderLbl = new JLabel("Gender :");
		dobLbl = new JLabel("Date of birth :");
		phoneLbl = new JLabel("Phone # :");
		fullLbl = new JLabel("Full Name :");
		emailLbl = new JLabel("Email :");
		billLbl = new JLabel("Billing Information :");
		
		
		titleLbl.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		genderLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		dobLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18)); 
		phoneLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18)); 
		fullLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		emailLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18)); 
		billLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		
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
		
		genderTxt = new JComboBox<String>(genderOpt);
		dobTxt = new JTextField(25);
		phoneTxt = new JTextField(25);
		fullTxt = new JTextField(25);
		emailTxt = new JTextField(25);
		billTxt = new JTextField(25);
		
		
		genderTxt.setFont(font);
		dobTxt.setFont(font);
		phoneTxt.setFont(font);
		fullTxt.setFont(font);
		emailTxt.setFont(font);
		billTxt.setFont(font);

		
		// Add Components to JPanels
		
		right.add(fullLbl);
		right.add(fullTxt);
		right.add(dobLbl);
		right.add(dobTxt);
		right.add(genderLbl);
		right.add(genderTxt);
		right.add(phoneLbl);
		right.add(phoneTxt);
		right.add(emailLbl);
		right.add(emailTxt);
		right.add(billLbl);
		right.add(billTxt);
		
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
		
		// Read from JComboBox and assign Strings to the gender property based on the selected index
		
		int type = genderTxt.getSelectedIndex();
    	if(type == 0){
    		this.gender = "Male";
    	}
    	else if(type == 1){
    		this.gender = "Female";
    	}
    	else {
    		this.gender = "Other";
    	}
			
    	
    	// Read from JTextFields and assign Strings to the class' private variables
    	
		this.dob = dobTxt.getText().trim();
		this.phone = phoneTxt.getText().trim();
		this.fullName = fullTxt.getText().trim();
		this.email = emailTxt.getText().trim();
		this.bill = billTxt.getText().trim();
		
		
	}
	

	
	// The loadData() method instantiates the PatientList data property and loads the data of the serialized file into the newly instantiated Class 
	public void loadData(){
		
		// Instantiate data using default PatientList constructor
		this.data = new PatientList();					/* =====> data is empty at this point */
		
		 
		File f = new File("patient.ser");				/* ====> Create an abstract representation of patient.ser */
		 
		
		// Check if the file exists

		if(f.exists() && !f.isDirectory()) { 
			data.load();					/* ====> if file exists use the .load() method from PatientList class to load existing data */
		}
		    	
		else {
		    try {
		    	f.createNewFile();		/* ====> if file doesn't exists create a new file with that name and save on it a default(empty) PatientList */
		    	data.save();
		    }
		    catch(Exception a){
		    	System.out.println("Error creating File");
		    }	
		
		}
	}
		
		
	
	
	// The saveData() method is used after the loadData() to add the user input to the existing .ser file 
	public void saveData(){
		
	
		// Use the PatientList .add(..) method to add the user input to the PatientList that was previously loaded
		data.add(fullName, dob, phone, gender, email, bill);
		
		// User the PatientList .save() method to save the Linked list to the patient.ser file
		data.save();
		
	}
	
	
	// ==== ACTION EVENT READER ====
	
	public void actionPerformed(ActionEvent e){
	    if (e.getSource() == saveBtn){					/* ====> If user clicks on saveBtn */
	    	
	    	
	    	// Read the data from the GUI using the read() method 
			read();
			

	    	loadData();									/* ====> Load existing data */
	    	saveData();									/* ====> Save new data */
	    	
	    	// Success message dialog
	    	JOptionPane.showMessageDialog(this, "Patient was added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
	    	// Go to Doc menu
	    	JFrame doc = new DocMenu();
    	    doc.setVisible(true);
    	    
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