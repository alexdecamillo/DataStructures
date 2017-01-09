package ShortestPath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ShortestPath.AdjacencyList.Vertex;

public class SSADTest {

	public static void main(String[] args) throws IOException {
		final String dir = System.getProperty("user.dir") + "\\ShortestPath";
		
		// Initializing file, populating AdList, and running computePath
		File file = new File(dir + "\\Graph.txt");
		AdjacencyList adList = new AdjacencyList(file);
		List<Vertex> list = adList.list;
		SSADAlgorithm algo = new SSADAlgorithm(list.get(adList.startVertex));
        
		// Initializing file writing objects
        File write = new File(dir + "\\SSADResults.txt");
        FileWriter fw = new FileWriter(write);
        BufferedWriter writer = new BufferedWriter(fw);
        
        String str1 = "Node  | Out-neighbors";
        String str2 = "--------------------------------------------------------";
        writer.write(str1);
        writer.newLine();
        writer.write(str2);
        writer.newLine();
        writer.flush();
        str1 = str2 = "";
        
        for(int i=0; i < list.size(); i++){
        	str1 = list.get(i).name + "        ";
        	writer.write(str1);
        	
        	for(int j=0; j < list.get(i).adjacencies.size(); j++){
        		str2 += list.get(i).adjacencies.get(j).target.name + ": " 
        				+ list.get(i).adjacencies.get(j).weight + "    ";
        	}
        	writer.write(str2);
        	writer.newLine();
        	writer.flush();
        	str1 = str2 = "";
        }
        
        writer.newLine();
        str1 = "Start vertex is: " + adList.startVertex;
        writer.write(str1);
        writer.newLine();
        writer.newLine();
        
        str1 = "Dest | Total Weight | Path";
        str2 = "--------------------------------------------------------";
        writer.write(str1);
        writer.newLine();
        writer.write(str2);
        writer.newLine();
        writer.flush();
        str1 = str2 = "";
        
        for(int i=0; i < list.size(); i++){
        	writer.write(i + "        ");
        	str1 = list.get(i).minDistance + "         ";
        	writer.write(str1);
        	
        	List<Vertex> path = algo.getShortestPathTo(list.get(i));
            for(int j=1; j < path.size(); j++){
            	str2 += path.get(j).name + "  ";
            }
        	writer.write(str2);
        	writer.newLine();
        	writer.flush();
        	str1 = str2 = "";
        }
        
        writer.close();
	}

}
