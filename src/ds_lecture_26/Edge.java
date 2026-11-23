package ds_lecture_26;

public class Edge implements Comparable<Edge>{
	int from, to, weight;
	
	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		if(this.weight > o.weight) return 1;
		else if(this.weight < o.weight) return -1;
		else return 0;
	}
}
