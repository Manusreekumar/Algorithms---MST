# Algorithms---MST

Contents




1.	About the program


2.	Approaches and Time Complexity


3.	Sample Instance of Program Run


4.	How to run the MSTCompare Program?










# About the program
“MSTCompare” program provides the minimum spanning tree output of a given graph by using two popular MST algorithms – Kruskal’s and Prim’s. MSTCompare accepts a notepad file having the details of a graph in the following structure:
1.	Number of vertices
2.	Vertex Names separated by comma
3.	Number of Edges.
4.	The source, destination and weights given in the format: (source, destination) =weight
The program runs Kruskal’s algorithm and Prim’s Algorithm on the graph inputs and provides two notepad files - “Kruskals out” and “Prims out” in the same path of the input file. 
The output will be in the format:
1.	Number of edges in the MST
2.	(source, destination) =weight.
The order of the edges will be different based on the different approaches used by the two algorithms. Kruskal’s algorithm picks up edges based on ascending order of their weights and checks for cycle before inserting into MST, while Prim’s identifies the minimum edge in the cut set between MST and rest of the graph.

# Approaches and Time Complexity

1.	Kruskal’s Algorithm
Kruskal’s Algorithm uses Union Find data structure for finding the MST. Let us analyze the time complexity in the major steps of Kruskal’s algorithm. Let E be the number of edges and V be the number of vertices.
a)	Creating the result array for holding the final set of MST = O(V)
b)	The edges of Graph are sorted using Merge sort – O (E log E)
c)	Creating the subsets array for holding the parent and rank of vertices – O(V)
d)	Union and find operation – O(logV) (Rank property is used during Union operation to bring down the Find cost to almost O (1))
e)	Iterations through edges using Union Find to identify MST – O(ElogV)
Hence the total complexity = O(ElogE) + O(ElogV) + O(V) + O(V)
       = O(ElogV)

2.	Prim’ algorithm
Prim’s algorithm uses Heap data structure for retrieving the minimum weighted edge out of cut set, and Adjacency List to find the adjoining vertices for updating the weights inside the Heap. Let us analyze the time complexity of Prim’s algorithm:
a)	Heap implementation using Priority Queue = O(E=VlogV) for initialization and O(logV) for extracting minimum weight vertex 
b)	Iteration through all the vertices and corresponding adjoining edges in Adjacency List = O(V+E)
c)	Decrease key function after updating weights in Heap = O((V+E) * logV) = O (E log V)
Hence total complexity = O((V+E) logV) = O(ElogV)


# How to run the MSTCompare Program?

Please follow the below steps for running the MSTCompare Program:
a)	Run the executable jar file.
b)	Upon prompt, input the path of the input file with the full path name. For Example, if the input path is “graph.in.txt”, then the path should be in the format C:\Users\manu sreekumar\Downloads\Intro to Algorithms\graph.in.txt
c)	After program is complete, two notepad files Kruskals.out and Prims.out would be created in the same path of the input file.
