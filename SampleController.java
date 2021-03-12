package project3;

import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import project2.Employee;
import project2.Parttime;
import project2.Profile;

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
    /**
    Event Handler for the add button
    @param event
    */
    void add(ActionEvent event) {
    	//messageArea.clear(); //clear the TextArea.
    	try {
    		String emp = name.getText();
    		RadioButton selectDep = (RadioButton) dep.getSelectedToggle();
    		String dept = selectDep.getText();
    		String date = dateHired.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		    Profile profile = new Profile(emp, dept, date);
		    Employee employee = new Employee(profile);
    		
    		RadioButton selectEmp = (RadioButton) empType.getSelectedToggle();
    		if(empType.equals("Full Time")) {
    			String annualSal = annSal.getText();
				double annSalary = Double.parseDouble(annualSal);
				Fulltime fulltime = new Fulltime(profile, annSalary);
				
				if(!(dept.equals("CS") || dept.equals("ECE") || dept.equals("IT"))) { //**i guess if radio button then dont need this
					messageArea1.appendText("'" + dept + "'" + " is not a valid department code. \n");
				}
				else if(!fulltime.getProfile().getDateHired().isValid()) {
					messageArea1.appendText(date + " is not a valid date! \n");
				}
				else {
					if(company.add(fulltime)) {
						messageArea1.appendText("Employee added. \n");
					}
					else {
						messageArea1.appendText("Employee is already in the list. \n");
					}
				}	 		

    		} else if (empType.equals("Management")) {
    			String annualSal = annSal.getText();
				double annSalary = Double.parseDouble(annualSal);
	    		RadioButton selectMgmt = (RadioButton) mgmtType.getSelectedToggle(); //dont need to check if dept valid or mgmtRole valid since just radio buttons
	    		String mgmtRole = selectMgmt.getText();
	    		Management management=null;
	    		
	    		 if(mgmtRole.equals("Manager")) {
	    			management = new Management(profile, annSalary, mgmtRole);
					//management.setRole("Manager");
				
	    		}else if(mgmtRole.equals("Department Head")) {
					management = new Management(profile, annSalary, mgmtRole);
					//management.setRole("Department Head");
	
	    		}else if(mgmtRole.equals("Director")) {
					management = new Management(profile, annSalary, mgmtRole);
					//management.setRole("Director");
		
	    		}
	    		 
				if(!management.getProfile().getDateHired().isValid()) { 
					messageArea1.appendText(date + " is not a valid date! \n");
				}
				else {
					if(company.add(management)) {
						messageArea1.appendText("Employee added. \n");
					}
					else {
						messageArea1.appendText("Employee is already in the list. \n");
					}
				}
	    		
    		} else if (empType.equals("Parttime")) {
    			String hourlyRate = hourlyRate.getText();
				double hourRate = Double.parseDouble(hourlyRate);
				Parttime parttime = new Parttime(profile, hourRate);
				
				if(!(dept.equals("CS") || dept.equals("ECE") || dept.equals("IT"))) { //**i guess if radio button then dont need this
					messageArea1.appendText("'" + dept + "'" + " is not a valid department code. \n");
				}
				else if(!parttime.getProfile().getDateHired().isValid()) {
					messageArea1.appendText(date + " is not a valid date! \n");
				}
				else {
					if(company.add(parttime)) {
						messageArea1.appendText("Employee added. \n");
					}
					else {
						messageArea1.appendText("Employee is already in the list. \n");
					}
				}	 	
    		}
    	//}
    	//Show the error message with a pop-up window.
    	//catch (Exception e) {
			//messageArea1.appendText("Error.\n");
    	//}
    }
    
    @FXML
    /**
    Event Handler for the add button
    @param event
    */
    void remove(ActionEvent event) {
    	try {
    		String emp = name.getText();
			RadioButton selectDep = (RadioButton) dep.getSelectedToggle();
			String dept = selectDep.getText();
    		String date = dateHired.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    		Profile profile = new Profile(emp, dept, date);
    		Employee employee = new Employee(profile);
			if(company.remove(employee)) {
				messageArea1.appendText("Employee removed. \n");
			}
			else {
				if(company.getNumEmployee()==0) { 
					messageArea1.appendText("Employee database is empty. \n");
				}
				else {
					messageArea1.appendText("Employee does not exist. \n");
				}
			}
    	}
    	catch (Exception e) {
    		messageArea1.appendText("Error. \n");
    	}
    }

    @FXML
    /**
    Event Handler for the add button
    @param event
    */
    void calculatePayments(ActionEvent event) {
    	company.processPayments();
    	messageArea1.appendText("Calculation of employee payments is done \n");
    }
    	
    @FXML
    /**
    Event Handler for the add button
    @param event
    */
    void setHours(ActionEvent event) {
    	String emp = name.getText();
		RadioButton selectDep = (RadioButton) dep.getSelectedToggle();
		String dept = selectDep.getText();
		String date = dateHired.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		Profile profile = new Profile(emp, dept, date);
		String hours = hours.getText();
		double hourss = Double.parseDouble(hours);
		Parttime parttime=new Parttime(profile, 0);
		parttime.setHours(hourss);
		if(hourss<0) {
			messageArea1.appendText("Working hours cannot be negative. \n");
		}
		else if(hourss>100) {
			messageArea1.appendText("Invalid Hours: over 100. \n");
		}
		else {
			if(company.setHours(parttime)) {
				messageArea1.appendText("Working hours set. \n");
			} //no print for if employee doesnt exist or isnt parttime
		}
    }

    @FXML
    /**
    Event Handler for the add button
    @param event
    */
    void print(ActionEvent event) { //either separate or combine like did for add
    	company.print();
    }
    	
    @FXML
    /**
    Event Handler for the add button
    @param event
    */
    void printByDept(ActionEvent event) {
    	company.printByDepartment();
    }
    
    @FXML
    /**
    Event Handler for the add button
    @param event
    */
    void printByDate(ActionEvent event) {
    	company.printByDate();
    }
    

}