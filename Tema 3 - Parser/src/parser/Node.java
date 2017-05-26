package parser;

public abstract class Node implements Visitable {
	Node left;
	Node right;
	boolean isOperand;
	boolean isOperator;

	/**
	 * Get left son for current node
	 * 
	 * @return left son
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * Set the left son for current node
	 * 
	 * @param left
	 *            node to be set as left son
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * Get right son for current node
	 * 
	 * @return right son
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * Set the right son for current node
	 * 
	 * @param right
	 *            node to be set as right son
	 */
	public void setRight(Node right) {
		this.right = right;
	}

	/**
	 * @return true if current node is operand, false otherwise
	 */
	public boolean isOperand() {
		return isOperand;
	}

	/**
	 * Sets isOperand value
	 * 
	 * @param isOperand
	 *            value
	 */
	public void setOperand(boolean isOperand) {
		this.isOperand = isOperand;
	}

	/**
	 * @return true if current node is operator, false otherwise
	 */
	public boolean isOperator() {
		return isOperator;
	}

	/**
	 * Sets isOperator value
	 * 
	 * @param isOperator
	 *            value
	 */
	public void setOperator(boolean isOperator) {
		this.isOperator = isOperator;
	}

	/**
	 * Allows node to be visited by visitor
	 */
	public Operand accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
