package application;
/**
 * @author ACER
 */
public class DList implements ILinkedList {
/**
 * size.
*/
private int size;
/**
 * header , tailer.
 */
private DNode header, tailer;
/**
 * constructor that creates an empty list.
 */
public DList() {
	size = 0;
	header = new DNode(null, null, null);
	tailer = new DNode(null, null, header);
	header.setNext(tailer);
}
	@Override
	public final void add(final int index, final Object element) {
		// TODO Auto-generated method stub
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
			}
		int count = 0;
		DNode n = header.getnext(), p = header;
		while (count < index) {
		p = p.getnext();
		n = n.getnext();
		count++;
		}
		DNode e = new DNode(element, n, p);
		n.setPrev(e);
		p.setNext(e);
		size++;
	}
	@Override
	public final void add(final Object element) {
		// TODO Auto-generated method stub
		DNode p = tailer.getPrevious();
		DNode e = new DNode(element, tailer, p);
		tailer.setPrev(e);
		p.setNext(e);
		size++;
	}
	@Override
	public final Object get(final int index) {
		// TODO Auto-generated method stub
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
			}
		DNode v = header.getnext();
		int count = 0;
		while (count < index) {
			count++;
			v = v.getnext();
		}
		return v.getElement();
	}
	@Override
	public final void set(final int index, final Object element) {
		// TODO Auto-generated method stub
		if (index >= size || index < 0) {
		throw new IndexOutOfBoundsException();
		}
		DNode v = header.getnext();
		int count = 0;
		while (count < index) {
			count++;
			v = v.getnext();
		}
		v.setElement(element);
	}
	@Override
	public final void clear() {
		// TODO Auto-generated method stub
		header.setNext(tailer);
		tailer.setPrev(header);
		size = 0;
	}
	@Override
	public final boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}
	@Override
	public final void remove(final int index) {
		// TODO Auto-generated method stub
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
			}
		size--;
		int count = 0;
		DNode n = header.getnext(), p = header;
		DNode v = header.getnext();
		while (count < index) {
		p = p.getnext();
		v = v.getnext();
		n = n.getnext();
		count++;
		}
		n = n.getnext();
		p.setNext(n);
		n.setPrev(p);
		v.setNext(null);
		v.setPrev(null);
	}
	@Override
	public final int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public final ILinkedList sublist(final int fromIndex,
			final int toIndex) {
		// TODO Auto-generated method stub
		if (fromIndex < 0 || toIndex >= size) {
			throw new IndexOutOfBoundsException();
		}
		int count = 0;
		int num = toIndex - fromIndex + 1;
		DNode current = header.getnext();
		while (count < fromIndex) {
			current = current.getnext();
			count++;
		}
		count = 0;
		DList newList = new DList();
		while (count < num) {
			newList.add(current.getElement());
			current = current.getnext();
			count++;
		}
		return newList;
	}

	@Override
	public final boolean contains(final Object o) {
		// TODO Auto-generated method stub
		int count = 0;
		DNode v = header.getnext();
		while (count < size) {
			if (v.getElement().equals(o)) {
				return true;
			}
			v = v.getnext();
			count++;
		}
		return false;
	}
}
