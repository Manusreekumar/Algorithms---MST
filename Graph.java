import java.util.*;
import java.lang.*;
import java.io.*;

//Graph class
public class Graph {
	//Path variable for storing the input path.
	public static String pathval;
	//HashMap for storing the string input of vertex and corresponding position values
	static Map<Integer, String> vertexmap = new HashMap<Integer, String> ();
	//Edge class for storing source ,destination and weight.
	class Edge
	{
		int src , dest;
		int weight;
				
	};
	
	// Mergesort class for sorting the array of edges objects.
	class MergeSort 
	{
		public void sort( Edge[] edge, int startval , int endval)
		{
			
			if (startval < endval)
			{
				int midval = (startval + endval) / 2;
				
				sort(edge, startval, midval);
				sort(edge, midval + 1, endval);
				
				merge(edge, startval, midval, endval);
			}
		}
		
		public void merge(Edge[] edge, int startval, int midval, int endval)
		{
			int arrsize1 = midval  - startval + 1;
			int arrsize2 = endval - midval;
			
			
			Edge[] arr1 = new Edge[arrsize1];
			Edge[] arr2 = new Edge[arrsize2];
			
			for(int i = 0; i < arrsize1; ++i)
			{
				arr1[i] = edge[startval+i];
			
			}
			
			for(int i = 0; i < arrsize2; ++i)
			{
				arr2[i] = edge[midval+1+i];
			
			}
			
			int i = 0;  int j = 0;
			
			int k = startval;
			
			while(i < arrsize1 && j < arrsize2)
			{
				//Here the weights attribute is compared for sorting the edges.
				if (arr1[i].weight  <= arr2[j].weight)
				{
					edge[k] = arr1[i];
			
					i++;
				}
				else
				{
					edge[k] = arr2[j];
			
					j++;
				}
				
				k++;
			}
			
			while (i < arrsize1)
			{
					edge[k] = arr1[i];
			
					i++;
					k++;
				
			}
			
			while (j < arrsize2)
				
			{
				edge[k] = arr2[j];
			
				j++;
				k++;
			}
			
			
			
		}


	}
	//Min Heap class for storing the vertices as part of Prims algorithm
	public class MinHeap
	{
		
		// for storing the weight , position and size of the heap structure
	    private int[][] Heap;
	    private int[] pos;
	    private int size;
	 
	    private static final int FRONT = 0;
	    
	  //Constructor for intialising the heap objects
	    public MinHeap(int V)
	    {
	        this.size = 0;
	        this.pos = new int[V];
	        this.Heap = new int[V][2];
	        
	    }
	    
	    
	    //To check whether a vertex is present in Min Heap
	    
	    
	    public boolean IsinMinHeap (int v)
	    {
	    	
	    	if (this.pos[v] < this.size)
	    		return true;
	    	return false;
	    	
	    }
	    //To check whether the heap is empty
	    public boolean IsEmpty()
	    {
	    	if (size == 0)
	    		return true;
	    	return false;
	    }
	    
	    // For swapping the values in the Heap
	    private void swap(int fpos, int spos)
	    {
	        int tmp[];
	        tmp = this.Heap[fpos];
	        this.Heap[fpos] = this.Heap[spos];
	        this.Heap[spos] = tmp;
	    }
	    //For Heapifying the heap
	    private void minHeapify(int pos)
	    {
	    	int smallest = pos;
	    	int left = 2*  pos + 1;
	    	int right = 2* pos + 2;
	    	if (left < this.size && this.Heap[left][1] < this.Heap[smallest][1])
	    		smallest = left;
	    	if (right < this.size && this.Heap[right][1] < this.Heap[smallest][1])
	    		smallest = right;
	    	if (smallest != pos)
	    	{
	    		this.pos[this.Heap[smallest][0]] = pos;
	    		this.pos[this.Heap[pos][0]] = smallest;
	    		this.swap(smallest, pos);
	    		this.minHeapify(smallest);
	    	}	
	    
	    }
	    
	    // For adjusting the parent and the child after weights are updated inside the heap
	    public void decreasekey(int pos , int weight)
	    {
	    	int i = this.pos[pos];
	    	this.Heap[i][1] = weight;
	    	while (i > 0 && this.Heap[i][1] < this.Heap[((i-1)/2)][1])
	    			{
	    				
	    				this.pos[this.Heap[i][0]] = (i-1)/2;
	    				this.pos[this.Heap[((i-1)/2)][0]] = i;
	    				this.swap(i, (i-1)/2);
	    				i = (i-1)/2;
	    			}
	    }
	    
