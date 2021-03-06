package project3;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import project2.Fulltime;
import project2.Management;
import project2.Parttime;
import project2.Profile;

/**
This class controls the JavaFX file so that pressing certain buttons and entering certain information inputs the correct data into the employee
database. There are buttons for adding and removing employees, calculating payments, setting hours for a parttime worker, printing, and inputting
and outputting to and from a file. The class goes along with the Sample file to "control" it and help us use it for our database.
@author mayeesha, rebecca
*/
public class SampleController {
	Company company = new Company();
		
	@FXML
	private TextField name;

	@FXML
	private DatePicker dateHired;

	@FXML
	private TextField annSal;

	@FXML
	private TextField hrsWorked;

	@FXML
	private TextField rate;
	    
	@FXML
	private Button clearButton;

	@FXML
	private Button addButton;

	@FXML
	private Button removeButton;

	@FXML
	private Button sethrButton;

	@FXML
	private java.awt.TextArea messageArea1;

	@FXML
	private TextArea messageArea2;
	    
	@FXML
	private ToggleGroup dep, empType, mgmtType;
	    
	@FXML
	private RadioButton csID, itID, eceID, FullTimeID, PartTimeID, ManagementID, 
	    						managerID, depheadID, directorID;
	    
	@FXML
	/**
	This method adds an employee (fulltime, parttime, management) to the company based on
	the information given in Employee Management and after pressing the Add Employee button. There is an
	error message if the date hired is invalid, the employee is already in the company, or not enough 
	information is given.
	@param event of information given
	*/
	void add(ActionEvent event) {
	   	//messageArea.clear(); //clear the TextArea.
	   	try {
	   		String emp = name.getText();
			String dept = "";
			String[] dateSplit = dateHired.getValue().toString().split("-");
			String date = dateSplit[1] + "/" + dateSplit[2] + "/" + dateSplit[0];
	    	if (FullTimeID.isSelected()) {
	    		String annualSal = annSal.getText();
				double annSalary = Double.parseDouble(annualSal);
				if (csID.isSelected()) {
					dept = "CS";
				} 
				else if (itID.isSelected()) {
					dept = "IT";
				}
				else if (eceID.isSelected()) {
					dept = "ECE";
				}
				Profile profile = new Profile(emp, dept, date);
				Fulltime fulltime = new Fulltime(profile, annSalary);
				if (!fulltime.getProfile().getDateHired().isValid()) {
					messageArea1.appendText(date + " is not a valid date! \n");
				}
				else {
					if (company.add(fulltime)) {
						messageArea1.appendText("Employee added. \n");
					}
					else {
						messageArea1.appendText("Employee is already in the list. \n");
					}
				}
	    	} 
	    	else if (ManagementID.isSelected()) {
	    		String annualSal = annSal.getText();
				double annSalary = Double.parseDouble(annualSal);
				String role = "";
	    		if (managerID.isSelected()) {
	    			role = "Manager";
	    		}
	    		else if (depheadID.isSelected()) {
	    			role = "Department Head";
	    		}
	    		else if (directorID.isSelected()) {
	    			role = "Director";
	    		}
	    		if (csID.isSelected()) {
	    			dept = "CS";
	    		}
	    		else if (itID.isSelected()) {
	    			dept = "IT";
	    		}
	    		else if (eceID.isSelected()) {
	    			dept = "ECE";
	    		}
	    		Profile profile = new Profile(emp, dept, date);
				Management management = new Management(profile, annSalary, role);
				if (!management.getProfile().getDateHired().isValid()) { 
					messageArea1.appendText(date + " is not a valid date! \n");
				}
				else {
					if (company.add(management)) {
						messageArea1.appendText("Employee added. \n");
					}
					else {
						messageArea1.appendText("Employee is already in the list. \n");
					}
	    		}
	    	} 
	    	else if (PartTimeID.isSelected()){
	    		String hrRate = rate.getText();
				double hrlyRate = Double.parseDouble(hrRate);
				if (csID.isSelected()) {
					dept = "CS";
				}
				else if (itID.isSelected()) {
					dept = "IT";
				}
				else if (eceID.isSelected()) {
					dept = "ECE";
				}
	    		Profile profile = new Profile(emp, "CS", "06/16/2000");
	    		Parttime parttime = new Parttime(profile, hrlyRate);
				if (!parttime.getProfile().getDateHired().isValid()) {
					messageArea1.appendText(date + " is not a valid date! \n");
				}
				else {
					if (company.add(parttime)) {
						messageArea1.appendText("Employee added. \n");
					}
					else {
						messageArea1.appendText("Employee is already in the list. \n");
					}
				}	
	    	}
	   	}	
	    //Show the error message with a pop-up window.
	    catch (Exception e) {
			messageArea1.appendText("Error. Please recheck inputs! \n");
	    }
	}
	
