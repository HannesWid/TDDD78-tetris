package se.liu.tetris.calendar;

public class Month
{
    private String name;
    private int number;
    private int days;

    public Month(final String name, final int number, final int days) {
	this.name = name;
	this.number = number;
	this.days = days;
    }

    public String getName() {
	return name;
    }

    public int getNumber() {
	return number;
    }

    public int getDays() {
	return days;
    }

    public static int getMonthNumber(String name){
        switch(name) {
	    case "January":
	        return 1;
	    case "February":
		return 2;
	    case "Mars":
		return 3;
	    case "April":
		return 4;
	    case "May":
		return 5;
	    case "June":
		return 6;
	    case "July":
		return 7;
	    case "August":
		return 8;
	    case "September":
		return 9;
	    case "October":
		return 10;
	    case "November":
		return 11;
	    case "December":
		return 12;
	    default:
	        return -1;
	}
    }
    public static int getMonthDays(String name){
	switch(name) {
	    case "January":
	    case "December":
	    case "Mars":
	    case "May":
	    case "July":
	    case "August":
	    case "October":
	        return 31;
	    case "February":
		return 28;
	    case "April":
	    case "June":
	    case "September":
	    case "November":
		return 30;
	    default:
		return -1;
	}
    }
}
