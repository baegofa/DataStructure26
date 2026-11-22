package ds_lecture_26;

import java.util.LinkedList;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class ListGraph implements Graph {
	private LinkedList<Integer>[] adj, inv;
	private int N;
	private boolean directed;
	

	//@SuppressWarnings("unchecked")
	//LinkedList[] needs Generic type for safety
	@SuppressWarnings("unchecked")
	public ListGraph(int n, boolean directed) {
		N = n;
		this.directed = directed;
		
		adj = new LinkedList[N];
		for (int x=0;x<N;x++) {
			adj[x] = new LinkedList<Integer>();
			}
		
		if(directed == true) {
			inv = new LinkedList[N];
			for (int x =0;x<N;x++)
				inv[x] = new LinkedList<Integer>();
		}
	}

	@Override
	public int size() {
		return N;
	}

	@Override
	public void addEdge(int v, int w) { // v to w edge
		adj[v].add(w);
		if (directed == false) {
			adj[w].add(v);
		}
		else
			inv[w].add(v);
	}

	@Override
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	@Override
	public int inDegree(int v) {
		if(directed == false)
			return adj[v].size();
		else
			return inv[v].size();
	}

	@Override
	public int outDegree(int v) {
		return adj[v].size();
	}
	
	
	public boolean isAdjacent(int v, int w) { // 행렬이 더 빠르다.
		for(int x: adj[v])
			if(x==w)
				return true;
		return false;
	}
	
	public Iterable<Integer> dfs(int v){
		ArrayList<Integer> L = new ArrayList<Integer>(size());
		boolean[] visited = new boolean[size()];
		dfs(v,L,visited);
		return L;
	}
	
	private void dfs(int v, ArrayList<Integer>L,boolean[] visited) {
		visited[v] = true;
		L.add(v);
		for(int w : adj[v])
			if(!visited[w]) dfs(w,L,visited);
	}
	
	public Iterable<Integer> bfs(int v){
		ArrayList<Integer> L = new ArrayList<Integer>(size());
		boolean[] visited = new boolean[size()];
		ArrayDeque<Integer> queue = new ArrayDeque<>(size());
		
		visited[v] = true;
		L.add(v);
		queue.addLast(v);
		while(!queue.isEmpty()) {
			int k = queue.pollFirst();
			for(int w : adj[k])
				if(!visited[w]) {
					L.add(w);
					visited[w] = true;
					queue.addLast(w);
				}
		}
		return L;
	}
	
	public void connectedComponent() {
		ArrayList<Integer> L = new ArrayList<Integer>(size());
		boolean[] visited = new boolean[size()];
		int count = 0;
		
		for(int i = 0; i<size(); i++) {
			if(!visited[i]) {
				System.out.println("연결 요소" + count + " ");
				dfs(i,L,visited);
				for(int k : L) System.out.println(k + " ");
				System.out.println();
				L.clear();
				count += 1;
				
			}
		}
	}

}
