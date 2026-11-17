package ds_lecture_26;

public class TreeNode <E>{
	E element;
	TreeNode<E> left, right;
	
	public TreeNode(E element) {
		this.element = element;
	}
	
	@Override
	public String toString() {
		return "TreeNode["+element+"]";
	}
	
	
	
	
	
}
