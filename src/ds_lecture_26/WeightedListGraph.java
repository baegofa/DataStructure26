package ds_lecture_26;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class WeightedListGraph implements weightedGraph {
	private int N;
	private LinkedList<Edge>[] adj, inv;
	private boolean directed; // true = 방향성 그래프, false = 무방향성 그래프
	
	@SuppressWarnings("unchecked")
	public WeightedListGraph(int size, boolean directed) {
		this.N = size;
		this.directed = directed;
		
		adj = new LinkedList[N];
		for (int i=0; i<N; i++) {
			adj[i] = new LinkedList<Edge>();
		}
		
		if(directed) {
			inv = new LinkedList[N];
			for (int i=0; i<N; i++) {
				inv[i] = new LinkedList<Edge>();
			}
		}
	}
	
	@Override
	public int size() {
		return N;
	}

	@Override
	public void addEdge(int from, int to, int weight) {
		Edge e = new Edge(from,to,weight);
		adj[from].add(e);
		
		if(directed)
			inv[to].add(e);
		else
			adj[to].add(e);

	}

	@Override
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}

	@Override
	public Iterable<Edge> edges() {
		LinkedList<Edge> result = new LinkedList<>();
		
		for(int v = 0; v < N; v++) {
			for(Edge e : adj[v]) {
				if(e.from == v)
					result.add(e);
			}
		}
		return result;
	}
	
	public List<Edge> criticalActivity(int start, int finish){
		int[] earliest = getEarliest();
		int[] latest = getLatest(earliest[finish]);
//		System.out.println(Arrays.toString(earliest));
//		System.out.println(Arrays.toString(latest));
		
		
		
		return getCriticalAcitivity(earliest,latest);
	}
	
	private int[] getEarliest() {
		int[] earliest = new int[N];
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		int[] count = new int[N];
		
		for (int v = 0; v < N; v++) {
			count[v] = inv[v].size();
			if(count[v] == 0) 
				stack.push(v);
		}
		
		for (int i = 0; i < N; i++) {
			int v;
			if(stack.isEmpty()) return null;
			v = stack.pop();
			for(Edge e : adj[v]) {
				int k = e.to;
				count[k] -= 1;
				if(count[k] == 0) {
					stack.push(k);
				}
				if (earliest[k] < earliest[v] + e.weight)
					earliest[k] = earliest[v] + e.weight;
			}
		}
		return earliest;
	}
	
	private int[] getLatest(int length) {
		int[] latest = new int[N];
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		int[] count = new int[N];
		
		for (int v = 0; v < N; v++) {
			latest[v] = length;
			count[v] = adj[v].size();
			if(count[v] == 0) 
				stack.push(v);
		}
		
		for (int i = 0; i < N; i++) {
			int v;
			if(stack.isEmpty()) return null;
			v = stack.pop();
			for(Edge e : inv[v]) {
				int k = e.from;
				count[k] -= 1;
				if(count[k] == 0) {
					stack.push(k);
				}
				if (latest[k] > latest[v] - e.weight)
					latest[k] = latest[v] - e.weight;
			}
		}
		return latest;
	}
	
	private List<Edge> getCriticalAcitivity(int[] earliest, int[] latest){
		LinkedList<Edge> answer = new LinkedList<>();
		for(Edge e: edges()) {
			int early = earliest[e.from];
			int late = latest[e.to] - e.weight;
			if(early == late)
				answer.add(e);
		}
		return answer;
	}
}
