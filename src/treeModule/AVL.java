package treeModule;

public class AVL<E extends Comparable<E>> extends BST<E>{

	
	@Override
	protected TreeNode<E> insertRecursive(TreeNode<E> current, E value){
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
		else return current;
		return rebalanceNode(current);
	}
	
	@Override
	protected TreeNode<E> removeRecursive(TreeNode<E> current, E value){
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
		return rebalanceNode(current);
	}
	
	private TreeNode<E> rebalanceNode(TreeNode<E> node){
		int bf = getBalanceFactor(node);
		
		//Casos L
		if(bf > 1) {
			//Caso LL
			if(getBalanceFactor(node.left) >= 0) {
				return rotateRight(node);
			} else { //Caso LR
				return rotateLeftRight(node);
			}	
		} // Casos R
		else if (bf < 1) {
			//Caso RR
			if(getBalanceFactor(node.right) <= 0) {
				return rotateLeft(node);
			} else { //Caso RL	
				return rotateRightLeft(node);
			}
		}
		return node;
	}
	
	
	private TreeNode<E> rotateRight(TreeNode<E> y){
		TreeNode<E> x = y.left;
		TreeNode<E> z = x.right;
		
		 x.right = y;
		 y.left = z;
		 
		 return x;
	}
	
	private TreeNode<E> rotateLeft(TreeNode<E> y){
		TreeNode<E> x = y.right;
		TreeNode<E> z = x.left;
		
		 x.left = y;
		 y.right = z;
		 
		 return x;
	}
	
	private TreeNode<E> rotateLeftRight(TreeNode<E> node){
		node.left = rotateLeft(node);
		return rotateRight(node);
	}
	
	private TreeNode<E> rotateRightLeft(TreeNode<E> node){
		node.right = rotateRight(node);
		return rotateLeft(node);
	}
}
