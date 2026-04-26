package dictionary;

public class SimpleLinkedDictionaryNode<K,V> {

	public K key;
	public V value;
	public SimpleLinkedDictionaryNode<K,V> prev = null;
	public SimpleLinkedDictionaryNode<K,V> next = null;
	
	public SimpleLinkedDictionaryNode(K key, V value) {
		this.key = key;
		this.value = value;
	}

}
