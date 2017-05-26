package graph;

import java.io.*;
import java.util.*;

public class Main {
	static Graph graph;

	public static void main(String[] args) throws IOException {

		String nodeType;
		String name, name2;
		String fileName;
		Node newNode, newNode2;
		FileInputStream is = new FileInputStream(args[0]);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		String line = null;
		while ((line = reader.readLine()) != null) {
			Scanner scanner = new Scanner(line);
			String operation = scanner.next();
			switch (operation) {
			//Add node operation
			case ("Add"):
				//reading node's name and type
				nodeType = scanner.next();
				name = scanner.next();
				if (nodeType.charAt(3) == 'A')
					newNode = NodeFactory.createNode(name, nodeType.charAt(3), graph.Aversion);
				else if (nodeType.charAt(3) == 'B')
					newNode = NodeFactory.createNode(name, nodeType.charAt(3), graph.Bversion);
				else
					newNode = NodeFactory.createNode(name, nodeType.charAt(3), graph.Cversion);
				
				//reading the adjacent nodes
				String adjNodeName;
				while (scanner.hasNext() && (adjNodeName = scanner.next()) != null) {
					Node adjNode = graph.getNode(adjNodeName);
					if (adjNode != null)
						newNode.addAdjacentNode(adjNode);
				}
				
				//adding the node in graph
				newNode.setID(graph.numberOfNodes);
				graph.addNode(newNode);
				break;
				
			//Remove node operation
			case ("Del"):
				//reading node's name and type
				nodeType = scanner.next();
				name = scanner.next();				
				//getting node from graph
				newNode = graph.getNode(name);
				//removing node from graph
				if (newNode != null)
					graph.removeNode(newNode);
				break;
			//Add edge operation
			case ("AddM"):
				//reading nodes' name
				name = scanner.next();
				name2 = scanner.next();
				//getting nodes from graph
				newNode = graph.getNode(name);
				newNode2 = graph.getNode(name2);
				// check if both nodes are in graph
				if (newNode != null && newNode2 != null) 
					graph.addEdge(newNode, newNode2); //creates edge
				break;
			//Remove edge operation
			case ("DelM"):
				//reading nodes' name
				name = scanner.next();
				name2 = scanner.next();
				//getting nodes from graph
				newNode = graph.getNode(name);
				newNode2 = graph.getNode(name2);
				// check if both nodes are in graph
				if (newNode != null && newNode2 != null) 
					graph.removeEdge(newNode, newNode2); //removed edge
				break;
			//Setting operation
			case ("Settings"):
				int Avers, Bvers, Cvers;
				Avers = scanner.nextInt();
				Bvers = scanner.nextInt();
				Cvers = scanner.nextInt();
				graph = new Graph(Avers, Bvers, Cvers);
				break;
			//Serialize operation
			case ("Serialize"):
				//geting node's name and file's name
				name = scanner.next();
				fileName = scanner.next();
				//getting node from graph
				Node node = graph.getNode(name);
				PrintWriter writer = new PrintWriter(new File(fileName));
				//checking if node is in graph
				if (node != null)
					//serialise graph
					GraphSerializer.graphEncoder(node, writer, 0);
				graph = null;
				writer.close();
				break;
			//Deserialise graph
			case ("Deserialize"):
				fileName = scanner.next();
				int currentVersionA = graph.Aversion;
				int currentVersionB = graph.Bversion;
				int currentVersionC = graph.Cversion;
				//Create graph from file
				graph = GraphSerializer.graphDecoder(fileName, currentVersionA, currentVersionB, currentVersionC);
				break;
			default:
				System.out.println("Invalid Operation");

			}
			scanner.close();
		}
		reader.close();
	}

}
