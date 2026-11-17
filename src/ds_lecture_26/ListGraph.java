package ds_lecture_26;

import java.util.LinkedList;

public class ListGraph implements Graph {
	private LinkedList<Integer>[] adj, inv;
	private int N;
	private boolean directed;
	

	//@SuppressWarnings("unchecked")
	//LinkedList[] needs Generic type for safety
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

}
