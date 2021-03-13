package project3;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
The company class stores all the employees in an array-based list. These employees can 
be added, removed, checked out, have payments processed, and printed in a list.
@author mayeesha, rebecca
*/
public class Company { //set, process, dept
	private Employee[] emplist;
	private int numEmployee;
	
	/**
	Default constructor for an empty list
	*/
	public Company() {
		emplist = new Employee[0];
		numEmployee = 0;
	}
	
	/**
	Getter method for numEmployee so it can be used in another classe
	@return numEmployee
	*/
	public int getNumEmployee() {
		return numEmployee;
	}
	
	/**
	Method used to help other methods find an employee in the employee list and 
	return the index of the employee in the employee list
	@param employee to find in the employee list
	@return index of employee in list, -1 if not found
	*/
	private int find(Employee employee) { 
		int count = 0;
		while (count < numEmployee) {
			if (emplist[count].getProfile().equals(employee.getProfile())) {
				return count; //index in array not serial num
			}
			count++;
		}
		final static int CANT_FIND = -1;
		return CANT_FIND;
	}
	
	/**
	Helper method to grow the employee list capacity by 4 when the list is full
	*/
	private void grow() {
		final static int GROW_VAR = 4;
		Employee[] temp = new Employee[emplist.length + GROW_VAR];
		for (int i = 0; i < numEmployee; i++) {
			temp[i] = emplist[i];
		}
		emplist = temp;
	} 
	
	/**
	Method to add an employee to the employee list. Also, we grow the list capacity 
	when it's full and only add the employee if their profile is valid. 
	@param employee to be added
	@return true if added, false if not
	*/
	public boolean add(Employee employee) { //**********depends type of employee bro
		final static int CANT_FIND = -1;
		if (find(employee) == CANT_FIND) { //valid date and dept code already checked so just is employee already there
			if (emplist.length == numEmployee) { //list full
				grow();
			} 
			if (numEmployee == 0) {
				emplist[0] = employee; //all empty slots	
			}
			else {
				int ptr = emplist.length - 1;
				while (emplist[ptr] == null) { //first empty slot
					ptr--;
				}
				emplist[ptr + 1] = employee;
				
			}
			numEmployee++; //increase employee count
			return true;
		}
		return false; //already found employee there
	} 
	
