package parser;

public class OperandFactory {
	private static OperandFactory instance = null;

	private OperandFactory() {

	}

	public static OperandFactory getInstance() {
		if (instance == null)
			instance = new OperandFactory();
		return instance;
	}

	/**
	 * Given a type, create a Operand with the name and value given. Type can
	 * be: int, double, string. Throws IllegalArgumentException
	 * 
	 * @param name
	 *            operand's name
	 * @param type
	 *            operand's type
	 * @param value
	 *            operand's value
	 * @return operand
	 */
	public static Operand createOperand(String name, String type, String value) {
		switch (type) {
		case "int":
			int intValue = Integer.parseInt(value);
			return new IntegerType(name, intValue);
		case "double":
			double doubleValue = Double.parseDouble(value);
			return new DoubleType(name, doubleValue);
		case "string":
			return new StringType(name, value);
		}
		throw new IllegalArgumentException("The type " + type + " is not recognized.");
	}
}
