package dsaa.lab04;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            object = e;
        }
        // add element e after this
        public void addAfter(Element elem) {
			elem.next = this.next;
			elem.prev = this;
			elem.next.prev = elem;
			this.next = elem;
			size++;
		}
        // assert it is NOT a sentinel
        public void remove() {
            if (this != sentinel)
            {
                this.object = null;
                this.next.prev = this.prev;
                this.prev.next = this.next;
                this.next = null;
                this.prev = null;
            }
        }
        E object;
        Element next=null;
        Element prev=null;
    }


    Element sentinel;
    int size;

    private class InnerIterator implements Iterator<E>{
        private Element current;
        public InnerIterator() {
            current = sentinel;
        }
        @Override
        public boolean hasNext() {
            return current.next!=sentinel;
        }

        @Override
        public E next() {
            E e = current.next.object;
            current=current.next;
            return e;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        Element current;
        public InnerListIterator() {
            current = sentinel;
        }
        @Override
        public boolean hasNext() {
            return current.next!=sentinel;
        }

        @Override
        public E next() {
            E e = current.next.object;
            current=current.next;
            return e;
        }
        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasPrevious() {
            return current.prev!=sentinel;
        }
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public E previous() {
            E e = current.prev.object;
            current = current.prev;
            return e;
        }
        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void set(E arg0) {
            throw new UnsupportedOperationException();
        }
    }
    public TwoWayCycledOrderedListWithSentinel() {
        sentinel = new Element(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public boolean add(E e) {
        Element current = sentinel;
        while(current.next!=sentinel)
        {
            current = current.next;
            if(((Comparable<E>) current.object).compareTo(e)>0)
            {
                current.prev.addAfter(new Element(e));
                return true;
            }
        }
        current.addAfter(new Element(e));
        size++;
        return true;
    }

    private Element getElement(int index) throws NoSuchElementException
    {
        if (index<0)
            throw new NoSuchElementException();
        if(index>=size)
            throw new NoSuchElementException();
        Element current = sentinel;
        for(int i = 0; i<index; i++)
        {
            current = current.next;
        }
        return current.next;
    }

    private Element getElement(E obj) {
        Element current = sentinel;
        while(current.next!=sentinel)
        {
            current = current.next;
            if(current.object.equals(obj))
                return current;
        }
        return null;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void clear() {
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return getElement(element)!=null;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
        return getElement(index).object;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        if(!this.contains(element))
            return -1;
        int index = 0;
        Iterator<E> iter = new InnerIterator();
        while(iter.hasNext())
        {
            if(iter.next().equals(element))
                return index;
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (size==0)
            return true;
        else return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    @Override
    public E remove(int index) throws NoSuchElementException{
        E temp = getElement(index).object;
        getElement(index).remove();
        size--;
        return temp;
    }

    @Override
    public boolean remove(E e) {
        if(getElement(e)==null)
            return false;
        getElement(e).remove();
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

	public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
		if (other == null || other.isEmpty() || this.equals(other)) return;

		Element node = sentinel;
		Element otherNode = other.sentinel.next;

		while (otherNode != other.sentinel) {
			if (node.next == sentinel || ((Comparable<E>)  node.next.object).compareTo(otherNode.object) > 0) {
				otherNode = otherNode.next;
				node.addAfter(otherNode.prev);
			} else {
				node = node.next;
			}
		}

		other.clear();
	}

    public void removeAll(E e) {
        Element current = sentinel;
        while(current.next!=sentinel)
        {
            if(current.next.object.equals(e))
            {
                current.next.remove();
                size--;
            }
            else
                current = current.next;
        }
    }

}