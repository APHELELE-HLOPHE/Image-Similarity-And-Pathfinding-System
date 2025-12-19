/**
 * 
 */



/**
 * 
 */
public class Node<T> implements Position<T>{
	Node<T> next;
	Node<T> prev;
	T element;
	
	public Node(Node<T> next,Node<T> prev,T element) {
		this.next = next;
		this.prev = prev;
		this.element = element;
	}

	/**
	 * @return the next
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * @return the prev
	 */
	public Node<T> getPrev() {
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	/**
	 * @return the element
	 */
	public T getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(T element) {
		this.element = element;
	}
	
	
}
