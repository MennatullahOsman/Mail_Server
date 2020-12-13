package application;
/**
 *
 * @author ACER
 *
 */
public class SList implements ILinkedList{
/**
 * size.
 */
private int size;
/**
 *header, tailer.
 */
	private SNode header, tailer;
	/**
	 * constructor that creates an empty list.
	 */
	public SList() {
		size = 0;
		header = new SNode(null, null);
		tailer = new SNode(null, null);
		header.setNext(tailer);
	}
		@Override
		public final void add(final int index, final Object element) {
			// TODO Auto-generated method stub
			if (index > size || index < 0) {
				throw new IndexOutOfBoundsException();
				}
			int count = 0;
			SNode current = header;
			SNode per = current.getnext();
			while (count < index) {
			current = current.getnext();
			per = current.getnext();
			count++;
			}
			SNode select = new SNode(element, per);
			current.setNext(select);
			size++;
		}

		@Override
		public final void add(final Object element) {
			// TODO Auto-generated method stub
			int i = 0;
			 SNode current = header;
	            SNode e = new SNode(element, null);
	            while (i < size) {
	                current = current.getnext();
	                i++;
	            }
	            current.setNext(e);
	            e.setNext(tailer);
			size++;
		}

		@Override
		public final Object get(final int index) {
			// TODO Auto-generated method stub
			if (index >= size || index < 0) {
				throw new IndexOutOfBoundsException();
				}
			SNode select = header.getnext();
			int count = 0;
			while (count < index) {
				count++;
				select = select.getnext();
			}
			return select.getElement();
		}

		@Override
		public final void set(final int index, final Object element) {
			// TODO Auto-generated method stub
			if (index > size || index < 0) {
				throw new IndexOutOfBoundsException();
				}
			SNode select = header.getnext();
			int count = 0;
			while (count < index) {
				count++;
				select = select.getnext();
			}
			select.setElement(element);
		}

		@Override
		public final void clear() {
			// TODO Auto-generated method stub
			header.setNext(tailer);
			size = 0;
		}

		@Override
		public final boolean isEmpty() {
			// TODO Auto-generated method stub
			return size == 0;
		}

		@Override
		public final void remove(final int index) {
			// TODO Auto-generated method stub
			if (index >= size || index < 0) {
				throw new IndexOutOfBoundsException();
				}
			int count = 0;
			SNode current = header;
			SNode select = header.getnext();
			while (count < index) {
			current = current.getnext();
			select = select.getnext();
			count++;
			}
			current.setNext(select.getnext());
			select.setNext(null);
			size--;
		}

		@Override
		public final int size() {
			// TODO Auto-generated method stub
			return size;
		}

		@Override
public final ILinkedList sublist(final int fromIndex, final int toIndex) {
			// TODO Auto-generated method stub
			if (fromIndex < 0 || toIndex >= size) {
				throw new IndexOutOfBoundsException();
			}
			int count = 0;
			int num = toIndex - fromIndex + 1;
			SNode current = header.getnext();
			while (count < fromIndex) {
				current = current.getnext();
				count++;
			}
			count = 0;
			SList newList = new SList();
			while (count < num) {
				newList.add(current.getElement());
				current = current.getnext();
				count++;
			}
			return newList;
		}

		@Override
		public final boolean contains(final Object check) {
			// TODO Auto-generated method stub
			int count = 0;
			SNode current = header.getnext();
			while (count < size) {
				if (current.getElement().equals(check)) {
					return true;
				}
				current = current.getnext();
				count++;
			}
			return false;
		}

}
