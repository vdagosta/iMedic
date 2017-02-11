import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class RemEmpl extends JFrame implements ActionListener{

	/* The RemEmpl class allows to read the input from the GUI's JTextField and remove an existing User
	 * with the same properties as the input. The system will not remove a User if that user is the last Admin on the system
	 * so that at least one user will be able to enter the system again after quitting. */
	
	// ==== PROPERTIES ====
	
	// DATA ELEMENTS
	
	// Screen dimensions
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
	// Create an empty UserList class that will be loaded with the data from the .ser file
	private UserList data;
	
	// Property that will store the data read from the JTextField	
	private String username;
	
	
	// GUI ELEMENTS
	
	// Panels
	private JPanel top;
	private JPanel right;
	private JPanel bottom;
	private JPanel left;
	
	// Labels
	private JLabel title;
	private JLabel removeLbl;
	private JLabel imageLbl;
	
	// Icons
	private ImageIcon image;
	
	// Input fields
	private JTextField removeTxt;
	
	// Buttons
	private JButton removeBtn;
	private JButton menuBtn;
	
	// ==== CONSTRUCTOR ====
	
	RemEmpl(){
		
		
		// Panels 
		
		top = new JPanel();
		right = new JPanel();
		bottom = new JPanel();
		left = new JPanel();
		
		top.setLayout(new FlowLayout());
		right.setLayout(new GridLayout(2,2));
		bottom.setLayout(new FlowLayout());
		left.setLayout(new FlowLayout());
		
		
		// Labels
		
		title = new JLabel("Delete employee ");
		removeLbl = new JLabel("Employee's Username:");
		title.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		removeLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		
		// Icons
		
		image = new ImageIcon("remove.png");
		imageLbl = new JLabel(image);
		
		
		// Buttons
		
		removeBtn = new JButton("Delete");
		menuBtn = new JButton("Cancel");
		
		// Buttons ActionListeners
		removeBtn.addActionListener(this);
		menuBtn.addActionListener(this); 

		// Input Fields
		
		removeTxt = new JTextField(20);
		removeTxt.setPreferredSize(new Dimension(200,40));
		
		
		// Add Components to JPanels
		
		top.add(title);
		right.add(removeLbl);
		right.add(removeTxt);
		bottom.add(removeBtn);
		bottom.add(menuBtn);
		top.add(imageLbl);
		
		
		// Define Container 
		
		Container pane = this.getContentPane();   
		pane.setLayout(new BorderLayout());
		pane.add(top, BorderLayout.PAGE_START);
		pane.add(right, BorderLayout.CENTER);
		pane.add(bottom, BorderLayout.PAGE_END);
		
			
		// GUI properties
		
		this.setSize(600,260);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation((screen.width-500)/2,((screen.height-500)/2));

	
		// Exit system when exit JFrame
	
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
    	});
	
	
	} // END CONSTRUCTOR
	
	
	// ===== CLASS METHODS =====
	
	// The read() method reads the user input from the JTextField and assigns the input to the class' private property username
	public void read() {
		this.username = removeTxt.getText().trim();
	}
	
	
	// The loadData() method instantiates the Userlist data property and loads the data of the serialized file into the newly instantiated Class
	public void loadData(){
		
		// Instantiate data using default Userlist constructor	
		this.data = new UserList();
		
		// Check if the file exists
	    File f = new File("user.ser");
	    
	    if(f.exists() && !f.isDirectory()) { 
	    		data.load();						/* ====> if file exists use the .load() method from Userlist class to load existing data */
	    }
	    else {
		    	JOptionPane.showMessageDialog(this, "No employees in the database", "Warning", JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	/* The saveData() method is used after the loadData() to add the user input to the existing .ser file. It checks if the User that
	 * is being deleted is the last Admin, if it is it wont delete it. */
	public void saveData(){
		
		// Look for a user with a certain username. Get the User's accType. 
		String server = data.findUser(username).getAccType();
		
		// Check conditions
		if(data.isEmpty()){ 										/* ====> If the head is the only thing in the UserList, it cannot be removed  --- 1  */
			JOptionPane.showMessageDialog(this, "No employees in the database", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else if (data.getAdmSize() == 1){							/* ====> If there is only one Admin in the system  --- 2*/
			
			if(data.findUser(username)!= data.getHead() && server.equals("Admin")){	/* ====> If trying to delete last Admin or head --- 2.1 */
				JOptionPane.showMessageDialog(this, "Error! \n" + "Last Admin in database cannot be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {													/* ====> --- 2.2 */
		    	if(data.remove(username)){							/* ====> Try to remove it, if removed save the list --- 2.2.1*/
		    		data.save();									/* ====> Serialize the list */
		    		JOptionPane.showMessageDialog(this, "Employee was deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);			
		    	}
		    	else {													/* ====> If not removed send error message --- 2.2.2*/
		    		JOptionPane.showMessageDialog(this, "Username not found", "Warning", JOptionPane.ERROR_MESSAGE);
		    	}
			}
		}
		
		else{														/* ====> If there is more than one Admin in the system --- 3*/
	    	
			if(data.remove(username) && data.findUser(username) != data.getHead()){								/* ====> Try to remove it, if removed save the list --- 3.1 */
	    		data.save();										/* ====> Serialize the list */
	    		JOptionPane.showMessageDialog(this, "Employee was deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    	else {													/* ====> If not removed send error message --- 3.2*/
	    		JOptionPane.showMessageDialog(this, "Username not found", "Warning", JOptionPane.ERROR_MESSAGE);
	    	}
		}	
	}
	
	
	
	// ==== ACTION EVENT READER ====
	
	public void actionPerformed(ActionEvent e){
	    if (e.getSource() == removeBtn){						/* ====> If user clicks on removeBtn */
	    	
	    	// Read the data from the GUI using the read() method 
	    	read();
	    	// Ask the user for confirmation
	    	int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the user " + username + "?", "Warning", JOptionPane.YES_NO_OPTION);
	    	
	    	
	    	// If he decides to delete User
	    	if(choice == 0){
	    		loadData();										/* ====> Load existing data */
		    	saveData();										/* ====> Save new data */
	    	
		    // Go to Admin menu
	    	JFrame adm = new AdmMenu();
    	    adm.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    	}
	    	
	    }
	    else if(e.getSource() == menuBtn){						/* ====> If user clicks on menuBtn */
	    	
	    	// Go to Admin menu
	    	JFrame adm = new AdmMenu();
    	    adm.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    }
	
	}
	
	
} // END CLASS
