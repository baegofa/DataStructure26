package ds_lecture_26;

import java.util.Iterator;

import ds_lecture_26.SinglyLinkedList.Node;

import java.util.*;

public class CircularLinkedList<E> extends SinglyLinkedList<E>{
	
	@Override
	public void addFirst(E e) {
		if (last != null) {
			Node<E> newNode = new Node<>(e, last.next);
			last.next = newNode;
		}
		else {
			Node<E> newNode = new Node<>(e,null);
			newNode.next = newNode; //자기 순환
			last = newNode;
		}
		size++;
	}
	
	//addFirst와 addLast의 차이는 last = newNode의 위치이다.
	//addFirst는 null상황에서만 작동하고 addLast는 if-else문이 작동하고 나서 작동한다.
	
	@Override
	public void addLast(E e) {
		Node<E> newNode;
		if (last != null) {
			newNode = new Node<>(e, last.next);
			last.next = newNode;
		}
		else {
			newNode = new Node<>(e,null);
			newNode.next = newNode; //자기 순환
		}
		last = newNode;
		size++;
	}
	
	@Override
	//Warning : 여기서의 index input은 0으로 시작하는 index임을 가정함.
	public boolean remove(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size);
		if(index==0) {
			last.next = last.next.next;
			size--;
			return true;
		}
		else {
			Node<E> tempNode = last.next;
			for(int i=0;i<index-1;i++) {
				tempNode = tempNode.next;
			}
			tempNode.next = tempNode.next.next;
			if(index == size-1) last = tempNode;
			size--;
			return true;
		}
	}
	
	// CircularLinkedList
	@Override
	public Iterator<E> iterator() { return new CircularIterator(); }

	private class CircularIterator implements Iterator<E> {
	    private Node<E> nextNode = (last == null) ? null : last.next;
	    private int count = 0;
	    public boolean hasNext() { return count < size; }   // null 이 아니라 횟수로 종료
	    public E next() {
	        if (!hasNext()) throw new NoSuchElementException();
	        E item = nextNode.item;
	        nextNode = nextNode.next;
	        count++;
	        return item;
	    }
	}
	
	public static void main(String[] args) {
		CircularLinkedList<Integer> te = new CircularLinkedList<Integer>();
		te.addFirst(1);
		te.addFirst(3);
		te.addLast(2);
		System.out.println(te.isEmpty());
		Iterator<Integer> s = te.iterator();
		System.out.println(s.hasNext());
		System.out.println(s.next());
		System.out.println(s.next());
		System.out.println(s.next());
	}
	
}
