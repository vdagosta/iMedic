import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.table.*;

public class EmplTable extends JFrame implements ActionListener{

	/* The EmplTable class allows the user to visualize the linked list using a JTable whose size is based on the size
	 * of the linked list. Considering the nature of the .add(..) method of the Userlist class, the employees will be 
	 * added in alphabetical order based on their username property. */
	
	// ==== PROPERTIES ====
	
	// DATA ELEMENTS
	
	// Screen dimensions
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
	// Create an empty UserList class that will be loaded with the data from the .ser file
	private UserList data;
	
	// Create a string that will store the data inserted in the table
	private String tableData = " ";
	
	// JTable column names
	private String[] columnNames = {"Username", "Password", "Full Name", "Account Type", "Email", "Salary"};
	
	
	// GUI ELEMENTS
	
	// Panels
	private JScrollPane scrollPane;
	private JPanel bottomPnl;
	
	// Table
	private JTable table;
	
	// Buttons
	private JButton menu;
	
	
	// ==== CONSTRUCTOR ====
	
	EmplTable(){
		
		
		//  ===== TABLE ======
		
		// DATA LOADING
		
		// Instantiate data using default Userlist constructor
		this.data = new UserList();					/* =====> data is empty at this point */
				
				 
		File f = new File("user.ser");				/* ====> Create an abstract representation of user.ser */
				 
				
		// Check if the file exists

		if(f.exists() && !f.isDirectory()) { 
		data.load();								/* ====> if file exists use the .load() method from Userlist class to load existing data */
		}
		

		
		// COLLECT DATA AND STORE IT IN 2D ARRAY
	    		
	    // Iterate through the list using a User marker 
	    User marker = data.getHead();
	    marker = marker.getNext();
	    
	    // Getting list's size
	    int size = data.getSize();
	    
	    // Creating 2D array based on the list's size 
		String[][] myArray = new String[size][];
			for(int i=0;i<size;i++){
				myArray[i]= new String[6];    
			}
		
		// Inserting data into 2D array, retrieving different data from the User node based on the index j. 
	    for(int i=0;i<size;i++){
	        for(int j=0;j<6;j++){
	        	if(j==0){
            		tableData = marker.getUsername();
            	}
            	else if(j==1){
            		tableData = marker.getPassword();
            	}
            	else if(j==2){
            		tableData = marker.getFullName();
            	}
            	else if(j==3){
            		tableData = marker.getAccType();
            	}
            	else if(j==4){
            		tableData = marker.getEmail();
            	}
            	else if(j==5){
            		tableData = marker.getSalary();
            	}
 
	        // The array gets the data at row i and column j	
            myArray[i][j] = tableData;
           
	        } // END j LOOP
	        
	        // At the end of each column the marker moves to next element of the list
	        if(marker.getNext() != null){
	        	marker = marker.getNext(); 
        	}
	        
	    } // END i LOOP
	    
	    // Create JTable 
		table = new JTable(myArray, columnNames);
		table.setFillsViewportHeight(true);
		
		// Center data in middle of column
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		for(int i=0;i<6;i++){
		table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
	    
	    
		
		
		
		// ===== OTHER FRAME ELEMENTS =====
		
		// Panels
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1000, 1000));
		
		bottomPnl = new JPanel();
		bottomPnl.setLayout(new BorderLayout());
		bottomPnl.setPreferredSize(new Dimension(1000, 40));
		
		
		// Buttons
		
		Font myFont = new Font("Century Gothic", Font.PLAIN,20);
		menu = new JButton("Menu");
		menu.setFont(myFont);
		
		// Buttons ActionListeners
		menu.addActionListener(this);
		
		// Add Components to JPanels
		
		bottomPnl.add(menu, BorderLayout.LINE_END);
		
		// Define Container 
		
		Container pane = this.getContentPane();   
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.add(scrollPane);
		pane.add(bottomPnl);
		
		// GUI properties
		
		this.setSize(1000,500);
		this.setVisible(true);
		this.setLocation((screen.width-900)/2,((screen.height-600)/2));
		
		// Exit system when exit JFrame
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
    	});
		
	} // END CONSTRUCTOR
	
	
	
	// ==== ACTION EVENT READER ====
	
	public void actionPerformed(ActionEvent e){
	    if (e.getSource() == menu){						/* ====> If user clicks on saveBtn */
	    	
	    	// Go to Admin menu
	    	JFrame adm = new AdmMenu();
    	    adm.setVisible(true);
    	    
    	 // Close current frame
    	    this.dispose();
	    }    
	}
	
} // END CLASS
	
	

	

