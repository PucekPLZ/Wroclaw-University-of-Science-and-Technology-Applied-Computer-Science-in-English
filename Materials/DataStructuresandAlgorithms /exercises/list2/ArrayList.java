import java.util.Iterator;
import java.util.ListIterator;

public class ArrayList<E> extends AbstractList<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private final int _initialCapacity;
    private E[] _array;
    private int _size;

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_INITIAL_CAPACITY;
        }
        _initialCapacity = capacity;
        _array = (E[]) new Object[capacity];
        _size = 0;
    }

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public int size() {
        return _size;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int capacity) {
        if (_array.length < capacity) {
            E[] copy = (E[]) new Object[capacity + capacity / 2];
            System.arraycopy(_array, 0, copy, 0, _size);
            _array = copy;
        }
    }

    private void checkOutOfBounds(int index) {
        if (index < 0 || index >= _size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + _size);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        _array = (E[]) new Object[_initialCapacity];
        _size = 0;
    }

    @Override
    public boolean add(E value) {
        ensureCapacity(_size + 1);
        _array[_size] = value;
        _size++;
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > _size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + _size);
        }
        ensureCapacity(_size + 1);
        if (index != _size) {
            System.arraycopy(_array, index, _array, index + 1, _size - index);
        }
        _array[index] = value;
        _size++;
    }

    @Override
    public int indexOf(E value) {
        int i = 0;
        while (i < _size && !value.equals(_array[i])) {
            i++;
        }
        return i < _size ? i : -1;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    @Override
    public E get(int index) {
        checkOutOfBounds(index);
        return _array[index];
    }

    @Override
    public E set(int index, E element) {
        checkOutOfBounds(index);
        E oldValue = _array[index];
        _array[index] = element;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        checkOutOfBounds(index);
        E removedValue = _array[index];
        int copyFrom = index + 1;
        if (copyFrom < _size) {
            System.arraycopy(_array, copyFrom, _array, index, _size - copyFrom);
        }
        _size--;
        return removedValue;
    }

    @Override
    public boolean remove(E value) {
        int pos = 0;
        while (pos < _size && !_array[pos].equals(value)) {
            pos++;
        }
        if (pos < _size) {
            remove(pos);
            return true;
        }
        return false;
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
        private int _pos = 0;

        @Override
        public boolean hasNext() {
            return _pos < _size;
        }

        @Override
        public E next() {
            return _array[_pos++];
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        private int _pos = 0;

        @Override
        public void add(E value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return _pos < _size;
        }

        @Override
        public boolean hasPrevious() {
            return _pos > 0;
        }

        @Override
        public E next() {
            return _array[_pos++];
        }

        @Override
        public int nextIndex() {
            return _pos;
        }

        @Override
        public E previous() {
            return _array[--_pos];
        }

        @Override
        public int previousIndex() {
            return _pos - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