    @FXML
    /**
    This method removes an employee from the database based on the information given in Employee Management and after pressing
    the Remove Employee button. There is an error message if the employee database is empty, the employee doesn't exist, or not
    enough information is given.
    @param event of information given
    */
    void remove(ActionEvent event) {
    	try {
    		String emp = name.getText();
    		String dept = "";
    		String[] dateSplit = dateHired.getValue().toString().split("-");
			String date = dateSplit[1] + "/" + dateSplit[2] + "/" + dateSplit[0];
    		if (csID.isSelected()) {
				dept = "CS";
			} 
			else if (itID.isSelected()) {
				dept = "IT";
			}
			else if (eceID.isSelected()) {
				dept = "ECE";
			}
    		Profile profile = new Profile(emp, dept, date);
    		Employee employee = new Employee(profile);
			if (company.remove(employee)) {
				messageArea1.appendText("Employee removed. \n");
			}
			else {
				if (company.getNumEmployee() == 0) { 
					messageArea1.appendText("Employee database is empty. \n");
				}
				else {
					messageArea1.appendText("Employee does not exist. \n");
				}
			}
    	}
    	catch (Exception e) {
    		messageArea1.appendText("Error. Please recheck input! \n");
    	}
    }

    @FXML
    /**
    This method calculates payments when the Calculate button is pressed.
    @param event of button pressed
    */
    void calculate(ActionEvent event) {
    	try {
    		company.processPayments();
    		messageArea2.appendText("Calculation of employee payments is done \n");
    	}
    	catch (Exception e) {
    		messageArea2.appendText("Error. \n");
    	}
    }
    	
    @FXML
    /**
    This method sets the hours of a parttime worker based on the information given about the parttime worker in Employee
    Management and after pressing the Set Hours button. There is an error message if working hours is below 0 or above 100 or
    if not enough information is given.
    @param event of information given
    */
    void setHours(ActionEvent event) {
    	try {
    		String emp = name.getText();
    		String dept = "";
    		String[] dateSplit = dateHired.getValue().toString().split("-");
			String date = dateSplit[1] + "/" + dateSplit[2] + "/" + dateSplit[0];
    		if(csID.isSelected()) {
    			dept = "CS";
    		} 
    		else if (itID.isSelected()) {
    			dept = "IT";
    		}
    		else if (eceID.isSelected()) {
    			dept = "ECE";
    		}
    		Profile profile = new Profile(emp, dept, date);
    		String hours = hours.getText();
    		int hourss = Integer.parseInt(hours);
    		Parttime parttime = new Parttime(profile, 0);
    		parttime.setHours(hourss);
    		if (hourss < 0) {
    			messageArea1.appendText("Working hours cannot be negative. \n");
    		}
    		else if (hourss>100) {
    			messageArea1.appendText("Invalid Hours: over 100. \n");
    		}
    		else {
    			if (company.setHours(parttime)) {
    				messageArea1.appendText("Working hours set. \n");
    			} //no print for if employee doesnt exist or isnt parttime
    		}
    	}
    	catch (Exception e) {
    		messageArea1.appendText("Error. Please recheck input! \n");
    	}
    }

    @FXML
    /**
    The method prints all the employees in the company after the Print Employees button is pressed. If there are no employees yet, there's an
    error message.
    @param event of button pressed
    */
    String print(ActionEvent event) { //either separate or combine like did for add
    	try {
    		if (company.getNumEmployee() > 0) {
    			messageArea2.appendText("--Printing earning statements-- \n");
    			messageArea2.appendText(company.print());
    		}
    		else {
    			messageArea2.appendText("Employee database is empty. \n");
    		}
    		return company.print();
    	}
    	catch (Exception e) {
    		messageArea2.appendText("Error. \n");
    	}
    }
    	
