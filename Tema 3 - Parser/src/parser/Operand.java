package parser;

public abstract class Operand extends Node {
	String name;
	boolean isNumeric;

	public Operand(String name) {
		this.name = name;
		this.isNumeric = true;
		super.setOperand(true);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public boolean isNumeric() {
		return isNumeric;
	}

	public void setNumeric(boolean isNumeric) {
		this.isNumeric = isNumeric;
	}

	/**
	 * 
	 * @return operand type
	 */
	public abstract String getType();

	/**
	 * 
	 * @return operand value as a string
	 */
	public abstract String getStringValue();

	/**
	 * 
	 * @return operand value as an integer
	 */
	public abstract int getIntegerValue();

	/**
	 * 
	 * @return operand value as a double
	 */
	public abstract double getDoubleValue();

	@Override
	public Operand accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
