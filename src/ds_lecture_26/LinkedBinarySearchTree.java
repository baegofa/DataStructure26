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
	
	// replaceNode의 left는 deleteNode의 insucc이므로 무조건 null이라 right를 parent의 left에 연결
	public void delete(K key) {
		Node<K,V> deleteNode = treeSearch(key), replaceNode = inSuc(deleteNode);
		if(root == null) return;
		if(key == root.key) return;
		
		replaceNode.parent.left = replaceNode.right;
		
		replaceNode.parent = deleteNode.parent;
		replaceNode.left = deleteNode.left;
		replaceNode.right = deleteNode.right;
		
		if(deleteNode.parent.left == deleteNode)
			deleteNode.parent.left = replaceNode;
		else
			deleteNode.parent.right = replaceNode;
			
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
		for(x = x.right; x.left != null; x = x.left);
		return x;
	}
	protected Node<K,V> inPre(Node<K,V> x) {
		ArrayList<Node<K,V>> result = new ArrayList<Node<K,V>>();
		inorder(x, result);
		int temp = result.indexOf(x);
		return result.get(temp-1);
	}
}
