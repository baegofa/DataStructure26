package ds_lecture_26;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>, Iterable<E>{
	protected class Node<E> {
		E item;
		Node<E> next;
		Node(E element, Node<E> next){
			this.item = element;
			this.next = next;
		}
	}
	
	protected Node<E> first;
	protected Node<E> last;
	protected int size = 0;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size()==0);
	}
	
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
		if(i < 0 || i >= n) throw new IndexOutOfBoundsException("Illegal index : " + i);
	}
	
	protected Node<E> node(int i) throws IndexOutOfBoundsException{
		Node<E> ptr = first;
		for(int k =0; k<i;k++) 
			ptr = ptr.next;
		return ptr;
		
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		Node<E> x = node(i);
		return x.item;
	}

	@Override
	public E set(int i, E newValue) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		Node<E> x = node(i);
		E oldValue = x.item;
		x.item = newValue;
		return oldValue;
		
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size+1);
		if(i==0)
			addFirst(e);
		else if(i==size-1)
			addLast(e);
		else {
			Node<E> preNode = node(i-1);
			Node<E> newNode = new Node<>(e, preNode.next);
			preNode.next = newNode;
			size++;
		}
	}

	@Override
	public boolean remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		if(i==0) {
			first = first.next;
			if(first == null)
				last = null;
		}
		else {
			Node<E> preNode = node(i-1);
			Node<E> deleted = preNode.next;
			preNode.next = deleted.next;
			if(deleted == last)
				last = preNode;
		}
		size--;
		return true;
	}

	@Override
	public void addFirst(E e) {
		if(first == null) {
			first = new Node<>(e, null);
			last = first;
			size++;
		}
		else {
			Node<E> addNode = new Node<>(e,first);
			first = addNode;
			size++;
		}
	}

	@Override
	public void addLast(E e) {
		Node<E> x = new Node<E>(e, null);
		if (first == null)
			first = x;
		else
			last.next = x;
		last = x;
		size += 1;
		
	}

	@Override
	public boolean remove(E e) {
		if(first == null) return false;
		
		if(e.equals(first.item)) {
			first = first.next;
			if(first == null) last = null;
			size--;
			return true;
		}
		
		for(Node<E> x = first; x.next != null; x = x.next)
			if(e.equals(x.next.item)) {
				Node<E> deleted = x.next;
				x.next = deleted.next;
				if(deleted == last) last = x;
				size--;
				return true;
			}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}
	
	private class SinglyLinkedListIterator implements Iterator<E>{
		private Node<E> lastReturned, nextNode;
		private int nextIndex;
		
		public SinglyLinkedListIterator() {nextNode = first;}
		
		public boolean hasNext() {return nextIndex < size;}
		
		public E next() {
			if(!hasNext()) throw new NoSuchElementException();
			lastReturned = nextNode;
			nextNode = nextNode.next;
			nextIndex++;
			return lastReturned.item;
		}
	}
	public static void main(String[] arsgs) {
		SinglyLinkedList<Integer> test = new SinglyLinkedList<Integer>();
		test.addFirst(1);
		test.add(0, 18);
		Iterator<Integer> s = test.iterator();
		System.out.println(s.next());
		System.out.println(s.next());

	}
}
