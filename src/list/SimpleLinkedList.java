package list;

public class SimpleLinkedList<E> implements SimpleList<E> {
	private LinkedNode<E> first = null;
	private LinkedNode<E> last = null;
	private int size = 0;

	@Override
	public boolean add(E element) {
		LinkedNode<E> newNode = new LinkedNode<E>(element);
		if (size == 0){
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
	public void add(int index, E element) {
		if (index == size) {
			add(element); 
			return;
		}
		
		validateIndex(index);
		
		LinkedNode<E> newNode = new LinkedNode<E>(element);
		
		if (index == 0) {
			newNode.next = first;
			first.prev = newNode;
			first = newNode;
		} else {
			LinkedNode<E> indexNode = getNodeByIndex(index);
			LinkedNode<E> prevNode = indexNode.prev;
			
			newNode.prev = prevNode;
			newNode.next = indexNode;
			prevNode.next = newNode;
			indexNode.prev = newNode;

		}
		
		size++;
	}

	@Override
	public E remove(int index) {
		
		validateIndex(index);
		
		LinkedNode<E> actualNode = getNodeByIndex(index);
		LinkedNode<E> prevActualNode = actualNode.prev;
		
		if (index == 0) { // Quiero borrar el primer elemento
			first = actualNode.next;
			actualNode.prev = null;
		}
		
		else if (size == 1) { // La lista tiene solo 1 elemento, la lista queda vacía
			first = null;
			last = null;
		}

		else if (index == size - 1) { // Quiero eliminar el ultimo elemento
			last = actualNode.prev;
			prevActualNode.next = null;
		}
		
		else { // Caso que quiera eliminar en el medio
			prevActualNode.next = actualNode.next;
			actualNode.next.prev = actualNode.prev;		
		}
		
		size--;
		return actualNode.value;
	}

	@Override
	public boolean remove(Object object) {
		
		LinkedNode<E> currentNode = first; 
				
		while (currentNode != null) {
			
			if ((object == null && currentNode.value == null) || (object != null && object.equals(currentNode.value))){
				
				if (size == 1) {
					clear();
					return true;
				}
				
				else if (currentNode == first) {
					first = currentNode.next;
					currentNode.next.prev = null;
				}
				
				else if (currentNode == last) {
					last = currentNode.prev;
					currentNode.prev.next = null;
				}
				
				else {
					currentNode.prev.next = currentNode.next;
					currentNode.next.prev = currentNode.prev;
				}
				size--;
				return true;
			}
		currentNode = currentNode.next;
		}
		return false;
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
	public boolean contains(Object object) {
		
		if (isEmpty()) return false;
		
		LinkedNode<E> currentNode = first; 
		
		while (currentNode != null) {
			if ((object == null && currentNode.value == null) || (object != null && object.equals(currentNode.value))) return true;
			currentNode = currentNode.next;
		}
		
		return false;
		
	}

	@Override
	public E get(int index) {
		validateIndex(index);
		E indexNodeValue = getNodeByIndex(index).value;
		return indexNodeValue;
	}

	@Override
	public E set(int index, E element) {
		validateIndex(index);
		
		LinkedNode <E> actualNode = getNodeByIndex(index);
		E latestElement = actualNode.value;
		
		actualNode.value = element;
		
		
		return latestElement;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	private void validateIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private LinkedNode<E> getNodeByIndex(int index) {
		
		validateIndex(index);
		
		if (index < size - index) {
			int counter = 0;
			LinkedNode<E> actual = first;
			while(counter < index) {
				actual = actual.next;
				counter++;
			}
			return actual;
		} else {
			int counter = size - 1;
			LinkedNode<E> actual = last;
			while(counter > index) {
				actual = actual.prev;
				counter--;
			}
			return actual;			
		}	
	}
	
}
