package ds_lecture_26;

public interface Queue<E> {
	
	int size();
	boolean isEmpty();
	boolean isFull();
	void enqueue(E e);
	E dequeue();
	E first();

}
