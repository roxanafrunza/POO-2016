package graph;

public class NodeV1 extends Node {

	List<Node> adjacentNodes;

	public NodeV1(String name, char type) {
		super(name, type, 1);
	}

	public List<Node> getAdjacentNodes() {
		return this.adjacentNodes;
	}

	@Override
	public void addAdjacentNode(Node n) {
		if (adjacentNodes == null)
			adjacentNodes = new List<Node>();
		adjacentNodes.add(n);

	}

	@Override
	public void deleteAdjacentNode(Node n) {
		adjacentNodes.remove(n);

	}

	/**
	 * Change node version from 2 to 3. The adjacent nodes are saved in a Set
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
