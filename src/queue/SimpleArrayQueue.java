package queue;

import java.util.NoSuchElementException;

public class SimpleArrayQueue<E> implements SimpleQueue<E> {
	
	private E[] elements;
	private int capacity = 0;
	private int size = 0;
	static final int DEFAULT_CAPACITY = 4;
	
	public SimpleArrayQueue() {
		this.capacity = DEFAULT_CAPACITY;
		this.elements = (E[]) new Object[this.capacity];
	}

	@Override
	public void enqueue(E element) {
		validateSize(size+1);
		elements[size] = element;
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) throw new NoSuchElementException("La Cola está vacía");
		E element = elements[0];
		elements[0] = null;
		for (int i = 0; i < size - 1; i++) {
			elements[i] = elements[i+1];
		}
		size--;
		return element;
	}

	@Override
	public E peek() {
		if (isEmpty()) throw new NoSuchElementException("La Cola está vacía");
		return elements[0];
	}

	@Override
	public void clear() {
		for(int i = 0; i<size; i++) {
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
