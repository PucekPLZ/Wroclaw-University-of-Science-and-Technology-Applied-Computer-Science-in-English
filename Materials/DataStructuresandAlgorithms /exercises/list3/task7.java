public class task7 {
    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static int solve_josephus(int n, int k) {
        Node head = new Node(1);
        Node previous = head;

        for (int i = 2; i <= n; i++) {
            previous.next = new Node(i);
            previous = previous.next;
        }
        previous.next = head;

        Node current = head;
        while (current.next != current) {
            for (int i = 1; i < k - 1; i++) {
                current = current.next;
            }

            current.next = current.next.next;
            current = current.next;
        }
        return current.data;
    }

    public static void main(String[] args) {
        int n = 6, k = 3;
        System.out.println("\n" + solve_josephus(n, k));
    }
}