package treeModule;

public class TreeExercise {

	public static void main(String[] args) {
		BST<Integer> tree = new BST();
		
		tree.insert(1);
		
		System.out.println(tree.contains(0));
	}

}
