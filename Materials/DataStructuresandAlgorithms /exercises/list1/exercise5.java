import java.util.Iterator;
import java.util.NoSuchElementException;

public class exercise5 implements Iterator<Integer> {
    private int[] primes;
    private int index = 0;
    
    public exercise5(int N) {
        int count = 0;
        for (int num = 2; num < N; num++) {
            if (isPrime(num)) {
                count++;
            }
        }

        primes = new int[count];

        int pos = 0;
        for (int num = 2; num < N; num++) {
            if (isPrime(num)) {
                primes[pos++] = num;
            }
        }
    }

    private boolean isPrime(int num) {
        int sqrt = (int)Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
    
    @Override
    public boolean hasNext() {
        return index < primes.length;
    }
    
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return primes[index++];
    }

    public static void main(String[] args) {
        exercise5 ex5 = new exercise5(100);
        while (ex5.hasNext()) {
            System.out.println(ex5.next());
        }
    }
}