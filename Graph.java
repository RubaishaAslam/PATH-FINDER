/*
 * Written By: Rubaisha Aslam 
 * Student Number: 251179721
 * Assignment 5
 * This class implements adjacency matrix  graph
*/


import java.util.Iterator;
import java.util.LinkedList;


public class Graph {

	private Node idNodes[];	
	private Edge adjacencyMatrix[][];	
	int check;
	
	
	// constructor that initialize the nodes and matrix 
	public Graph(int n){
		idNodes = new Node[n];
		adjacencyMatrix = new Edge[n][n];
		
		for (int i=0; i < n; i++){
			idNodes[i] = new Node(i);
		}
		check = idNodes.length;
	
	}
	
	
	// returns the node with id if exists else exception 
	public Node getNode(int id) throws GraphException{
		Node saved;
		if (id >=0 && id < check) {
			saved = idNodes[id];
			return saved;
			}
		else
			throw new GraphException("Invalid");
	}

	
	// add edges in between two edges and add the type to it else prints error 
	public void addEdge(Node nodeu, Node nodev, String type) throws GraphException{

		if(nodeu.getId() > -1 && nodeu.getId() < check && nodev.getId()> -1 && nodev.getId()< check) {			
			while (adjacencyMatrix[nodeu.getId()][nodev.getId()] == null && adjacencyMatrix[nodev.getId()][nodeu.getId()] == null){
				// save the node
				adjacencyMatrix[nodeu.getId()][nodev.getId()] = new Edge(nodeu, nodev, type);
				adjacencyMatrix[nodev.getId()][nodeu.getId()] = new Edge(nodev, nodeu, type);
			}}
		else
			throw new GraphException("Invalid");
	}



	
	// returns the all edges with u using java iterator else throw error 
	public Iterator incidentEdges(Node u) throws GraphException{
		
		
	if (u.getId() > -1 && u.getId() < check){
			
		 LinkedList<Edge> incident = new LinkedList<Edge>();
			//checks and add nodes adjacent 
			for (int i=0; i<idNodes.length; i++){
				if (adjacencyMatrix[u.getId()][i] != null){
					incident.add(adjacencyMatrix[u.getId()][i]);
				}
			}
			
			return incident.iterator();
	}
	else
		throw new GraphException("Invalid");
				
				
	
	}
	// return the edge between the nodes given 
	public Edge getEdge(Node u, Node v) throws GraphException{
		int first = u.getId();
		int second = v.getId();
		if(first< check && second < check && first> -1 && second > -1) {
					return adjacencyMatrix[first][second];
				}
		else 
			throw new GraphException("Invalid");
			
	}

	// return true if false if the nodes are adjacent 
	public boolean areAdjacent(Node u, Node v) throws GraphException{
		int first = u.getId();
		int second = v.getId();
		// checks if the node id exists, if its less than the length and its not null if so return true else return false or return error
		if(first< check && second < check){
			if (first> -1 && second > -1) {
				if (adjacencyMatrix[first][second] != null) {
					return true;
				}
				else
					return false;
			}}
		else
			throw new GraphException("Invalid");
		return false;
	}
}
