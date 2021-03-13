package project3;

import java.text.DecimalFormat;

/**
The management class is a subclass of the fulltime class which holds the same attributes as the 
fulltime class. There are additional instance variables such as additional compensation and role 
for the specific attributes of a management employee which will help calculate the employee's 
final payment. 
@author mayeesha, rebecca
*/
public class Management extends Fulltime {
	
	private String role;
	private double additionalComp;
	
	/**
	This constructor is extended from the superclass, Fulltime with its superclass,
	Employee, with additional parameters in specific to the type of employee(management).
	@param employee's profile
	@param employee's annual salary
	@param employee's role 
	*/
	public Management(Profile profile, double annualSalary, String role) {
		super(profile, annualSalary);
		this.role = role;
		if (role.equals("Manager")) {
			additionalComp = 192.31; //5,000/26
		}
		else if (role.equals("Department Head")) {
			additionalComp = 365.38; //9,500/26
		}
		else if (role.equals("Director")) { //Director
			additionalComp = 461.54; //12,000/26
		}
	}
	
	/**
	Setter method for the role of a management employee according to its specific
	number, 1/2/3. The employee's additional compensation is set here as well which 
	is just the annual salary divided by 26. 
	@param role of an employee
	*/
	
	/*
	public void setRole(String role) {
		this.role=role;
		if(role.equals("Manager")) {
			additionalComp=192.31; //5,000/26
		}
		else if(role.equals("Department Head")) {
			additionalComp=365.38; //9,500/26
		}
		else { //Director
			additionalComp=461.54; //12,000/26
		}
	}
	*/
	
	/**
	This method will calculate the final payment for a management employee using their 
	additional compensation, and getter/setter methods to access or set values in order 
	to calculate the final payment.
	*/
	@Override 
	public void calculatePayment() { 
		super.calculatePayment();
		setPayment(getPayment() + additionalComp);
	} 
	
	/**
	This method checks if management employees are equal and already in the database. It 
	compares a management employee's profile with another management employee's profile 
	and returns a boolean accordingly. 
	@param obj of type management to check with another management employee object
	@return true if management employees are equal, false if not
	*/
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Management) {
			Management management = (Management) obj;
			return management.getProfile().equals(this.getProfile());
		}
		return false;
	}
	
	/**
	The method creates a string description of a management employee and refers to the 
	superclass toString method to apply polymorphism in order to print the output without 
	repetitive code.
	@return string description
	*/
	
	@Override 
	public String toString() { 
		String pattern = "###,##0.00";
		DecimalFormat df = new DecimalFormat(pattern);
		return super.toString() + "::" + role + " Compensation $" 
				+ df.format(additionalComp); 
	} 
	
	
	/**
	The method creates a string description of a management employee with the import/export database format
	@return string description
	*/
	/*
	@Override
	public String toString() { 
		String pattern = "###,##0.00";
		DecimalFormat df = new DecimalFormat(pattern);
		final static int MANAGER_CODE = 1;
		final static int DEPARTMENT_HEAD_CODE = 2;
		final static int DIRECTOR_CODE = 3;
		int roleCode = 0;
		if (role.equals("Manager")) {
			roleCode = MANAGER_CODE;
		} 
		else if (role.equals("Department Head")) {
			roleCode = DEPARTMENT_HEAD_CODE;
		}
		else if (role.equals("Director")) {
			roleCode = DIRECTOR_CODE;
		}
		return super.toString() + "," + roleCode;
	} 
	*/
	
}