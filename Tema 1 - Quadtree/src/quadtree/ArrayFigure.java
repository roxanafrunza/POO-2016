package quadtree;

public class ArrayFigure {
	Figure[] array;
	int count, size;

	/**
	 * Creates an array of size n
	 * 
	 * @param n
	 *            size of the array
	 */
	public ArrayFigure(int n) {
		array = new Figure[n];
		size = n;
	}

	/**
	 * Add a figure in the array Double the size if the number of elements
	 * exceeds the current size
	 * 
	 * @param fig
	 *            the figure that needs to be added in array
	 */
	public void add(Figure fig) {
		if (count >= size) {
			Figure[] tmp = new Figure[2 * size];
			System.arraycopy(array, 0, tmp, 0, size);
			size *= 2;
			array = tmp;
		}
		array[count] = fig;
		count++;
	}

	/**
	 * Remove the figure with the ID given
	 * 
	 * @param ID
	 *            ID of the figure to be removed
	 */
	public void remove(int ID) {

		int limit = count - 1;
		for (int i = 0; i < limit; i++) {
			if (array[i].ID == ID) {
				for (int j = i; j < limit; j++) {
					array[j] = array[j + 1];
				}
				count--;
			}
		}
		if (array[count - 1].ID == ID)
			count--;
	}

	/**
	 * Get the figure that has the ID given
	 * 
	 * @param ID
	 *            the ID of the figure we search for
	 * @return Figure with the ID given if exists, null otherwise
	 */
	public Figure get(int ID) {
		for (int i = 0; i < count; i++) {
			if (array[i].ID == ID)
				return array[i];
		}
		return null;
	}

	/**
	 * Get the figure at the index given
	 * 
	 * @param index
	 * @return Figure or null if index is out of bounds
	 */
	public Figure getByIndex(int index) {
		if (index < 0 || index > count - 1)
			return null;
		return array[index];
	}

	/**
	 * Display all elements in the array
	 */
	public void displayAll() {

		for (int i = 0; i < count; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * Check if array has no elements
	 * 
	 * @return True if array is empty
	 */
	public boolean isEmpty() {
		return (count == 0);
	}

}
