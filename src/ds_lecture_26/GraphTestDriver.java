package ds_lecture_26;

public class GraphTestDriver {
	static final boolean DEBUG = true;
	// format : if(DEBUG) {System.err.print(0);}

	public static void main(String[] args) {
//		Graph G = new MatrixGraph(7, false);  // 정점이 7개인 무방향성 그래프 : 인접행렬
		Graph G = new ListGraph(7, false);
		G.addEdge(0, 1);
		G.addEdge(0, 2);
		G.addEdge(1, 3);
		G.addEdge(1, 4);
		G.addEdge(2, 5);
		G.addEdge(2, 6);
		
		System.out.print("정점 2에 인접한 정점들: ");
		for (int v : G.adj(2)) 
			System.out.print(v + " ");
		System.out.println();
		System.out.println("정점 1의 inDegree = " + G.inDegree(1));
		System.out.println("정점 0의 outDegree = " + G.outDegree(0));
		
//		G = new MatrixGraph(6, true);
		G = new ListGraph(6, true);
//		null error exist below the code
//		DEBUG NOTE : 생성자의 inv가 directed == false 조건문으로 되어있었음. 따라서 directed == true 로 수정.
		G.addEdge(1, 0);
		G.addEdge(1, 3);
		G.addEdge(2, 1);
		G.addEdge(3, 2);
		G.addEdge(3, 4);
		G.addEdge(3, 5);
		G.addEdge(4, 0);
		G.addEdge(5, 0);
		G.addEdge(5, 1);
		G.addEdge(5, 4);
		
		System.out.print("정점 3에 인접한 정점들: ");
		for (int v : G.adj(3))
			System.out.print(v + " ");
		System.out.println();
		System.out.println("정점 0의 inDegree = " + G.inDegree(0));
		System.out.println("정점 5의 outDegree = " + G.outDegree(5));
	}

}
