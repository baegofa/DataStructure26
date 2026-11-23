package ds_lecture_26;

public class ListWeightedGraph implements weightedGraph {

	@Override
	public void addEdge(int to, int from, int weight) {
		Edge w = new Edge(from,to,weight);
		
	}

	@Override
	public Iterable<Edge> adj(int v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Edge> edges() {
		// TODO Auto-generated method stub
		return null;
	}

}
