package quadtree;

import java.util.Scanner;

public class Diamond extends Figure {
	double x1, x2, x3, x4;
	double y1, y2, y3, y4;

	/**
	 * Build a diamond with parameters read from scanner
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
		x4 = s.nextDouble();
		y4 = s.nextDouble();
	}

	@Override
	public void getFrameCoordinates() {
		super.x1 = x2;
		super.x2 = x4;
		super.y1 = Math.min(y3, y1);
		super.y2 = Math.max(y1, y3);

	}

	public double getTriangleArea(double x1, double y1, double x2, double y2, double x3, double y3) {
		double area = (1 / 2) * (Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)));
		System.out.println("x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) : "
				+ Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))));
		System.out.println("area:" + area);
		return area;
	}

	@Override
	public boolean contains(double x, double y) {
		double areaP12 = Math.abs(x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2));
		double areaP23 = Math.abs(x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2));
		double areaP34 = Math.abs(x4 * (y - y3) + x * (y3 - y4) + x3 * (y4 - y));
		double areaP41 = Math.abs(x4 * (y1 - y) + x1 * (y - y4) + x * (y4 - y1));

		double diagonal1 = Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));
		double diagonal2 = Math.sqrt(Math.pow((x2 - x4), 2) + Math.pow((y2 - y4), 2));
		double area = (diagonal1 * diagonal2);

		return areaP12 + areaP23 + areaP34 + areaP41 == area;

	}

}
