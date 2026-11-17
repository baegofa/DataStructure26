package ds_lecture_26_fixedByClaude;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList_fixedByClaude<E> implements List_fixedByClaude<E>, Iterable<E> {
	// FIX: static 중첩 클래스로 바꿨다. 원본은 내부 클래스가 바깥의 E 를 가리는 Node<E> 를 다시 선언했다.
	protected static class Node<E> {
		E item;
		Node<E> next;
		Node(E element, Node<E> next) {
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
		return size == 0;
	}

	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n)
			throw new IndexOutOfBoundsException("Illegal index : " + i);
	}

	protected Node<E> node(int i) {
		Node<E> ptr = first;
		for (int k = 0; k < i; k++)
			ptr = ptr.next;
		return ptr;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return node(i).item;
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
		checkIndex(i, size + 1);
		if (i == 0)
			addFirst(e);
		else if (i == size)	// FIX: 원본은 i == size-1 이라 마지막 원소 "앞"에 넣어야 할 때 맨 뒤에 넣었고,
			addLast(e);	//      i == size 일 때는 else 로 빠져 last 가 갱신되지 않았다
		else {
			Node<E> preNode = node(i - 1);
			preNode.next = new Node<>(e, preNode.next);
			size++;
		}
	}

	@Override
	public boolean remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		if (i == 0) {
			first = first.next;
			if (first == null)
				last = null;
		}
		else {
			Node<E> preNode = node(i - 1);
			Node<E> deleted = preNode.next;
			preNode.next = deleted.next;
			if (deleted == last)
				last = preNode;
		}
		size--;
		return true;
	}

	@Override
	public void addFirst(E e) {
		first = new Node<>(e, first);
		if (last == null)
			last = first;
		size++;
	}

	@Override
	public void addLast(E e) {
		Node<E> addNode = new Node<>(e, null);
		if (first == null)
			first = addNode;
		else
			last.next = addNode;
		last = addNode;
		size++;
	}

	@Override
	public boolean remove(E e) {
		if (first == null) return false;

		// FIX: e 가 null 이어도 NullPointerException 이 나지 않도록 Objects.equals 사용
		if (Objects.equals(e, first.item)) {
			first = first.next;
			if (first == null) last = null;
			size--;
			return true;
		}

		for (Node<E> x = first; x.next != null; x = x.next)
			if (Objects.equals(e, x.next.item)) {
				Node<E> deleted = x.next;
				x.next = deleted.next;
				if (deleted == last) last = x;
				size--;
				return true;
			}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}

	private class SinglyLinkedListIterator implements Iterator<E> {
		private Node<E> nextNode = first;

		@Override
		public boolean hasNext() { return nextNode != null; }

		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			E item = nextNode.item;
			nextNode = nextNode.next;
			return item;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (Node<E> x = first; x != null; x = x.next) {
			sb.append(x.item);
			if (x.next != null) sb.append(", ");
		}
		return sb.append("]").toString();
	}

	public static void main(String[] args) {
		SinglyLinkedList_fixedByClaude<Integer> test = new SinglyLinkedList_fixedByClaude<>();
		test.addFirst(1);
		test.add(0, 18);
		test.add(2, 30);	// 맨 뒤에 추가 (원본에서는 last 가 갱신되지 않던 경우)
		test.add(2, 20);	// 마지막 원소 앞에 추가 (원본에서는 맨 뒤에 붙던 경우)
		test.addLast(40);
		System.out.println(test);	// [18, 1, 20, 30, 40]
		for (int x : test)
			System.out.print(x + " ");
		System.out.println();
		test.remove(Integer.valueOf(40));
		test.remove(0);
		System.out.println(test + ", size=" + test.size());	// [1, 20, 30], size=3
	}
}
