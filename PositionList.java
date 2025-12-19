/**
 * 
 */

import java.util.Iterator;

/**
 * 
 */
public class PositionList<T> implements IPositionList<T>,Iterable<T>{
	private Node<T> header;
	private Node<T> trailer;
	private int size;
	
	public PositionList() {
		this.header = new Node<>(null, null, null);
		this.trailer = new Node<>(null,header,null);
		header.setNext(trailer);
		this.size = 0;
	}
	
	public T first(){
		return header.next.element;
	}
	
	public T last() {
		return trailer.prev.element;
	}
	
	public Node<T> checkPosition(Position<T> p){
	    if(!(p instanceof Node<T>)) 
	        throw new IllegalArgumentException("Incorrect argument!");
	    Node<T> n = (Node<T>)p;
	    if(n.getNext() == null || n.getPrev() == null) {
	        if (n == header || n == trailer) {
	            // Allow header and trailer to be treated as valid positions
	            return n;
	        }
	        throw new IllegalArgumentException("Incorrect argument!");
	    }
	    return n;
	}


	@Override
	public Position<T> AddAfter(Position<T> p, T element) {
	    Node<T> node = checkPosition(p);
	    Node<T> nextNode = node.getNext(); // cache before overwriting
	    Node<T> newNode = new Node<>(nextNode, node, element);
	    node.setNext(newNode);
	    nextNode.setPrev(newNode);
	    size++;
	    return newNode;
	}


	@Override
	public Position<T> AddBefore(Position<T> p, T element) {
		// TODO Auto-generated method stub
		Node<T> node = checkPosition(p);
		return AddAfter(node.getPrev(),element);
		
	}

	@Override
	public Position<T> AddFirst(T element) {
		// TODO Auto-generated method stub
		Node<T> newNode = new Node<>(header.getNext(),header,element);
		header.getNext().setPrev(newNode);
		header.setNext(newNode);
		size++;
		return newNode;
	}

	@Override
	public Position<T> AddLast(T element) {
		// TODO Auto-generated method stub
		return AddAfter(trailer.getPrev(),element);
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		Node<T> remNode = header.getNext();
		header.setNext(remNode.getNext());
		remNode.getNext().setPrev(header);
		remNode.setNext(null);
		remNode.setPrev(null);
		size--;
		return remNode.getElement();
	}

	@Override
	public T removeAfter(Position<T> p ) {
		// TODO Auto-generated method stub
		Node<T> node = checkPosition(p);
		Node<T> remNode = node.getNext();
		node.setNext(remNode.getNext());
		remNode.getNext().setPrev(node);
		remNode.setNext(null);
		remNode.setPrev(null);
		size--;
		return remNode.getElement();
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		Node<T> remNode = trailer.getPrev();
		remNode.getPrev().setNext(trailer);
		trailer.setPrev(remNode.getPrev());
		remNode.setNext(null);
		remNode.setPrev(null);
		size--;
		return remNode.getElement();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new PositionListIterator<>();
	}
	
	private class PositionListIterator<T> implements Iterator<T>{
		private Node<T> Current = (Node<T>) header.getNext();
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			
			return Current != trailer;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(hasNext()) {
				T element = Current.getElement();
				Current = Current.getNext();
				return element;
			}
			return null;
		}
		
	}

	public void remove(T element) {
		// TODO Auto-generated method stub
		Node<T> current = header.next;
		
		while(current != null) {
			if(current.getElement().equals(element)) {
				current.getPrev().setNext(current.getNext());
				current.getNext().setPrev(current.getPrev());
				current.setNext(null);
				current.setPrev(null);
			}
			current = current.next;
		}
		
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	
}
