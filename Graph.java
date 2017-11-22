import java.util.*;
import java.lang.*;
import java.io.*;

public class Graph {
	
	class Edge implements Comparable<Edge>
	{
		char src , dest;
		int weight;
		
		public int compareTo(Edge compareEdge)
		{
			return this.weight-compareEdge.weight;
		}
		
	};
	
	class subset
	{
		int parent;
		int rank;
	}
	int V, E;
	Edge edge[];
	
	
	
	Graph (int v, int e) {
		V = v;
		E = e;

		edge = new Edge[E];
		for ( int i = 0 ; i < e; ++i) 
			edge[i] = new Edge();
		
		}
	int find( subset subsets[], int i)
	{
		if (subsets[i].parent !=  i)
			subsets[i].parent = find (subsets, subsets[i].parent);
		return subsets[i].parent;
	}

	void Union (subset subsets[], int x , int y)
	{
		/*int xset = find(parent, x);
		int yset = find(parent , y); */
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);
		if (subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if (subsets[xroot].rank > subsets[yroot].rank)
			subsets[yroot].parent = xroot;
		else
		{
			subsets[yroot].parent = xroot;
			subsets[xroot].rank ++;
			
		}
		
		
	
	}
	

	void KruskalMST()
	{
		Edge result[] = new Edge[V];
		int e = 0;
		int i = 0;
		for ( i =0; i <V; ++i)
			result [i] = new Edge();
		Arrays.sort(edge);
		
		subset subsets[]  = new subset[V];
		for (i = 0; i <V; ++i)
			subsets[i] = new subset();
		for (int v = 0; v <V ; ++v)
		{
			subsets[v].parent = v;
			subsets[v].rank =0 ;
			
		}
		i = 0;
		while (e < V- 1)
		{
			Edge next_edge = new Edge();
			next_edge = edge[i++];
			int x = find(subsets, ((int)next_edge.src-97));
			int y = find(subsets, ((int)next_edge.dest-97));
			
			if (x != y )
			{
				result[e++] = next_edge;
				Union(subsets, x,y);
			}
		}
		System.out.println("Kruskal.out:" + "\n" + e);
		//System.out.println(e);
		
		for (i=0; i<e; i ++)
			System.out.println("(" + result[i].src + "," + result[i].dest + ")" + "=" + result[i].weight);
		
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	
	{
		// TODO Auto-generated method stub
		Scanner reader = new Scanner (System.in);
		System.out.println("Enter the input file path");
		String pathval = reader.nextLine();
		pathval = pathval.replaceAll("\\\\","\\\\\\\\");
		
		String line;
		String delims = "[(,)=]+";
		int i = 0;
		
		FileReader fr = new FileReader(pathval);
		BufferedReader bufferedReader = new BufferedReader (fr);
		line = bufferedReader.readLine();
		int V = Integer.parseInt(line);
		line = bufferedReader.readLine();
		line = bufferedReader.readLine();
		int E = Integer.parseInt(line);
		Graph graph = new Graph(V,E);
		char[] einfochars = null;
		
		
		
		
		while ((line = bufferedReader.readLine()) != null)
		{
			
			einfochars = line.toCharArray();
			graph.edge[i].src = einfochars[1];
			graph.edge[i].dest = einfochars[3];
			graph.edge[i].weight = Character.getNumericValue(einfochars[6]);
			++i; 
		
		}
	
		
		/*
		
		int V = 4, E=5;
		Graph graph = new Graph(V,E);
		
		graph.edge[0].src= 'a';
		graph.edge[0].dest = 'b';
		graph.edge[0].weight = 10;
		graph.edge[1].src= 'a';
		graph.edge[1].dest = 'c';
		graph.edge[1].weight = 6;
		graph.edge[2].src= 'a';
		graph.edge[2].dest = 'd';
		graph.edge[2].weight = 5;
		graph.edge[3].src= 'b';
		graph.edge[3].dest = 'd';
		graph.edge[3].weight = 15;
		graph.edge[4].src= 'c';
		graph.edge[4].dest = 'd';
		graph.edge[4].weight = 4;
		
	*/
		
	graph.KruskalMST();
	

}
}


