package ds_lecture_26_fixedByClaude;

public class ArrayStack_fixedByClaude<E> implements Stack_fixedByClaude<E> {
	private static final int INITIAL_CAPACITY = 100;	// FIX: 오타 CAPCITY -> CAPACITY
	private final E[] arr;
	private int top = -1;

	public ArrayStack_fixedByClaude() {
		this(INITIAL_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayStack_fixedByClaude(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("capacity must be positive: " + capacity);
		arr = (E[]) new Object[capacity];
	}

	@Override
	public int size() {
		return top + 1;
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return top == arr.length - 1;
	}

	@Override
	public void push(E e) {
		if (isFull()) {
			System.out.println("Stack Full!!");
			return;
		}
		arr[++top] = e;
	}

	@Override
	public E top() {
		if (isEmpty()) return null;
		return arr[top];
	}

	@Override
	public E pop() {
		if (isEmpty()) return null;
		E item = arr[top];
		arr[top--] = null;	// FIX: 꺼낸 칸의 참조를 지워 GC 가 회수할 수 있게 한다
		return item;
	}

	public static void main(String[] args) {
		Stack_fixedByClaude<Integer> S = new ArrayStack_fixedByClaude<>();

		S.push(5);
		S.push(3);
		System.out.println(S.size());	// 2
		System.out.println(S.pop());	// 3
		System.out.println(S.isEmpty());	// false
		System.out.println(S.pop());	// 5
		System.out.println(S.isEmpty());	// true
		System.out.println(S.pop());	// null
		S.push(7);
		S.push(9);
		System.out.println(S.top());	// 9
		S.push(4);
		System.out.println(S.size());	// 3
		System.out.println(S.pop());	// 4
		S.push(6);
		S.push(8);
		System.out.println(S.pop());	// 8
	}
}
