package parser;

public class Minus extends Operator {

	public Minus() {
		super();
	}

	@Override
	public Operand accept(Visitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public String getOperatorType() {
		return "-";
	}

	/**
	 * The substitution operand: operand1 - operand2
	 * 
	 * @param operand1
	 *            first operand
	 * @param operand2
	 *            second operand
	 * @return operand result
	 */
	public Operand substitution(Operand operand1, Operand operand2) {

		String resultName = operand1.getName() + " - " + operand2.getName();
		if (operand1 instanceof IntegerType) {
			// integerType - integerType = integerType
			if (operand2 instanceof IntegerType) {
				int result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getIntegerValue() - operand2.getIntegerValue();
					return OperandFactory.createOperand(resultName, "int", Integer.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "int", Integer.toString(result));
					op.setNumeric(false);
					return op;
				}

			} else if (operand2 instanceof DoubleType) {
				// integerType - doubleType = doubleType
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getDoubleValue() - operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			} else if (operand2 instanceof StringType) {
				// integerType - stringType = integerType
				int result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getIntegerValue() - operand2.getIntegerValue();
					return OperandFactory.createOperand(resultName, "int", Integer.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "int", Integer.toString(result));
					op.setNumeric(false);
					return op;
				}
			}
		} else if (operand1 instanceof DoubleType) {
			// doubleType - integerType = integerType
			if (operand2 instanceof IntegerType) {
				double result = 0;
				if (operand1.isNumeric() && operand2.isNumeric) {
					result = operand1.getDoubleValue() - operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}

			} else if (operand2 instanceof DoubleType) {
				// doubleType - doubleType = doubleType
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric) {
					result = operand1.getDoubleValue() - operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			} else if (operand2 instanceof StringType) {
				// doubleType - stringType = doubleType
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getDoubleValue() - operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			}

		} else if (operand1 instanceof StringType) {
			if (operand2 instanceof IntegerType) {
				// stringType - integerType = stringType
				String result = "";
				// if operand isn't NaN
				if (operand1.isNumeric()) {
					int value = operand2.getIntegerValue();
					String s = operand1.getStringValue();
					if (value >= 0 && value <= s.length()) {
						result = s.substring(0, s.length() - value);
					} else if (value >= 0 && value > s.length())
						result = "";
					else if (value < 0)
						result = s + new String(new char[Math.abs(value)]).replace("\0", "#");
				} else
					// if operand is NaN, result is "NaN" + string;
					result = operand2.getStringValue();
				return OperandFactory.createOperand(resultName, "string", result);

			} else if (operand2 instanceof DoubleType) {
				// stringType - doubleType = doubleType
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getDoubleValue() - operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			} else if (operand2 instanceof StringType) {
				// stringType - stringType
				int result = operand1.getIntegerValue() - operand2.getIntegerValue();
				return OperandFactory.createOperand(resultName, "int", Integer.toString(result));
			}
		}
		throw new IllegalArgumentException("The operand's type is not recognized.");
	}
}
