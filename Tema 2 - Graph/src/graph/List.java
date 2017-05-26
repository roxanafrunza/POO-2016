package graph;

import java.util.*;

public class List<E> {
	private LinkedList<E> elements;

	/**
	 * Creates an empty list
	 */
	public List() {
		elements = new LinkedList<E>();
	}

	/**
	 * Creates a list based on a collection (array or set)
	 * 
	 * @param c
	 *            the collection whose elements are to be placed into this list
	 */
	public List(Collection<? extends E> c) {
		elements = new LinkedList<E>(c);
	}

	/**
	 * Returns elements from array
	 * 
	 * @return a linked list containing all the elements
	 */
	public LinkedList<E> getElements() {
		return elements;
	}

	/**
	 * Returns element at index from linked list
	 * 
	 * @param index
	 *            an integer representing the index
	 * @return element
	 */
	public E getElement(int index) {
		return elements.get(index);
	}

	/**
	 * Adds the element at the end of list
	 * 
	 * @param el
	 *            element to be inserted
	 */
	public void add(E el) {
		elements.add(el);
	}

	/**
	 * Deletes the element from the list
	 * 
	 * @param el
	 *            element to be removed
	 */
	public void remove(E el) {
		elements.remove(el);
	}

	/**
	 * Return the number of elements from list
	 * 
	 * @return number of elements
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * Checks if list has no elements
	 * 
	 * @return true if list is empty
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

}
