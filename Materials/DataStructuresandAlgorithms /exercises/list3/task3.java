import java.util.ArrayList;

public class task3<T> {
    private ArrayList<T> items; // actual stack data
    private int cursor;

    public task3() {
        items = new ArrayList<>();
        cursor = 0; // At the very top
    }

    public void push(T item) {
        items.add(item);
        cursor = 0;
    }

    public T pop() {
        if (items.isEmpty()) {
            return null;
        }
        T item = items.remove(items.size() - 1);
        cursor = 0;
        return item;
    }

    public T peek() {
        if (items.isEmpty() || cursor >= items.size()) {
            return null;
        }
        return items.get(items.size() - 1 - cursor);
    }

    public void top() {
        cursor = 0;
    }

    public void down() {
        if (cursor < items.size() - 1) {
            cursor++;
        } else {
            System.out.println("reached at the very bottom");
        }
    }

    public static void main(String[] args) {

        task3<String> stack = new task3<>();

        System.out.println("\n\n");

        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");

        System.out.println("Top element (peek): " + stack.peek());

        stack.down();
        System.out.println("Peek after moving down: " + stack.peek());

        stack.down();
        System.out.println("Peek after moving down: " + stack.peek());

        stack.down();
        System.out.println("Peek after moving down: " + stack.peek());

        stack.down();

        stack.top();
        System.out.println("Cursor reset to top. Peek: " + stack.peek());

        stack.pop();
        System.out.println("Peek after pop: " + stack.peek());

        stack.pop();
        stack.pop();
        System.out.println("Peek after popping all elements: " + stack.peek());
    }
}