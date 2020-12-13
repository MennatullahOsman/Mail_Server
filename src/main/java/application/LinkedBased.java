package application;

public class LinkedBased implements IQueue, ILinkedBased {
	/**
	 * @author pc
	 */
	class Node {
		/**
		 * value.
		 */
		Object data;
		/**
		 * node.
		 */
		Node next;
		/**
		 * @param key data
		 * @param n node
		 */
		public Node(final Object key, final Node n) {
			/**
			 * value.
			 */
			data = key;
			/**
			 *node.
			 */
			next = n;
		}
	}
	/**
	 * length of the queue.
	 */
	int size = 0;
	/**
	 * last element.
	 */
	Node rear;
	/**
	 * first element.
	 */
	Node front;
	/**
	 * linkedbased.
	 */
	public LinkedBased() {
		/**
		 * node.
		 */
		front = null;
		/**
		 * node.
		 */
		rear = null;
	}
	@Override
	public void enqueue(final Object item) {
		Node newItem = new Node(item, null);
		if (front == null) {
			front = newItem;
			rear = newItem;
		} else {
			rear.next = newItem;
			rear = newItem;
		}
		size++;
	}
	@Override
	public Object dequeue() {
		Object temp;
		if (front == null) {
		throw new RuntimeException("Queue is Empty !");
		} else if (front == rear) {
			temp = front.data;
			front = null;
			rear = null;
			size--;
		} else {
			temp = front.data;
			front = front.next;
			size--;
		}
		return temp;
	}
	@Override
	public boolean isEmpty() {
		return front == null;
	}
	@Override
	public int size() {
		return size;
	}
}
