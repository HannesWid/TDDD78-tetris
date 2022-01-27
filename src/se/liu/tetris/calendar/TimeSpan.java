package se.liu.tetris.calendar;

public class TimeSpan
{
    private TimePoint start;
    private TimePoint end;

    public TimeSpan(final TimePoint start, final TimePoint end) {
	this.start = start;
	this.end = end;
    }
    public TimePoint getStart() {
	return start;
    }

    public TimePoint getEnd() {
	return end;
    }
    public String toString(){
	String timeSpanString = new StringBuilder().append(start).append("-").append(end).toString();
	return timeSpanString;
    }
}
