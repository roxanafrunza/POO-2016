package quadtree;

public class Quadrant {
	double x1, x2;
	double y1, y2;
	double width, height;

	/**
	 * Builds a quadrant with given coordinates Calculates width and height
	 * 
	 * @param x1
	 *            x-axis minimum
	 * @param x2
	 *            x-axis maximum
	 * @param y1
	 *            y-axis minimum
	 * @param y2
	 *            y-axis maximum
	 */
	public Quadrant(double x1, double y1, double x2, double y2) {

		double tmp;
		// Check if coordinates are in proper order
		if (x1 > x2) {
			tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		if (y1 > y2) {
			tmp = y1;
			y1 = y2;
			y2 = tmp;
		}

		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;

		width = Math.abs(x1 - x2);
		height = Math.abs(y1 - y2);
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	/**
	 * Checks if quadrant contains point (x,y)
	 * 
	 * @param x
	 *            x-axis coordinate
	 * @param y
	 *            y-axis coordinate
	 * @return True if quadrant contains point
	 */
	public boolean contains(double x, double y) {
		return ((x >= x1 && x <= x2) && (y >= y1 && y <= y2));
	}

	/**
	 * Checks if quadrant intersects figure Figure is given by its frame
	 * coordinates (x1,x2,y1,y2)
	 *
	 * @param fig
	 *            figure with which we check intersection
	 * @return True if figure intersects quadrant
	 */
	public boolean intersects(Figure fig) {

		// Get frame for figure
		fig.getFrameCoordinates();
		return !(x1 > fig.x2 || x2 < fig.x1 || y1 > fig.y2 || y2 < fig.y1);
	}

}
