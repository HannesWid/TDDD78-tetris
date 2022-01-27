package se.liu.tetris.lab3;

import se.liu.tetris.lab1.Person;

import java.util.List;

public class Stack extends  ListManipulator
{
    public Stack(final List<Person> elements) {
	super();
    }

    public void push(Person person){
        elements.add(0,person);
    }
    public Person pop(){
        Person person = elements.get(0);
        elements.remove(0);
        return person;
    }
}
