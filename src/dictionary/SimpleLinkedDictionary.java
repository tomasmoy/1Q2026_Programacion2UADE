package dictionary;

import java.util.ArrayList;
import java.util.List;

public class SimpleLinkedDictionary<K,V> implements SimpleDictionary<K, V> {

	public SimpleLinkedDictionaryNode<K,V> first = null;
	public SimpleLinkedDictionaryNode<K,V> last = null;
	public int size = 0;
	
	@Override
	public V put(K key, V value) {
		if (key == null || value == null) throw new NullPointerException("Los valores no pueden ser null.");
		SimpleLinkedDictionaryNode<K,V> newPair = new SimpleLinkedDictionaryNode<K,V>(key,value); 
		
		if (first == null) {
			first = newPair;
			last = newPair;
			size++;
			return null;
		}
		
		SimpleLinkedDictionaryNode<K,V> current = first;
		while(current != null) {
			if (current.key.equals(key)){
				V oldValue = current.value;
				current.value = value;
				return oldValue;
			}
			current = current.next;
		}
		last.next = newPair;
		last = newPair;
		size++;
		return null;
	}

	@Override
	public boolean remove(K key) {
		if (key == null) throw new NullPointerException("La clave no puede ser null.");
		SimpleLinkedDictionaryNode<K,V> current = first;
		if (size == 0) return false;

		while (current != null) {
			if (current.key.equals(key)) {
				if (size == 1) {
					first = null;
					last = null;
				}
				
				else if (current == first) {
					first = current.next;
					first.prev = null;
				}
				
				else if (current == last) {
					last = current.prev;
					last.next = null;
				}
				
				else {
					current.prev.next = current.next;
					current.next.prev = current.prev;
				}
				
				size--;
				return true;
			}
			
			current = current.next;
		}
		return false;	

	}

	@Override
	public boolean containsKey(K key) {
		if (key == null) throw new NullPointerException("La clave no puede ser null.");
		SimpleLinkedDictionaryNode<K,V> current = first;
		while (current != null) {
			if (current.key.equals(key)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public V get(K key) {
		if (key == null) throw new NullPointerException("La clave no puede ser null.");
		SimpleLinkedDictionaryNode<K,V> current = first;
		while (current != null) {
			if (current.key.equals(key)) {
				return current.value;
			}
			current = current.next;
		}
		return null;
	}

	@Override
	public K[] keys() {
		SimpleLinkedDictionaryNode<K,V> current = first;
		K[] keyArray = (K[]) new Object[size];
		int i = 0;
		while (current != null) {
			keyArray[i++] = current.key;
			current = current.next;
		}
		return keyArray;
	}

	@Override
	public V[] values() {
		SimpleLinkedDictionaryNode<K,V> current = first;
		V[] valuesArray = (V[]) new Object[size];
		int i = 0;
		while (current != null) {
			valuesArray[i++] = current.value;
			current = current.next;
		}
		return valuesArray;
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