	    //For Extracting the vertex with minimum weight from Heap
	    public int[] ExtractMin()
	    {
	    	
	        int[] popped = Heap[FRONT];
	       
	        int[] lastnode = this.Heap[this.size-1]; 
	        Heap[FRONT] = lastnode;
	        
	        this.pos[lastnode[0]] = 0;
	        this.pos[popped[0]] = this.size - 1;
	        this.size -= 1;
	        
	        minHeapify(FRONT);
	        
	        return popped;
	    }
	}
	
	//Subset class for parent and rank values in Kruskal's algorithm
	class subset
	{
		
		int parent;
		int rank;
	}
	
	//Vertex and Edges of Graph
	int V, E;
	Edge edge[];
	//Adjacency List declaration for getting adjacent values in Prim's algorithm
	List<Edge> AdjList[];
	
	// Graph constructor
	Graph (int v, int e) {
		V = v;
		E = e;
		AdjList = new LinkedList[v];
		edge = new Edge[E];
		for ( int i = 0 ; i < e; ++i)
		{
			edge[i] = new Edge();
			
		}
		for ( int i = 0 ; i < v; ++i)
		{
			AdjList[i] = new LinkedList<Edge>();
			
		}
		
		
		}
	
	// For adding bidirectional edges to the Graph
	void addEdge(Edge edge)
	{
		
		Edge newnode1 = new Edge();
		Edge newnode2 = new Edge();
		newnode1.src = edge.src;
		newnode1.dest = edge.dest;
		newnode1.weight = edge.weight;
		//Adding destination edge details to the source edge in Adjacency list
		AdjList[edge.src].add(0, newnode1 );
		
		newnode2.src = edge.dest;
		newnode2.dest = edge.src;
		newnode2.weight = edge.weight;
		//Adding source edge details to destination for biconnectivity.
		AdjList[edge.dest].add(0, newnode2 );
		
		//AdjList[edge.src].add(0,edge);
	}
	
	// For finding root / parent of a vertex .
	int find( subset subsets[], int i)
	{
		if (subsets[i].parent !=  i)
			subsets[i].parent = find (subsets, subsets[i].parent);
		return subsets[i].parent;
	}
	
	// Union function for combining the new edge to the MST if parents of two
	//vertices in the incoming edge are not present in the subset array
	void Union (subset subsets[], int x , int y)
	{
		/*int xset = find(parent, x);
		int yset = find(parent , y); */
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);
		//rank is used to reduce the time for finding the parent in the next set 
		// of iterations.
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
	
