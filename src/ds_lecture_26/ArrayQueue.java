package ds_lecture_26;

public class ArrayQueue<E> {
	private static final int INITIAL_CAPACITY = 100;
	private E[] arr;
	private int front = -1, rear = -1;
	
	public ArrayQueue() {
		this(INITIAL_CAPACITY);
	}
	public ArrayQueue(int capacity) {
		arr = (E[]) new Object[capacity];
	}
	
	public int size() {
		return rear - front;
	}
	
	public boolean isEmpty() {
		return front == rear;
	}
	
	public boolean isFull() {
		return rear == arr.length -1;
	}
	
	public void enqueue(E e) {
		if (isFull()) {
			System.out.println("full!");
			return;
		}
		arr[++rear] = e;
	}
	
	public E first() {
		if (isFull()) {
			System.out.println("full!");
			return null;
		}
		return arr[front+1];
	}
	public E dequeue() {
		if (isEmpty()) {
			System.out.println("empty");
			return null;
		}
		return arr[++front];
	}
	public static void main(String[] args) {
    	ArrayQueue<Integer> Q = new ArrayQueue<Integer>(5);
//    	Queue<Integer> Q = new ArrayCircularQueue<>(5);
//    	Queue<Integer> Q = new LinkedQueue<>();
        Q.enqueue(5);
        Q.enqueue(3);
        System.out.println(Q.size());
        System.out.println(Q.dequeue());
        System.out.println(Q.isEmpty());
        System.out.println(Q.dequeue());
        System.out.println(Q.isEmpty());
        System.out.println(Q.dequeue());
        Q.enqueue(1);
        Q.enqueue(2);
        Q.enqueue(3);
        System.out.println(Q.first());
        Q.enqueue(4);
        System.out.println(Q.size());
        System.out.println(Q.dequeue());
    }


}
