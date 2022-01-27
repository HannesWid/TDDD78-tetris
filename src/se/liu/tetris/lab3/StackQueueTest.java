package se.liu.tetris.lab3;

import se.liu.tetris.lab1.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StackQueueTest{
    public static void main(String[] args) {
	List<Person> testArray = new ArrayList<>();
	Stack testStack = new Stack(testArray);
	testStack.push(personMaker("Erik",2001,10,2));
	testStack.push(personMaker("Adam",1997,2,25));
	testStack.push(personMaker("Erika",2004,12,1));
	testStack.push(personMaker("Lars",1992,5,22));
	testStack.push(personMaker("Gustav",2002,4,13));
	while(!testStack.isEmpty()) {
	    System.out.println(testStack.pop());
	}
    }
    public static Person personMaker(String name, int year, int month, int day) {
	LocalDate localDate = LocalDate.of(year, month, day);
	Person localPerson = new Person(name, localDate);
	return localPerson;
    }
}
