
public class User implements java.io.Serializable {

	/*The User class is the node of the doubly linked list UserList */
	
	// ==== PROPERTIES ====
	
	// Links to other Users
	private User next;
	private User prev;
	
	// Data stored in each User
	private String fullName;
	private String username;
	private String password;
	private String accType;
	private String email;
	private String salary;
	
	
	
	// ==== CONSTRUCTORS ==== 
	
	// The User() constructor creates the head of the UserList.
	User(){
		
		this.next = null;
		this.prev = null;
		
		this.fullName = " ";
		this.username = "username";
		this.password = "password";
		this.accType = "Admin";
		this.email = " ";
		this.salary = " ";
		
	} // END DEFAULT CONSTRUCTOR
	
	
	// The User(User,User) overloaded constructor creates a default User with its parameters as prev and next User. 
	User(User next, User prev){
	
		this.next = next;
		this.prev = prev;
		
		
		this.fullName = " ";
		this.username = " ";
		this.password = " ";
		this.accType = " ";
		this.email = " ";
		this.salary = " ";
	
	
	} // END OVERLOADED CONSTRUCTOR
	
	
	// ==== SETTERS ====
	
	
	public void setNext(User next){
		this.next = next;
	}
	
	public void setPrev(User prev){
		this.prev = prev;
	}
	
	
	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setAccType(String accType){
		this.accType = accType;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setSalary(String salary){
		this.salary = salary;
	}
	
	
	
	// ==== GETTERS ====
	
	public User getNext(){
		return next;
	}
	
	public User getPrev(){
		return prev;
	}
	
	public String getFullName(){
		return fullName;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getAccType(){
		return accType;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getSalary(){
		return salary;
	}
				
		
} // END CLASS

	
	
	
	

