package project3;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
This Date class stores the day, month, and year from user input in each 
identified variable and makes sure if a date is valid. 
@author mayeesha, rebecca
*/
public class Date implements Comparable<Date> {
	private int year;
	private int month; 
	private int day;
	
	/**
	Constructor that makes a date object based on a string of the form mm/dd/yyyy
	@param date in string form
	*/
	public Date(String date) { //taking mm/dd/yyyy and create a Date object
		StringTokenizer st = new StringTokenizer(date, "/", false);
		month = Integer.parseInt(st.nextToken()); 
		day = Integer.parseInt(st.nextToken());
		year = Integer.parseInt(st.nextToken());
	}
	
	/**
	Constructor that creates a date object with the current date
	*/
	public Date() { //create an object with today's date (see Calendar class)
		year=Calendar.getInstance().get(Calendar.YEAR);
		month=Calendar.getInstance().get(Calendar.MONTH)+1;
		day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	//...
		
	/**
	Getter method for year so it can be used in other classes
	@return year
	*/
	public int getYear() {
		return year;
	}
	
	/**
	Getter method for month so it can be used in other classes
	@return month
	*/
	public int getMonth() {
		return month;
	}
	
	/**
	Getter method for day so it can be used in other classes
	@return day
	*/
	public int getDay() {
		return day;
	}
	
	/**
	The method tests if a date is valid or not. If the date has a year less than 1900 or a
	date after today's date, it's not valid. Also, the date can't have a day that is greater
	than the number of days in that month (depends on the month and leap years).
	@return true if date is valid, false if invalid
	*/
	public boolean isValid() {
		final static int MIN_YEAR = 1900; final static int JAN = 1; final static int FEB = 2; final static int MAR = 3;
			final static int APR = 4; final static int MAY = 5; final static int JUN = 6; final static int JUL = 7;
			final static int AUG = 8; final static int SEP = 9; final static int OCT = 10; final static int NOV = 11;
			final static int DEC = 12; final static int LOW_DAY = 1; final static int NO_LEAP = 28; 
			final static int YES_LEAP = 29; final static int LOW_END = 30; final static int HIGH_END = 31; 
			final static int FIRST_CHECK = 4; final static int SECOND_CHECK = 100; final static int THIRD_CHECK = 400; 
			
		if (year < MIN_YEAR || year > Calendar.getInstance().get(Calendar.YEAR)
			|| (year == Calendar.getInstance().get(Calendar.YEAR) && month > (Calendar.getInstance().get(Calendar.MONTH) + 1))
			|| (year == Calendar.getInstance().get(Calendar.YEAR) && month == (Calendar.getInstance().get(Calendar.MONTH) + 1)
				&& day > Calendar.getInstance().get(Calendar.DAY_OF_MONTH))) {
			return false;
		}
		else if ((month == JAN || month == MAR || month == MAY || month == JUL || month == AUG || month == OCT
			|| month == DEC) && day > HIGH_END) {
			return false;
		}
		else if ((month == APR || month == JUL || month == SEP || month == NOV) && day > LOW_END) {
			return false;
		}
		else if (month > DEC || month < JAN || day < LOW_DAY) {
			return false;
		}
		else if (month == FEB) {
			if (year % FIRST_CHECK == 0) {
				if (year % SECOND_CHECK == 0) {
					if (year % THIRD_CHECK == 0) {
						if (day > YES_LEAP) {
							return false;
						}
					}
					else {
						if (day > NO_LEAP) {
							return false;
						}
					}
				}
				else {
					if (day > YES_LEAP) {
						return false;
					}
				}
			}
			else {
				if (day > NO_LEAP) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	The method compares the date of two employees and returns -1/1/0 accordingly if they
	are equal, greater than, or less than.
	@param date hired
	@return an int value to show if a date is equal, greater than, or less than. (-1/0/1)
	*/
	@Override
	public int compareTo(Date date) { 
		if (this.year == date.getYear() && this.month == date.getMonth() && day == date.getDay()) {
			return 0;
		}
		else if ((this.year < date.getYear()) || (this.year == date.getYear() 
				&& this.month < date.getMonth()) || (this.year == date.getYear() 
				&& this.month == date.getMonth() && this.day < date.getDay())) {
			final static int LOWER = -1;
			return LOWER;
		}
		else {
			return 1;
		}
	}
}	

	
	
	
	
	
	
	