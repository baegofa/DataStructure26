package ds_lecture_26;

public class ArrayCircularQueue<E> implements Queue<E>{
	private static final int INITIAL_CAPACITY = 100;
	private E[] arr;
	private int N;
	private int front = 0, rear = 0;
	
	public ArrayCircularQueue() {
		this(INITIAL_CAPACITY);
	}
	public ArrayCircularQueue(int capacity) {
		N = capacity;
		arr = (E[]) new Object[capacity];
	}
	
	public int size() {
		return (rear + N - front) % N;
	}
	
	public boolean isEmpty() {
		return front == rear;
	}
	
	public boolean isFull() {
		return front == (rear + 1) % N;
	}
	
	public void enqueue(E e) {
		if (isFull()) {
			System.out.println("full!");
			return;
		}
		rear = (rear + 1) % N;
		arr[++rear] = e;
	}
	
	public E first() {
		if (isFull()) {
			return null;
		}
		return arr[(front+1) % N];
	}
	public E dequeue() {
		if (isEmpty()) {
			System.out.println("empty");
			return null;
		}
		front = (front + 1) % N;
		return arr[++front];
	}
	public static void main(String[] args) {
//    	ArrayQueue<Integer> Q = new ArrayQueue<Integer>(5);
     	Queue<Integer> Q = new ArrayCircularQueue<>(5);
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
