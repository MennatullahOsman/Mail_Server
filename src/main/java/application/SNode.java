package application;

/**
*
* @author ACER
*
*/
public class SNode {
	/**
	 * element.
	 */
	private Object element;
	/**
	 * next.
	 */
	private SNode next;
/**
* Constructor that creates a node with given fields.
* @param data is data
* @param next is next
*/
	public SNode(final Object data, final SNode next) {
		element = data;
		this.next = next;
	}
/**
* Returns the element of this node.
* @return element
*/
	public Object getElement() {
		return element;
	}
/**
*Returns the next node of this node.
* @return next
*/
	public SNode getnext() {
		return next;
	}
/**
*sets the element of this node.
* @param data
*/
	public void setElement(final Object data) {
		element = data;
	}
/**
*sets the next node of this node.
* @param next
*/
	public void setNext(final SNode next) {
		this.next = next;
	}
}

