package project3;

import java.text.DecimalFormat;

/**
The parttime class is a subclass of the employee class which holds the same attributes as the 
employee class. There are additional instance variables such as hours and hourlyRate for the specific
attributes of a parttime employee which will help calculate a parttime employee's final payment. 
@author mayeesha, rebecca
*/
public class Parttime extends Employee {

	private int hours;
	private double hourlyRate;
	
	/**
	This constructor is extended from the superclass, Employee with an additional
	parameter in specific to the type of employee(parttime).
	@param employee's profile
	@param employee's hourly rate
	*/
	public Parttime(Profile profile, double hourlyRate) {
		super(profile);
		hours = 0;
		this.hourlyRate = hourlyRate;
	}
	
	/**
	Getter method for the hours worked of the parttime employee so that it can be used 
	to calculate the employee's final payment and be used in other classes.
	@return employee's hours worked
	*/
	public int getHours() {
		return hours;
	}
	
	/**
	Setter method for the hours worked of a parttime employee so the value can be set
	in other  classes.
	@param employee's payment
	*/
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	/**
	This method will calculate the final payment for a parttime employee using their 
	hours worked, and getter/setter methods to access or set values in order to 
	calculate the final payment.
	*/
	@Override 
	public void calculatePayment() { 
		final static double MAX_HOURS_PERIOD = 80;
		final static double OVERTIME_RATE = 1.5; //check in payrollprocessing or company that its not over 100 hours
		if (hours <= MAX_HOURS_PERIOD) {
			setPayment(getPayment() + hours*hourlyRate);
		}
		else {
			setPayment(getPayment() + MAX_HOURS_PERIOD * hourlyRate);
			setPayment(getPayment() + OVERTIME_RATE * (hours - MAX_HOURS_PERIOD) * hourlyRate);
		}
	}
	
	/**
	This method checks if parrtime employees are equal and already in the database. It 
	compares a parttime employee's profile with another parttime employee's profile and 
	returns a boolean accordingly. 
	@param obj of type parttime to check with another parttime employee object
	@return true if parttime employees are equal, false if not
	*/
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Fulltime) {
			Parttime parttime = (Parttime) obj;
			return parttime.getProfile().equals(this.getProfile());
		}
		return false;
	}
	
	/**
	The method creates a string description of a parttime employee and refers to the 
	superclass toString method to apply polymorphism in order to print the output without 
	repetitive code.
	@return string description
	*/
	
	@Override 
	public String toString() { 
		String pattern = "###,##0.00";
		DecimalFormat df = new DecimalFormat(pattern);
		return super.toString() + "::Payment $" + df.format(getPayment()) + "::PART TIME::Hourly Rate $" 
				+ df.format(hourlyRate) + "::Hours worked this period: " + hours;
	} 
}