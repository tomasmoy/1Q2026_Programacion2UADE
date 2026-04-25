package priorityqueue;

public class LinkedPriorityNode<E> {

	public LinkedPriorityNode<E> prev = null;
	public LinkedPriorityNode<E> next = null;
	public E value;
	public int priority;
	
	public LinkedPriorityNode(E newValue, int newPriority) {
		this.value = newValue;
		this.priority = newPriority;
	}

}
