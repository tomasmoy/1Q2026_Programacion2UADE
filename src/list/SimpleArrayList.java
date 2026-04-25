package list;

public class SimpleArrayList<E> implements SimpleList<E> {
	
	private E[] elements;
	private int capacity = 0;
	private int size = 0;
	private static final int DEFAULT_CAPACITY = 4;
	
	@SuppressWarnings("unchecked")
	public SimpleArrayList(int capacity) {
		if (capacity == 0) {
			this.capacity = DEFAULT_CAPACITY;
		} else {
			this.capacity = capacity;
		}
		this.elements = (E[]) new Object[this.capacity];
		this.size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public SimpleArrayList() {
		this.capacity = DEFAULT_CAPACITY;
		this.elements = (E[]) new Object[this.capacity];
		this.size = 0;
	}

	@Override
	public boolean add(E element) {
		validateSize(size+1);
		elements[size] = element; //size -> ultima pos = size-1
		size++;
		return true;	
	} 

	@Override
	public void add(int index, E element) {
		validateAddIndex(index);
		validateSize(size+1);
		
		for(int i=size-1; i>=index; i--) {
			elements[i+1] = elements[i];
		}
		
		elements[index] = element;
		
		size++;
	}

	@Override
	public E remove(int index) {
		validateIndex(index);
		E deletedElement = elements[index]; 
		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i+1];
		}	
		elements[size-1] = null;
		size--;
		return deletedElement;
	}

	@Override
	public boolean remove(Object object) {
		for (int i = 0; i < size; i++) {
			if ((object == null && elements[i] == null) || (object != null && object.equals(elements[i]))) {
				remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object object) {
		for (int i = 0; i < size; i++) {
			if ((object == null && elements[i] == null) || (object != null && object.equals(elements[i]))){
				return true;
			} 
		}
		return false;
	}

	@Override
	public E get(int index) {
		validateIndex(index);
		return elements[index];
	}

	@Override
	public E set(int index, E element) {
		validateIndex(index);
		E lastElement = elements[index];
		elements[index] = element;
		return lastElement;
		
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
	
	private void validateIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private void validateAddIndex(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}

}
