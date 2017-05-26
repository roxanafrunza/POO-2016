package graph;

public class NodeV2 extends Node {

	Array<Node> adjacentNodes;

	public NodeV2(String name, char type) {
		super(name, type, 2);
	}

	public Array<Node> getAdjacentNodes() {
		return this.adjacentNodes;
	}

	@Override
	public void addAdjacentNode(Node n) {
		if (adjacentNodes == null)
			adjacentNodes = new Array<Node>();
		adjacentNodes.add(n);

	}

	@Override
	public void deleteAdjacentNode(Node n) {
		adjacentNodes.remove(n);
	}

	/**
	 * Change node version from 1 to 2. The adjacent nodes are saved in a List
	 * 
	 * @return the list containing adjacent nodes
	 */
	public List<Node> changeToV2() {
		List<Node> adjNodes = new List<Node>(adjacentNodes.getElements());
		return adjNodes;
	}

	/**
	 * Change node version from 1 to 3. The adjacent nodes are saved in a Set
	 * 
	 * @return the set containing adjacent nodes
	 */
	public Set<Node> changeToV3() {
		Set<Node> adjNodes = new Set<Node>(adjacentNodes.getElements());
		return adjNodes;
	}

	@Override
	public boolean hasAdjacentNode(Node n) {
		if (adjacentNodes == null)
			return false;
		return adjacentNodes.getElements().contains(n);

	}

	@Override
	public Node getNodeByIndex(int index) {
		return adjacentNodes.getElement(index);
	}

	@Override
	public int getNumberOfAdjacentNodes() {
		if (adjacentNodes == null)
			return 0;
		return adjacentNodes.size();
	}

}
