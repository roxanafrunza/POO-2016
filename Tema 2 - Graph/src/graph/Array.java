package graph;

import java.util.*;

public class Array<E> {

	private ArrayList<E> elements;

	/**
	 * Creates an empty array
	 */
	public Array() {
		elements = new ArrayList<E>();
	}

	/**
	 * Creates an based on a collection (list or set)
	 * 
	 * @param c
	 *            the collection whose elements are to be placed into this list
	 */
	public Array(Collection<? extends E> c) {
		elements = new ArrayList<E>(c);
	}

	/**
	 * Checks if array is empty
	 * 
	 * @return true if array has no elements
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/**
	 * Returns elements from array
	 * 
	 * @return an arraylist containing all the elements
	 */
	public ArrayList<E> getElements() {
		return elements;
	}

	/**
	 * Returns element at index from arraylist
	 * 
	 * @param index
	 *            an integer representing the index
	 * @return element
	 */
	public E getElement(int index) {
		return elements.get(index);

	}

	/**
	 * Returns number of elements in array
	 * 
	 * @return integer representing number of elements
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * Adds element at the end of the array
	 * 
	 * @param el
	 *            element to be inserted
	 */
	public void add(E el) {
		elements.add(el);
	}

	/**
	 * Removes first appearance of element from array
	 * 
	 * @param el
	 *            element to be deleted
	 */
	public void remove(E el) {
		elements.remove(el);
	}
}
