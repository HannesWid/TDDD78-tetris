package se.liu.tetris.lab3;

import se.liu.tetris.lab1.Person;

import java.util.List;

public class Queue extends ListManipulator
{

    public Queue(final List<Person> elements) {
        super();
    }

    public void enqueue(Person person){
        elements.add(size(), person);
    }
    public Person dequeue(){
        Person person = elements.get(0);
        elements.remove(0);
        return person;
    }
}
