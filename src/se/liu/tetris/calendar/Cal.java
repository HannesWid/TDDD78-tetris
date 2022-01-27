package se.liu.tetris.calendar;

import java.util.ArrayList;
import java.util.List;

public class Cal
{
    private List<Appointment> appointments;

    public Cal() {
	appointments = new ArrayList<>();
    }

    public void show(){
	for (Appointment appointment : appointments) {
	    System.out.println(appointment);
	}
    }
    public void book(int year, String month, int day,
		     int startHour, int startMinute, int endHour,
		     int endMinute, String subject){
        if(isValidBook(year, month, day, startHour, startMinute, endHour, endMinute))
        {
	TimePoint startTime = new TimePoint(startHour, startMinute);
	TimePoint endTime = new TimePoint(endHour, endMinute);
	TimeSpan currentTimeSpan = new TimeSpan(startTime, endTime);
	Month currentMonth = new Month(month, Month.getMonthNumber(month), Month.getMonthDays(month));
	SimpleDate date = new SimpleDate(year,currentMonth, day);
	Appointment appointment = new Appointment(subject, date, currentTimeSpan);
	appointments.add(appointment);
	}
        else {
	    throw new IllegalArgumentException("felmeddelande");
	}
    }

    private boolean isValidBook(final int year, final String month, final int day,
				final int startHour, final int startMinute,
			        final int endHour, final int endMinute) {
        boolean validDate = year > 1970 && Month.getMonthNumber(month) > -1 && Month.getMonthDays(month) >= day;
        boolean validStartTime = 0 <= startHour && startHour <= 23 && 0 <= startMinute && startMinute <= 59;
        boolean validEndTime = 0 <= endHour && endHour <= 23 && 0 <= endMinute && endMinute <= 59;
	return validDate && validStartTime && validEndTime;
    }

    public static void main(String[] args) {
        Cal myCal = new Cal();
        myCal.book(2001, "Mars", 10, 10,50,11,30,"MatteLektion");
	myCal.book(2002, "April", 12, 13,50,15,20,"SvenskaLektion");
	myCal.book(2005,"September", 14, 10,10,11,15,"Early break");
	myCal.show();
    }
}
