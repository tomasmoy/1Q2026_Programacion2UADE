package set;

import list.LinkedNode;

public class SimpleLinkedSet<E> implements SimpleSet<E> {

	private LinkedNode<E> last = null;
	private LinkedNode<E> first = null;
	private int size = 0;

	@Override
	public boolean add(E element) {
		if (element == null) throw new IllegalArgumentException("El elemento ingresado no puede ser null!");
		if (contains(element)) return false;
		LinkedNode<E> newNode = new LinkedNode<>(element);
		if (size == 0) {
			first = newNode;
		} else {
			newNode.prev = last;
			last.next = newNode;
		}
		last = newNode;
		size++;
		return true;
	}

	@Override
	public boolean remove(E element) {
		if (element == null) throw new IllegalArgumentException("El elemento ingresado no puede ser null!");
		if (!contains(element)) return false;
		
		LinkedNode<E> current = first;
		
		while (current != null) {
			if (element.equals(current.value)) {
				if (size == 1) {
					clear();
					return true;
				}
				if (current.prev != null) {
				    current.prev.next = current.next;
				} else {
				    first = current.next;
				}
				if (current.next != null) {
				    current.next.prev = current.prev;
				} else {
				    last = current.prev;
				}
				size--;
				return true;
			}				
			current = current.next;
		}
		return false;
	}

	@Override
	public boolean contains(E element) {
		if (element == null) throw new IllegalArgumentException("El elemento ingresado no puede ser null!");
		LinkedNode<E> current = first;
		
		while (current != null) {
			if(element.equals(current.value)) return true;
			current = current.next;
		}
		return false;
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

	@Override
	public E[] toArray() {
		LinkedNode<E> current = first;
		E[] result = (E[]) new Object[size];
		int counter = 0;
		while (current != null) {
			result[counter] = current.value;
			counter++;
			current = current.next;
		}
		return result;
	}

	@Override
	public SimpleSet<E> unionWith(SimpleSet<E> other) {
		if (other == null) throw new IllegalArgumentException();
		SimpleSet <E> result = new SimpleLinkedSet<E>();
		E[] otherArray = other.toArray();
		
		LinkedNode<E> current = first;
		
		while (current != null) {
			result.add(current.value);
			current = current.next;
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
		SimpleSet <E> result = new SimpleLinkedSet<E>();
		LinkedNode<E> current = first;
		while (current != null) {
			if (other.contains(current.value)) {
				result.add(current.value);
			}
			current = current.next;
		}
		
		return result;
	}

	@Override
	public SimpleSet<E> differenceWith(SimpleSet<E> other) {
		if (other == null) throw new IllegalArgumentException();
		SimpleSet <E> result = new SimpleLinkedSet<E>();
		LinkedNode<E> current = first;
		while (current != null) {
			if (!other.contains(current.value)) {
				result.add(current.value);
			}
			current = current.next;				
		}
		
		return result;
	}
	

}
