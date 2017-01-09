package ShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdjacencyList {

	public List<Vertex> list = new ArrayList<Vertex>();
	public int startVertex;
	
	/*
	 * Constructor for the AdjacencyList
	 * Adds the given file to the list by
	 * its vertexes and edges.
	 * 
	 * Assumes a certain format of graph given
	 */
	public AdjacencyList(File file){
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) { e.printStackTrace(); }
		
		input.next(); input.next(); input.next();
		int v = input.nextInt(); 
		input.nextLine(); input.next(); input.next();
		startVertex = input.nextInt();
		input.nextLine();
		
		for(int k=0; k < v; k++){
			list.add(new Vertex(k + ""));
		}
		
		for(int i=0; i < v; i++){
			list.get(i).adjacencies = new ArrayList<Edge>();
			for(int j=0; j < v; j++){
				int weight = input.nextInt();
				if(weight != 0){
					list.get(i).adjacencies.add(new Edge(list.get(j), weight));
				}
			}
		}
	}

	/*
	 * Class for vertexes in graph.
	 * Stores name, edges, min distance (from start), 
	 * and the previous vertex on the shortest path through.
	 * 
	 * minDistance and previous can only be used after running SSAD
	 * algorithm on vertex
	 */
	class Vertex implements Comparable<Vertex>
	{
		public final String name;
		public List<Edge> adjacencies;
		public double minDistance = Double.POSITIVE_INFINITY;
		public Vertex previous;
		public Vertex(String argName) { name = argName; }
		@Override
		public int compareTo(Vertex arg0) {
			// TODO Auto-generated method stub
			return Double.compare(minDistance, arg0.minDistance);
		}
	}

	/*
	 * Class that stores target and weight
	 * of an edge between vertexes
	 */
	class Edge
	{
		public final Vertex target;
		public final double weight;
		public Edge(Vertex target, double weight){ 
			this.target = target; 
			this.weight = weight; 
		}
	}
}
