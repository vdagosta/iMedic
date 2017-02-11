import java.io.*;

public class UserList {

	/*The UserList class is the doubly linked list that stores the data save in the system */
	
	// ==== PROPERTIES ====
	
	private User head;
	private User tail;

	
	// ==== CONSTRUCTOR ==== 
	
	// The UserList() default constructor creates a default user assigning to it both head and tail
	public UserList(){
		this.head = new User();
		this.tail = this.head;

	} // END CONSTRUCTOR
	
	
	
	// ==== GETTERS ====
	
	public User getHead(){
		return head;
	}
	
	public User getTail(){
		return tail;
	}
	
	// ==== METHODS ====
	
	
	
	/* The findUser method iterates throught the whole list and returns the User whose username property matches the method parameter.
	 * If no elements of the list match the parameter than the method returns the head of the list */
	public User findUser(String username){
		
		
        boolean found = false;										/* ====> Boolean controls while loop */
        User correct = new User();									/* ====> Create instance of the correct user*/
		User marker = new User();									/* ====> Create a marker */
		marker = this.head;											/* ====> Set the marker at the beginning of the list */
		
		// Loop keeps on going until the correct User is not found or until the next User is null
        while(!found){    
        	if (marker.getUsername().equals(username)){				/* ====> If the marker found the right user based on its username */ 
        		found = true;										/* ====> Set found true, end loop*/
                correct = marker;									/* ====> Make correct point to the same User as marker */
        	}
                    
            else if (marker.getNext() == null){				/* ====> If the marker reaches end of the list */ 
            	found = true;								/* ====> Set found true, end loop */
            	correct = head;								/* ====> Make correct point to the head */
            }
                    
            else {						
            	marker = marker.getNext();					/* ====> Move marker to the next element of the list */
            }
        }
                
        return correct;										/* ====> Return correct User */
                
	}
	
	/* The findEmail(..) method iterates throught the whole list and returns the User whose email property matches the method parameter.
	 * If no elements of the list match the parameter than the method returns the head of the list */
	public User findEmail(String email){
		
        boolean found = false;										/* ====> Boolean controls while loop */
        User correct = new User();									/* ====> Create instance of the correct user*/
        User marker = new User();									/* ====> Create a marker */
        marker = this.head;											/* ====> Set the marker at the beginning of the list */
        
        // Loop keeps on going until the correct User is not found or until the next User is null
        while(!found){    
        	if (marker.getEmail().equals(email)){					/* ====> If the marker found the right user based on its email */ 
        		found = true;										/* ====> Set found true, end loop */
        		correct = marker;									/* ====> Make correct point to the same User as marker */
        	}
                
            else if (marker.getNext() == null){						/* ====> If the marker reaches end of the list */ 
            	found = true;										/* ====> Set found true, end loop */
            	correct = head;										/* ====> Make correct point to the head */
            }
                
            else {
            	marker = marker.getNext();							/* ====> Move marker to the next element of the list */
            }
        }
            
        return correct;												/* ====> Return correct User */       
        
	}
	
	/* The isEmpty() method returns true if the UserList has only the head */
	public boolean isEmpty(){
		
		
		boolean empty = true;										/* ====> Boolean set to true */
		
		User marker = new User();									/* ====> Create a marker */
		marker = this.getHead();									/* ====> Set the marker at the beginning of the list */
		
		if(marker.getNext()!= null){								/* ====> If the head is linked to at least one other User the list is not empty */
			empty = false;
		}
		
		else {														/* ====> If the head is not linked to at least one other User the list is empty */
			empty = true;
		}
		
		return empty;												/* ====> Return the boolean */
	}
	
	/* The getSize() method iterates throught the whole list and returns the number of Users in the list */
	public int getSize(){
		
		int counter = 0;											/* ====> Size counter */
		
		User marker = new User();									/* ====> Create a marker */
		marker = this.getHead();									/* ====> Set the marker at the beginning of the list */
		
		while(marker.getNext()!= null){								/* ====> Move marker to next element until it reaches the end. */ 
			marker = marker.getNext();
			counter++;												/* ====> Increment counter everytime the loop goes through */
		}
									
		return counter;												/* ====> Return number of Users in UserList */
		
	}
	
	
	/* The getAdmSize() method iterates throught the whole list and returns the number of Users in the list whose accType is Admin */
	public int getAdmSize(){
		
		int counter = 0;											/* ====> Size counter */
		
		User marker = new User();									/* ====> Create a marker */
		marker = this.getHead();									/* ====> Set the marker at the beginning of the list */
		
		while(marker.getNext()!= null){								/* ====> Move marker to next element until it reaches the end. */
			marker = marker.getNext();
			if(marker.getAccType().equals("Admin")){				/* ====> If marker is an Admin then increment counter */
			counter++;
			}
		}
		
		return counter;												/* ====> Return number of Admins in UserList */
		
	}
	
