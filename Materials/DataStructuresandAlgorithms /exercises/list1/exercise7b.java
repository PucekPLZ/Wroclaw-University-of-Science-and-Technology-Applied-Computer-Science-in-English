import java.util.Iterator;
import java.util.NoSuchElementException;


public class exercise7b <T> implements Iterator<T> {
    private T[][] array;
    private int row;
    private int col;
    
    public exercise7b(T[][] array) {
        this.array = array;
        row = array.length - 1;
        col = (row >= 0) ? array[row].length - 1 : 0;
    }
    
    @Override
    public boolean hasNext() {
        while (row >= 0) {
            if (col >= 0) {
                return true;
            } else {
                row--;
                if (row >= 0) {
                    col = array[row].length - 1;
                }
            }
        }
        return false;
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T result = array[row][col--];
        return result;
    }

    public static void main(String[] args) {
        Integer[][] array1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        exercise7b<Integer> ex7b = new exercise7b<>(array1);

        while (ex7b.hasNext()) {
            System.out.println(ex7b.next());
        }
    }
}
