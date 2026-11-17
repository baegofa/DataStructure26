package ds_lecture_26_fixedByClaude;

// FIX: Queue 인터페이스를 구현하도록 했다.
public class ArrayQueue_fixedByClaude<E> implements Queue_fixedByClaude<E> {
	private static final int INITIAL_CAPACITY = 100;
	private final E[] arr;
	private int front = -1, rear = -1;

	public ArrayQueue_fixedByClaude() {
		this(INITIAL_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayQueue_fixedByClaude(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("capacity must be positive: " + capacity);
		arr = (E[]) new Object[capacity];
	}

	@Override
	public int size() {
		return rear - front;
	}

	@Override
	public boolean isEmpty() {
		return front == rear;
	}

	@Override
	public boolean isFull() {
		return rear == arr.length - 1;
	}

	@Override
	public void enqueue(E e) {
		if (isFull()) {
			System.out.println("full!");
			return;
		}
		arr[++rear] = e;
	}

	@Override
	public E first() {
		if (isEmpty()) {	// FIX: 원본은 isFull() 을 검사했다
			System.out.println("empty");
			return null;
		}
		return arr[front + 1];
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			System.out.println("empty");
			return null;
		}
		E item = arr[++front];
		arr[front] = null;
		// FIX: 큐가 비면 인덱스를 처음으로 되돌려 앞쪽 빈 칸을 다시 쓸 수 있게 한다.
		// (비어 있지 않은 상태에서 앞쪽 칸이 낭비되는 문제는 선형 큐의 한계 -> 원형 큐 참고)
		if (isEmpty())
			front = rear = -1;
		return item;
	}

	public static void main(String[] args) {
		Queue_fixedByClaude<Integer> Q = new ArrayQueue_fixedByClaude<>(5);
		Q.enqueue(5);
		Q.enqueue(3);
		System.out.println(Q.size());	// 2
		System.out.println(Q.dequeue());	// 5
		System.out.println(Q.isEmpty());	// false
		System.out.println(Q.dequeue());	// 3
		System.out.println(Q.isEmpty());	// true
		System.out.println(Q.dequeue());	// empty, null
		Q.enqueue(1);
		Q.enqueue(2);
		Q.enqueue(3);
		System.out.println(Q.first());	// 1
		Q.enqueue(4);
		System.out.println(Q.size());	// 4
		System.out.println(Q.dequeue());	// 1
	}
}