	/**
	The method to remove an employee from the employee list. The employee is found using 
	the find method and the list maintains the current sequence of employee's after the 
	removal. If the employee is not in the company, the method returns false.
	@param employee to be removed
	@return true if successfully removed, false if employee not in company
	*/
	public boolean remove(Employee employee) { 
		int index = find(employee);
		final static int CANT_FIND = -1;
		if (index > CANT_FIND) {
			for (int i = index; i < emplist.length - 1; i++) {
				emplist[i] = emplist[i + 1];
			}
			if (numEmployee == emplist.length) { //put a null space
				emplist[emplist.length - 1] = null; 
			}
			numEmployee--;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	Method to set a part time employees working hours.
	@param part time employee's hours to be set 
	@return true if parttime employee exists, false if not
	*/
	public boolean setHours(Employee employee) { 
		int index = find(employee);
		final static int CANT_FIND = -1;
		if (index > CANT_FIND && emplist[index] instanceof Parttime) { //exists and its parttime
			((Parttime) emplist[index]).setHours(((Parttime) employee).getHours());
			return true;
		}
		return false; //**idk if possible to not have employee/no print for it	
	} 
	
	/**
	Method to process the payments for the employees in the company. This method is 
	to ensure that the employees receive their proper payments by calling the 
	calculatePayment methods in the other classes.
	*/
	public void processPayments() { 
		for (int i = 0; i < numEmployee; i++) {
			if (emplist[i].getPayment() == 0) {
				if (emplist[i] instanceof Fulltime) {
					if (emplist[i] instanceof Management) {
						Management management = (Management) emplist[i];
						management.calculatePayment();
					}
					else {
						Fulltime fulltime = (Fulltime) emplist[i];
						fulltime.calculatePayment();
					}
				} 
				else { //parttime
					Parttime parttime = (Parttime) emplist[i];
					parttime.calculatePayment();
				}
			}
		}
	} 
	
	/**
	Method to print the earning statements for all the employers in the company
	in the normal order from the employee list
	*/
	public String print() {
		String result = "";
		for (int i = 0; i < numEmployee; i++) {
			result = result + emplist[i].toString() + "\n"; //**make sure its part/full toString not just employee
		}
		return result;
	} 
	
	/**
	Method to print the earning statements for all the employers in the company
	by the order of their Department from the employee list
	*/
	public String printByDepartment() { //**idk how sorted besides just cs ece it		
		mergeSortDept(emplist, 0, numEmployee - 1);
		String result = "";
		for (int i = 0; i < numEmployee; i++) {
			result = result + emplist[i].toString() + "/n";
		}
		return result;
	}

	/**
	Helper method to merge sort the employees in order of department
	@param employee list array
	@param left index
	@param right index
	*/
	public static void mergeSortDept(Employee[] emplist, int left, int right) { 
		if (right <= left) return;
		int HALF = 2;
		int mid = (left + right) / HALF; //left, right, mid are indexes
		mergeSortDept(emplist, left, mid);
		mergeSortDept(emplist, mid + 1, right);
		mergeDept(emplist, left, mid, right);
	}

	/**
	Helper method to merge two arrays together so it can be sorted in order of department
	@param employee list array
	@param left index
	@param mid index
	@param right index
	*/
	public static void mergeDept(Employee[] emplist, int left, int mid, int right) {
		Employee[] leftEmplist = new Employee[mid - left + 1];
		Employee[] rightEmplist = new Employee[right - mid];
		for (int i = 0; i < mid - left + 1; i++) {
			leftEmplist[i] = emplist[left + i];
		}
		for (int j = 0; j < right - mid; j++) {
			rightEmplist[j] = emplist[mid + j + 1];
		}
		int leftIndex = 0;
		int rightIndex = 0;
		for (int k = left; k < right + 1; k++) {
			if (leftIndex < mid - left + 1 && rightIndex < right - mid) { //setter, getter constructors for book
				if ((leftEmplist[leftIndex].getProfile().getDepartment().equals("CS") &&
								rightEmplist[rightIndex].getProfile().getDepartment().equals("ECE"))
						|| (leftEmplist[leftIndex].getProfile().getDepartment().equals("CS") &&
								rightEmplist[rightIndex].getProfile().getDepartment().equals("IT"))
						|| (leftEmplist[leftIndex].getProfile().getDepartment().equals("ECE") &&
								rightEmplist[rightIndex].getProfile().getDepartment().equals("IT"))) {
					emplist[k] = leftEmplist[leftIndex];
					leftIndex++;
				}
				else {
					emplist[k] = rightEmplist[rightIndex];
					rightIndex++;
				}
			}
			else if (leftIndex < mid - left + 1) {
				emplist[k] = leftEmplist[leftIndex];
				leftIndex++;
			}
			else if (rightIndex < right-mid) {
				emplist[k] = rightEmplist[rightIndex];
				rightIndex++;
			}
		}
	}
	
	/**
	Method to print the earning statements for all the employers in the company
	by the order of date from the employee list
	*/
	public String printByDate() { //print the list of books by datePublished (ascending)
		mergeSortDate(emplist, 0, numEmployee - 1);
		String result = "";
		for (int i = 0; i < numEmployee; i++) {
			result = result + emplist[i].toString() + "\n";
		}
		return result;
	}
	
	/**
	Helper method to merge sort the employees in order of dates hired
	@param employee list array
	@param left index
	@param right index
	*/
	public static void mergeSortDate(Employee[] emplist, int left, int right) { 
		if (right <= left) return;
		int HALF = 2;
		int mid = (left + right) / HALF; //left, right, mid are indexes
		mergeSortDate(emplist, left, mid);
		mergeSortDate(emplist, mid + 1, right);
		mergeDate(emplist, left, mid, right);
	}
	
	/**
	Helper method to merge two arrays together so it can be sorted in order of dates hired
	@param employee list array
	@param left index
	@param mid index
	@param right index
	*/
	public static void mergeDate(Employee[] emplist, int left, int mid, int right) {
		Employee[] leftEmplist = new Employee[mid-left+1];
		Employee[] rightEmplist = new Employee[right-mid];
		for (int i = 0; i < mid - left + 1; i++) {
			leftEmplist[i] = emplist[left + i];
		}
		for (int j = 0; j < right - mid; j++) {
			rightEmplist[j] = emplist[mid + j + 1];
		}
		int leftIndex = 0;
		int rightIndex = 0;
		for (int k = left; k < right + 1; k++) {
			if (leftIndex<mid-left+1 && rightIndex<right-mid) { //setter, getter constructors for book
				if ((leftEmplist[leftIndex].getProfile().getDateHired().getYear()
								< rightEmplist[rightIndex].getProfile().getDateHired().getYear())
						|| (leftEmplist[leftIndex].getProfile().getDateHired().getYear()
								== rightEmplist[rightIndex].getProfile().getDateHired().getYear() 
							&& leftEmplist[leftIndex].getProfile().getDateHired().getMonth()
								< rightEmplist[rightIndex].getProfile().getDateHired().getMonth())
						|| (leftEmplist[leftIndex].getProfile().getDateHired().getYear()
								== rightEmplist[rightIndex].getProfile().getDateHired().getYear() 
							&& leftEmplist[leftIndex].getProfile().getDateHired().getMonth()
								== rightEmplist[rightIndex].getProfile().getDateHired().getMonth() 
							&& leftEmplist[leftIndex].getProfile().getDateHired().getDay()
								< rightEmplist[rightIndex].getProfile().getDateHired().getDay())) {
					emplist[k] = leftEmplist[leftIndex];
					leftIndex++;
				}
				else {
					emplist[k] = rightEmplist[rightIndex];
					rightIndex++;
				}
			}
			else if (leftIndex < mid - left + 1) {
				emplist[k] = leftEmplist[leftIndex];
				leftIndex++;
			}
			else if (rightIndex < right-mid) {
				emplist[k] = rightEmplist[rightIndex];
				rightIndex++;
			}
		}
	}
	
	/**
	The method exports the database of employees to a path.
	@param path
	 */
	public void exportDatabase(String path) {
		try {
			FileWriter write = new FileWriter(path);
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(emplist[0].toString());
			for(int i = 1; i < numEmployee; i++) {
				writer.append(emplist[i].toString() + "\n"); //**make sure its part/full toString not just employee
			}
			writer.close();
		}
		catch (IOException e) {
		}
	}
}