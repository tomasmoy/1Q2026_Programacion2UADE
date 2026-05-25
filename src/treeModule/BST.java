package treeModule;

import list.SimpleArrayList;

public class BST<E extends Comparable<E>> {
	public TreeNode<E> root = null;
	private int size = 0;

	public void insert(E value) {
		root = insertRecursive(root,value);
	}
	
	private TreeNode<E> insertRecursive(TreeNode<E> current, E value){
		if (current == null) {
			size++;
			return new TreeNode<E>(value);
		}
		
		int compareResult = value.compareTo(current.value);
		
		if(compareResult > 0) {
			current.right = insertRecursive(current.right,value);
		} else if (compareResult < 0){
			current.left = insertRecursive(current.left,value);
		}
		
		return current;
	}
	
	public boolean contains(E value) {
		return containsRecursive(root, value);
	}
	
	private boolean containsRecursive(TreeNode<E> current, E value) {
		if (current == null) {
			return false;
		}
		
		if (current.value.equals(value)) {
			return true;
		}
		
		int compareRes = value.compareTo(current.value);
		
		if (compareRes > 0) {
			return containsRecursive(current.right,value);
		} else {
			return containsRecursive(current.left,value);
		}
			
	}
	
	public void remove(E value) {
		root = removeRecursive(root, value);
	}
	
	private TreeNode<E> removeRecursive(TreeNode<E> current, E value){
		if (current == null) return null;
		
		int compareResult = value.compareTo(current.value);
		
		if (compareResult == 0) { // Encontré el valor
			if (current.left == null && current.right == null) { // Leaf
				size--;
				return null;
			}
			else if (current.left == null) { // Tiene 1 hijo a la izquierda
				size--;
				return current.right;
			}
			else if (current.right == null) { // Tiene 1 hijo a la derecha
				size--;
				return current.left;
			} 
			//Caso 3 -> Buscamos el minimo de la derecha
			TreeNode<E> succesor = findSuccesor(current.right);
			//Reemplazo el valor del nodo actual con el valor del sucesor.
			current.value = succesor.value;
			//Elimino el nodo sucesor (Para no duplicar)
			current.right = removeRecursive(current.right, succesor.value);
			
		}
		
		else if (compareResult < 0){
			current.left = removeRecursive(current.left,value);
		}
		else if (compareResult > 0) {
			current.right = removeRecursive(current.right,value);
		}
		return current;
	}
	
	private TreeNode<E> findSuccesor(TreeNode<E> current) {
		while(current.left != null) {
			current = current.left;
		}
		return current;
	}
	
	//DFS
	public SimpleArrayList<E> preOrder(TreeNode<E> root) {
		SimpleArrayList<E> result = new SimpleArrayList<E>();
		preOrderRec(root, result);
		return result;
	}
	
	private void preOrderRec(TreeNode<E> current, SimpleArrayList<E> result) {
		if (current == null) return;
		result.add(current.value);
		preOrderRec(current.left,result);
		preOrderRec(current.right,result);
	}
	
	public SimpleArrayList<E> inOrder(){
		SimpleArrayList<E> result = new SimpleArrayList<E>();
		inOrderRec(root, result);
		return result;
	}
	
	private void inOrderRec(TreeNode<E> current, SimpleArrayList<E> result) {
		if (current == null) return;
		inOrderRec(current.left,result);
		result.add(current.value);
		inOrderRec(current.right,result);
	}
	
	public SimpleArrayList<E> postOrder(TreeNode<E> root){
		SimpleArrayList<E> result = new SimpleArrayList<E>();
		postOrderRec(root, result);
		return result;
	}
	
	private void postOrderRec(TreeNode<E> current, SimpleArrayList<E> result) {
		if (current == null) return;
		postOrderRec(current.left,result);
		postOrderRec(current.right,result);
		result.add(current.value);
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public int getSize() {
		return size;
	}
	
}
