package com.practice.graph;

public class MST {

	private static final int V=5;

	int minKey(int key[], Boolean mstSet[])	{
		int min = Integer.MAX_VALUE, min_index=-1;
		for (int v = 0; v < V; v++)
			if (mstSet[v] == false && key[v] < min){
				min = key[v];
				min_index = v;
			}
		return min_index;
	}

	void printMST(int mst[], int n, int graph[][]){
		System.out.println("Edge   Weight");
		for (int i = 1; i < V; i++)System.out.println(mst[i]+" - "+ i+"    "+graph[i][mst[i]]);
	}

	void primMST(int graph[][])	{
		// Array to store constructed MST
		int mst[] = new int[V];

		// Key values used to pick minimum weight edge in cut
		int key[] = new int [V];

		// To represent set of vertices not yet included in MST
		Boolean mstSet[] = new Boolean[V];

		// Initialize all keys as INFINITE
		for (int i = 0; i < V; i++){
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}

		// Always include first 1st vertex in MST.
		key[0] = 0;     // Make key 0 so that this vertex is
		// picked as first vertex
		mst[0] = -1; // First node is always root of MST

		// The MST will have V vertices
		for (int count = 0; count < V-1; count++){
			// Pick the minimum key vertex from the set of vertices not yet included in MST
			int u = minKey(key, mstSet);

			// Add the picked vertex to the MST Set
			mstSet[u] = true;

			// Update key value and mst index of the adjacent
			// vertices of the picked vertex. Consider only those
			// vertices which are not yet included in MST
			for (int v = 0; v < V; v++)

				// graph[u][v] is non zero only for adjacent vertices of m
				// mstSet[v] is false for vertices not yet included in MST
				// Update the key only if graph[u][v] is smaller than key[v]
				if (graph[u][v]!=0 && mstSet[v] == false && graph[u][v] <  key[v]){
					mst[v]  = u;
					key[v] = graph[u][v];
				}
		}

		// print the constructed MST
		printMST(mst, V, graph);
	}

	public static void main (String[] args)
	{
		/* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
		MST t = new MST();
		int graph[][] = new int[][] {{0, 2, 0, 6, 0},
				{2, 0, 3, 8, 5},
				{0, 3, 0, 0, 7},
				{6, 8, 0, 0, 9},
				{0, 5, 7, 9, 0},
		};

		// Print the solution
		t.primMST(graph);
	}
}