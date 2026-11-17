package ds_lecture_26;

public class ArrayStack<E> implements Stack<E>{
	private static final int INITIAL_CAPCITY = 100;
	private E[] arr;
	private int top = -1;
	
	public ArrayStack() {
		this(INITIAL_CAPCITY);
	}
	public ArrayStack(int capacity){
		arr = (E[]) new Object[capacity];
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return top + 1;
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return top == -1;
	}

	public boolean isFull() {
		// TODO Auto-generated method stub
		return top == arr.length-1;
	}

	public void push(E e) {
		// TODO Auto-generated method stub
		if(isFull()) {
			System.out.println("Stack Full!!");
			return;
		}
		top += 1;
		arr[top] = e;
		return;
	}
				
	public E top() {
		if(isEmpty()) return null;
		return arr[top];
	}
	
	public E pop() {
		if(isEmpty()) return null;
		E item = arr[top];
		top--;
		return item;
	}
	
	public static void main(String[] args) {
        Stack<Integer> S = new ArrayStack<>();

        S.push(5);
        S.push(3);
        System.out.println(S.size());
        System.out.println(S.pop());
        System.out.println(S.isEmpty());
        System.out.println(S.pop());
        System.out.println(S.isEmpty());
        System.out.println(S.pop());
        S.push(7);
        S.push(9);
        System.out.println(S.top());
        S.push(4);;
        System.out.println(S.size());
        System.out.println(S.pop());
        S.push(6);
        S.push(8);;
        System.out.println(S.pop());
    }
}
    
