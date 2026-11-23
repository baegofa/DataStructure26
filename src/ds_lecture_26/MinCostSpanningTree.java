package ds_lecture_26;

import java.util.LinkedList;

class edge {
	int to, weight;
	
	edge(int to, int weight){
		this.to = to;
		this.weight = weight;
	}
}

//K는 node 의 키, W는 가중치
public class MinCostSpanningTree<edge> {
	private LinkedList<edge>[] adj, inv;
	private int size;
	private boolean directed;
	
	public MinCostSpanningTree(int n, boolean directed) {
		size = n;
		this.directed = directed;
		
		adj = new LinkedList[size];
		for(int x = 0; x < size; x++) 
			adj[x] = new LinkedList<edge>();
		
		if(directed) {
			inv = new LinkedList[size];
			for (int x = 0; x < size; x++) {
				inv[x] = new LinkedList<edge>();
			}
		}
	}

	public int size() {
		return size;
	}

	public void addEdge(Node<K,W> v,Node<K,W> w, W weight) {
		adj[v.key]
	}

	public Iterable<Integer> adj(int v) {
		// TODO Auto-generated method stub
		return null;
	}

	public int inDegree(int v) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int outDegree(int v) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	protected void kruskalAlgorithm() {
		
	}
	
	protected void primAlgorithm() {
		
	}
	
	protected void SolinAlgorithm() {
		
	}
}
