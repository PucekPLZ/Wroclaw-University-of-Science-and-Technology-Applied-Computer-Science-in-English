import java.util.Iterator;

public class exercise2 implements Iterator<Integer> {
    private int current;
    
    public exercise2(int start) {
        this.current = start;
    }
    
    @Override
    public boolean hasNext() {
        // This iterator is infinite.
        return true;
    }
    
    @Override
    public Integer next() {
        return current++;
    }

    public static void main(String[] args) {
        exercise2 it = new exercise2(10);
        for (int i = 0; i < 10; i++) {
            System.out.println(it.next());
        }
    }
}
