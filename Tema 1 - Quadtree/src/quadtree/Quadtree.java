package quadtree;

public class Quadtree {

	Quadrant quadrant;
	ArrayFigure figures;
	Quadtree NW, NE, SE, SW;
	boolean split;

	/**
	 * Build a new quadtree given by quadrant limits
	 * 
	 * @param limit1
	 *            - minimum x value of quadrant
	 * @param limit2
	 *            - minimum y value of quadrant
	 * @param limit3
	 *            - maximum x value of quadrant
	 * @param limit4
	 *            - maximum y value of quadrant
	 */
	public Quadtree(double limit1, double limit2, double limit3, double limit4) {
		this.quadrant = new Quadrant(limit1, limit2, limit3, limit4);
		this.NW = null;
		this.NE = null;
		this.SE = null;
		this.SW = null;
		this.figures = new ArrayFigure(1);
		this.split = false;
	}

	/**
	 * Insert figure in quadtree
	 * 
	 * @param quadtree
	 *            the tree where the figure is inserted
	 * 
	 * @param fig
	 *            figure to be inserted
	 */
	public void insert(Quadtree quadtree, Figure fig) {

		// Check if figure intersects quadrant
		// Exit if false
		if (quadtree.quadrant.intersects(fig) == false) {
			return;
		}

		// If there is a leaf with no figures, insert and exit
		if (quadtree.split == false && quadtree.figures.isEmpty() == true) {
			quadtree.figures = new ArrayFigure(1);
			quadtree.figures.add(fig);
			return;
		}
		// If there is a leaf with at least one figure in its quadrant
		if (quadtree.split == false) {
			if (quadtree.figures.count != 0) {
				// Check if figure intersects other figure already in current
				// node
				for (int i = 0; i < quadtree.figures.count; i++) {
					Figure figure = quadtree.figures.getByIndex(i);
					// If true, insert and exit
					if (fig.intersects(figure) == true) {
						quadtree.figures.add(fig);
						return;
					}
				}
			}

			// Otherwise, we create the 4 children
			quadtree.SW = new Quadtree(quadtree.quadrant.x1, quadtree.quadrant.y1,
					(quadtree.quadrant.x1 + quadtree.quadrant.x2) / 2,
					(quadtree.quadrant.y1 + quadtree.quadrant.y2) / 2);
			quadtree.NW = new Quadtree(quadtree.quadrant.x1, (quadtree.quadrant.y1 + quadtree.quadrant.y2) / 2,
					(quadtree.quadrant.x1 + quadtree.quadrant.x2) / 2, quadtree.quadrant.y2);
			quadtree.NE = new Quadtree((quadtree.quadrant.x1 + quadtree.quadrant.x2) / 2,
					(quadtree.quadrant.y1 + quadtree.quadrant.y2) / 2, quadtree.quadrant.x2, quadtree.quadrant.y2);
			quadtree.SE = new Quadtree((quadtree.quadrant.x1 + quadtree.quadrant.x2) / 2, quadtree.quadrant.y1,
					quadtree.quadrant.x2, (quadtree.quadrant.y1 + quadtree.quadrant.y2) / 2);

			// Mark that the current node is split
			quadtree.split = true;

			// Take every figure in the current node, remove it from current
			// node
			// Insert recursively in corresponding children
			while (quadtree.figures.count > 0) {
				Figure figure = quadtree.figures.getByIndex(0);
				quadtree.figures.remove(figure.ID);
				if (quadtree.SW.quadrant.intersects(figure))
					insert(quadtree.SW, figure);
				if (quadtree.NW.quadrant.intersects(figure))
					insert(quadtree.NW, figure);
				if (quadtree.SE.quadrant.intersects(figure))
					insert(quadtree.SE, figure);
				if (quadtree.NE.quadrant.intersects(figure))
					insert(quadtree.NE, figure);
			}

		}

		// Insert figure given by parameters
		if (quadtree.SW.quadrant.intersects(fig))
			insert(quadtree.SW, fig);
		if (quadtree.NW.quadrant.intersects(fig))
			insert(quadtree.NW, fig);
		if (quadtree.SE.quadrant.intersects(fig))
			insert(quadtree.SE, fig);
		if (quadtree.NE.quadrant.intersects(fig))
			insert(quadtree.NE, fig);

	}

