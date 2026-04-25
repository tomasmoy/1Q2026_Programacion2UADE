package stack;

import java.util.NoSuchElementException;

import list.LinkedNode;

public class SimpleLinkedStack<E> implements SimpleStack<E> {

	private LinkedNode<E> last = null;
	private int size = 0;

	@Override
	public void push(E element) {
		LinkedNode<E> newNode = new LinkedNode<>(element);
		if(!isEmpty()) {
			newNode.prev = last;
		}
		
		last = newNode;
		size++;
	}

	@Override
	public E pop() {
		validateElementsQty();
		E removedElement = last.value;
		if (size == 1) {
			last = null;
		} else {			
			last = last.prev;
		}
		size--;
		return removedElement;
	}

	@Override
	public E peek() {
		validateElementsQty();
		return last.value;
	}

	@Override
	public void clear() {
		last = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public void validateElementsQty() {
		if (isEmpty()) throw new NoSuchElementException("La Pila está vacía");
	}

}
