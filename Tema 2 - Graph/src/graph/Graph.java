package graph;

import java.util.*;

public class Graph {
	ArrayList<Node> nodes;
	int numberOfNodes;
	int Aversion, Bversion, Cversion;

	public Graph(int Av, int Bv, int Cv) {
		nodes = new ArrayList<Node>(10);
		numberOfNodes = 0;
		Aversion = Av;
		Bversion = Bv;
		Cversion = Cv;
	}

	/**
	 * Gets node version according to node's type Throws
	 * IllegalArgumentException if node's type isn't A,B or C
	 * 
	 * @param type
	 *            node's type; can be A/B/C
	 * @return
	 */
	public int getVersion(char type) {
		switch (type) {
		case ('A'):
			return Aversion;
		case ('B'):
			return Bversion;
		case ('C'):
			return Cversion;
		}
		throw new IllegalArgumentException("The type " + type + " is not recognized.");
	}

	/**
	 * Given a node's type, it sets the version for the entire graph
	 * 
	 * @param version
	 *            node's version, should be 1/2/3
	 * @param type
	 *            node's type, should be A/B/C
	 */
	public void setVersion(int version, char type) {
		switch (type) {
		case ('A'):
			Aversion = version;
			break;
		case ('B'):
			Bversion = version;
		case ('C'):
			Cversion = version;
		}

	}

	/**
	 * Checks if node is in graph
	 * 
	 * @param n
	 *            node
	 * @return true if node is in graph
	 */
	public boolean contains(Node n) {
		return nodes.contains(n);
	}

	public Node getNode(String name) {
		for (int i = 0; i < numberOfNodes; i++)
			if (nodes.get(i).name.equals(name))
				return nodes.get(i);
		return null;
	}

	/**
	 * Given an ID, checks if the node with the ID given is in graph and returns
	 * it
	 * 
	 * @param ID
	 *            node's ID
	 * @return node if exists, null otherwise
	 */
	public Node getNodeByID(int ID) {
		for (int i = 0; i < numberOfNodes; i++)
			if (nodes.get(i).ID == ID)
				return nodes.get(i);
		return null;
	}

	/**
	 * Add an edge between two nodes
	 * 
	 * @param n1
	 *            first node
	 * @param n2
	 *            second node
	 */
	public void addEdge(Node n1, Node n2) {

		// Nodes aren't in the graph
		if (!nodes.contains(n1) || !nodes.contains(n2))
			return;

		// The edge already exists
		if (n1.hasAdjacentNode(n2) && n2.hasAdjacentNode(n1))
			return;

		// Add the edge
		if (n1.hasAdjacentNode(n2) == false)
			n1.addAdjacentNode(n2);
		if (n2.hasAdjacentNode(n1) == false)
			n2.addAdjacentNode(n1);

	}

	/**
	 * Remove edge between two node
	 * 
	 * @param n1
	 *            first node
	 * @param n2
	 *            second node
	 */
	public void removeEdge(Node n1, Node n2) {
		// The nodes aren't in the graph
		if (!nodes.contains(n1) || !nodes.contains(n2))
			return;
		// The edge doesn't exist
		if (!n1.hasAdjacentNode(n2) && !n2.hasAdjacentNode(n1))
			return;
		// Delete the edge
		if (n1.hasAdjacentNode(n2) == true)
			n1.deleteAdjacentNode(n2);
		if (n2.hasAdjacentNode(n1) == true)
			n2.deleteAdjacentNode(n1);
	}

	/**
	 * Insert node in graph. Creates the edges between the node and those
	 * adjacent to it
	 * 
	 * @param n
	 *            node to be inserted
	 */
	public void addNode(Node n) {

		// Add the node in the graph
		nodes.add(n);
		// Increase the number of nodes in graph
		numberOfNodes++;
		// We create the edges with other nodes
		int size = n.getNumberOfAdjacentNodes();
		for (int i = 0; i < size; i++) {
			Node temp = n.getNodeByIndex(i);
			this.addEdge(n, temp);
		}

	}

	/**
	 * Remove node from graph
	 * 
	 * @param n
	 *            node to be removed
	 */
	public void removeNode(Node n) {

		// We remove the edges with other nodes
		while (n.getNumberOfAdjacentNodes() > 0) {
			Node temp = n.getNodeByIndex(0);
			this.removeEdge(n, temp);
		}
		// Remove the node from the graph
		nodes.remove(n);
		// Decrement the number of nodes in graph
		numberOfNodes--;
	}

	/**
	 * Given a node, create the edges with all his adjacent nodes in graph Two
	 * nodes are adjacent if after deserialization the level difference is equal
	 * with two
	 * 
	 * @param node
	 *            node we want to link in the graph
	 * 
	 * @param level
	 *            node's level
	 */
	public void linkNode(Node node, int level) {

		for (int i = 0; i < numberOfNodes; i++) {
			//int levelDifference = Math.abs(level - nodes.get(i).getLevel());
			int levelDifference = level - nodes.get(i).getLevel();
			if (levelDifference == 2 && node != nodes.get(i))
				addEdge(node, nodes.get(i));
		}
	}
	

}