	/**
	 * Given a point (x,y), checks in the quadtree the figures that contain the
	 * point
	 * 
	 * @param quadtree
	 * @param x
	 *            x-axis coordinate
	 * @param y
	 *            y-axis coordinate
	 * @param array
	 *            - array with the figures that contains (x,y)
	 */
	public void collisionPoint(Quadtree quadtree, double x, double y, ArrayFigure array) {

		// Check if quadrant in current node contains point
		// Exit if false
		if (quadtree == null || quadtree.quadrant.contains(x, y) == false)
			return;

		// If we are in a leaf, check the figures that contain the given point
		// Add the figures that satisfy the condition in an array of figures
		if (quadtree.split == false) {
			for (int i = 0; i < quadtree.figures.count; i++) {
				Figure fig = quadtree.figures.getByIndex(i);
				if (fig.contains(x, y) == true && array.get(fig.ID) == null)
					array.add(fig);
			}
			return;
		}

		// Check recursively the 4 children of current node
		collisionPoint(quadtree.SW, x, y, array);
		collisionPoint(quadtree.NW, x, y, array);
		collisionPoint(quadtree.NE, x, y, array);
		collisionPoint(quadtree.SE, x, y, array);

	}

	/**
	 * Given a figure, checks the figure that intersect it in the quadtree
	 * 
	 * @param quadtree
	 * @param figure
	 *            the figure we want to check the collision with
	 * @param array
	 *            array of figures where we'll save all figures that intersect
	 *            figure
	 */
	public void collisionFigure(Quadtree quadtree, Figure figure, ArrayFigure array) {

		// Get frame for given figure
		figure.getFrameCoordinates();

		// Check if intersects quadrant in current node
		// Exit if false
		if (quadtree == null || quadtree.quadrant.intersects(figure) == false)
			return;

		// Check every figure in current node (if it's a leaf)
		// If it matches the condition, add it to the array of figures
		if (quadtree.split == false) {
			for (int i = 0; i < quadtree.figures.count; i++) {
				Figure fig = quadtree.figures.getByIndex(i);
				if (fig.intersects(figure) == true && array.get(fig.ID) == null)
					array.add(fig);
			}
		}

		// Check recursively the four children
		collisionFigure(quadtree.SW, figure, array);
		collisionFigure(quadtree.NW, figure, array);
		collisionFigure(quadtree.NE, figure, array);
		collisionFigure(quadtree.SE, figure, array);
	}

	/**
	 * Delete the figure given by parameter from the quadtree
	 * 
	 * @param quadtree
	 *            the tree where the figure is removed from
	 * @param figure
	 *            the figure to be removed from tree
	 */
	public void delete(Quadtree quadtree, Figure figure) {

		// Get frame coordinates for figure
		figure.getFrameCoordinates();

		// Check if figure intersects quadrant from current node
		// Exit if false
		if (quadtree.quadrant.intersects(figure) == false) {
			return;
		}

		// Get figure by ID and remove it from current node
		if (quadtree.split == false) {
			if (quadtree.figures.get(figure.ID) != null) {
				quadtree.figures.remove(figure.ID);
				return;
			}
		}

		// If current node isn't a leaf, check recursively the 4 children
		if (quadtree.NW.quadrant.intersects(figure))
			quadtree.delete(quadtree.NW, figure);
		if (quadtree.SW.quadrant.intersects(figure))
			quadtree.delete(quadtree.SW, figure);
		if (quadtree.SE.quadrant.intersects(figure))
			quadtree.delete(quadtree.SE, figure);
		if (quadtree.NE.quadrant.intersects(figure))
			quadtree.delete(quadtree.NE, figure);

	}

}
