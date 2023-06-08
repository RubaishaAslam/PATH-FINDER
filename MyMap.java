/*
 * Written By: Rubaisha Aslam 
 * Student Number: 251179721
 * Assignment 5
 * This class implements a graph to store the map and finds a path
 * from the starting point to the destination while keeping the conditions satisfied and if not says not found 
*/


import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;


public class MyMap {
	private Graph myGraph;
	private int start, end;
	private int privateRoad;
	private int conRoad; 
	private Stack<Node> stack = new Stack<Node>();	//A stack for storing a path from start to end
	private int first = 0;
	private boolean horiz = true;



	// constructor of class MyMap
	// reads the input file and find information about the path 
	// create a graph and connect graph accordingly 
	public MyMap(String inputFile) throws MapException, IOException, GraphException, FileNotFoundException {



        Scanner scanner = new Scanner(new FileReader(inputFile));
        scanner.nextLine(); //scan lines
        start = Integer.parseInt(scanner.nextLine()); //read the start line
        end = Integer.parseInt(scanner.nextLine()); //read end line 

        int width = Integer.parseInt(scanner.nextLine());
        int length = Integer.parseInt(scanner.nextLine());

        privateRoad = Integer.parseInt(scanner.nextLine());// private roads
        conRoad = Integer.parseInt(scanner.nextLine()); // construction road

        myGraph = new Graph(width * length); //Initialize graph
        // scan through the inputs
        while (scanner.hasNextLine()) {
            int curr = 0;
            int inti =0;
            String line;
            String currLine;
            line = scanner.nextLine();
            currLine = line;
            String stri = null;


            // checks if the row is horizontal or vertical
            if(currLine.charAt(0) == '+'){
                horiz = true;
            } else if(currLine.charAt(0) != '+'){
                horiz = false;
            }
            // if it is horizontal add the odd values and type in between
            if (horiz == true) {

                for(inti =1; inti < currLine.length();inti = inti + 2) {
                    if (currLine.charAt(inti) != 'B') {
                    	// look at the values and save integer 
                        if(currLine.charAt(inti) == 'P') {
                            stri = "public";}
                        if(currLine.charAt(inti) == 'V') {
                            stri = "private";}
                        if(currLine.charAt(inti) == 'C') {
                            stri = "construction";}
                        // add the edge in graph 
                        myGraph.addEdge(myGraph.getNode(curr+ first), myGraph.getNode(curr+ first + 1), stri);
                    }
                    curr = curr + 1;
                }
            }

            // if it is horizontal add the even values and type in between
            else if (horiz == false) {
                for(inti =0; inti < currLine.length();inti = inti + 2) {
                    if (currLine.charAt(inti) != 'B') {
                        if(currLine.charAt(inti) == 'P') {
                            stri = "public";}
                        if(currLine.charAt(inti) == 'V') {
                            stri = "private";}
                        if(currLine.charAt(inti) == 'C') {
                            stri = "construction";}

                        myGraph.addEdge(myGraph.getNode(curr+ first), myGraph.getNode(curr + first + width), stri);

                    }
                    curr = curr + 1;
                }
                first = first + width;
            }
        }
    }

	// return the saved graph else return error 
	public Graph getGraph() {
		return myGraph;
	}
	
	// return id of the starting node
	public int getStartingNode() {
		return start;
		
	}
	
	// return id of the end node
	public int getDestinationNode() {
		return end;
		
	}
	
	// return numbers of max private road 
	public int maxPrivateRoads() {
		return privateRoad;
		
	}
	
	// return numbers of max construction road 
	public int maxConstructionRoads() {
		return conRoad;
		
	}
	
	public Iterator<Node> findPath(int start, int destination, int maxPrivate, int maxConstruction) throws GraphException{
		if(path(start, destination, maxPrivate, maxConstruction) == true && !stack.isEmpty()) {
			return stack.iterator();
		}else {
			return null;
		}
		
		
	}
	
	private boolean path(int start, int destination, int maxPrivate, int maxConstruction) throws GraphException{
		
		
		Node s;
		
		s = myGraph.getNode(start);
		 
		Node d = myGraph.getNode(destination);
		s.markNode(true);
		stack.push(s);
		
		Iterator<Edge> list = this.myGraph.incidentEdges(s);
		
		if(s.getId() == d.getId()) {
			return true;
		}
		else {
		while(list.hasNext()) {
			
			Edge newEdge = list.next();
			if(newEdge.secondNode().getMark() == false) {
				if(newEdge.getType().compareTo("private") == 0 && maxPrivate > 0) {
					maxPrivate--;
					if(path(newEdge.secondNode().getId(), destination, maxPrivate, maxConstruction)) {
						return true;	
					}
					
					}
				else if(newEdge.getType().compareTo("construction") == 0 && maxConstruction > 0) {
					maxConstruction--;
					if(path(newEdge.secondNode().getId(), destination, maxPrivate, maxConstruction)) {
						return true;	
					}
					
					}
				else if (newEdge.getType().compareTo("public") == 0) {
					if(path(newEdge.secondNode().getId(), destination, maxPrivate, maxConstruction)) {
						return true;
					}
					
				}
			}
			
			
		
		}
		stack.pop();
		s.markNode(false);
		
		return false;
		}
		
	}

	
	
	
	/*
	 * Private helper method to determine if the line in the text file is odd or even.
	 * Function returns true if the line is even and false if the line is odd.
	 */
	private boolean horizontal(int n) {
		boolean boolVar;
		if(n % 2 == 0) {
			boolVar = true;
		}else {
			boolVar = false;
		}
		return boolVar;
	}

}