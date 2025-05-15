package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            object = e;
        }
        public Element(E e, Element next, Element prev) {
            object = e;
            this.next = next;
            this.prev = prev;
        }
        E object;
        Element next = null;
        Element prev = null;
    }

    Element head;
    Element tail;
    // can be realization with the field size or without

    private class InnerIterator implements Iterator<E>{
        private Element pos;
        // TODO maybe more fields...
        public InnerIterator() {
            pos = head;
        }
        @Override
        public boolean hasNext() {
            return pos != null;
        }

        @Override
        public E next() {
            if(pos == null)
                throw new NoSuchElementException();
            E e = pos.object;
            pos = pos.next;
            return e;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        private Element current;
        private Element lastReturned = null;
        // TODO maybe more fields....

        public InnerListIterator() {
            current = head;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public boolean hasPrevious() {
            if(current == null)
                return tail != null;
            else
                return current.prev != null;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            lastReturned = current;
            E e = current.object;
            current = current.next;
            return e;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            if(!hasPrevious())
                throw new NoSuchElementException();
            if(current == null)
                current = tail;
            else
                current = current.prev;
            lastReturned = current;
            return current.object;
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
        public void set(E e) {
            if(lastReturned == null)
                throw new IllegalStateException();
            lastReturned.object = e;
        }
    }

    public TwoWayUnorderedListWithHeadAndTail() {
        // make a head and a tail
        head = null;
        tail = null;
    }

    @Override
    public boolean add(E element) {
        Element newElement = new Element(element, null,null);
        if(head == null) {
            head = newElement;
            tail = newElement;
        }
        else {
            tail.next = newElement;
            newElement.prev = tail;
            tail = newElement;
        }
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        if(index < 0)
            throw new NoSuchElementException();
        if(index == 0) {
            if(head == null)
                add(element);
            else {
                Element newElement = new Element(element, head, null);
                head.prev = newElement;
                head = newElement;
            }
            return;
        }
        Element current = head;
        int currentIndex = 0;
        while(current != null && currentIndex < index - 1) {
            current = current.next;
            currentIndex++;
        }
        if(current == null)
            throw new NoSuchElementException();
        if(current.next == null)
            add(element);
        else {
            Element newElement = new Element(element, current.next, current);
            current.next.prev = newElement;
            current.next = newElement;
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public boolean contains(E element) {
        Iterator<E> iter = new InnerIterator();
        while(iter.hasNext())
            if(iter.next().equals(element))
                return true;
        return false;
    }

    @Override
    public E get(int index) throws NoSuchElementException{
        if(index < 0)
            throw new NoSuchElementException();
        Element current = head;
        int currentIndex = 0;
        while(current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
        if(current == null)
            throw new NoSuchElementException();
        return current.object;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException{
        if(index < 0)
            throw new NoSuchElementException();
        Element current = head;
        int currentIndex = 0;
        while(current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
        if(current == null)
            throw new NoSuchElementException();
        E old = current.object;
        current.object = element;
        return old;
    }

    @Override
    public int indexOf(E element) {
        if(!this.contains(element))
            return -1;
        int index = 0;
        Iterator<E> iter = new InnerIterator();
        while(iter.hasNext()){
            if(iter.next().equals(element))
                return index;
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
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
    public E remove(int index) throws NoSuchElementException {
        if(index < 0)
            throw new NoSuchElementException();
        if(isEmpty())
            throw new NoSuchElementException();
        Element current = head;
        int currentIndex = 0;
        while(current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
        if(current == null)
            throw new NoSuchElementException();
        E removedValue = current.object;
        if(current.prev != null)
            current.prev.next = current.next;
        else
            head = current.next;
        if(current.next != null)
            current.next.prev = current.prev;
        else
            tail = current.prev;
        return removedValue;
    }

    @Override
    public boolean remove(E e) {
        int idx = indexOf(e);
        if(idx == -1)
            return false;
        else {
            remove(idx);
        }
        return true;
    }

    @Override
    public int size() {
        Iterator<E> iter = new InnerIterator();
        int size = 0;
        while(iter.hasNext()){
            iter.next();
            size++;
        }
        return size;
    }
    public String toStringReverse() {
        ListIterator<E> iter = new InnerListIterator();
        while(iter.hasNext())
            iter.next();
        String retStr = "";
        while(iter.hasPrevious())
            retStr += "\n" + iter.previous().toString();
        return retStr;
    }

    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
        if(head != other.head) {
            if(other.isEmpty())
                return;
            else if(isEmpty()) {
                head = other.head;
                tail = other.tail;
            }
            else {
                tail.next = other.head;
                other.head.prev = tail;
                tail = other.tail;
            }
            other.clear();
        }
    }
}