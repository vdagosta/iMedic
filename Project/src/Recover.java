import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.*;

public class Recover extends JFrame implements ActionListener{

	/* The Recover class allows the user to retrieve his login credentials by having them sent to the email that was inserted
	 * in the system. It checks if the input email is in the system and if it is then it sends the user's credentials to that email.
	 * 
	 * If the user has no internet connection then a error message will pop up. */

	// ==== PROPERTIES ====
	
	// DATA ELEMENTS
	
	// Screen dimensions
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			
	// Create an empty UserList class that will be loaded with the data from the .ser file
	private UserList data;
	
	// Properties that will store the data read from the JTextField
	private String email;
	
	
	
	
	// GUI ELEMENTS
	
	// Panels
	private JPanel top;
	private JPanel right;
	private JPanel bottom;
	private JPanel left;
	
	// Labels
	private JLabel title;
	private JLabel emailLbl;
	private JLabel fillLbl1;
	private JLabel fillLbl2;
	private JLabel imageLbl;
	
	// Icons
	private ImageIcon image;
	
	// Input fields
	private JTextField emailTxt;
	
	// Buttons
	private JButton sendBtn;
	private JButton cancelBtn;
	
	
	Recover(){
		
		// Panels
		
		top = new JPanel();
		right = new JPanel();
		bottom = new JPanel();
		left = new JPanel();
		
		top.setLayout(new FlowLayout());
		right.setLayout(new GridLayout(4,1));
		bottom.setLayout(new FlowLayout());
		left.setLayout(new FlowLayout());
		
		
		// Labels
		
		title = new JLabel("Recover Credentials");
		emailLbl = new JLabel("Your I-Medic Associated Email Address");
		title.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		emailLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		fillLbl1 = new JLabel("               ");
		fillLbl1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		fillLbl2 = new JLabel("               ");
		fillLbl2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		
		// Icons
		
		image = new ImageIcon("message.png");
		imageLbl = new JLabel(image);
		
		
		// Buttons
		
		sendBtn = new JButton("Send Email");
		cancelBtn = new JButton("Cancel");
		
		// Buttons ActionListeners
		sendBtn.addActionListener(this);
		cancelBtn.addActionListener(this); 
		
		
		// Input Fields
		
		Font font = new Font("Century Gothic", Font.PLAIN, 16);
		emailTxt = new JTextField(20);
		emailTxt.setPreferredSize(new Dimension(200,40));
		emailTxt.setFont(font);
		
	
		// Add Components to JPanels
		
		top.add(title);
		right.add(emailLbl);
		right.add(emailTxt);
		right.add(fillLbl1);
		bottom.add(sendBtn);
		bottom.add(cancelBtn);
		left.add(imageLbl);
		right.add(fillLbl2);
		
		
		// Define Container 
		
		Container pane = this.getContentPane();   
		pane.setLayout(new BorderLayout());
		pane.add(top, BorderLayout.PAGE_START);
		pane.add(left, BorderLayout.LINE_START);
		pane.add(right, BorderLayout.CENTER);
		pane.add(bottom, BorderLayout.PAGE_END);
		
		
		// GUI properties
		
		this.setLocation((screen.width-400)/2,((screen.height-500)/2));
		this.setSize(500,250);
		this.setVisible(true);
		this.setResizable(false);
	

		// Exit system when exit JFrame
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
    	});
	
	} // END CONSTRUCTOR
	
	
	
	
	// ===== CLASS METHODS =====
	
	
	
	// The read() method reads the user inputs from the JTextField and assigns the input to the class' private properties 
	public void read(){	
			this.email = emailTxt.getText().trim();			
	}
			
	
	/* The check() mehod verifies if the data stored in the class' property email matches any User's email, if it does then it finds the User's 
	 * credentials and returns them in a formatted manner. If it doesn't find the email in the system it returns null */
	public String check(){
			
		// Retrieve username password and email of a User who has same email as the input
		
		String emailCheck = data.findEmail(this.email).getEmail();
			
			
		String message = null;
		String realUsername = null;
		String realPassword = null;
		
		// Check if the class property email matches the User's email property
		if(email.equals(emailCheck)){
				
			realUsername = data.findEmail(this.email).getUsername();		/* ====> Retrieve username */
			realPassword = data.findEmail(this.email).getPassword();		/* ====> Retrieve password */
				
			message = "Your username is [" + realUsername + "] and your password is [" + realPassword + "]";
		}
			
		return message;
	}
	
	
	
		
	
	// The loadData() method instantiates the Userlist data property and loads the data of the serialized file into the newly instantiated Class 
	public void loadData(){
			
		// Instantiate data using default Userlist constructor
		
		this.data = new UserList();							/* =====> data is empty at this point */
		
		File f = new File("user.ser");						/* ====> Create an abstract representation of user.ser */
			    
		if(f.exists() && !f.isDirectory()) { 				
			data.load();									/* ====> If file exists use the .load() method from Userlist class to load existing data */
		}
			    	
		else {												/* ====> If there are no existing Users */
			JOptionPane.showMessageDialog(this, "No employees in the database. Use default credentials.", "Warning", JOptionPane.WARNING_MESSAGE);
			this.dispose();
		}
		
	}
	
	
	// The sendEmail() method allows the user to retrieve his credentials by sending an email to his system associated email address
	public void sendEmail(){

		// Retrieve the email address
		String realEmail = data.findEmail(this.email).getEmail();
			
		// Retrieve credentials if email matches
		String message = check();
			
			
		// If the email has been found in the system and it's not the head of the UserList than send an email 
		if (message != null && !email.equals(" ")){
			
			try {
				// Send email containing a message to a certain email address
				SendEmail email = new SendEmail();
				email.send(message , realEmail);
					
				if(email.isSent()){	
					JOptionPane.showMessageDialog(this, "Recovery email was sent", "Success", JOptionPane.INFORMATION_MESSAGE); /* ====> Success */
					// Close current frame
					this.dispose();
				}
					
				else {
					JOptionPane.showMessageDialog(this, "Error! \n" + "No internet connection", "Error", JOptionPane.ERROR_MESSAGE);/* ====> Error */
					// Close current frame
					this.dispose();
				}
					
			} 
			
			// Double Catch in case the email fails to send
			catch (AddressException e1) {
					e1.printStackTrace();
			} 
			
			catch (MessagingException e1) {
					e1.printStackTrace();
			} //Recover Data
				
		}
		
		// Else if the email has not been found in the system do not send an email and warn the user
		else {
			File f = new File("user.ser");
			  
		    if(f.exists() && !f.isDirectory()) { 
				JOptionPane.showMessageDialog(this, "The I-Medic system does not recognize your email.", "Error", JOptionPane.ERROR_MESSAGE); 
		    }		
		}
				
	}
	
	
	
	// ==== ACTION EVENT READER ====
	
	
	public void actionPerformed(ActionEvent e){
	    if (e.getSource() == sendBtn){							/* ====> If user clicks on sendBtn */
	
	    	read();												/* ====> Read input from GUI */
	    	loadData();											/* ====> Load any existing User */
	    	sendEmail();										/* ====> Send email */
	    	
	    	
	    }
	    
	    else if(e.getSource() == cancelBtn){					/* ====> If user clicks on cancelBtn */
		    this.dispose();										/* ====> Close window */
	    }
	}
	
} // END CLASS
