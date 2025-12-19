/**
 * 
 */

/**
 * This interface provices the method signature for function for Position list management 
 */
public interface IPositionList<T> {
	
	public Position<T> AddAfter(Position<T> node,T element);
	public Position<T> AddBefore(Position<T> node, T element);
	public Position<T> AddFirst(T element);
	public Position<T> AddLast(T element);
	public T removeFirst();
	public T removeAfter(Position<T> node);
	public T removeLast();
	public boolean isEmpty();
	
}
