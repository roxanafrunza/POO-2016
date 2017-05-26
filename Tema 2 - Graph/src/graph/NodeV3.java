package graph;

public class NodeV3 extends Node {
	Set<Node> adjacentNodes;

	public NodeV3(String name, char type) {
		super(name, type, 3);
	}

	public Set<Node> getAdjacentNodes() {
		return this.adjacentNodes;
	}

	@Override
	public void addAdjacentNode(Node n) {
		if (adjacentNodes == null)
			adjacentNodes = new Set<Node>();
		adjacentNodes.add(n);

	}

	@Override
	public void deleteAdjacentNode(Node n) {
		adjacentNodes.remove(n);

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
