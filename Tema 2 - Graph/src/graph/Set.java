package graph;

import java.util.*;

public class Set<E> {
	private HashSet<E> elements;

	/**
	 * Creates an empty set
	 */
	public Set() {
		elements = new HashSet<E>();
	}

	/**
	 * Creates a set based on a collection (array or list)
	 * 
	 * @param c
	 *            the collection whose elements are to be placed into this set
	 */
	public Set(Collection<? extends E> c) {
		elements = new HashSet<E>(c);
	}

	/**
	 * Returns a hashset containing all elements in SET
	 * 
	 * @return
	 */
	public HashSet<E> getElements() {
		return elements;
	}

	/**
	 * Returns an elements specified at index position
	 * 
	 * @param index
	 *            position where the element is found
	 * @return element or null if index doesn't exist
	 */
	public E getElement(int index) {
		if (index < 0 || index >= elements.size())
			throw new IndexOutOfBoundsException();
		for (E temp : elements) {
			if (index == 0)
				return temp;
			index--;
		}
		return null;

	}

	/**
	 * Checks if set has no elements
	 * 
	 * @return true if set contains no elements
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/**
	 * Adds element in set
	 * 
	 * @param el
	 *            element to be inserted
	 */
	public void add(E el) {
		elements.add(el);
	}

	/**
	 * Removes element from set
	 * 
	 * @param el
	 *            element to be removed
	 */
	public void remove(E el) {
		elements.remove(el);
	}

	public int size() {
		return elements.size();
	}
}