	/* The getDocSize() method iterates throught the whole list and returns the number of Users in the list whose accType is Doctor */
	public int getDocSize(){
		
		int counter = 0;											/* ====> Size counter */
		
		User marker = new User();									/* ====> Create a marker */
		marker = this.getHead();									/* ====> Set the marker at the beginning of the list */
		
		while(marker.getNext()!= null){								/* ====> Move marker to next element until it reaches the end. */
			marker = marker.getNext();
			if(marker.getAccType().equals("Doctor")){				/* ====> If marker is an Admin then increment counter */
			counter++;
			}
		}
		
		return counter;												/* ====> Return number of Admins in UserList */
		
	}
	
	
	/* The getRecSize() method iterates throught the whole list and returns the number of Users in the list whose accType is Receptionist */
	public int getRecSize(){
		
		int counter = 0;											/* ====> Size counter */
		
		User marker = new User();									/* ====> Create a marker */
		marker = this.getHead();									/* ====> Set the marker at the beginning of the list */
		
		while(marker.getNext()!= null){								/* ====> Move marker to next element until it reaches the end. */
			marker = marker.getNext();
			if(marker.getAccType().equals("Receptionist")){				/* ====> If marker is an Admin then increment counter */
			counter++;
			}
		}
		
		return counter;												/* ====> Return number of Admins in UserList */
		
	}
		
	
	/* The add() method adds a new User to the UserList. The new User is inserted in the list alphabetically so that UserList will always be sorted */
	public void add(String fullName, String username, String password, String accType, String email, String salary){
		
		// Determine position where new User needs to be added
		User marker = new User();									/* ====> Create a marker */
		marker = this.getHead();									/* ====> Set the marker at the beginning of the list */
		
		// Check if the list contains only the head
		if(marker.getNext()!= null){
		marker = marker.getNext();									/* ====> If not then skip the head */
		}
		
		
		/*Iterate through the whole list and compare Strings. Move the marker until it reaches the end or until the input
		 * username is greater than the marker's username . */
		while((marker.getNext() != null) && (username.compareTo(marker.getUsername())>0)){
					marker = marker.getNext();
				}		
		
		/* When marker finds a user whose username is greater than the input it moves the marker back a position so that the new
		 * user can be appended to that marker */
		if(marker.getPrev() != null){
				if(username.compareTo(marker.getUsername())<0){
					marker = marker.getPrev();
				}
		}
		
		
		// Create new user with the method's parameters as properties. Append it to the marker
		User newUser = new User(marker.getNext(), marker);
		newUser.setFullName(fullName);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setAccType(accType);
		newUser.setEmail(email);
		newUser.setSalary(salary);
		
		// Set connections to the new User
		newUser.getPrev().setNext(newUser);
		if(newUser.getNext() != null){
		newUser.getNext().setPrev(newUser);
		}

	}
	
	
	/* The remove() method removes an existing User from the UserList iterating through the list and looking for a User with 
	 * the same username as the method parameter. It returns true if the User was deleted successfully. */
	public boolean remove(String username){
		
		boolean removed = false;
		
		User removable = this.findUser(username);							/* ====> Find User that needs to be removed */
		
		if(removable != head){												/* ====> If the findUser found a match, so the returned User != head */
			if(removable.getNext()!= null){
				removable.getNext().setPrev(removable.getPrev());			/* ====> If User is not tail */
				removable.getPrev().setNext(removable.getNext());
				removable.setNext(null);
				removable.setPrev(null);
                }
            else {															/* ====> If User is tail */
            	removable.getPrev().setNext(null);
                removable.setNext(null);
                removable.setPrev(null);
            }
          
                removed = true;												/* ====> User has been deleted */
		}
		
        else{																/* ====> If the findUser did not find a match, so the returned User == head */
        	removed = false;												/* ====> User has not been deleted */
        }
                
                
        return removed;     												
	}
	
	
	
	/* The load() method loads an existing UserList from the user.ser file */
	public void load(){
	
		try {
			
			// Open Streams
			FileInputStream inFile = new FileInputStream("user.ser");
			ObjectInputStream objIn = new ObjectInputStream(inFile);
			
			// Load the existing UserList at the head
			this.head = (User)objIn.readObject();
			
			// Close Streams
			objIn.close();
			inFile.close();
		}

		catch(Exception d) {
		    System.out.println("Error loading");
		}

	}
	
	
	
	/* The load() method serializes an existing UserList to the the user.ser file */
	public void save(){
		
		try {
				 
			// Open Streams
			FileOutputStream outFile = new FileOutputStream("user.ser");
			ObjectOutputStream outObj = new ObjectOutputStream(outFile);
				 
			// Serializing the head will save the whole list
			outObj.writeObject(this.head);
				 
			// Close Streams 
			outObj.close();
			outFile.close();
		}

		catch(Exception e) {
			System.out.println("Error saving");
		}
			
	}
		
		
} // END CLASS