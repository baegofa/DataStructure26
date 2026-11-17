package ds_lecture_26;

import java.util.ArrayList;

public class MatrixGraph implements Graph {
	private int[][] G; //인접 행렬
	private int N;
	private boolean directed;

	public MatrixGraph(int n, boolean directed) {
		N = n;
		this.directed = directed;
		G = new int[N][N];
	}

	@Override
	public int size() {
		return N;
	}

	@Override
	public void addEdge(int v, int w) {
		G[v][w] = 1;
		if(directed == false)
			G[w][v] = 1;
	}

	@Override
	public Iterable<Integer> adj(int v) {
		ArrayList<Integer> result = new ArrayList<>(N);  //최대 개수로 잡는 것이 편함 -> 동적 할당 줄임
		for(int x = 0; x < N; x++) 
			if (G[v][x] == 1)
				result.add(x);
		return result;
	}

	@Override
	public int inDegree(int v) {
		int count = 0;
		for (int x=0; x<N; x ++)
			if(G[x][v] == 1)
				count++;
		return count;
	}

	@Override
	public int outDegree(int v) {
		int count = 0;
		for (int x=0; x<N; x ++)
			if(G[v][x] == 1)
				count++;
		return count;
	}
	public boolean isAdjacent(int v, int w) {
		return G[v][w] == 1;
	}

}
