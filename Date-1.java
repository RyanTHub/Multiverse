import java.util.*;
/*(without IMPORTS, no main(), no System calls).
1. Do not use any imports from the JAVA API, nor any package statements. 
2. I also insist the fields of your Class must remain private access.  Only three fields allowed in your Date Class (year, month, day).
3. Add a default constructor that initializes the default Date to January 1, 1970
4. The addDays and addWeeks methods can actually subtract days or weeks for a negative parameter passed.  Test these for thousands of days too.
5. Create a static version of daysTo, used as shown in test code below.  And this is the ONLY place that static appears in your Date Class.
*/
/*
 * Write a class called Date that represents a date consisting of a year, month, and day. A Date object should have the following methods:
public Date(int year, int month, int day)
Constructs a new Date object to represent the given date.
public void addDays(int days)
Moves this Date object forward in time by the given number of days. public void addWeeks(int weeks)
Moves this Date object forward in time by the given number of seven-day weeks.
public int daysTo(Date other)
Returns the number of days that this Date must be adjusted to make it equal to the given other Date. public int getDay()
Returns the day value of this date; for example, for the date 2006/07/22, returns 22.
public int getMonth()
Returns the month value of this date; for example, for the date 2006/07/22, returns 7.
public int getYear()
Returns the year value of this date; for example, for the date 2006/07/22, returns 2006.
public boolean isLeapYear()
Returns true if the year of this date is a leap year. A leap year occurs every four years, except for multiples of 100 that are not multiples of 400. For example, 1956, 1844, 1600, and 2000 are leap years, but 1983, 2002, 1700, and 1900 are not.
  public String toString()
Returns a String representation of this date in year/month/day order, such as "2006/07/22"
 */
/*
 * Ryan Tsing
 * CS210
 * 11/28/15
 * Creating a Date.java that can be adjusted and called through in a different class called Assign4.java
 */

public class Date extends GregorianCalendar  { 
	private int year; 
	private int day; 
	private int month;
	
	public Date() { //default constructor 
		this(1970, 1, 1);
	}
	public Date(int year, int month, int day) { //year month day 
		if(year < 0) { 
			throw new IllegalArgumentException(); // for any input that there are -1 year(s) or less 
		}
		if(month < 1 || month > 12) {
			throw new IllegalArgumentException(); // for any input for months that go beyond 12 or before 1 month.
		}
		this.year = year; // assigns to variable year 
		this.month = month; //assigns to variable month
		if(day < 1 || day > daysInThisMonth()) {
			throw new IllegalArgumentException();
		}
		this.day = day; // assigns to variable day 
	}
	public static int daysTo(Date d1, Date d2) { // establishing the dates
		int days = 0;
		Date incrementalDate = new Date(d1.getYear(), d1.getMonth(), d1.getDay()); // Output wanted 
		while(true) {
			if(incrementalDate.getMonth() == d2.getMonth() && incrementalDate.getYear() == d2.getYear()) {
				return days + (d2.getDay() - incrementalDate.getDay()); 
			}
			if(incrementalDate.getYear() == d2.getYear()) { 
				if(incrementalDate.getMonth() > d2.getMonth()) {
					int daysToSubtract = 28;
					days -= daysToSubtract;
					incrementalDate.addDays(-daysToSubtract);
				} else {
					int daysToAdd = 28;
					days += daysToAdd; 
					incrementalDate.addDays(daysToAdd);
 				} 
			} else { 
				if(incrementalDate.getYear() > d2.getYear()) { //365 days a year, added or subtracted 
					int daysToSubtract = 365; 
					days -= daysToSubtract; 
					incrementalDate.addDays(-daysToSubtract);
				} else { 
					int daysToAdd = 365; 
					days += daysToAdd; 
					incrementalDate.addDays(daysToAdd);
				} 
			} 
		}
	}
	public void addDays(int days) { //When called, adds days or subtracts depending on what input
		int daysRemaining = days;
		while (daysRemaining != 0) {
			int daysInThisMonth = daysInThisMonth();
			int additDays = day + daysRemaining; //Days variable added to the days remaining
			if (additDays > daysInThisMonth) {  
				daysRemaining -= daysInThisMonth - day + 1;	
				month++;
				if (month == 13) {
					year++;
					month = 1;
				}
				day = 1;
			} else if (additDays < 1) { 
				month--; 
				if (month == 0) {
					year--; 
					month = 12;
				}
				daysRemaining += day;
				day = daysInThisMonth();
			} else { 
				day += daysRemaining;
				daysRemaining = 0; 
			}
		}
	}
	public void addWeeks(int weeks) { //7 DAYS A WEEK 
		addDays(weeks*7);
	}
	public int daysTo(Date other) {
		return Date.daysTo(this, other);
	}
	public int getDay() { //returns Day 
		return day;
	}
	public int getMonth() { // returns Month
		return month;
	}
	public int getYear() { // returns Year
		return year;
	}
	public boolean isLeapYear() { //finding out leap years
		if (year % 100 == 0) {
			return year % 400 == 0;
		}
		return year % 4 == 0; 
	}
	public String toString() { 
		String monthString = month > 9 ? "" + month : "0" + month;
		String dayString = day > 9 ? "" + day : "0" + day; 
		return year + "/" + monthString + "/" + dayString;   
	 }
	private int daysInThisMonth() { //The Months separated by how many days they have. 
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ) { //31 Day Months
			return 31; 
		} else if (month == 4 || month == 6 || month == 9 || month == 11) { //30 Day Months
			return 30; 
		} else { //special month february
			return isLeapYear() ? 29 : 28; 
		}
	}
}
