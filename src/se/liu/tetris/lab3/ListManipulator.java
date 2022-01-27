package se.liu.tetris.lab3;

import se.liu.tetris.lab1.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListManipulator
{

    protected List<Person> elements;

    public ListManipulator() {
	this.elements = new ArrayList<>();
    }

    public int size(){
        return elements.size();
    }

    public boolean isEmpty(){
        return elements.isEmpty();
    }

    public boolean contains(final Object o) {
	return elements.contains(o);
    }

    public Iterator<Person> iterator() {
	return elements.iterator();
    }

    public boolean add(final Person person) {
	return elements.add(person);
    }

    public boolean remove(final Object o) {
	return elements.remove(o);
    }

    public void clear(){
        elements.clear();
    }

    public boolean contains(Person person){
        return elements.contains(person);
    }
}
