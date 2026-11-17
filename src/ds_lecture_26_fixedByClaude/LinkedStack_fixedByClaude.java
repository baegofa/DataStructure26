package ds_lecture_26_fixedByClaude;

// FIX: raw 타입 Stack 대신 Stack<E> 를 구현해 (E) 캐스팅과 Object 반환을 없앴다.
public class LinkedStack_fixedByClaude<E> extends SinglyLinkedList_fixedByClaude<E>
		implements Stack_fixedByClaude<E> {

	public LinkedStack_fixedByClaude() {
		super();
	}

	@Override
	public boolean isFull() {
		return false;	// 연결 리스트는 가득 차지 않는다
	}

	// FIX: 스택의 top 을 리스트의 "맨 앞"으로 두었다.
	// 원본은 맨 뒤를 top 으로 써서 pop 할 때마다 끝 직전 노드를 찾느라 O(n) 이었고,
	// 원소가 하나뿐이면 반복문이 돌지 않아 pop 이 null 을 반환하고 아무것도 지우지 않았다.
	@Override
	public void push(E e) {
		addFirst(e);	// O(1)
	}

	@Override
	public E top() {
		return isEmpty() ? null : first.item;
	}

	@Override
	public E pop() {
		if (isEmpty()) return null;
		E item = first.item;
		remove(0);	// O(1)
		return item;
	}

	public static void main(String[] args) {
		Stack_fixedByClaude<Integer> s = new LinkedStack_fixedByClaude<>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		System.out.println(s.pop());	// 4
		System.out.println(s.top());	// 3
		System.out.println(s.pop());	// 3
		System.out.println(s.pop());	// 2
		System.out.println(s.pop());	// 1  (원본에서는 null 이 나오던 경우)
		System.out.println(s.pop());	// null
		System.out.println(s.isEmpty());	// true
	}
}
