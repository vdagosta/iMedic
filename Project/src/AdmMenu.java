import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdmMenu extends JFrame implements ActionListener{

	/* The AdmMenu class is the GUI interface that allows the user to test the functionality of the program  
	 * allowing to navigate the program and offering the options of adding new employees, removing them from
	 * the list, visualize the linked list using a JTable and visualizing the hospitals general information. */
	
	// ==== PROPERTIES ====
	
	// Screen dimensions
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	// GUI ELEMENTS
	
	//Panels
	private JPanel mainPnl;
	private JPanel topPnl;
	private JPanel botPnl;
	
	// Labels
	private JLabel nameLbl;
	private JLabel imageLbl;
	
	// Icons
	private ImageIcon add;
	private ImageIcon remove;
	private ImageIcon list;
	private ImageIcon info;
	private ImageIcon imageTop;
	
	// Buttons
	private JButton newEmpl;
	private JButton deleteEmpl;
	private JButton displayEmpl;
	private JButton infoHosp;
	
	private JButton logoutBtn;

	
	// ==== CONSTRUCTOR ====
	
	AdmMenu(){
		
		// Panels
		
		mainPnl = new JPanel();
		mainPnl.setLayout(new GridLayout(4,1));
			
		topPnl = new JPanel();
		topPnl.setLayout(new FlowLayout());
		
		botPnl = new JPanel();
		
		// Labels
		
		nameLbl = new JLabel("i-Medic", SwingConstants.CENTER);
		nameLbl.setFont(new Font("Century Gothic", Font.PLAIN, 65));
		
		
		// Icons
		
		imageTop = new ImageIcon("image1.png");
		imageLbl = new JLabel(imageTop);
		
		add = new ImageIcon("add.png");
		int width1 = add.getIconWidth();
		int height1 = add.getIconHeight();
		
		remove = new ImageIcon("remove.png");
		int width2 = add.getIconWidth();
		int height2 = add.getIconHeight();
		
		list = new ImageIcon("list.png");
		int width3 = add.getIconWidth();
		int height3 = add.getIconHeight();
		
		info = new ImageIcon("info.png");
		int width4 = add.getIconWidth();
		int height4 = add.getIconHeight();
		
		
		// Buttons
		
		
		Font smallFont = new Font("Century Gothic", Font.PLAIN,20);
		logoutBtn = new JButton("Log Out");
		logoutBtn.setFont(smallFont);
		logoutBtn.setFocusPainted(false);
		
		
		Font myFont = new Font("Century Gothic", Font.PLAIN,35);
		
		
		newEmpl = new JButton("    Add Employee    ");
		newEmpl.setFont(myFont);
		newEmpl.setIcon(add);
		newEmpl.setSize(width1, height1);
		
		deleteEmpl = new JButton("  Delete Employee  ");	
		deleteEmpl.setFont(myFont);
		deleteEmpl.setIcon(remove);
		deleteEmpl.setSize(width2, height2);
		
		displayEmpl = new JButton("     Show Staff List     ");
		displayEmpl.setFont(myFont);
		displayEmpl.setIcon(list);
		displayEmpl.setSize(width3, height3);
		
		infoHosp = new JButton("Show Hospital Infos  ");
		infoHosp.setFont(myFont);
		infoHosp.setIcon(info);
		infoHosp.setSize(width4, height4);
	
	
		newEmpl.setFocusPainted(false);
		deleteEmpl.setFocusPainted(false);
		displayEmpl.setFocusPainted(false);
		infoHosp.setFocusPainted(false);
		
		// Buttons ActionListeners 
		newEmpl.addActionListener(this);
	    deleteEmpl.addActionListener(this);
	    displayEmpl.addActionListener(this);
	    infoHosp.addActionListener(this);
	    logoutBtn.addActionListener(this);
	    
	    //Add Components to JPanels
	    
		topPnl.add(nameLbl);
		topPnl.add(imageLbl);
		mainPnl.add(newEmpl);
		mainPnl.add(deleteEmpl);
		mainPnl.add(displayEmpl);
		mainPnl.add(infoHosp);
		botPnl.add(logoutBtn);
		
		// Define Container 
		
		Container pane = this.getContentPane();   
		pane.setLayout(new BorderLayout());
		pane.add(topPnl, BorderLayout.PAGE_START);
		pane.add(mainPnl, BorderLayout.CENTER);
		pane.add(botPnl, BorderLayout.PAGE_END);
		
		// GUI properties
		
		this.setSize(600,700);
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
	
	
	
	// ==== ACTION EVENT READER ====
	
	public void actionPerformed(ActionEvent d){
	    if (d.getSource() == displayEmpl){				/* ====> If user clicks on displayEmpl */
	    	
	    	// Go to employee table
	    	JFrame userList = new EmplTable();
    	    userList.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    }
	    
	    else if(d.getSource() == newEmpl){				/* ====> If user clicks on newEmpl */
	    	
	    	// Go to add employee menu
	    	JFrame addPat = new AddEmpl();
    	    addPat.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    }
	    
	    else if(d.getSource() == deleteEmpl){			/* ====> If user clicks on deleteEmpl */
	    	
	    	// Go to remove employee menu
	    	JFrame delete = new RemEmpl();
    	    delete.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    }
	    
	    else if(d.getSource() == infoHosp){				/* ====> If user clicks on infoHosp */
	    	
	    	// Go to hospital infos menu
	    	JFrame info = new HospInfo();
    	    info.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    	
	    }  
	    else if(d.getSource() == logoutBtn){
	    	
	    	// Ask the user for confirmation
	    	int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Warning", JOptionPane.YES_NO_OPTION);
	    	

	    	if(choice == 0){									/* ====> If user decides to log out */
	    		this.dispose();
	    	    Interface h = new Interface();					/* ====> Go to log in Interface */
	    	    h.loadData();									
	    	}
	    }
	}
	
} // END CLASS