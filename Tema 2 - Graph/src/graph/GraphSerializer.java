package graph;

import java.io.*;
import java.util.Scanner;

public class GraphSerializer {

	/**
	 * Creates a line with the following template <Nume> node's name </Nume>.
	 * 
	 * @param node
	 *            node which name we want in template
	 * @return a string with the specific format
	 */
	public static String nameTag(Node node) {
		return "<Nume>" + node.name + "</Nume>\n";
	}

	/**
	 * Creates a string consisting of tabs.
	 * 
	 * @param n
	 *            the number of tabs in string
	 * @return the string
	 */
	public static String numberOfTabs(int n) {
		String string = "";
		for (int i = 0; i < n; i++)
			string += "\t";
		return string;
	}

	/**
	 * Creates a line with the template used for already serialized nodes
	 * 
	 * @param node
	 *            node which data we want in template
	 * @return a string with the information
	 */
	public static String referenceTag(Node node) {
		return "<Reference class=\"Nod" + node.type + "\" Version=\"" + node.version + "\" id=\"" + node.ID + "\">\n";
	}

	/**
	 * Creates a line with the template used for a node that is serialized for
	 * the first time
	 * 
	 * @param node
	 *            node which data we want in template
	 * @return a string with the information
	 */
	public static String objectTag(Node node) {

		return "<Object class=\"Nod" + node.type + "\" Version=\"" + node.version + "\" id=\"" + node.ID + "\">\n";
	}

	/**
	 * Creates a string with one of the tag <LIST>, <VECTOR>, <SET> according to
	 * the node's version. Throws IllegalArgumentException if version isn't 1, 2
	 * or 3
	 * 
	 * @param node
	 *            the node which version we use
	 * @return a string with the tag
	 */
	public static String typeTagOpen(Node node) {
		switch (node.version) {
		case (1):
			return "<LIST>\n";
		case (2):
			return "<VECTOR>\n";
		case (3):
			return "<SET>\n";
		}
		throw new IllegalArgumentException("The version " + node.version + " is not recognized.");
	}

	/**
	 * Creates a string with one of the tag </LIST>, </VECTOR>, </SET> according
	 * to the node's version. Throws IllegalArgumentException if version isn't
	 * 1, 2 or 3
	 * 
	 * @param node
	 *            the node which version we use
	 * @return a string with the tag
	 */
	public static String typeTagClose(Node node) {
		switch (node.version) {
		case (1):
			return "</LIST>\n";
		case (2):
			return "</VECTOR>\n";
		case (3):
			return "</SET>\n";
		}
		throw new IllegalArgumentException("The version " + node.version + " is not recognized.");
	}

	/**
	 * Given a graph, we serialise it using DFS and write the data in file
	 * 
	 * @param node
	 *            the node where we start serialisation from
	 * @param output
	 *            printwriter used to write in file
	 * @param currentLevel
	 *            integer used to remember the level (number of tabs) of current
	 *            line
	 */

	public static void graphEncoder(Node node, PrintWriter output, int currentLevel) {

		// add tabs in current line
		output.print(numberOfTabs(2 * currentLevel));
		// if the node has been already serialised, we add the reference tag and
		// exit
		if (node.serialized == true) {
			output.print(referenceTag(node));
			return;
		}
		// printing the object tag
		output.print(objectTag(node));
		// adding the number of tabs at the beginning of file
		output.print(numberOfTabs(2 * currentLevel + 1));
		// printing the name tag
		output.print(nameTag(node));
		// adding the number of tabs at the beginning of file
		output.print(numberOfTabs(2 * currentLevel + 1));
		// printing the version tag
		output.print(typeTagOpen(node));
		node.serialized = true; // the nodes had been serialised
		for (int i = 0; i < node.getNumberOfAdjacentNodes(); i++) {
			// recursively we get each adjacent node and call the serialiser
			// function
			graphEncoder(node.getNodeByIndex(i), output, currentLevel + 1);
		}
		// closing version and object tag
		output.print(numberOfTabs(2 * currentLevel + 1));
		output.print(typeTagClose(node));
		output.print(numberOfTabs(2 * currentLevel));
		output.print("</OBJECT>\n");

	}

