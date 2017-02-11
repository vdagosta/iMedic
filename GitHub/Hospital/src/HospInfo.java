import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class HospInfo extends JFrame implements ActionListener {

	
	// ==== PROPERTIES ====
	
	// DATA ELEMENTS
	
	// Screen dimensions
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	// Create an empty UserList class that will be loaded with the data from the .ser file
	private UserList userData;
	
	// Create an empty PatientList class that will be loaded with the data from the .ser file
	//private PatientList patintData;
		
	// Properties that store the data that will be inserted in the JLabels
	private String totUsers = "0";    /* ====> Read from UserList */
	private String totAdm = "0";		/* ====> Read from UserList */
	private String totDoc = "0";		/* ====> Read from UserList */
	private String totRec = "0"; 		/* ====> Read from UserList */
	private String totPat = "0";		/* ====> Read from PatientList */
	private String maxPat = "525";	
	private String maxUsers = " 1200";
	private String maxCap = "1725";
	
	
	// GUI ELEMENTS
		
	//Panels
	private JPanel mainPnl;
	private JPanel topPnl;	
	private JPanel botPnl;
		
	// Labels
	private JLabel nameLbl;
	private JLabel imageLbl;
	
	private JLabel totUsersLbl;
	private JLabel totUsersOut;
	private JLabel totAdmLbl;
	private JLabel totAdmOut;
	private JLabel totDocLbl;
	private JLabel totDocOut;
	private JLabel totRecLbl;
	private JLabel totRecOut;
	private JLabel totPatLbl;
	private JLabel totPatOut;
	private JLabel maxPatLbl;
	private JLabel maxPatOut;
	private JLabel maxUsersLbl;
	private JLabel maxUsersOut;
	private JLabel maxCapLbl;
	private JLabel maxCapOut;
	
	
	// Icons
	private ImageIcon imageTop;
	
	// Buttons
	private JButton backBtn;
	
	// ==== CONSTRUCTOR ====

	HospInfo(){
	
		// Panels 
		
		topPnl = new JPanel();	
		mainPnl = new JPanel();
		mainPnl.setLayout(new GridLayout(8,2));
		botPnl = new JPanel();
	
		// Labels
		
		CheckList();
		
		// Left Side
		nameLbl = new JLabel("i-Medic", SwingConstants.CENTER);
		nameLbl.setFont(new Font("Century Gothic", Font.PLAIN, 65));
		totUsersLbl = new JLabel(" Current number of Employees :");
		totUsersLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		totAdmLbl = new JLabel(" Current number of Admins :");
		totAdmLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		totDocLbl = new JLabel(" Current number of Doctors :");
		totDocLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		totRecLbl = new JLabel(" Current number of Receptionists :");
		totRecLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		totPatLbl = new JLabel(" Current number of Patients :");
		totPatLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		maxPatLbl = new JLabel("  Max number of beds :");
		maxPatLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		maxUsersLbl = new JLabel("  Max Employee capacity :");
		maxUsersLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		maxCapLbl = new JLabel("  Max Hospital capacity :");
		maxCapLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
	
		// Right Side
		totUsersOut = new JLabel(totUsers , SwingConstants.CENTER);
		totUsersOut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		totAdmOut = new JLabel(totAdm , SwingConstants.CENTER);
		totAdmOut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		totDocOut = new JLabel(totDoc , SwingConstants.CENTER);
		totDocOut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		totRecOut = new JLabel(totRec, SwingConstants.CENTER);
		totRecOut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		totPatOut = new JLabel(totPat , SwingConstants.CENTER);
		totPatOut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		maxPatOut = new JLabel(maxPat , SwingConstants.CENTER);
		maxPatOut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		maxUsersOut = new JLabel(maxUsers , SwingConstants.CENTER);
		maxUsersOut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		maxCapOut = new JLabel(maxCap , SwingConstants.CENTER);
		maxCapOut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		// Icons
		
		imageTop = new ImageIcon("image1.png");
		imageLbl = new JLabel(imageTop);
		
		// Buttons
		
		Font myFont = new Font("Century Gothic", Font.PLAIN,20);
		backBtn = new JButton("Menu");
		backBtn.setFont(myFont);
		backBtn.setFocusPainted(false);
		
		// Buttons ActionListeners
		backBtn.addActionListener(this);

		
		//Add Components to JPanels
		
		topPnl.add(nameLbl);
		topPnl.add(imageLbl);
		
		mainPnl.add(totUsersLbl);
		mainPnl.add(totUsersOut);
		mainPnl.add(totAdmLbl);
		mainPnl.add(totAdmOut);
		mainPnl.add(totDocLbl);
		mainPnl.add(totDocOut);
		mainPnl.add(totRecLbl);
		mainPnl.add(totRecOut);
		mainPnl.add(totPatLbl);
		mainPnl.add(totPatOut);
		mainPnl.add(maxUsersLbl);
		mainPnl.add(maxUsersOut);
		mainPnl.add(maxPatLbl);
		mainPnl.add(maxPatOut);
		mainPnl.add(maxCapLbl);
		mainPnl.add(maxCapOut);
		
		botPnl.add(backBtn, BorderLayout.LINE_END);
		
		// Define Container 
		
		Container pane = this.getContentPane();   
		pane.setLayout(new BorderLayout());
		pane.add(topPnl, BorderLayout.PAGE_START);
		pane.add(mainPnl, BorderLayout.LINE_START);
		pane.add(botPnl, BorderLayout.PAGE_END);
		
		// GUI properties
		
		this.setSize(510,700);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation((screen.width-500)/2,((screen.height-900)/2));
		
		// Exit system when exit JFrame
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
    	});
		
	
	} // END CONSTRUCTOR
	
	
	
	// ===== CLASS METHODS =====
	
	
	// The loadUserData() method instantiates the UserList data property and loads the data of the serialized file into the newly instantiated Class 
	public void loadUserData(){
			
		// Instantiate data using default Userlist constructor
		this.userData = new UserList();					/* =====> data is empty at this point */
			
			 
		File f = new File("user.ser");				/* ====> Create an abstract representation of user.ser */
			 
			
		// Check if the file exists

		if(f.exists() && !f.isDirectory()) { 
			userData.load();					/* ====> if file exists use the .load() method from Userlist class to load existing data */
		}
			    		
	}

	// The loadPatientData() method instantiates the PatientList data property and loads the data of the serialized file into the newly instantiated Class 

