package com.practice.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class KruskalMinimumSpanningTree {

	static class Node {
		private int nodeId;
		private boolean visited;

		public Node(int nodeId) {
			this.nodeId = nodeId;
			this.visited = false;
		}

		public int getNodeId() {
			return nodeId;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

	}

	static class Edge {
		Node node1, node2;
		private int weight;

		Edge(Node node1, Node node2, int weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}

		public int getWeight() {
			return weight;
		}

		public void setNodesVisited() {
			node1.setVisited(true);
			node2.setVisited(true);
		}
		public boolean areNodesVisited() {
			return (node1.isVisited() && node2.isVisited());
		}

	}

	static class Graph {
		int[][] adjMatrix;
		Edge[] edges;
		Node[] nodes;
		int numberOfNodes;
		int numberOfEdges;
		Graph(int[][] adjMatrix) {
			this.adjMatrix = adjMatrix;
			numberOfEdges = (this.adjMatrix.length) * (this.adjMatrix.length - 1) / 2; // n*(n-1)/2 , each node makes edge with other
			// and since it is undirected graph, hence /2
			numberOfNodes = this.adjMatrix.length;
			edges = new Edge[numberOfEdges];
			nodes = new Node[numberOfNodes];
			for (int i = 0; i < nodes.length; i++) {
				nodes[i] = new Node( i);
			}
			this.setEdges();
		}

		public void setEdges() {
			int counter = 0;
			for (int i = 0; i < numberOfNodes; i++) {
				for (int j = 0; j < i; j++) {
					edges[counter] = new Edge(nodes[i], nodes[j], adjMatrix[i][j]);
					counter++;
				}
			}
		}

		public int computeMinimumSpanningTree() {
			this.sortEdges();
			Edge [] mst = new Edge[edges.length];
			for (int i = 0; i < edges.length; i++) {
				if (!edges[i].areNodesVisited()) {
					mst[i] = edges[i];
					edges[i].setNodesVisited();
				}
				else{
					mst[i]  = null;
				}
			}
			int totalCost=0;
			for (int i = 0; i < mst.length; i++) {
				if(mst[i] != null){
					totalCost+= mst[i].getWeight();
					System.out.println(mst[i].node1.getNodeId()+","+mst[i].node2.getNodeId()+","+mst[i].weight);

				}
			}
			return totalCost;

		}

		public void sortEdges() {

			// bubble sort on edges weight (any sort can be used.)
		/*	for (int i = 0 ; i <edges.length - 1; i++)
			{
				for (int j = 0; j < edges.length -i-1; j++)
				{
					if (edges[j].getWeight() > edges[j+1].getWeight())
					{
						Edge temp = edges[j+1];
						edges[j+1] = edges[j];
						edges[j] = temp;
					} 
				} 
			} */
			System.out.println();
			System.out.println("Printing edge list");
			for (int i = 0; i < edges.length; i++) {
				System.out.println(edges[i].node1.getNodeId()+","+edges[i].node2.getNodeId()+","+edges[i].weight);
			}
			System.out.println("Printing finished");
		}
	}

	public static void main(String[] args) {		
		/*
		matrix[0]: [0 ,0 ,0 ,0 ,0]
		matrix[1]: [18,0 ,0 ,0 ,0]
		matrix[2]: [64,55,0 ,0 ,0]
		matrix[3]: [67,78,78,0 ,0]
		matrix[4]: [02,18,43,87,0]
		 */
		int [][] matrix = {{0,0,0,0,0},{18,0,0,0,0},{64,55,0,0,0},{67,78,78,0,0},{2,18,43,87,0}};
		System.out.println("Graph is :");
		for (int i = 0; i < 5; i++) {
			System.out.print("matrix["+i+"]: ");
			for (int j = 0; j < i; j++) {
				System.out.print(matrix[i][j] + ",");
			}
			System.out.println();
		}
		Graph graph = new Graph(matrix);
		System.out.println("The Total Cost is : " + graph.computeMinimumSpanningTree());
	}
}