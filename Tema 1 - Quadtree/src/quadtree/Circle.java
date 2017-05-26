package quadtree;

import java.util.Scanner;

public class Circle extends Figure {
	double radius;
	double x, y;

	/**
	 * Build a circle with parameters read from scanner
	 * 
	 * @param s
	 *            scanner used to read from the file
	 */

	public void read(Scanner s) {
		radius = s.nextDouble();
		x = s.nextDouble();
		y = s.nextDouble();
	}

	@Override
	public String toString() {
		return "Circle [radius=" + radius + ", x=" + x + ", y=" + y + "]" + super.toString();
	}

	@Override
	public void getFrameCoordinates() {
		super.x1 = x - radius;
		super.x2 = x + radius;
		super.y1 = y - radius;
		super.y2 = y + radius;
	}

	@Override
	public boolean contains(double x, double y) {
		if ((Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)) > Math.pow(radius, 2))
			return false;
		return true;
	}

}
