package dictionary;

import java.util.ArrayList;
import java.util.List;

public class SimpleLinkedDictionary<K,V> implements SimpleDictionary<K, V> {

	public SimpleLinkedDictionaryNode<K,V> first = null;
	public SimpleLinkedDictionaryNode<K,V> last = null;
	public int size = 0;
	
	@Override
	public V put(K key, V value) {
		if (key == null || value == null) throw new NullPointerException();
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
		return null;
	}

	@Override
	public boolean remove(K key) {
		// TODO Auto-generated method stub
		size--;
		return false;
	}

	@Override
	public boolean containsKey(K key) {
		if (key == null) throw new NullPointerException();
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
		if (key == null) throw new NullPointerException();
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
		while (current != null) {
			keyArray.add(current.key);
			current = current.next;
		}
	}

	@Override
	public V[] values() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	

}
