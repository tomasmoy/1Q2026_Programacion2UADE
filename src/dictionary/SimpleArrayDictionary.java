package dictionary;

import java.util.NoSuchElementException;

public class SimpleArrayDictionary<K,V> implements SimpleDictionary<K, V> {

	private int size = 0;
	private int capacity = 0;
	private static final int DEFAULT_CAPACITY = 0;
	private K[] keys;
	private V[] values;
	
	public SimpleArrayDictionary() {
		this.capacity = DEFAULT_CAPACITY;
		this.keys = (K[]) new Object[this.capacity];
		this.values = (V[]) new Object[this.capacity];
	}
	
	public SimpleArrayDictionary(int capacity) {
		this.capacity = capacity;
		this.keys = (K[]) new Object[this.capacity];
		this.values = (V[]) new Object[this.capacity];
	}

	@Override
	public V put(K key, V value) {
		if (key == null|| value == null) throw new NullPointerException();
		
		try {
			int index = keyIndex(key);
			V oldValue = values[index];
			values[index] = value;
			return oldValue;
		} catch (Exception e) {
			validateSize(size+1);
			keys[size] = key;
			values[size] = value;
			size++;
			return null;
		}
	}

	@Override
	public boolean remove(K key) {
		if (key == null) throw new NullPointerException();
		
		for (int i = 0; i < size; i++) {
			if (keys[i].equals(key)) {
				shiftLeft(i);
				size--;
				return true;
			}
		}
		
		return false;
		
	}

	@Override
	public boolean containsKey(K key) {
		if (key == null) throw new NullPointerException();
		for (int i = 0; i < size; i++) {
			if (keys[i].equals(key)) return true;
		}
		return false;
	}

	@Override
	public V get(K key) {
		if (key == null) throw new NullPointerException();
		for (int i = 0; i < size; i++) {
			if (keys[i].equals(key)) {
				return values[i];
			}
		}
		return null;
	}

	@Override
	public K[] keys() {
		return keys;
	}

	@Override
	public V[] values() {
		return values;
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
		keys = (K[]) new Object[capacity];
		values = (V[]) new Object[capacity];
		size = 0;
	}
	
	private void shiftLeft(int startingIndex) {
		for (int i = startingIndex; i < size - 1; i++) {
			values[i] = values[i+1];
			keys[i] = keys[i+1];
		}
		values[size-1] = null;
		keys[size-1] = null;
	}
	
	
	private void validateSize(int newSize) {
		if(newSize > capacity) resize();
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		int newCapacity = capacity * 2;
		K[] newKeys = (K[]) new Object[newCapacity];
		V[] newValues = (V[]) new Object[newCapacity];
		
		for (int i = 0; i < size; i++) {
			newKeys[i] = keys[i];
			newValues[i] = values[i];
		}
		
		keys = newKeys;
		capacity = newCapacity;
		values = newValues;
	}

	public int keyIndex(K key) {
		if (key == null) throw new NullPointerException();
		for (int i = 0; i < size; i++) {
			if (keys[i].equals(key)) return i;
		}
		throw new NoSuchElementException();
	}
}
