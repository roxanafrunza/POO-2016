package parser;

public class StringType extends Operand {
	String value;

	public StringType(String name, String value) {
		super(name);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		if (!isNumeric())
			return "NaN";
		return this.value;
	}

	@Override
	public String getType() {
		return "string";
	}

	@Override
	public String getStringValue() {
		return this.getValue();
	}

	@Override
	public int getIntegerValue() {
		return this.getValue().length();
	}

	@Override
	public double getDoubleValue() {
		return getValue().length();
	}

}
