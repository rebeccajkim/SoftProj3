package project3;

/**
The Profile class is to store the name, department code, and date hired of an employee and create 
a profile for each employee. Employee profiles are compared to see if they already exist or not. 
This profile class contains a lot of getter and setter methods so other classes can set and get 
variables with ease.
@author mayeesha, rebecca
*/
public class Profile {
	private String name;//employee's name in the form "lastname, firstname"
	private String department; //department code CS, ECE, IT
	private Date dateHired;
	
	/**
	This constructor uses the name, department code, and date hired of an employee to create 
	a profile for them. 
	@param employee's profile
	*/
	public Profile(String name, String department, String date) {
		this.name = name;
		this.department = department;
		dateHired = new Date(date);
	}
	
	/**
	Getter method for name of an employee so it can be used in other classes
	@return name
	*/
	public String getName() {
		return name;
	}
	
	/**
	Getter method for the department of an employee so it can be used in other classes
	@return date hired
	*/
	public String getDepartment() {
		return department;
	}
	
	/**
	Getter method for date hired of the employee so it can be used in other 
	classes
	@return date hired
	*/
	public Date getDateHired() {
		return dateHired;
	}
	
	/**
	Setter method for the name of the employee so the value can be set 
	in other  classes
	@param employee name
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	Setter method for the department code so that the value can be set
	in other classes
	@param department code
	*/
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	Setter method for the date an employee joined so the value can be set
	in other classes
	@param date when employee joined
	*/
	public void setDate(Date dateHired) {
		this.dateHired = dateHired;
	}
	
	/**
	The method creates a string description of an employee using their profile attributes 
	@return string description
	*/
	
	@Override
	public String toString() {
		return name + "::" + department + "::" + dateHired.getMonth() + "/" + dateHired.getDay() 
			+ "/" + dateHired.getYear();
	}
	
	/**
	The method compares an employees profile with another by checking if their name, department
	code, and date are equal and returns a boolean value accordingly. 
	@return true if employees' profiles match, false if not
	*/
	@Override
	public boolean equals(Object obj) { 
		if (obj instanceof Profile) {
			Profile profile = (Profile) obj;
			if (profile.name.equals(this.name) && profile.department.equals(this.department) 
					&& profile.dateHired.getYear() == this.dateHired.getYear() && profile.dateHired.getMonth() 
					== this.dateHired.getMonth() && profile.dateHired.getDay() == this.dateHired.getDay()) {
				return true;
			}
			return false;
		}
		return false;
	}
}