	/**
	 * Given a file, we want to deserialise the graph written in it
	 * 
	 * @param fileName
	 *            the name of the file with data
	 * @param Avers
	 *            current version for node of type A
	 * @param Bvers
	 *            current version for node of type A
	 * @param Cvers
	 *            current version for node of type A
	 * @return the graph built from file
	 * @throws IOException
	 */
	public static Graph graphDecoder(String fileName, int Avers, int Bvers, int Cvers) throws IOException {

		Graph graph = new Graph(Avers, Bvers, Cvers);

		// Reading from file and creating the log file
		String castlog = "Deserialize_" + fileName + "_CAST.log";
		PrintWriter cast = new PrintWriter(new File(castlog));
		FileInputStream input = new FileInputStream(fileName);
		BufferedReader read = new BufferedReader(new InputStreamReader(input));

		int numberOfTabs = 0;
		String line = null;
		char type;
		int version, ID;
		String name;
		// reading line by line from file
		while ((line = read.readLine()) != null) {
			numberOfTabs = 0;
			// counting the number of tabs
			for (int i = 0; i < line.length(); i++)
				if (line.charAt(i) == '\t')
					numberOfTabs++;
			Scanner scan = new Scanner(line);
			String word = scan.next().substring(1);
			switch (word) {
			case ("Object"):
				// reading node's type
				type = scan.next().charAt(10);

				// reading node's version
				version = Parser.getNumber(scan.next());

				// reading node's ID
				ID = Parser.getNumber(scan.next());

				// reading node's name
				name = Parser.getName(read.readLine());

				// reading SET/VECTOR/LIST tag
				read.readLine();

				// Writing in Cast log
				cast.write(nodeCast(name, type, version, Avers, Bvers, Cvers));

				// Creating node
				int newVersion = graph.getVersion(type);
				Node node = NodeFactory.createNode(name, type, newVersion);
				node.setID(ID);
				node.setLevel(numberOfTabs);
				// Updating the graph
				graph.addNode(node);
				graph.linkNode(node, node.getLevel());
				break;
			case ("Reference"):
				// reading node's type
				type = scan.next().charAt(10);

				// reading node's version
				version = Parser.getNumber(scan.next());

				// reading node's ID
				ID = Parser.getNumber(scan.next());

				// linking the node
				Node temp = graph.getNodeByID(ID);
				graph.linkNode(temp, numberOfTabs);
				break;
			}
			scan.close();

		}
		read.close();
		cast.close();
		return graph;
	}

	/**
	 * Given a node, we create a string later to be written in the log file.
	 * 
	 * @param name
	 *            node's name
	 * @param type
	 *            node's type
	 * @param version
	 *            node's version
	 * @param Avers
	 *            current version for node of type A
	 * @param Bvers
	 *            current version for node of type B
	 * @param Cvers
	 *            current version for node of type C
	 * @return a string which specifies with FAIL or OK CAST
	 */
	public static String nodeCast(String name, char type, int version, int Avers, int Bvers, int Cvers) {
		int checkVersion = 0;
		String string = "";
		switch (type) {
		case ('A'):
			checkVersion = Avers;
			break;
		case ('B'):
			checkVersion = Bvers;
			break;
		case ('C'):
			checkVersion = Cvers;
			break;
		default:
			throw new IllegalArgumentException("The node's type" + type + " is not recognized.");
		}
		if (version == checkVersion)
			return "";
		if (version < checkVersion)
			string = "OK cast ";
		else
			string = "Fail cast ";

		string += "Nod" + type + " " + name + " from Version=\"" + version + "\" to Version=\"" + checkVersion + "\"\n";
		return string;
	}

}
