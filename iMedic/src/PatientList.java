import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PatientList {

	/*The PatientList class is the doubly linked list that stores the data save in the system */
	
	// ==== PROPERTIES ====
	
	private Patient head;
	private Patient tail;

	
	// ==== CONSTRUCTOR ==== 
	
	// The PatientList() default constructor creates a default user assigning to it both head and tail
	public PatientList(){
		this.head = new Patient();
		this.tail = this.head;

	} // END CONSTRUCTOR
	
	
	
	// ==== GETTERS ====
	
	public Patient getHead(){
		return head;
	}
	
	public Patient getTail(){
		return tail;
	}
	
	// ==== METHODS ====
	
	
	
	/* The findPatient method iterates throught the whole list and returns the Patient whose fullName property matches the method parameter.
	 * If no elements of the list match the parameter than the method returns the head of the list */
	public Patient findPatient(String fullName){
		
		
        boolean found = false;										/* ====> Boolean controls while loop */
        Patient correct = new Patient();									/* ====> Create instance of the correct user*/
		Patient marker = new Patient();									/* ====> Create a marker */
		marker = this.head;											/* ====> Set the marker at the beginning of the list */
		
		// Loop keeps on going until the correct Patient is not found or until the next Patient is null
        while(!found){    
        	if (marker.getFullName().equals(fullName)){				/* ====> If the marker found the right user based on its fullName */ 
        		found = true;										/* ====> Set found true, end loop*/
                correct = marker;									/* ====> Make correct point to the same Patient as marker */
        	}
                    
            else if (marker.getNext() == null){				/* ====> If the marker reaches end of the list */ 
            	found = true;								/* ====> Set found true, end loop */
            	correct = head;								/* ====> Make correct point to the head */
            }
                    
            else {						
            	marker = marker.getNext();					/* ====> Move marker to the next element of the list */
            }
        }
                
        return correct;										/* ====> Return correct Patient */
                
	}

	
	/* The isEmpty() method returns true if the PatientList has only the head */
	public boolean isEmpty(){
		
		
		boolean empty = true;										/* ====> Boolean set to true */
		
		Patient marker = new Patient();									/* ====> Create a marker */
		marker = this.getHead();									/* ====> Set the marker at the beginning of the list */
		
		if(marker.getNext()!= null){								/* ====> If the head is linked to at least one other Patient the list is not empty */
			empty = false;
		}
		
		else {														/* ====> If the head is not linked to at least one other Patient the list is empty */
			empty = true;
		}
		
		return empty;												/* ====> Return the boolean */
	}
	
	/* The getSize() method iterates throught the whole list and returns the number of Patients in the list */
	public int getSize(){
		
		int counter = 0;											/* ====> Size counter */
		
		Patient marker = new Patient();									/* ====> Create a marker */
		marker = this.getHead();									/* ====> Set the marker at the beginning of the list */
		
		while(marker.getNext()!= null){								/* ====> Move marker to next element until it reaches the end. */ 
			marker = marker.getNext();
			counter++;												/* ====> Increment counter everytime the loop goes through */
		}
									
		return counter;												/* ====> Return number of Patients in PatientList */
		
	}
	
	
	/* The add() method adds a new Patient to the PatientList. The new Patient is inserted in the list alphabetically so that PatientList will always be sorted */
	public void add(String fullName, String dob, String phone, String gender, String email, String bill){
		
		// Determine position where new Patient needs to be added
		Patient marker = new Patient();									/* ====> Create a marker */
		marker = this.getHead();									/* ====> Set the marker at the beginning of the list */
		
		// Check if the list contains only the head
		if(marker.getNext()!= null){
		marker = marker.getNext();									/* ====> If not then skip the head */
		}
		
		
		/*Iterate through the whole list and compare Strings. Move the marker until it reaches the end or until the input
		 * fullName is greater than the marker's fullName . */
		while((marker.getNext() != null) && (fullName.compareTo(marker.getFullName())>0)){
					marker = marker.getNext();
				}		
		
		/* When marker finds a user whose fullName is greater than the input it moves the marker back a position so that the new
		 * user can be appended to that marker */
		if(marker.getPrev() != null){
				if(fullName.compareTo(marker.getFullName())<0){
					marker = marker.getPrev();
				}
		}
		
		
		// Create new Patient with the method's parameters as properties. Append it to the marker
		Patient newPatient = new Patient(marker.getNext(), marker);
		newPatient.setFullName(fullName);
		newPatient.setDate(dob);
		newPatient.setPhone(phone);
		newPatient.setGender(gender);
		newPatient.setEmail(email);
		newPatient.setBill(bill);
		
		// Set connections to the new Patient
		newPatient.getPrev().setNext(newPatient);
		if(newPatient.getNext() != null){
		newPatient.getNext().setPrev(newPatient);
		}

	}
	
	
	/* The remove() method removes an existing Patient from the PatientList iterating through the list and looking for a Patient with 
	 * the same fullName as the method parameter. It returns true if the Patient was deleted successfully. */
	public boolean remove(String fullName){
		
		boolean removed = false;
		
		Patient removable = this.findPatient(fullName);							/* ====> Find Patient that needs to be removed */
		
		if(removable != head){												/* ====> If the findPatient found a match, so the returned Patient != head */
			if(removable.getNext()!= null){
				removable.getNext().setPrev(removable.getPrev());			/* ====> If Patient is not tail */
				removable.getPrev().setNext(removable.getNext());
				removable.setNext(null);
				removable.setPrev(null);
                }
            else {															/* ====> If Patient is tail */
            	removable.getPrev().setNext(null);
                removable.setNext(null);
                removable.setPrev(null);
            }
          
                removed = true;												/* ====> Patient has been deleted */
		}
		
        else{																/* ====> If the findPatient did not find a match, so the returned Patient == head */
        	removed = false;												/* ====> Patient has not been deleted */
        }
                
                
        return removed;     												
	}
	
	
	
	/* The load() method loads an existing PatientList from the patient.ser file */
	public void load(){
	
		try {
			
			// Open Streams
			FileInputStream inFile = new FileInputStream("patient.ser");
			ObjectInputStream objIn = new ObjectInputStream(inFile);
			
			// Load the existing PatientList at the head
			this.head = (Patient)objIn.readObject();
			
			// Close Streams
			objIn.close();
			inFile.close();
		}

		catch(Exception d) {
		    System.out.println("Error loading");
		}

	}
	
	
	
	/* The load() method serializes an existing PatientList to the the patient.ser file */
	public void save(){
		
		try {
				 
			// Open Streams
			FileOutputStream outFile = new FileOutputStream("patient.ser");
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