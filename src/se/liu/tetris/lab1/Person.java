package se.liu.tetris.lab1;

import java.time.LocalDate;
import java.time.Period;

public class Person
{
    private String name;
    private LocalDate birthday;

    public Person(final String name, final LocalDate birthday) {
	this.name = name;
	this.birthday = birthday;
    }

    public int getAge() {
        LocalDate birthDate = this.birthday;
	LocalDate today = LocalDate.now();
	int ageWithDays = Period.between(birthDate, today).getYears();
	return ageWithDays;
    }

    @Override public String toString() {
	return name + " " + getAge();
    }

    public static void main(String[] args) {
        LocalDate myBirthDate = LocalDate.of(2001,
					     9,
					     10);
        String myName = "Hannes";
        Person myInfo = new Person(myName, myBirthDate);
	System.out.println(myInfo.birthday);
	System.out.println(myInfo.name);
	System.out.println(myInfo.getAge());
	System.out.println(new Person("Erik", LocalDate.of(1993, 2, 10)));
	System.out.println(new Person("David", LocalDate.of(1991, 1, 5)));
	System.out.println(new Person("Sara", LocalDate.of(1977, 4, 20)));
    }

}
