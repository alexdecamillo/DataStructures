package ShortestPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import ShortestPath.AdjacencyList.Edge;
import ShortestPath.AdjacencyList.Vertex;

public class SSADAlgorithm {
	
	/*
	 * Method: SSADAlgorithm (Constructor)
	 * Description: Takes the source vertex as a 
	 * parameter and calls computePaths
	 */
	public SSADAlgorithm(Vertex source){
		computePaths(source);
	}
	
	/*
	 * Method: computePaths
	 * Description: Goes through the paths from the source
	 * to each vertex in the graph. Assigns the minDistance
	 * and previous variables to the vertexes during this
	 * process. Dijkstra’s algorithm is used here
	 */
	public void computePaths(Vertex source)
	{
		source.minDistance = 0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);
		System.out.println(vertexQueue.size());
		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();
			System.out.println(vertexQueue.size() + "  " + u.name);
			for (Edge e : u.adjacencies)
			{
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);

					v.minDistance = distanceThroughU ;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	/*
	 * Method: getShortestPathTo
	 * Description: Takes in a target vertex and backtracks
	 * using the previous member of the vertex class to
	 * find the path from the source to the target
	 */
	public List<Vertex> getShortestPathTo(Vertex target)
	{
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}
}
