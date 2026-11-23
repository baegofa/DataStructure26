package ds_lecture_26;

import java.util.ArrayList;

public class SetTree {
	public static final int SIZE = 10;
	int size;
	ArrayList<Integer> L = new ArrayList<>(size);
	
	public SetTree() {
		size = SIZE;
	}
	
	public SetTree(int n) {
		size = n;
	}
	
	int find(int i) {
		//i의 root를 구하는 method
		for(;L.get(i)>=0;i = L.get(i));
		return i;
	}
	void union(int i, int j) {
		if(i == j) return;
		//i를 root로 j를 통합
		int pi = find(i);
		int pj = find(j);
		L.set(pi, L.get(pi) + L.get(pj));
		L.set(pj, pi);
	}
	
	//union() 의 개선 버전
	void weightedUnion(int i, int j) {
		if (i == j) return;
		int pi = find(i) , pj = find(j);
		int temp = L.get(pi) + L.get(pj);
		
		// i의 root 가 가진 자식 수가 더 많거나 같은 경우
		if(L.get(pi) <= L.get(pj)) {
			L.set(pi, temp);
			L.set(pj, pi);
		}
		else {
			L.set(pj, temp);
			L.set(pi, pj);
		}
			
	}
	int collapsingFind(int i) {
		return 0;
	}
}
