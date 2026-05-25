package treeModule;

public class TreeNode<E> {
	public TreeNode<E> left = null;
	public TreeNode<E> right = null;
	public E value;
	
	public TreeNode(E value) {
		this.value = value;
	}

}
