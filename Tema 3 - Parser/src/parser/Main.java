package parser;

import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Operand> operands = new ArrayList<Operand>(10);

	public static void Display(Node n) {
		if (n == null) {
			System.out.println("null");
			return;
		}
		if (n instanceof Operator) {
			System.out.println(n);
		} else if (n instanceof Operand)
			System.out.println(n);
		Display(n.left);
		Display(n.right);
	}

	public static void main(String[] args) throws IOException {

		FileInputStream is = new FileInputStream("arbore.in");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		PrintWriter print = new PrintWriter(new File("arbore.out"));
		String line = null;
		Node result = null;
		while ((line = reader.readLine()) != null) {
			// read line from file
			Scanner scanner = new Scanner(line);
			String operation = scanner.next();

			switch (operation) {
			case ("eval"):
				String expression = scanner.nextLine();
				// eliminate blank space after "eval" and ";" from the end
				expression = expression.substring(1, expression.length() - 1);
				// create the result tree
				Parser aux = new Parser();
				result = aux.createTree(expression, operands);
				// get result using a visitor
				Operand finalValue = result.accept(new ConcreteVisitor());
				print.println(finalValue);
				break;
			default:
				String type = operation; // operand type
				String name = scanner.next(); // operand name
				scanner.next(); // reading equal sign
				String value = scanner.nextLine(); // operand value
				value = value.substring(1);
				value = value.replace(";", ""); // removing ";" from the end
				if (type.equals("string"))
					// if operand type is string, remove the quotations marks
					value = value.substring(1, value.length() - 1);
				// create operand
				Operand op = OperandFactory.createOperand(name, type, value);
				// add operand to operands list
				if (!operands.contains(op)) {
					operands.add(op);
				}

			}
			scanner.close();

		}
		reader.close();
		print.close();
	}
}