//	 	public void loadPatientData(){
//	
//				
//		// Instantiate data using default Userlist constructor
//		this.userData = new UserList();					/* =====> data is empty at this point */
//				
//				 
//		File f = new File("user.ser");				/* ====> Create an abstract representation of user.ser */
//				 
//				
//		// Check if the file exists
//
//		if(f.exists() && !f.isDirectory()) { 
//			userData.load();					/* ====> if file exists use the .load() method from Userlist class to load existing data */
//		}
//				    		
//	}
	
	
	public void CheckList(){
		
		// USERLIST 
		
		loadUserData();
		
		
		int totUsers = userData.getSize();
		int totAdm = userData.getAdmSize();
		int totDoc = userData.getDocSize();
		int totRec = userData.getRecSize();
		
		this.totUsers = Integer.toString(totUsers);
		this.totAdm = Integer.toString(totAdm);
		this.totDoc = Integer.toString(totDoc);
		this.totRec = Integer.toString(totRec);
		
		// PATIENTLIST
		
		//loadPatientData();
		
		//int totPat = patientData.getSize();
		//this.totPat = Integers.toString(totPat);
		
	}
	
	
	// ==== ACTION EVENT READER ====
	
	public void actionPerformed(ActionEvent e){
	    if (e.getSource() == backBtn){						/* ====> If user clicks on saveBtn */
	    	
	    	// Go to Admin menu
	    	JFrame adm = new AdmMenu();
    	    adm.setVisible(true);
    	    
    	 // Close current frame
    	    this.dispose();
	    }    
	}
	
	
	
	
	
	
	
}
