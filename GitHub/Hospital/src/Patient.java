public class Patient implements java.io.Serializable {

	/*The Patient class is the node of the doubly linked list PatientList */
	
	// ==== PROPERTIES ====
	
	// Links to other Patients
	private Patient next;
	private Patient prev;
	
	// Data stored in each Patient
	private String fullName;
	private String dob;
	private String phone;
	private String gender;
	private String email;
	private String bill;
	
	
	
	// ==== CONSTRUCTORS ==== 
	
	// The Patient() constructor creates the head of the PatientList.
	Patient(){
		
		this.next = null;
		this.prev = null;
		
		this.fullName = " ";
		this.dob = " ";
		this.phone = " ";
		this.gender = " ";
		this.email = " ";
		this.bill = " ";
		
	} // END DEFAULT CONSTRUCTOR
	
	
	// The Patient(Patient,Patient) overloaded constructor creates a default Patient with its parameters as prev and next Patient. 
	Patient(Patient next, Patient prev){
	
		this.next = next;
		this.prev = prev;
		
		
		this.fullName = " ";
		this.dob = " ";
		this.phone = " ";
		this.gender = " ";
		this.email = " ";
		this.bill = " ";
	
	
	} // END OVERLOADED CONSTRUCTOR
	
	
	// ==== SETTERS ====
	
	
	public void setNext(Patient next){
		this.next = next;
	}
	
	public void setPrev(Patient prev){
		this.prev = prev;
	}
	
	
	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	
	public void setDate(String dob){
		this.dob = dob;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setBill(String bill){
		this.bill = bill;
	}
	
	
	
	// ==== GETTERS ====
	
	public Patient getNext(){
		return next;
	}
	
	public Patient getPrev(){
		return prev;
	}
	
	public String getFullName(){
		return fullName;
	}
	
	public String getDate(){
		return dob;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public String getGender(){
		return gender;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getBill(){
		return bill;
	}
				
		
} // END CLASS

	