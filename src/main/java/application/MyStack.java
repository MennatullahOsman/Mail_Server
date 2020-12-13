package application;

/**
 *
 * @author ACER
 *
 */
public class MyStack implements IStack {
/**
 * stack.
 */
private SList mystack = new SList();
	@Override
	public final Object pop() {
		// TODO Auto-generated method stub
		Object o = mystack.get(0);
		mystack.remove(0);
		return o;
	}

	@Override
	public final Object peek() {
		// TODO Auto-generated method stub
		return mystack.get(0);
	}

	@Override
	public final void push(final Object element) {
		// TODO Auto-generated method stub
		mystack.add(0, element);
	}

	@Override
	public final boolean isEmpty() {
		// TODO Auto-generated method stub
		return mystack.isEmpty();
	}

	@Override
	public final int size() {
		// TODO Auto-generated method stub
		return mystack.size();
	}

}
