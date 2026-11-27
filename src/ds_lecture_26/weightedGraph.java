package ds_lecture_26;

public interface weightedGraph {
	int size();
	void addEdge(int from, int to, int weight);
	Iterable<Edge> adj(int v);
	Iterable<Edge> edges();
}
