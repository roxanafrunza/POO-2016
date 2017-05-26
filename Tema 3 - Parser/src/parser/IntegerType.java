package parser;

public class IntegerType extends Operand {
	int value;

	public IntegerType(String name, int value) {
		super(name);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		if (!isNumeric())
			return "NaN";
		return Integer.toString(this.value);
	}

	@Override
	public String getType() {
		return "int";
	}

	@Override
	public String getStringValue() {
		return Integer.toString(this.value);
	}

	@Override
	public int getIntegerValue() {
		return this.value;
	}

	@Override
	public double getDoubleValue() {
		return this.value;
	}
}
