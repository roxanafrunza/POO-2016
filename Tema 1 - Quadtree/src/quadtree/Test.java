package quadtree;

import java.io.*;
import java.util.*;

public class Test {

	static ArrayFigure figures = new ArrayFigure(20);
	static Quadtree quadtree;

	/**
	 * Using a scanner, function reads data from file. 
	 * Operation type: 
	 * 11 - insert a figure in quadtree; 
	 * 22 - delete a figure given by ID from tree; 
	 * 33 - check collision between point (x,y) and the figures from quadtree; 
	 * 44 - check collision between a figure and the figures from quadtree.
	 * 
	 * Figure type: 1 - rectangle 2 - triangle 3 - circle 4 - diamond
	 * 
	 * @throws FileNotFoundException
	 *             if file quadtree.in doesn't exist
	 */
	public static void readData() throws FileNotFoundException {
		Scanner input = new Scanner(new File("quadtree.in"));
		PrintWriter print = new PrintWriter(new File("quadtree.out"));

		// Reading quadrant limits
		double limit1 = input.nextDouble();
		double limit2 = input.nextDouble();
		double limit3 = input.nextDouble();
		double limit4 = input.nextDouble();

		// Building a quadtree with given limits
		quadtree = new Quadtree(limit1, limit2, limit3, limit4);
		while (input.hasNextInt()) {
			int operationID = input.nextInt(); // Reading operation type
			if (operationID == 11) { // Insert in quadtree
				int figureType = input.nextInt();
				if (figureType == 1) // Rectangle
				{
					Rectangle obj = new Rectangle();
					obj.ID = input.nextInt();
					obj.read(input);
					figures.add(obj);
					quadtree.insert(quadtree, obj);

				} else if (figureType == 2) // Triangle
				{
					Triangle obj = new Triangle();
					obj.ID = input.nextInt();
					obj.read(input);
					figures.add(obj);
					quadtree.insert(quadtree, obj);

				} else if (figureType == 3) // Circle
				{
					Circle obj = new Circle();
					obj.ID = input.nextInt();
					obj.read(input);
					figures.add(obj);
					quadtree.insert(quadtree, obj);

				} else if (figureType == 4) // Diamond
				{
					Diamond obj = new Diamond();
					obj.ID = input.nextInt();
					obj.read(input);
					figures.add(obj);
					quadtree.insert(quadtree, obj);

				}

			} else if (operationID == 22) { // Delete from tree

				int figureID = input.nextInt();
				Figure fig = figures.get(figureID);
				quadtree.delete(quadtree, fig);
				figures.remove(figureID);

			} else if (operationID == 33) { // Point collision

				double x = input.nextDouble();
				double y = input.nextDouble();
				ArrayFigure array = new ArrayFigure(5);
				quadtree.collisionPoint(quadtree, x, y, array);
				if (array.count == 0)
					print.print("NIL");
				else {
					int[] arrayID = new int[array.count];
					int index = 0;
					for (int i = 0; i < array.count; i++) {
						Figure fig = array.getByIndex(i);
						arrayID[index++] = fig.ID;
					}

					Arrays.sort(arrayID);
					print.print(arrayID[0]);
					for (int i = 1; i < array.count; i++) {
						print.print(" ");
						print.print(arrayID[i]);
					}
				}
				print.println();
			} else if (operationID == 44) { //Point collision
				int figureType = input.nextInt();
				if (figureType == 1) // Rectangle
				{
					Rectangle obj = new Rectangle();
					obj.read(input);
					ArrayFigure array = new ArrayFigure(5);
					quadtree.collisionFigure(quadtree, obj, array);
					if (array.count == 0)
						print.print("NIL");
					else {
						int[] arrayID = new int[array.count];
						int index = 0;
						for (int i = 0; i < array.count; i++) {
							Figure fig = array.getByIndex(i);
							arrayID[index++] = fig.ID;
						}

						Arrays.sort(arrayID);
						print.print(arrayID[0]);
						for (int i = 1; i < array.count; i++) {
							print.print(" ");
							print.print(arrayID[i]);
						}
					}

				} else if (figureType == 2) // Triangle
				{
					Triangle obj = new Triangle();
					obj.read(input);
					ArrayFigure array = new ArrayFigure(5);
					quadtree.collisionFigure(quadtree, obj, array);
					if (array.count == 0)
						print.print("NIL");
					else {
						int[] arrayID = new int[array.count];
						int index = 0;
						for (int i = 0; i < array.count; i++) {
							Figure fig = array.getByIndex(i);
							arrayID[index++] = fig.ID;
						}

						Arrays.sort(arrayID);
						print.print(arrayID[0]);
						for (int i = 1; i < array.count; i++) {
							print.print(" ");
							print.print(arrayID[i]);
						}
					}

				} else if (figureType == 3) // Circle
				{
					Circle obj = new Circle();
					obj.read(input);
					ArrayFigure array = new ArrayFigure(5);
					quadtree.collisionFigure(quadtree, obj, array);
					if (array.count == 0)
						print.print("NIL");
					else {
						int[] arrayID = new int[array.count];
						int index = 0;
						for (int i = 0; i < array.count; i++) {
							Figure fig = array.getByIndex(i);
							arrayID[index++] = fig.ID;
						}

						Arrays.sort(arrayID);
						print.print(arrayID[0]);
						for (int i = 1; i < array.count; i++) {
							print.print(" ");
							print.print(arrayID[i]);
						}
					}

				} else if (figureType == 4) // Diamond
				{
					Diamond obj = new Diamond();
					obj.read(input);
					ArrayFigure array = new ArrayFigure(5);
					quadtree.collisionFigure(quadtree, obj, array);
					if (array.count == 0)
						print.print("NIL");
					else {
						int[] arrayID = new int[array.count];
						int index = 0;
						for (int i = 0; i < array.count; i++) {
							Figure fig = array.getByIndex(i);
							arrayID[index++] = fig.ID;
						}

						Arrays.sort(arrayID);
						print.print(arrayID[0]);
						for (int i = 1; i < array.count; i++) {
							print.print(" ");
							print.print(arrayID[i]);
						}
					}
				}
				print.println();
			}
		}

		input.close();
		print.close();
	}

	public static void main(String[] args) throws FileNotFoundException {
		readData();
	}

}
