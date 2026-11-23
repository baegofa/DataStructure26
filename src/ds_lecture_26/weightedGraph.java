package ds_lecture_26;

public interface weightedGraph {
	int size = 0;
	void addEdge(int to, int from, int weight);
	
	Iterable<Edge> adj(int v);
	Iterable<Edge> edges();
}
