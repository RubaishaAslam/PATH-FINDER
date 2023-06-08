/*
 * Written By: Rubaisha Aslam 
 * Assignment 5
 * This class represents a node of the graph
*/
public class Node {
	private int id;
	private boolean markit;
	
	// constructor of class that creates node with id 
	public Node(int id) {
		this.id = id;
		this.markit =false;	
	}

	// marks the node with given value so true or false
	public void markNode(boolean mark){
		this.markit = mark;
	}
	// returns the marks node 
	public boolean getMark() {
		return markit;
	}
	
	//returns id of node
	public int getId() {
		return this.id;
	}
}