    @FXML
    /**
    The method prints the employees ordered by department after the Print By Dep button is pressed. If there are no employees yet, there's an
    error message.
    @param event of button pressed
    */
    String printByDept(ActionEvent event) {
    	try {
    		if (company.getNumEmployee() > 0) {
    			messageArea2.appendText("--Printing earning statements-- \n");
    			messageArea2.appendText(company.printByDepartment());
    		}
    		else {
    			messageArea2.appendText("Employee database is empty. \n");
    		}
    		return company.printByDepartment();
    	}
    	catch (Exception e) {
    		messageArea2.appendText("Error. \n");
    	}
    }
    
    @FXML
    /**
    The method prints the employees in order of date hired after the Print By Date button is pressed. If there are no employees yet, there's an
    error message.
    @param event of button pressed
    */
    String printByDate(ActionEvent event) {
    	try {
    		if (company.getNumEmployee() > 0) {
    			messageArea2.appendText("--Printing earning statements-- \n");
    			messageArea2.appendText(company.printByDate());
    		}
    		else {
    			messageArea2.appendText("Employee database is empty. \n");
    		}
    		return company.printByDate();
    	}
    	catch (Exception e) {
    		messageArea2.appendText("Error. \n");
    	}
    }
    
    @FXML
    /**
    This method imports the database from the file chosen to be opened after the Import button is pressed.
    @param event of button pressed
    */
    void importFile(ActionEvent event) {
    	try {
    		FileChooser chooser = new FileChooser();
    		chooser.setTitle("Import File");
    		chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"), //**idk wtf this is
    				new FileChooser.ExtensionFilter("All Files", "*.*"));
    		Stage stage = new Stage();
    		File file = chooser.showOpenDialog(stage);
    		String filePath = file.getAbsolutePath();
    		String fileName = file.getName();
    		File database = new File(filePath);
    		
    		Scanner scanner = new Scanner(database); //**no errors ig
    		String line = scanner.nextLine();
    		while (scanner.hasNextLine()) {
    			StringTokenizer st = new StringTokenizer(line, ",", false);
    			String command = st.nextToken(); 
    			if (command.equals("P")) { //part-time employee 
    				String name = st.nextToken();
    				String depCode = st.nextToken();
    				String date = st.nextToken();
    				double hourlyRate = Double.parseDouble(st.nextToken());
    				Profile profile = new Profile(name, depCode, date);
    				Parttime parttime = new Parttime(profile, hourlyRate); 
					company.add(parttime);
				} 
    			else if (command.equals("F")) { //full-time employee
    				String name = st.nextToken();
    				String depCode = st.nextToken();
    				String date = st.nextToken();
    				double annualSalary = Double.parseDouble(st.nextToken());
    				Fulltime fulltime = new Fulltime(new Profile(name, depCode, date), annualSalary);
    				company.add(fulltime);
				}	 		
    			else if(command.equals("AM")) { //management
    				String name = st.nextToken();
    				String depCode = st.nextToken();
    				String date = st.nextToken();
    				double annualSalary = Double.parseDouble(st.nextToken());
    				int intCode = Integer.parseInt(st.nextToken());
    				final static int MANAGER_CODE = 1;
    				final static int DEPARTMENT_HEAD_CODE = 2;
    				final static int DIRECTOR_CODE = 3;
    				Management management = null;
    				if (intCode == MANAGER_CODE) {
						management = new Management(new Profile(name, depCode, date), annualSalary, "Manager");
					}
					else if (intCode == DEPARTMENT_HEAD_CODE) {
						management = new Management(new Profile(name, depCode, date), annualSalary, "Department Head");
					}
					else if (intCode == DIRECTOR_CODE) { //could just do else ig
						management = new Management(new Profile(name, depCode, date), annualSalary, "Director");
					}
					company.add(management);
				}
    			line = scanner.nextLine();
			} 
    		scanner.close();
    	}
    	catch (Exception e) {
    		messageArea2.append("Error. \n");
    	}
    }
    
    @FXML
    /**
    The method exports the database from the file chosen for the data to be saved to after the Export button is pressed.
    @param event of button pressed
    */
    void exportFile(ActionEvent event) {
    	try {
    		FileChooser chooser = new FileChooser();
    		chooser.setTitle("Export File");
    		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
    				new ExtensionFilter("All Files", "*.*"));
    		Stage stage = new Stage();
    		File file = chooser.showSaveDialog(stage);
    		String filePath = file.getAbsolutePath();
    		String fileName = file.getName();
    		company.exportDatabase(filePath);
    		messageArea2.append("File exported. \n");
    	}
    	catch (Exception e) {
    		messageArea2.append("Error. \n");
    	}
    }
    

}