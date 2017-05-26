package graph;

public class NodeFactory {
	private static NodeFactory instance = null;

	private NodeFactory() {

	}

	public static NodeFactory getInstance() {
		if (instance == null)
			instance = new NodeFactory();
		return instance;
	}

	/**
	 * Creates a node given its name, type and version. Throws
	 * IllegalArgumentException if the version isn't 1, 2 or 3
	 * 
	 * @param name
	 *            node's name
	 * @param type
	 *            node's type
	 * @param version
	 *            node's version
	 * @return a new node
	 */
	public static Node createNode(String name, char type, int version) {
		switch (version) {
		case (1):
			return new NodeV1(name, type);
		case (2):
			return new NodeV2(name, type);
		case (3):
			return new NodeV3(name, type);
		}
		throw new IllegalArgumentException("The version " + version + " is not recognized.");
	}
}
