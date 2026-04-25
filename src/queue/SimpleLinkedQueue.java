package queue;

import java.util.NoSuchElementException;

import list.LinkedNode;

public class SimpleLinkedQueue<E> implements SimpleQueue<E> {

	private LinkedNode<E> last = null;
	private LinkedNode<E> first = null;
	private int size = 0;
	
	@Override
	public void enqueue(E element) {
		LinkedNode<E> newNode = new LinkedNode<>(element);
		
		if (isEmpty()) {
			first = newNode;
			last = newNode;
		} else {
			last.next = newNode;
			last = newNode;
		}
		size++;

	}

	@Override
	public E dequeue() {
		validateElementsQty();
		E dequeuedElement = first.value; 
		
		if(size == 1) {
			first = null;
			last = null;
		} else {
			first = first.next;
		}
		size--;
		return dequeuedElement;
	}

	@Override
	public E peek() {
		validateElementsQty();
		return first.value;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public void validateElementsQty() {
		if (isEmpty()) throw new NoSuchElementException("La Cola está vacía");
	}
	
}
