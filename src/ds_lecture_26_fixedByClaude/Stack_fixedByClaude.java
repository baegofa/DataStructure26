package ds_lecture_26_fixedByClaude;

public interface Stack_fixedByClaude<E> {
	int size();
	boolean isEmpty();
	boolean isFull();
	void push(E e);
	E top();
	E pop();
}
