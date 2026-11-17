package ds_lecture_26;

import java.util.Iterator;

public class CircularListQueue<E> extends CircularLinkedList<E> implements Queue<E>{

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		addLast(e);
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty() == true) return null;
		else {
			Iterator<E> dequeueIterator = iterator();
			if(dequeueIterator.hasNext()==false) return null;
			else {
				E returnValue = dequeueIterator.next();
				remove(0);
				return returnValue;
			}
		}
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub
		return last.next.item;
	}
	public static void main(String[] args) {
		CircularListQueue<Integer> test2 = new CircularListQueue<>();
		test2.enqueue(1);
		test2.enqueue(2);
		test2.enqueue(3);
		System.out.println(test2.dequeue());
		System.out.println(test2.dequeue());
		test2.enqueue(4);
		test2.enqueue(5);
		test2.remove(2);
		System.out.println(test2.dequeue());
		System.out.println(test2.dequeue());
		System.out.println(test2.dequeue());
	}
}
