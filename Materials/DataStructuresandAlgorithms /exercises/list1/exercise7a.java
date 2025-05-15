import java.util.Iterator;
import java.util.NoSuchElementException;

public class exercise7a <T> implements Iterator<T> {
    private T[][] array;
    private int row = 0, col = 0;
    
    public exercise7a(T[][] array) {
        this.array = array;
    }
    
    @Override
    public boolean hasNext() {
        while (row < array.length) {
            if (col < array[row].length) {
                return true;
            } else {
                row++;
                col = 0;
            }
        }
        return false;
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[row][col++];
    }

    public static void main(String[] args) {
        Integer[][] array1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        exercise7a<Integer> ex7a = new exercise7a<>(array1);
        System.out.println(ex7a);
        while (ex7a.hasNext()) {
            System.out.println(ex7a.next());
        }
    }
}