	// PrintArr for Prim's algorithm output
	public void printArr(int[] parent, int [] key, int n) throws IOException
	{
		System.out.println("Prims Out");
		// extracting the input path so that the output could be placed in the
		//same path
		int ind = pathval.lastIndexOf('\\');
		String parsedpath = null;
		
		
		
		parsedpath = pathval.substring(0, ind+1);
		
		File file = new File( parsedpath + "Prims.out.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		//Printing the output to notepad
		pw.println(n-1);
		System.out.println(n-1);
		for (int i = 1; i < n; i++)
		{
			
			pw.println("("+ vertexmap.get(parent[i]) + "," + vertexmap.get(i) + ") =" + key[i]);
			System.out.println("("+ vertexmap.get(parent[i]) + "," + vertexmap.get(i) + ") =" + key[i]);
		
		}
		
		pw.close();
			
	}
	
	//Prim's algorithm
	void PrimsMST() throws IOException
	{
		// Key for storing the weight of the MST
		int Key[] = new int[V];
		//Parent for storing the MST
		int parent[] = new int[V];
		//Min Heap for holding the vertices
		MinHeap Heap = new MinHeap(V);
		//Initialising the heap with maximum weights for vertices , parent values as minimum
		//position of values as vertex integers.
		for (int i = 0; i < V; i++)
		{
			parent[i] = -1;
			Key[i] = Integer.MAX_VALUE;
			Heap.Heap[i][0] = i;
			Heap.Heap[i][1] = Key[i];
			Heap.pos[i] = i;
			
		}	
		
		Heap.pos[0] = 0;
		Key[0] = 0;
		
		Heap.decreasekey(0, Key[0]);
		//Updating the size of the heap as number of vertices.
		Heap.size = V;
		
		//Performs the following operations till the Heap is empty.
		while (Heap.IsEmpty() == false)
		{
			//Vertex with minimum weight is extracted
			int[] newheapnode = Heap.ExtractMin();
			int u = newheapnode [0];
			//Searching all adjacent edges of the minimum weighted node
			for (Edge pcrawl : AdjList[u])
			{
				int v = pcrawl.dest;
				//IF the vertex is in Minimum heap and weight is less than weight of 
				//incoming edge, then MST is updated and weight is updated
				if (Heap.IsinMinHeap(v) == true && pcrawl.weight < Key[v])
				{
					
					Key[v] = pcrawl.weight;
					parent[v] = u;
			
					
					Heap.decreasekey(v, Key[v]);
				}
			}
			
		}
		
		printArr(parent,Key,V);
			
		
	}
	
	//Kruskal's algorithm
	void KruskalMST() throws IOException
	{
		//result edge array for holding the final MST
		Edge result[] = new Edge[V];
		int e = 0;
		int i = 0;
		for ( i =0; i <V; ++i)
			result [i] = new Edge();
		//Arrays.sort(edge);
		
		//Sorting the edges in the Graph in ascending order using MergeSort
		MergeSort ob = new MergeSort();
		ob.sort(edge, 0, E-1);
		

		// Subset object is created for holding the parent and rank details
		subset subsets[]  = new subset[V];
		for (i = 0; i <V; ++i)
			subsets[i] = new subset();
		for (int v = 0; v <V ; ++v)
		{
			subsets[v].parent = v;
			subsets[v].rank =0 ;
			
		}
		i = 0;
		//int flag = 0;
		//The following operations will run until all the edges are covered
		try {
			while (e < V-1)
			{
				Edge next_edge = new Edge();
				next_edge = edge[i++];
			// Find operation is run on two vertices
				int x = find(subsets, ((int)next_edge.src));
				int y = find(subsets, ((int)next_edge.dest));
			//If parents are not the same , the new edge is added to the MST	
				if (x != y )
				{
					result[e++] = next_edge;
					Union(subsets, x,y);
				}
				
				//flag++;
			}
		}
		
		catch (ArrayIndexOutOfBoundsException exception)
		{
			System.out.println("The Graph is disjointed. Please correct");
			System.exit(0);
		}
		
		System.out.println("Kruskal.out:" + "\n" + e);
	
		String parsedpath = null;
		
		int ind = pathval.lastIndexOf('\\');
		parsedpath = pathval.substring(0, ind+1);		
		
		
		File file = new File( parsedpath + "Kruskals.out.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);

		//Using the VertexMap Hash variable for retrieving the String equivalents
		//of the integer values that are added
		for (i=0; i<e; i ++)
		{
			pw.println("(" + vertexmap.get(result[i].src) + "," + vertexmap.get(result[i].dest) + ")" + "=" + result[i].weight);
			System.out.println("(" + vertexmap.get(result[i].src) + "," + vertexmap.get(result[i].dest) + ")" + "=" + result[i].weight);
		
		}
			
		pw.close();
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	
	{
		// TODO Auto-generated method stub
		//For reading the input file
		Scanner reader = new Scanner (System.in);
		System.out.println("Enter the input file path");
		
		pathval = reader.nextLine();
		pathval = pathval.replaceAll("\\\\","\\\\\\\\");
		
		String line;
		String delims = "[(,)=]+";
		int i = 0;
		//Parsing the input file values for updating the vertex , edge and 
		//weight values
		FileReader fr = new FileReader(pathval);
		BufferedReader bufferedReader = new BufferedReader (fr);
		line = bufferedReader.readLine();
		int V = Integer.parseInt(line);
		if (V <= 0)
		{
			System.out.println("The vertex input cannot be zero or negative");
			System.exit(0);
		}
		line = bufferedReader.readLine();
		String[] parseval = line.split(delims);
		for ( i = 0; i < V; i++)
		{
			vertexmap.put(i,parseval[i]);
		}
				
		line = bufferedReader.readLine();
		int E = Integer.parseInt(line);
		if (E <= 0)
		{
			System.out.println("The number of edges cannot be zero or negative");
			System.exit(0);
		}
		Graph graph = new Graph(V,E);
		
		//char[] einfochars = null;
		String[] parseval1 = null;
		i = 0;
		
		try
		{
			while ((line = bufferedReader.readLine()) != null)
			{
				
				
				parseval1 = line.split(delims);
				for (int j = 0; j <V ; j ++)
				{
					if (parseval1[1].compareTo(vertexmap.get(j))==0)
					{
						graph.edge[i].src = j;
						
					}
					if (parseval1[2].compareTo(vertexmap.get(j))==0)
					{
						graph.edge[i].dest = j;
						
					}
				}
				graph.edge[i].weight = Integer.parseInt(parseval1[3]);
				graph.addEdge(graph.edge[i] );
				++i; 
				
			
			}

		}
		catch (ArrayIndexOutOfBoundsException exception) {
			
			System.out.println("Exception occured. Please check the input");
			System.exit(0);
		}
		
	graph.KruskalMST();
	graph.PrimsMST();
	

}
}


