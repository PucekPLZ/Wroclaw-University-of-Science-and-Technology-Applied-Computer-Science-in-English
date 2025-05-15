import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class exercise6 <T> implements Iterator<T> {
    private T[] array;
    private int cursor;      // index of next element to return
    private int size;        // number of valid elements in the array
    private int lastRet = -1; // index of last returned element (-1 if none)

    public exercise6(T[] array) {
        this.array = array;
        this.cursor = 0;
        this.size = array.length; // initialize size with the array length
    }

    @Override
    public boolean hasNext() {
        return cursor < size;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastRet = cursor;
        return array[cursor++];
    }

    @Override
    public void remove() {
        if (lastRet < 0) {
            throw new IllegalStateException("remove() cannot be called before next() or after already removed");
        }
        // Shift elements left to cover the removed element.
        int numMoved = size - lastRet - 1;
        if (numMoved > 0) {
            System.arraycopy(array, lastRet + 1, array, lastRet, numMoved);
        }
        // Set the last valid slot to null and update size.
        array[--size] = null;
        // Adjust the cursor to account for the shift.
        cursor = lastRet;
        // Reset lastRet so that remove() can't be called twice consecutively.
        lastRet = -1;
    }

    public static void main(String[] args) {
        // Create an array of integers.
        Integer[] numbers = {1, 2, 3, 4, 5};
        
        // Create an iterator over the array.
        exercise6<Integer> iterator = new exercise6<>(numbers);
        
        System.out.println("Original array: " + Arrays.toString(numbers));
        System.out.println("Iterating through the array...");
        
        // Iterate over the array.
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            System.out.println("Current element: " + num);
            
            // Remove the element if it is equal to 3.
            if (num == 3) {
                iterator.remove();
                System.out.println("Removed element: " + num);
            }
        }
        
        // Print the array after removal.
        System.out.println("Array after removal: " + Arrays.toString(numbers));
    }
}