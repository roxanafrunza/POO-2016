package quadtree;

import java.util.Scanner;

public class Triangle extends Figure {
	double x1, x2, x3;
	double y1, y2, y3;

	/**
	 * Build a triangle with parameters read from scanner
	 * 
	 * @param s
	 *            scanner used to read from the file
	 */
	public void read(Scanner s) {
		x1 = s.nextDouble();
		y1 = s.nextDouble();
		x2 = s.nextDouble();
		y2 = s.nextDouble();
		x3 = s.nextDouble();
		y3 = s.nextDouble();
	}

	@Override
	public void getFrameCoordinates() {
		super.x1 = Math.min(x1, x2);
		super.x2 = Math.max(x1, x3);
		super.y1 = y3;
		super.y2 = y1;

	}

	public double getArea(double x1, double y1, double x2, double y2, double x3, double y3) {
		double area = (Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))) / 2;
		return area;
	}

	@Override
	public boolean contains(double x, double y) {
		double totalArea = getArea(this.x1, this.y1, this.x2, this.y2, this.x3, this.y3);
		double area1 = getArea(x, y, this.x2, this.y2, this.x3, this.y3);
		double area2 = getArea(this.x1, this.y1, x, y, this.x3, this.y3);
		double area3 = getArea(this.x1, this.y1, this.x2, this.y2, x, y);
		return ((area1 + area2 + area3) == totalArea);
	}

}
