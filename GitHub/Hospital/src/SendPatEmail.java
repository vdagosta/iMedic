import java.awt.*;
import java.awt.event.*;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.*;

public class SendPatEmail extends JFrame implements ActionListener{

	/* The SendPatEmail class allows the user to send an email to a certain email address
	 * 
	 * If the user has no internet connection then an error message will pop up. */

	// ==== PROPERTIES ====
	
	// DATA ELEMENTS
	
	// Screen dimensions
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	// Properties that will store the data read from the JTextField
	private String email;
	private String body;
	
	
	// GUI ELEMENTS
	
	// Panels
	private JPanel top;
	private JPanel center;
	private JPanel bottom;

	
	// Labels
	private JLabel emailLbl;
	private JLabel bodyLbl;
	
	// Input fields
	private JTextField emailTxt;
	private JTextArea bodyTxt;
	
	// Buttons
	private JButton sendBtn;
	private JButton cancelBtn;
	
	
	SendPatEmail(){
		
		// Panels
		
		top = new JPanel();
		bottom = new JPanel();
		center = new JPanel();

		center.setLayout(new FlowLayout());
		top.setLayout(new GridLayout(3,2));
		bottom.setLayout(new FlowLayout());

		
		
		// Labels
		
		emailLbl = new JLabel("Patient's email address ");
		emailLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		bodyLbl = new JLabel("Email body ");
		bodyLbl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		
		// Buttons
		
		sendBtn = new JButton("Send Email");
		cancelBtn = new JButton("Cancel");
		
		// Buttons ActionListeners
		sendBtn.addActionListener(this);
		cancelBtn.addActionListener(this); 
		
		
		// Input Fields

		emailTxt = new JTextField(20);
		bodyTxt = new JTextArea(100,200);

		
		// Add Components to JPanels
		
		top.add(emailLbl);
		top.add(emailTxt);
		top.add(bodyLbl);
		center.add(bodyTxt);
		bottom.add(sendBtn);
		bottom.add(cancelBtn);

		
		
		// Define Container 
		
		Container pane = this.getContentPane();   
		pane.setLayout(new BorderLayout());
		pane.add(top, BorderLayout.PAGE_START);
		pane.add(center, BorderLayout.LINE_START);
		pane.add(bottom, BorderLayout.PAGE_END);
		
		
		// GUI properties
		
		this.setLocation((screen.width-800)/2,((screen.height-900)/2));
		this.setSize(900,900);
		this.setVisible(true);

	

		// Exit system when exit JFrame
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
    	});
	
	} // END CONSTRUCTOR
	
	
	
	
	
	// The read() method reads the dob inputs from the JTextFields and JComboBox and assigns those inputs to the class' private properties 
	public void read() {
	
    	
    	// Read from JTextFields and assign Strings to the class' private variables
    	
		this.email = emailTxt.getText().trim();
		this.body = bodyTxt.getText().trim();
		
		
	}
	
	
	
	
	
	
	// ==== ACTION EVENT READER ====
	
	public void actionPerformed(ActionEvent d){
	    if (d.getSource() == sendBtn){
	    	
	    	read();
	    	if (email != null){
				try {
					// Send email containing a message to a certain email address
					SendEmail msg = new SendEmail();
					msg.send(this.body , this.email);
						
					if(msg.isSent()){	
						JOptionPane.showMessageDialog(this, "Email was sent", "Success", JOptionPane.INFORMATION_MESSAGE); /* ====> Success */
						// Close current frame
						this.dispose();
				    	// Go to Doc menu
				    	JFrame doc = new DocMenu();
			    	    doc.setVisible(true);
					}
						
					else {
						JOptionPane.showMessageDialog(this, "Error! \n" + "No internet connection", "Error", JOptionPane.ERROR_MESSAGE);/* ====> Error */
						// Close current frame
						this.dispose();
				    	// Go to Doc menu
				    	JFrame doc = new DocMenu();
			    	    doc.setVisible(true);
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
	    	
	    }	
	    else if(d.getSource() == cancelBtn){								/* ====> If user clicks on cancelBtn */
	    	
	    		// Go to Admin menu
	    	JFrame doc = new DocMenu();
    	    doc.setVisible(true);
    	    
    	    // Close current frame
    	    this.dispose();
	    	
	    }
	}
	
}
