package ds_lecture_26;

public class LinkedStack<E> extends SinglyLinkedList<E> implements Stack{
	public LinkedStack() {
		super();
	}

	@Override
	public boolean isFull() {
		System.out.println("LinkedStack이므로 full이 아닙니다.");
		return false;
	}

	@Override
	public void push(Object e) {
		this.addLast((E)e);
		
	}

	@Override
	public Object top() {
		if(last != null)
			return last.item;
		else return null;
	}

	@Override
	public Object pop() {
		if(last != null) {
			for(Node<E> c = first;c.next != null;c = c.next) {
				if(c.next == last) {
					E returnItem = last.item;
					c.next = null;
					size--;
					last = c;
					return returnItem;
				}
				else continue;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		System.out.println(s.pop());
		System.out.println(s.top());
	}
	
}
