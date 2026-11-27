package ds_lecture_26;

public class Edge{
	int from, to, weight;
	
	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge <"+ from + "," + to + ">, weight=" + weight;
	}

	
}
