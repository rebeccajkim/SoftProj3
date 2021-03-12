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
	private RadioButton csID, itID, eceID, FullTimeID, PartTimeID, ManagementID, 
	    						managerID, depheadID, directorID;
	    
	@FXML
	/**
	Event Handler for the add button
	@param event
	*/
	void add(ActionEvent event) {
	   	//messageArea.clear(); //clear the TextArea.
	   	try {
	   		String emp = name.getText();
			String dept = "";
			//String date = dateHired.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

			//System.out.println();
	    	if(FullTimeID.isSelected()) {
	    		String annualSal = annSal.getText();
				double annSalary = Double.parseDouble(annualSal);
				if(csID.isSelected()) {
					dept = "CS";
				} 
				else if(itID.isSelected()) {
					dept = "IT";
				}
				else if(eceID.isSelected()) {
					dept = "ECE";
				}
				Profile profile = new Profile(emp, dept, date);
				Fulltime fulltime = new Fulltime(profile, annSalary);
				if(!fulltime.getProfile().getDateHired().isValid()) {
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
	    	} 
	    	else if(ManagementID.isSelected()) {
	    		String annualSal = annSal.getText();
				double annSalary = Double.parseDouble(annualSal);
				String role = "";
	    		if(managerID.isSelected()) {
	    			role = "Manager";
	    		}
	    		else if(depheadID.isSelected()) {
	    			role = "Department Head";
	    		}
	    		else if(directorID.isSelected()) {
	    			role = "Director";
	    		}
	    		if(csID.isSelected()) {
	    			dept = "CS";
	    		}
	    		else if(itID.isSelected()) {
	    			dept = "IT";
	    		}
	    		else if (eceID.isSelected()) {
	    			dept = "ECE";
	    		}
	    		Profile profile = new Profile(emp, dept, date);
				Management management = new Management(profile, annSalary, role);
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
	    	} 
	    	else if(PartTimeID.isSelected()){
	    		String hrRate = rate.getText();
				double hrlyRate = Double.parseDouble(hrRate);
				if(csID.isSelected()) {
					dept = "CS";
				}
				else if(itID.isSelected()) {
					dept = "IT";
				}
				else if (eceID.isSelected()) {
					dept = "ECE";
				}
	    		Profile profile = new Profile(emp, "CS", "06/16/2000");
	    		Parttime parttime = new Parttime(profile, hrlyRate);
				if(!parttime.getProfile().getDateHired().isValid()) {
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
	   	}	
	    //Show the error message with a pop-up window.
	    catch (Exception e) {
			messageArea1.appendText("Error.\n");
	    }
	}
	
    @FXML
    /**
    Event Handler for the remove button
    @param event
    */
    void remove(ActionEvent event) {
    	try {
    		String emp = name.getText();
    		String dept = "";
    		//String date = dateHired.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    		if(csID.isSelected()) {
				dept = "CS";
			} 
			else if(itID.isSelected()) {
				dept = "IT";
			}
			else if(eceID.isSelected()) {
				dept = "ECE";
			}
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
    Event Handler for the payment button
    @param event
    */
    void calculatePayments(ActionEvent event) {
    	try {
    		company.processPayments();
    		messageArea1.appendText("Calculation of employee payments is done \n");
    	}
    	catch (Exception e) {
    		messageArea1.appendText("Error. \n");
    	}
    }
    	
    @FXML
    /**
    Event Handler for the set hours button
    @param event
    */
    void setHours(ActionEvent event) {
    	try {
    		String emp = name.getText();
    		String dept = "";
    		//String date = dateHired.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    		if(csID.isSelected()) {
    			dept = "CS";
    		} 
    		else if(itID.isSelected()) {
    			dept = "IT";
    		}
    		else if(eceID.isSelected()) {
    			dept = "ECE";
    		}
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
    	catch (Exception e) {
    		messageArea1.appendText("Error. \n");
    	}
    }

    @FXML
    /**
    Event Handler for the print button
    @param event
    */
    void print(ActionEvent event) { //either separate or combine like did for add
    	company.print();
    }
    	
    @FXML
    /**
    Event Handler for the print by department button
    @param event
    */
    void printByDept(ActionEvent event) {
    	company.printByDepartment();
    }
    
    @FXML
    /**
    Event Handler for the print by date button
    @param event
    */
    void printByDate(ActionEvent event) {
    	company.printByDate();
    }
    

}