package ds_lecture_26_fixedByClaude;

public interface Queue_fixedByClaude<E> {
	int size();
	boolean isEmpty();
	boolean isFull();
	void enqueue(E e);
	E dequeue();
	E first();
}
