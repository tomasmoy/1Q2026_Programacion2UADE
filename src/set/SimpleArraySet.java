package set;

public class SimpleArraySet<E> implements SimpleSet<E> {
	
	private int size = 0;
	private int capacity = 0;
	private static final int DEFAULT_CAPACITY = 0;
	private E[] elements;
	
	public SimpleArraySet() {
		this.elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public SimpleArraySet(int capacity) {
		this.elements = (E[]) new Object[capacity];
	}

	@Override
	public boolean add(E element) {
		if (element == null) throw new IllegalArgumentException("El elemento ingresado no puede ser null!");
		if(contains(element)) return false;
		validateSize(size+1);
		elements[size] = element;
		size++;
		return true;
	}

	@Override
	public boolean remove(E element) {
		if (element == null) throw new IllegalArgumentException("El elemento ingresado no puede ser null!");
		for (int i = 0; i < size; i++) {
			if (element.equals(elements[i])) {
				if(size == 1) elements[i] = null;
				else elements[i] = elements[size-1];
				size --;
				return true;
			}
		}
		
		return true;
	}

	@Override
	public boolean contains(E element) {	
		if(element == null) throw new IllegalArgumentException("El elemento a validar no puede ser null!");
		
		for (int i = 0; i < size; i++) {
			if ((element == null && elements[i] == null) || (element != null && element.equals(elements[i]))){
				return true;
			} 
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}

	@Override
	public E[] toArray() {
		E[] result = (E[]) new Object[size];
		for (int i = 0; i < size;i++) {
			result[i] = elements[i];
		}
		return result;
	}

	@Override
	public SimpleSet<E> unionWith(SimpleSet<E> other) {
		if (other == null) throw new IllegalArgumentException();
		SimpleSet <E> result = new SimpleArraySet<E>();
		E[] otherArray = other.toArray();
		
		for (int i = 0; i < size; i++) {
			result.add(elements[i]);
		}
		
		for (int i = 0; i < other.size(); i++) {
			if(!result.contains(otherArray[i])) {
				result.add(otherArray[i]);
			}
		}
		
		return result;
	}

	@Override
	public SimpleSet<E> intersectWith(SimpleSet<E> other) {
		if (other == null) throw new IllegalArgumentException();
		SimpleSet <E> result = new SimpleArraySet<E>();
		
		for (int i = 0; i < size; i++) {
			if (other.contains(elements[i])) {
				result.add(elements[i]);
			}
		}
		
		return result;
	}

	@Override
	public SimpleSet<E> differenceWith(SimpleSet<E> other) {
		if (other == null) throw new IllegalArgumentException();
		SimpleSet <E> result = new SimpleArraySet<E>();
		
		for (int i = 0; i < size; i++) {
			if (!other.contains(elements[i])) {
				result.add(elements[i]);
			}
		}
		
		return result;
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
