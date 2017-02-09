import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class DocMenu extends JFrame implements ActionListener{

	/* The DocMenu class is the GUI interface that allows the user to test the functionality of the program  
	 * allowing to navigate the program and offering the options of adding new patients, removing them from
	 * the list, visualize the linked list using a JTable and sending emails. */
	
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
	private ImageIcon message;
	private ImageIcon imageTop;
	
	// Buttons
	private JButton newPat;
	private JButton deletePat;
	private JButton displayPat;
	private JButton msgPat;
	
	private JButton logoutBtn;

	
	// ==== CONSTRUCTOR ====
	
	DocMenu(){
		
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
		
		message = new ImageIcon("message.png");
		int width4 = add.getIconWidth();
		int height4 = add.getIconHeight();
		
		
		// Buttons
		
		
		Font smallFont = new Font("Century Gothic", Font.PLAIN,20);
		logoutBtn = new JButton("Log Out");
		logoutBtn.setFont(smallFont);
		logoutBtn.setFocusPainted(false);
		
		
		Font myFont = new Font("Century Gothic", Font.PLAIN,35);
		
		
		newPat = new JButton("    Add Patient    ");
		newPat.setFont(myFont);
		newPat.setIcon(add);
		newPat.setSize(width1, height1);
		
		deletePat = new JButton("  Delete Patient  ");	
		deletePat.setFont(myFont);
		deletePat.setIcon(remove);
		deletePat.setSize(width2, height2);
		
		displayPat = new JButton("     Show Patient List     ");
		displayPat.setFont(myFont);
		displayPat.setIcon(list);
		displayPat.setSize(width3, height3);
		
		msgPat = new JButton("     Send email       ");
		msgPat.setFont(myFont);
		msgPat.setIcon(message);
		msgPat.setSize(width4, height4);
	
	
		newPat.setFocusPainted(false);
		deletePat.setFocusPainted(false);
		displayPat.setFocusPainted(false);
		msgPat.setFocusPainted(false);
		
		// Buttons ActionListeners 
		newPat.addActionListener(this);
	    deletePat.addActionListener(this);
	    displayPat.addActionListener(this);
	    msgPat.addActionListener(this);
	    logoutBtn.addActionListener(this);
	    
	    //Add Components to JPanels
	    
		topPnl.add(nameLbl);
		topPnl.add(imageLbl);
		mainPnl.add(newPat);
		mainPnl.add(deletePat);
		mainPnl.add(displayPat);
		mainPnl.add(msgPat);
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
	    if (d.getSource() == displayPat){				/* ====> If user clicks on displayPat */
	    	
	    	// Go to employee table
	    	JFrame patientList = new PatTable();
    	    patientList.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    }
	    
	    else if(d.getSource() == newPat){				/* ====> If user clicks on newPat */
	    	
	    	// Go to add employee menu
	    	JFrame addNew = new AddPat();
    	    addNew.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    }
	    
	    else if(d.getSource() == deletePat){			/* ====> If user clicks on deleteEmpl */	
	    	// Go to remove employee menu
	    	JFrame delete = new RemPat();
    	    delete.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    }
	    
	    else if(d.getSource() == msgPat){				/* ====> If user clicks on msgPat */
	    	
	    	// Go to hospital infos menu
	    	JFrame msg = new SendPatEmail();
    	    msg.setVisible(true);
    	    
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
