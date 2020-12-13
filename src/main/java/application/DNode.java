package application;
/**
 * @author toka,nada
 */
public class DNode {
/**
 * element.
 */
private Object element;
 /**
  * pointer to next and previous nodes.
  */
private DNode next, prev;
/**
 * Constructor that creates a node with given fields.
 * @param e element
 * @param n next
 * @param p previous
 */
public DNode(final Object e, final DNode n, final DNode p) {
	element = e;
	next = n;
	prev = p;
}
/**
 * Returns the element of this node.
 * @return element
 */
public Object getElement() {
	return element;
}
/**
 * Returns the previous node of this node.
 * @return prev
 */
public DNode getPrevious() {
	return prev;
}
/**
 * Returns the next node of this node.
 * @return next
 */
public DNode getnext() {
	return next;
}
/**
 * sets the element of this node.
 * @param e element
 */
public void setElement(final Object e) {
	element = e;
}
/**
 * sets the previous node of this node.
 * @param p previous
 */
public void setPrev(final DNode p) {
	prev = p;
}
/**
 * sets the next node of this node.
 * @param n next
 */
public void setNext(final DNode n) {
	next = n;
}
}
