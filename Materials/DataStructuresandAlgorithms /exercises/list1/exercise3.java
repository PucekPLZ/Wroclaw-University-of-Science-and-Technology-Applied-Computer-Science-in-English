import java.util.Iterator;

public class exercise3 implements Iterator<Integer> {
    private int count = 0;
    private int a = 1;
    private int b = 1;
    
    @Override
    public boolean hasNext() {
        // This iterator is infinite.
        return true;
    }
    
    @Override
    public Integer next() {
        if (count == 0) {
            count++;
            return a;
        } else if (count == 1) {
            count++;
            return b;
        } else {
            int c = a + b;
            a = b;
            b = c;
            count++;
            return c;
        }
    }

    public static void main(String[] args) {
        exercise3 it = new exercise3();
        for (int i = 0; i < 10; i++) {
            System.out.println(it.next());
        }
    }
}