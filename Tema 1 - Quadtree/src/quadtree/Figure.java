package quadtree;

public abstract class Figure {
	double x1, x2;
	double y1, y2;
	int ID;

	/**
	 * Method that gets the frame coordinates for a specific figure. Implemented
	 * differently for every type of figure
	 */
	public abstract void getFrameCoordinates();

	/**
	 * Method that checks if a point is in a specific figure. Implemented
	 * differently for every type of figure
	 * 
	 * @param x
	 *            x-axis coordinate
	 * @param y
	 *            y-axis coordinate
	 * @return True if figure contains point (x,y)
	 */
	public abstract boolean contains(double x, double y);

	
	/**
	 * Check if two figures given by their frame intersect
	 * @param fig
	 * @return True if 2 figures intersect
	 */
	public boolean intersects(Figure fig) {
		fig.getFrameCoordinates();
		return !(x1 > fig.x2 || x2 < fig.x1 || y1 > fig.y2 || y2 < fig.y1);
	}

}
