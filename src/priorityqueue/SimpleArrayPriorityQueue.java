package priorityqueue;

import java.util.NoSuchElementException;

public class SimpleArrayPriorityQueue<E> implements SimplePriorityQueue<E> {
	
	private int size = 0;
	private int capacity = 0;
	private E[] elements;
	private int[] priorities;
	private static final int DEFAULT_CAPACITY = 4;
	
	public SimpleArrayPriorityQueue() {
		this.capacity = DEFAULT_CAPACITY;
		this.elements = (E[]) new Object[DEFAULT_CAPACITY];
		this.priorities = new int[DEFAULT_CAPACITY];
	}
	
	public SimpleArrayPriorityQueue(int capacity) {
		this.capacity = capacity;
		this.elements = (E[]) new Object[capacity];
		this.priorities = new int[capacity];
	}

	@Override
	public void enqueue(E element, int priority) {
		if (element == null) throw new IllegalArgumentException("El elemento no puede ser null!");
		validateSize(size+1);
		int insertIndex = size;
		
		for (int i = insertIndex; i > 0 && priority < priorities[i] ; i--) {
			elements[i] = elements[i-1];
			priorities[i] = priorities[i-1];
			insertIndex = i - 1;
		}
		elements[insertIndex] = element;
		priorities[insertIndex] = priority;
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) throw new NoSuchElementException();
		E elementDeQueued = elements[0];
		elements[0] = null;
		shiftLeft(0);
		size--;
		return elementDeQueued;
	}

	@Override
	public E peek() {
		if (isEmpty()) throw new NoSuchElementException("La cola esta vacía");
		return elements[0];
	}

	@Override
	public int getHighestPriority() {
		if (isEmpty()) throw new NoSuchElementException("La cola esta vacía");
		return priorities[0];
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
		capacity = DEFAULT_CAPACITY;
		elements = (E[]) new Object[capacity];
		priorities = new int[capacity];
		size = 0;
	}
	
	public E[] toArray() {
		E[] result = (E[]) new CaseObject[size];
		for (int i = 0; i < size;i++) {
			result[i] = elements[i];
		}
		return result;
	} 
	
	private void shiftLeft(int startingIndex) {
		for (int i = startingIndex; i < size - 1; i++) {
			elements[i] = elements[i+1];
			priorities[i] = priorities[i+1];
		}
		elements[size-1] = null;
	}
	
	
	private void validateSize(int newSize) {
		if(newSize > capacity) resize();
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		int newCapacity = capacity * 2;
		E[] newElements = (E[]) new Object[newCapacity];
		int[] newPriorities = new int[newCapacity];
		
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
			newPriorities[i] = priorities[i];
		}
		
		elements = newElements;
		capacity = newCapacity;
		priorities = newPriorities;
	}
	

}
