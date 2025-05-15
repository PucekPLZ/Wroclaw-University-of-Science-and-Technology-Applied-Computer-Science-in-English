import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedListWithHead<E> extends AbstractList<E> {

    private class Element {
        private E value;
        private Element next;

        public Element(E data) {
            this.value = data;
            this.next = null;
        }

        public E getValue() {
            return value;
        }
        public void setValue(E value) {
            this.value = value;
        }
        public Element getNext() {
            return next;
        }
        public void setNext(Element next) {
            this.next = next;
        }
    }

    private Element head = null;
    private int _size = 0;

    public OneWayLinkedListWithHead() {
        // Initially empty list.
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
        _size = 0;
    }

    private Element getElement(int index) {
        if (index < 0 || index >= _size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + _size);
        }
        Element actElem = head;
        while (index > 0) {
            actElem = actElem.getNext();
            index--;
        }
        return actElem;
    }

    @Override
    public boolean add(E e) {
        Element newElem = new Element(e);
        if (head == null) {
            head = newElem;
        } else {
            Element tail = head;
            while (tail.getNext() != null) {
                tail = tail.getNext();
            }
            tail.setNext(newElem);
        }
        _size++;
        return true;
    }

    @Override
    public void add(int index, E data) {
        if (index < 0 || index > _size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + _size);
        }
        Element newElem = new Element(data);
        if (index == 0) {
            newElem.setNext(head);
            head = newElem;
        } else {
            Element actElem = getElement(index - 1);
            newElem.setNext(actElem.getNext());
            actElem.setNext(newElem);
        }
        _size++;
    }

    @Override
    public int indexOf(E data) {
        int pos = 0;
        Element actElem = head;
        while (actElem != null) {
            if (actElem.getValue().equals(data)) {
                return pos;
            }
            pos++;
            actElem = actElem.getNext();
        }
        return -1;
    }

    @Override
    public boolean contains(E data) {
        return indexOf(data) >= 0;
    }

    @Override
    public E get(int index) {
        Element actElem = getElement(index);
        return actElem.getValue();
    }

    @Override
    public E set(int index, E data) {
        Element actElem = getElement(index);
        E oldData = actElem.getValue();
        actElem.setValue(data);
        return oldData;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + _size);
        }
        E retValue;
        if (index == 0) {
            retValue = head.getValue();
            head = head.getNext();
        } else {
            Element actElem = getElement(index - 1);
            if (actElem.getNext() == null) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + _size);
            }
            retValue = actElem.getNext().getValue();
            actElem.setNext(actElem.getNext().getNext());
        }
        _size--;
        return retValue;
    }

    @Override
    public boolean remove(E value) {
        if (head == null) {
            return false;
        }
        if (head.getValue().equals(value)) {
            head = head.getNext();
            _size--;
            return true;
        }
        Element actElem = head;
        while (actElem.getNext() != null && !actElem.getNext().getValue().equals(value)) {
            actElem = actElem.getNext();
        }
        if (actElem.getNext() == null) {
            return false;
        }
        actElem.setNext(actElem.getNext().getNext());
        _size--;
        return true;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    private class InnerIterator implements Iterator<E> {
        private Element current;
        private Element prev;             
        private Element lastReturned;     
        private Element prevLastReturned;  
        private boolean canRemove;

        public InnerIterator() {
            current = head;
            prev = null;
            lastReturned = null;
            prevLastReturned = null;
            canRemove = false;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = current;
            prevLastReturned = prev;
            E value = current.getValue();
            prev = current;
            current = current.getNext();
            canRemove = true;
            return value;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("remove() cannot be called at this time.");
            }
            if (prevLastReturned == null) { 
                head = head.getNext();
            } else {
                prevLastReturned.setNext(lastReturned.getNext());
            }
            prev = prevLastReturned;
            lastReturned = null;
            canRemove = false;
            _size--;
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        private int currentIndex;

        public InnerListIterator() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E value = get(currentIndex);
            currentIndex++;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            currentIndex--;
            return get(currentIndex);
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported in this ListIterator.");
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("set() is not supported in this ListIterator.");
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("add() is not supported in this ListIterator.");
        }
    }
}
