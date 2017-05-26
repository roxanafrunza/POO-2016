package parser;

public interface Visitable {

	public Operand accept(Visitor visitor);
}
