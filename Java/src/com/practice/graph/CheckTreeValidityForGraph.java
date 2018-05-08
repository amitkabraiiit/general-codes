package com.practice.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class CheckTreeValidityForGraph {
	public String graphValidBinaryTree(char[][] edges) {

		if (edges == null || edges.length == 0)return "E5";

		// Check inDegree for each node
		Map<Character, Integer> inDegree = new HashMap<>();
		for (char[] edge : edges) {
			if (inDegree.containsKey(edge[1])) {
				int degree = inDegree.get(edge[1]);
				inDegree.put(edge[1], degree + 1);
			} else inDegree.put(edge[1], 1);

			if (!inDegree.containsKey(edge[0])) inDegree.put(edge[0], 0);
		}

		// For a valid binary tree, there is one and only one node with inDegree 0, and all the other nodes has inDegree 1
		char root = '\0';
		int count = 0;
		for (char vertexId : inDegree.keySet()) {
			if (inDegree.get(vertexId) == 0) {
				root = vertexId;
				count++;
			}
			if (count > 1) return "E4";
			if (inDegree.get(vertexId) > 1) return "E4";
		}

		// No root
		if (count == 0) return "E3";

		// Step 2: transform the edge list to adjList
		Map<Character, Set<Character>> adjList = new HashMap<>();
		for (char[] edge : edges) {
			if (adjList.containsKey(edge[0])) {
				Set<Character> neighbors = adjList.get(edge[0]);
				// Duplicate edges
				if (neighbors.contains(edge[1])) return "E2";
				else	neighbors.add(edge[1]);

				// More than 2 children
				if (neighbors.size() > 2)return "E1";
			} else {
				Set<Character> neighbors = new HashSet<>();
				neighbors.add(edge[1]);
				adjList.put(edge[0], neighbors);
			}
		}

		// Step 3: do a topological sort, if not exist, return E3, cycle presents
		// Note: Couldn't use a boolean[] visited, because vertexId is char
		// Have to use a set
		Set<Character> visited = new HashSet<>();
		boolean ans = toplogicalSort(root, visited, adjList);

		if (!ans) 	return "E3";

		// Step 4: there must be valid binary tree exists, output the results.
		String result = treeToString(root, adjList);
		return result;
	}



	private boolean toplogicalSort(char vertex, Set<Character> visited, Map<Character, Set<Character>> adjList) {
		if (visited.contains(vertex)) return false;
		visited.add(vertex);
		Set<Character> neighbors = adjList.get(vertex);

		if (neighbors != null) {
			for (char neighbor : neighbors) {
				if (!toplogicalSort(neighbor, visited, adjList)) return false;
			}
		}
		visited.remove(vertex);
		return true;
	}

	private String treeToString(char root, Map<Character, Set<Character>> adjList) {
		Set<Character> neighbors = adjList.get(root);
		if (neighbors == null || neighbors.size() == 0) return "(" + root + ")";
		StringBuffer result = new StringBuffer();
		result.append("(" + root);
		for (char neighbor : neighbors) result.append(treeToString(neighbor, adjList));
		result.append(")");
		return result.toString();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numEdges = sc.nextInt();
		char[][] edges = new char[numEdges][2];
		for (int i = 0; i < numEdges; i++) {
			String edge = sc.next();
			edges[i][0] = edge.charAt(1);
			edges[i][1] = edge.charAt(3);
		}

		CheckTreeValidityForGraph solution = new CheckTreeValidityForGraph();
		String result = solution.graphValidBinaryTree(edges);
		System.out.println(result);
		sc.close();
	}
}
