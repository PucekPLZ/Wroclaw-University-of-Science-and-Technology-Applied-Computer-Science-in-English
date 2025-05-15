package dsaa.lab01;

public class Drawer {
	private static void drawLine(int n, char ch) {
		for (int i = 0; i < n; i++) {
            System.out.print(ch);
        }
	}

    public static void helper(int n, int i) {
        int x = 2 * i - 1;
        int dots = n - i;
        drawLine(dots, '.');
        drawLine(x, 'X');
        drawLine(dots, '.');
        System.out.println();
    }


	public static void drawPyramid(int n) {
		for (int i = 1; i <= n; i++) {
            helper(n, i);
        }
	}

	
	public static void drawChristmassTree(int n) {
		for (int height = 1; height <= n; height++) {
            for (int i = 1; i <= height; i++) {
                helper(n, i);
            }
        }
	}

    public static void drawSquare(int n) {
        drawLine(n, 'X');
        System.out.println();
        for (int i = 1; i < n - 1; i++) {
            System.out.print("X");
            drawLine(n - 2, ' ');
            System.out.println("X");
        }
        drawLine(n, 'X');
        System.out.println();
    }
}
