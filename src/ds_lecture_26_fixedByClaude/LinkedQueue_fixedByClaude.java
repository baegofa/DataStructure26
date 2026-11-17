package ds_lecture_26_fixedByClaude;

// FIX: raw 타입 Queue 대신 Queue<E> 를 구현해 (E) 캐스팅과 Object 반환을 없앴다.
public class LinkedQueue_fixedByClaude<E> extends SinglyLinkedList_fixedByClaude<E>
		implements Queue_fixedByClaude<E> {

	public LinkedQueue_fixedByClaude() {
		super();
	}

	@Override
	public boolean isFull() {
		return false;	// 연결 리스트는 가득 차지 않는다 (FIX: 조회 메서드에서 출력하지 않도록 했다)
	}

	@Override
	public void enqueue(E e) {
		addLast(e);
	}

	@Override
	public E dequeue() {
		if (isEmpty()) return null;
		E item = first.item;
		remove(0);	// remove(0) 이 비었을 때 last = null 처리까지 한다
		return item;
	}

	@Override
	public E first() {
		return isEmpty() ? null : first.item;	// FIX: 원본 get(0) 은 빈 큐에서 예외를 던졌다
	}

	public static void main(String[] args) {
		Queue_fixedByClaude<Integer> q = new LinkedQueue_fixedByClaude<>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		System.out.println(q.first());	// 1
		System.out.println(q.dequeue());	// 1
		System.out.println(q.dequeue());	// 2
		System.out.println(q.dequeue());	// 3
		System.out.println(q.dequeue());	// null
		System.out.println(q.first());	// null (원본에서는 예외)
		System.out.println(q.isEmpty());	// true
		System.out.println(q.isFull());	// false
	}
}
