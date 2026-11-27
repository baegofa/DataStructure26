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
	
	private void dfs(int v, ArrayList<Integer> L, boolean[] visited) {
		L.addLast(v);
		visited[v] = true;
		for(int w : adj[v])
			if(!visited[w]) dfs(w,L,visited);
	}
	
	public Iterable<Integer> bfs(int v) {
		ArrayList<Integer> L = new ArrayList<>(size());
		boolean[] visited = new boolean[size()];
		ArrayDeque<Integer> queue = new ArrayDeque<>(size());
		
		L.addLast(v);
		queue.addLast(v);
		visited[v] = true;
		while(!queue.isEmpty()) {
			int k = queue.pollFirst();
			for(int w : adj[k])
				if(!visited[w]) {
					L.addLast(w);
					visited[w] = true;
					queue.addLast(w);
				}
		}
		return L;
	}
	
	public void connectedComponents() {
		//ArrayList<Integer> L = new ArrayList<>(size());에서 우변에 <>채우는 것은 차이가 없음
		ArrayList<Integer> L = new ArrayList<>(size());
		boolean[] visited = new boolean[size()];
		int count = 0;
		
		for(int i = 0; i < size(); i++) {
			if(!visited[i]) {
				System.out.println("연결요소"+count+":");
				dfs(i,L,visited);
				for(int k : L) {
					System.out.print(k+" ");
				}
				System.out.println();
				L.clear();
				count++; // i와 차이가 없지 않나? -> if문으로 dfs가 동작할 때만 증가하게 됨.
			}
		}
	}
	
	public Iterable<Integer> topSort(){
		ArrayList<Integer> list = new ArrayList<>(N);
		ArrayDeque<Integer> stack = new ArrayDeque<>(N);
		int[] count = new int[N];
		
		for (int v = 0; v < N; v++) {
			count[v] = inv[v].size();
			if(count[v] == 0) stack.push(inv[v].element());
		}
		
		for (int i = 0; i < N; i++) {
			int v;
			if(stack.isEmpty()) return null;
			v = stack.pop();
			list.addLast(v);
			for(int k : adj[v]) {
				count[k] -= 1;
				if(count[k] == 0) stack.push(k);
			}
		}
		return list;
	}
}
