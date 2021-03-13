package project3;

import java.text.DecimalFormat;

/**
The fulltime class is a subclass of the employee class which holds the same attributes as the 
employee class. There are additional instance variables such as annualSalary for the specific
attributes of a fulltime employee which will help calculate a fulltime employee's final payment. 
@author mayeesha, rebecca
*/
public class Fulltime extends Employee {
	
	private double annualSalary;
	
	/**
	This constructor is extended from the superclass, employee, with an additional
	parameter in specific to the type of employee(fulltime).
	@param employee's profile
	@param employee's annual salary
	*/
	public Fulltime(Profile profile, double annualSalary) {
		super(profile);
		this.annualSalary = annualSalary;
	}
	
	/**
	This method will calculate the final payment for a fulltime employee using their 
	annual salary, and getter/setter methods to access or set values in order to 
	calculate the final payment.
	*/
	@Override 
	public void calculatePayment() { 
		final static double PAY_PERIODS = 26;
		setPayment(getPayment() + (annualSalary / PAY_PERIODS));
	}
	
	/**
	This method checks if fulltime employees are equal and already in the database. It 
	compares a fulltime employee's profile with another fulltime employee's profile and 
	returns a boolean accordingly. 
	@param obj of type fulltime to check with another fulltime employee object
	@return true if fulltime employees are equal, false if not
	*/
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Fulltime) {
			Fulltime fulltime = (Fulltime) obj;
			return fulltime.getProfile().equals(this.getProfile());
		}
		return false;
	}
	
	/**
	The method creates a string description of a fulltime employee and refers to the 
	superclass toString method to apply polymorphism in order to print the output without 
	repetitive code.
	@return string description
	*/
	@Override 
	public String toString() { 
		String pattern = "###,##0.00";
		DecimalFormat df = new DecimalFormat(pattern);
		return super.toString() + "::Payment $" + df.format(getPayment()) + "::FULL TIME::Annual Salary $" 
				+ df.format(annualSalary);
	} 
}