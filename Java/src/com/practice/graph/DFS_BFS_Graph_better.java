package com.practice.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class Graph {
	int v;
	List<List<Integer>> adjl = null;
	boolean visited[] = null;
	
	Map<Integer,Integer> map = new HashMap<>();
	Set<Integer> set = new HashSet<>();
	
	
	public Graph(int nodes) {
		v = nodes;
		adjl = new ArrayList<>();
		for( int i =0 ; i< nodes; i++)adjl.add(new ArrayList<>());
		visited = new boolean[v];
	}
	public void addEdge(int src, int dest){
		adjl.get(src).add(dest);
	}

	public void runDFS(int n){
		if(!visited[n]){
			visited[n] = true;
			System.out.println(n);
			for(Integer i : adjl.get(n)){
				if(!visited[i]){
					visited[i] = true;
					System.out.println(i);
					for(Integer j : adjl.get(i)){
						runDFS(j);
					}
				}
			}
		}
	}

	public void runBFS(int n){
		System.out.println("Running bfs ...");
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);
		while(!q.isEmpty()){
			int currRoot = q.poll();
			if(!visited[currRoot]) visited[currRoot] = true;
			else continue;
			System.out.println(currRoot);
			for(Integer i : adjl.get(currRoot)){
				q.add(i);
			}

		}
	}
	public Graph cloneGraph(int n){
		Graph g = new Graph(v);
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);
		while(!q.isEmpty()){
			int t = q.poll();
			if(!visited[t])	visited[t] = true;
			else continue;
			for(Integer j : adjl.get(t)){
				g.addEdge(t, j); // copy of original t and j is added to graph g here.
				q.add(j);
			}

		}
		return g;
	}

	public void resetVisited(){
		for(int i = 0 ; i < v; i++)visited[i] = false;
	}
	public void tsorting(){
		resetVisited();
		Stack<Integer> s = new Stack<>();
		for(int i=0 ; i<v;i++){
			if(visited[i]!=true)tsortingUtil(s,i);
		}
		while(!s.empty()){
			System.out.println(s.pop());
		}

	}
	private void tsortingUtil(Stack<Integer> s, int i) {
		visited[i]= true;
		for(Integer j : adjl.get(i)){
			if(visited[j]!=true){
				tsortingUtil(s,j);
			}
		}
		s.push(i);
	}
}


public class DFS_BFS_Graph_better {
	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(1, 2);
		g.addEdge(2, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 2);
		g.addEdge(3, 4);
		g.addEdge(4, 3);
		g.addEdge(1, 4);
		g.addEdge(4, 1);
		g.addEdge(4, 5);
		g.addEdge(5, 4);
		g.runBFS(3);
		g.resetVisited();
		System.out.println("Running dfs ...");
		g.runDFS(3);
		g.resetVisited();
		System.out.println("cloned");
		Graph cloned = g.cloneGraph(3);
		g.resetVisited();
		cloned.runBFS(3);
		cloned.resetVisited();
		System.out.println("Running dfs ...");
		cloned.runDFS(3);
		cloned.resetVisited();
		System.out.println("Running tsort ...");
		g.tsorting();
	}
}
