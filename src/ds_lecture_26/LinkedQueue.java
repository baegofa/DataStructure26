package ds_lecture_26;

public class LinkedQueue<E> extends SinglyLinkedList<E> implements Queue{
	
	public LinkedQueue() {
		super();
	}

	@Override
	public boolean isFull() {
		System.out.println("LinkedQueue이므로 full이 아닙니다.");
		return false;
	}

	@Override
	public void enqueue(Object e) {
		this.addLast((E)e);
	}

	@Override
	public Object dequeue() {
		if(isEmpty()) return null;
		else {
			E returnValue = this.first.item;
			remove(0);
			if(first == null) last = null;
			return returnValue;
		}	
	}

	@Override
	public Object first() {
		return get(0);
	}
	
	public static void main(String[] args) {
		LinkedQueue<Integer> sd = new LinkedQueue<Integer>();
		sd.enqueue(1);
		sd.enqueue(2);
		sd.enqueue(3);
		System.out.println(sd.dequeue());
		System.out.println(sd.dequeue());
		System.out.println(sd.dequeue());
		System.out.println(sd.dequeue());
		sd.isEmpty();
		sd.isFull();
	}

}
