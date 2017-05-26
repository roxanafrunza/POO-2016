package quadtree;

import java.util.Scanner;

public class Rectangle extends Figure {
	double x1, x2;
	double y1, y2;
	double width;
	double height;

	/**
	 * Build a rectangle with parameters read from scanner
	 * 
	 * @param s
	 *            scanner used to read from the file
	 */
	public void read(Scanner s) {
		x1 = s.nextDouble();
		y1 = s.nextDouble();
		x2 = s.nextDouble();
		y2 = s.nextDouble();
	}

	/**
	 * Calculate width and height using coordinates
	 */
	public void getDimensions() {
		width = Math.abs(x1 - x2);
		height = Math.abs(y1 - y2);
	}

	@Override
	public void getFrameCoordinates() {
		super.x1 = x1;
		super.x2 = x2;
		super.y1 = y1;
		super.y2 = y2;
	}

	@Override
	public boolean contains(double x, double y) {
		return ((x >= this.x1 && x <= this.x2) && (y >= this.y1 && y <= this.y2));
	}

}
