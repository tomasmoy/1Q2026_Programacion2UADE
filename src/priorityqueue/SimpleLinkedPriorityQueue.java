package priorityqueue;

import java.util.NoSuchElementException;

public class SimpleLinkedPriorityQueue<E> implements SimplePriorityQueue<E> {
	
	public LinkedPriorityNode<E> first;
	public LinkedPriorityNode<E> last;
	private int size = 0;
	
	@Override
	public void enqueue(E element, int priority) {
		if (element == null) throw new IllegalArgumentException("El elemento no puede ser null!");
		LinkedPriorityNode<E> newElement = new LinkedPriorityNode<E>(element, priority); 
		
		if(isEmpty()) {
			first = newElement;
			last = newElement;
			size++;
			return;
		} 
		
		LinkedPriorityNode<E> currentNode = last;
		
		while(currentNode.prev != null && priority < currentNode.priority) {
			currentNode = currentNode.prev;
		}
		
		if(currentNode == first) {
			newElement.next = currentNode;
			currentNode.prev = newElement;
			first = newElement;
		} else if (currentNode == last) {
				newElement.prev = currentNode;
				currentNode.next = newElement;
				last = newElement;
		} else {
			newElement.prev = currentNode;
			newElement.next = currentNode.next;
			currentNode.next.prev = newElement;
			currentNode.next = newElement;
			
		}
		size++;			
	}

	@Override
	public E dequeue() {
		if (isEmpty()) throw new NoSuchElementException("La Cola Esta Vacía");
		LinkedPriorityNode<E> elementDeQueued = last;
		last = last.prev;
		if (last != null) last.next = null;
		else first = null;
		size--;
		return elementDeQueued.value;
	}

	@Override
	public E peek() {
		if (isEmpty()) throw new NoSuchElementException("La Cola Esta Vacía");
		return first.value;
	}

	@Override
	public int getHighestPriority() {
		return first.priority;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

}
