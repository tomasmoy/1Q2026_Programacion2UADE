package dictionary;

public interface SimpleDictionary<K,V> {
	public V put(K key, V value);
	public boolean remove(K key);
	public boolean containsKey(K key);
	public V get(K key);
	public K[] keys();
	public V[] values();
	public int size();
	public boolean isEmpty();
	public void clear();
}
