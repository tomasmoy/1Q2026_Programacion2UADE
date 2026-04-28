package stack;

import java.util.NoSuchElementException;

public class SimpleArrayStack<E> implements SimpleStack<E> {
	
	private E[] elements;
	private int capacity = 0;
	private int size = 0;
	private static final int DEFAULT_CAPACITY = 4;
	
		
	public SimpleArrayStack() {
		this.capacity = DEFAULT_CAPACITY;
		this.elements = (E[]) new Object[this.capacity];
	}

	@Override
	public void push(E element) {
		validateSize(size+1);
		elements[size] = element;
		size++;
	}

	@Override
	public E pop() {
		if (isEmpty()) throw new NoSuchElementException("La Pila está vacía");
		E element = elements[size-1];
		elements[size-1] = null;
		size--;
		return element;
	}

	@Override
	public E peek() {
		if (isEmpty()) throw new NoSuchElementException("La Pila está vacía");
		return elements[size-1];
	}

	@Override
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[i] = null;
		}
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
	
	private void validateSize(int newSize) {
		if(newSize > capacity) resize();
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		int newCapacity = capacity * 2;
		E[] newElements = (E[]) new Object[newCapacity];
		
		for (int i = 0; i < size; i++) {
			E copyElement = elements[i];
			newElements[i] = copyElement;
		}
		
		elements = newElements;
		capacity = newCapacity;
		
	}

}
