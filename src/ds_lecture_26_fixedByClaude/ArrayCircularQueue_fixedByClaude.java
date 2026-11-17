package ds_lecture_26_fixedByClaude;

public class ArrayCircularQueue_fixedByClaude<E> implements Queue_fixedByClaude<E> {
	private static final int INITIAL_CAPACITY = 100;
	private final E[] arr;
	private final int N;
	private int front = 0, rear = 0;

	public ArrayCircularQueue_fixedByClaude() {
		this(INITIAL_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayCircularQueue_fixedByClaude(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("capacity must be positive: " + capacity);
		// FIX: 원형 큐는 full/empty 구분을 위해 한 칸을 비워 둔다.
		// 요청한 capacity 만큼 실제로 넣을 수 있도록 배열을 한 칸 크게 만든다.
		N = capacity + 1;
		arr = (E[]) new Object[N];
	}

	@Override
	public int size() {
		return (rear + N - front) % N;
	}

	@Override
	public boolean isEmpty() {
		return front == rear;
	}

	@Override
	public boolean isFull() {
		return front == (rear + 1) % N;
	}

	@Override
	public void enqueue(E e) {
		if (isFull()) {
			System.out.println("full!");
			return;
		}
		rear = (rear + 1) % N;
		arr[rear] = e;	// FIX: 원본은 arr[++rear] 로 한 번 더 증가시켜 범위를 벗어났다
	}

	@Override
	public E first() {
		if (isEmpty()) {	// FIX: 원본은 isFull() 을 검사했다
			return null;
		}
		return arr[(front + 1) % N];
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			System.out.println("empty");
			return null;
		}
		front = (front + 1) % N;
		E item = arr[front];	// FIX: 원본은 arr[++front] 로 한 번 더 증가시켰다
		arr[front] = null;
		return item;
	}

	public static void main(String[] args) {
		Queue_fixedByClaude<Integer> Q = new ArrayCircularQueue_fixedByClaude<>(5);
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
		// 배열 끝을 넘어 앞쪽으로 돌아가는(wrap-around) 경우
		Q.enqueue(5);
		Q.enqueue(6);
		Q.enqueue(7);	// full!
		while (!Q.isEmpty())
			System.out.print(Q.dequeue() + " ");	// 2 3 4 5 6
		System.out.println();
	}
}
