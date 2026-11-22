package ds_lecture_26;

import java.util.ArrayList;

class Node<K,V>{
	K key;
	V val;
	Node<K,V> left, right, parent;
	
	public Node(K key,V val) {
		this.key = key;
		this.val = val;
	}
}

public class LinkedBinarySearchTree<K extends Comparable<K>,V>{
	protected Node<K,V> root;
	
	protected boolean isLeaf(Node<K,V> x) {
		return x.left == null && x.right == null;
	}
	
	protected Node<K,V> treeSearch(K searchKey){
		if (root == null) return null;
		Node<K,V> x = root;
		
		while(true) {
			int cmp = searchKey.compareTo(x.key);
			
			Node<K,V> next = cmp < 0 ? x.left : x.right;
			
			if(cmp == 0 || next == null) return x;
			x = next;
		}
	}
	
	public V get(K searchKey) {
		if(root == null) return null;
		
		Node<K,V> x = treeSearch(searchKey);
		if(x.key.equals(searchKey))
			return x.val;
		else
			return null;
	}
	
	public void put(K key, V val) {
		
		if(root == null) {
			root = new Node<K,V>(key,val);
			return;
		}
		Node<K,V> x = treeSearch(key);
		int cmp = key.compareTo(x.key);
		
		if(cmp == 0)
			x.val = val;
		
		else {
			Node<K,V> newNode = new Node<K,V>(key,val);
			if(cmp < 0) 
				x.left = newNode;
			else 
				x.right = newNode;
			newNode.parent = x;
		}
	}
	
	public void delete(K key) {
	    Node<K,V> x = treeSearch(key);
	    if (x == null || x.key.compareTo(key) != 0) return;

	    if (x.left != null && x.right != null) {   // 자식이 둘이면 successor 내용 복사
	        Node<K,V> s = inSuc(x);
	        x.key = s.key;
	        x.val = s.val;
	        x = s;                                 // 자식이 최대 1개인 노드를 제거
	    }

	    Node<K,V> child = x.left != null ? x.left : x.right;
	    if (child != null) child.parent = x.parent;

	    if (x.parent == null) root = child;
	    else if (x == x.parent.left) x.parent.left = child;
	    else x.parent.right = child;
	}
	
	private void inorder(Node<K,V> x, ArrayList<Node<K,V>> result) {
		if(x == null)
			return;
		else {
			if(x.left != null)
				inorder(x.left, result);
			
			result.add(x);
			
			if(x.right != null)
				inorder(x.right, result);
		}
	}
	protected Node<K,V> inSuc(Node<K,V> x) {
		if(isLeaf(x)) return null; // have to fix
		if(x.right == null && x.left != null) return null;
		for(x = x.right; x.left != null; x = x.left);
		return x;
	}
	//have to fix
	protected Node<K,V> inPre(Node<K,V> x) {
		ArrayList<Node<K,V>> result = new ArrayList<Node<K,V>>();
		inorder(x, result);
		int temp = result.indexOf(x);
		return result.get(temp-1);
	}
}
