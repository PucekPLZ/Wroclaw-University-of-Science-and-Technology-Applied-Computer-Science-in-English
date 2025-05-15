package dsaa.lab02;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object=e;
        }
        E object;
        Element next=null;
    }

    private Element sentinel;

    private class InnerIterator implements Iterator<E>{
        private Element currElement;
        public InnerIterator() {
            currElement = sentinel;
        }
        @Override
        public boolean hasNext() {
            return(currElement.next != null);
        }

        @Override
        public E next() {
            E e = currElement.next.object;
            currElement = currElement.next;
            return(e);
        }
    }

    public OneWayLinkedList() {
        sentinel = new Element(null);
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        Element element = new Element(e);
        Element last = sentinel;
        while(last.next!=null)
            last = last.next;
        last.next = element;
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        if (index < 0){
            throw new NoSuchElementException();
        }
        Element toAdd = new Element(element);
        Element currElement = sentinel;
        
        for(int i=0;i<index;i++)
        {
            if(currElement.next==null)
                throw new NoSuchElementException();
            currElement = currElement.next;
        }
        toAdd.next=currElement.next;
        currElement.next=toAdd;
    }

    @Override
    public void clear() {
        sentinel.next = null;
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
    public E get(int index) throws NoSuchElementException {
        Iterator<E> iter = new InnerIterator();
        Element currElement = sentinel;
        for(int i=0;i<index;i++)
        {
            if(iter.hasNext())
            {
                iter.next();
                currElement = currElement.next;
            }
        }
        if(currElement.next==null)
            throw new NoSuchElementException();
        return currElement.next.object;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        Iterator<E> iter = new InnerIterator();
        Element currElement = sentinel;
        for(int i=0;i<index;i++)
        {
            if(iter.hasNext())
            {
                iter.next();
                currElement = currElement.next;
            }
        }
        if(currElement.next==null)
            throw new NoSuchElementException();
        E temp = currElement.next.object;
        currElement.next.object = element;
        return temp;
    }

    @Override
    public int indexOf(E element) {
        if(!this.contains(element))
            return -1;
        int index=0;
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
       return sentinel.next==null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        if(sentinel.next==null)
            throw new NoSuchElementException();
        Element currElement = sentinel;
        for(int i=0;i<index;i++)
        {
            currElement = currElement.next;
            if(currElement.next==null)
                throw new NoSuchElementException();
        }
        E temp = currElement.next.object;
        currElement.next.object = null;
        currElement.next=currElement.next.next;
        return temp;
    }

    @Override
    public boolean remove(E e) {
        if(indexOf(e)==-1)
        return false;
        else
        {
            this.remove(this.indexOf(e));
        }
        return true;
    }

    @Override
    public int size() {
        Iterator<E> iter = new InnerIterator();
        int size=0;
        while(iter.hasNext())
        {
            iter.next();
            size++;
        }
        return size;
    }

    public void RemoveDuplication() {
        Element currElement = sentinel.next;
        while (currElement != null && currElement.next != null) {
            if (currElement.object.equals(currElement.next.object)) {
                currElement.next = currElement.next.next;
            } else {
                currElement = currElement.next;
            }
        }
    }
}