import java.util.Iterator;

public interface IMap<K,V> {
	public V get(K key) ;
	public V put(K key,V value);
	public V remove(K key);
	public boolean isEmpty();
	public int size();
	public Iterator keys();
	public Iterator values();
}
