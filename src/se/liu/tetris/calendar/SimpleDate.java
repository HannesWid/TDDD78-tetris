package se.liu.tetris.calendar;

public class SimpleDate
{
    private int year;
    private Month month;
    private int day;

    public SimpleDate(final int year, final Month month, final int day) {
	this.year = year;
	this.month = month;
	this.day = day;
    }

    public String toString(){
        String monthDate = "0" + month.getNumber();
        String date = year + "-" + monthDate + "-" + day;
        return date;


    }
}
