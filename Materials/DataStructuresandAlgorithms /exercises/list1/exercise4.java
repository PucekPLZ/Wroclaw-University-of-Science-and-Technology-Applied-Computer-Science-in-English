import java.util.Iterator;
import java.util.NoSuchElementException;

public class exercise4 <T> implements Iterator<T> {
    private Iterator<T> it1, it2;
    private boolean turn = true;
    
    public exercise4(Iterator<T> it1, Iterator<T> it2) {
        this.it1 = it1;
        this.it2 = it2;
    }
    
    @Override
    public boolean hasNext() {
        return it1.hasNext() || it2.hasNext();
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (it1.hasNext() && it2.hasNext()) {
            if (turn) {
                turn = false;
                return it1.next();
            } else {
                turn = true;
                return it2.next();
            }
        } else if (it1.hasNext()) {
            return it1.next();
        } else {
            return it2.next();
        }
    }

    public static void main(String[] args) {
        Integer[] array1 = {1, 2, 3, 4, 5};
        Integer[] array2 = {6, 7, 8};
        exercise4<Integer> ex4 = new exercise4<>(new exercise6<>(array1), new exercise6<>(array2));
        while (ex4.hasNext()) {
            System.out.println(ex4.next());
        }
    }
}