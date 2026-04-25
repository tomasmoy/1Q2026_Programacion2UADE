package set;

public interface SimpleSet<E> {
	public boolean add(E element);
	public boolean remove(E element);
	public boolean contains(E element);
	public int size();
	public boolean isEmpty();
	public void clear();
	public E[] toArray();
	public SimpleSet<E> unionWith(SimpleSet<E> other);
	public SimpleSet<E> intersectWith(SimpleSet<E> other);
	public SimpleSet<E> differenceWith(SimpleSet<E> other);
}
