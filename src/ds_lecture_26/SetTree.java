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
	
	//i의 root를 구하는 method
	int find(int i) throws IndexOutOfBoundsException{
		for(;L.get(i)>=0;i = L.get(i));
		return i;
	}
	
	//find를 개선 - 단일한 root와 하나의 level에 child를 전부 저장
	int collapsingFind(int i) {
		int root = find(i), node, next;
		//교재 방식
		for(node = i; node != root && L.get(node) != root; node = next) {
			next = L.get(node);
			L.set(node, root);
		}
		return root;
		
		// 내가 생각한 방식
		// 평가 : 메모리 할당방식에서 더 비효율적
//		ArrayList<Integer> temp = new ArrayList<>(size);
//		for(;L.get(i)>=0;i = L.get(i)) temp.addLast(i);
//		for(int w : temp) L.set(w, root);
//		temp.clear();
//		return root;
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
}
