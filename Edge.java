/*
 * Written By: Rubaisha Aslam 
 * Assignment 5
 * This class represents an edge of the graph.
*/

public class Edge {
    Node firstEndpoint;
    Node secondEndpoint;
    String type;
    
    //constructor of class saves end points of edges and type of node 
    public Edge (Node u, Node v, String type) {
        this.firstEndpoint = u;
        this.secondEndpoint = v;
        this.type = type;
    }
    
    // returns first end point
    public Node firstNode() {
        return firstEndpoint;
    }
    
    // return second end point 
    public Node secondNode () {
        return secondEndpoint;
    }
    
    // return type of edge 
    public String getType () {
        return type;
    }
}
