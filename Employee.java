package project3;

/**
The employee class holds all the main attributes of an employee. The employee object is created
and each employee has their own profile and payment. 
@author mayeesha, rebecca
*/
public class Employee {
	//not sure what other instance variables to add for an employee
	private Profile profile; 
	private double payment;

	/**
	This constructor uses the Profile of an employee to connect the information 
	to the Profile class where all three attributes are held for each employee.
	@param employee's profile
	*/
	public Employee(Profile profile) {
		this.profile=profile;
		payment=0;
	}
	
	/**
	This method gets the profile of an employee from the Profile class so it can be 
	used in other classes.
	@return employee's profile
	*/
	public Profile getProfile() { //**idk but did this in book for date ig
		return profile;
	}
	
	/**
	Getter method for the payment of an employee it can be used in other classes.
	@return employee's payment
	*/
	public double getPayment() { //***********payment in employee or full/part wtff - and when add, is it employee or full/part
		return payment;
	}
	
	/**
	Setter method for the payment of an employee so the value can be set in other classes
	@param employee's payment
	*/
	public void setPayment(double payment) {
		this.payment=payment;
	}
		
	/**
	This method is the method that the other classes with the same method will override 
	*/
	public void calculatePayment() {
	}
	
	/**
	This method checks if employee objects are equal and already in the database by comparing 
	one employee's profile with another, and returns boolean values accordingly.
	@param obj of type employee to check with another employee object
	@return true if employees are equal, false if not
	*/
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Employee) {
			Employee employee=(Employee) obj;
			return employee.profile.equals(this.profile);
		}
		return false;
	}
	
	/**
	The method creates a string description of an employee and refers to the profile toString 
	method to apply polymorphism in order to to print the output without repetitive code
	@return string description
	*/
	@Override  
	public String toString() { 
		return profile.toString();
	} 
}