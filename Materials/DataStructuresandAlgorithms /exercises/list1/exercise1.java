import java.util.Iterator;
import java.util.NoSuchElementException;

public class exercise1<T> implements Iterator<T> {
    private Iterator<T> base;
    private int k;
    
    public exercise1(Iterator<T> base, int k) {
        if (k < 1) {
            throw new IllegalArgumentException("k must be at least 1");
        }
        this.base = base;
        this.k = k;
    }
    
    @Override
    public boolean hasNext() {
        return base.hasNext();
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T result = base.next();
        // Skip the next k-1 elements
        for (int i = 1; i < k && base.hasNext(); i++) {
            base.next();
        }
        return result;
    }

    public static void main(String[] args) {
        Iterator<Integer> base = java.util.Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator();
        Iterator<Integer> it = new exercise1<>(base, 3);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
