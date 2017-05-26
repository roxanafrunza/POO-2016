package graph;

public abstract class Node {
	String name;
	char type;
	int version;
	boolean serialized;
	int ID;
	int level;

	public Node(String name, char type, int version) {
		this.name = name;
		this.type = type;
		this.version = version;
		this.level = 0;
		this.ID = 0;
		this.serialized = false;
	}

	public void setType(char ch) {
		this.type = ch;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setID(int i) {
		this.ID = i;
	}

	public char getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public int getVersion() {
		return this.version;
	}

	public int getLevel() {
		return this.level;
	}

	/**
	 * Checks if two nodes are equal Two nodes are considered equal if they have
	 * the same name
	 */
	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((Node) obj).name);
	}

	@Override
	public String toString() {
		if (this.serialized == true) {
			return "<Reference class=\"Nod" + this.type + "\" Version=\"" + this.version + "\" id=\"" + this.ID
					+ "\">\n";
		}
		return "<Object class=\"Nod" + this.type + "\" Version=\"" + this.version + "\" id=\"" + this.ID + "\" Level"
				+ this.level + ">\n";
	}

	/**
	 * Add adjacent node n to current node. This method is abstract, the
	 * implementation differs for every version of node as the way to save the
	 * adjacent nodes differs.
	 * 
	 * @param n
	 *            node to be added in adjacent list/array/set
	 */
	public abstract void addAdjacentNode(Node n);

	/**
	 * Delete adjacent node n to current node.This method is abstract, the
	 * implementation differs for every version of node as the way to save the
	 * adjacent nodes differs.
	 * 
	 * @param n
	 *            node to be removed from the adjacent list/array/set
	 */
	public abstract void deleteAdjacentNode(Node n);

	/**
	 * Checks if the node n is in the adjacency list/array/set of current node
	 * 
	 * @param n
	 *            the node to be checked
	 * @return true if node is adjacent with current node
	 */
	public abstract boolean hasAdjacentNode(Node n);

	/**
	 * Returns the node found at the index position in adjacency list/array/set
	 * 
	 * @param index
	 *            the position of elements
	 * @return node or null if index is out of bounds
	 */
	public abstract Node getNodeByIndex(int index);

	/**
	 * 
	 * @return the number of adjacent nodes of current node
	 */
	public abstract int getNumberOfAdjacentNodes();

}
