package ds_lecture_26;

import java.util.ArrayList;
import java.util.Comparator;

public class LinkedBinaryTree<E> {
	private Comparator<E> comp;
	private TreeNode<E> root;
	
	public TreeNode<E> addRoot(E e){
		this.root = new TreeNode<>(e);
		return root;
	}
	
	public void addLeft(TreeNode<E> node, E e) {
		node.left = new TreeNode<>(e);
	}
	
	public void addRight(TreeNode<E> node, E e) {
		node.right = new TreeNode<>(e);
	}
	
	public Iterable<E> inorder(){
		ArrayList<E> result = new ArrayList<>();
		inorder(root, result);
		return result;
	}
	
	private void inorder(TreeNode<E> node, ArrayList<E> result) {
		if(node == null)
			return;
		
		inorder(node.left, result);
		result.add(node.element);
		inorder(node.right, result);
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(TreeNode<E> node) {
		if(node==null)
			return 0;
		return size(node.left) + size(node.right) +1;
	}
	
	public int depth() {
		return size(root);
	}
	
	private int depth(TreeNode<E> node) {
		if(node==null)
			return 0;
		return Math.max(depth(node.left), depth(node.right)) +1;
	}
	
	public LinkedBinaryTree<E> swap(){
		LinkedBinaryTree<E> newTree = new LinkedBinaryTree<>();
		newTree.root = swap(root);
		return newTree;
	}
	
	private TreeNode<E> swap(TreeNode<E> node){
		if(node == null)
			return null;
		
		TreeNode<E> newNode = new TreeNode<>(node.element);
		newNode.left = swap(node.right);
		newNode.right = swap(node.left);
		return newNode;
	}
}
