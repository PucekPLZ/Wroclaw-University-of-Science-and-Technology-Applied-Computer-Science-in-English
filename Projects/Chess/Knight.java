public class Knight extends Piece {
    
    public Knight(int color, int col, int row) {
        super(color, col, row);

        type = Type.KNIGHT;

        if (color == GamePanel.WHITE) {
            image = getImage("resources/piece/w-knight.png");
        } else {
            image = getImage("resources/piece/b-knight.png");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        
        if (isWithinBoard(targetCol, targetRow)) {
            if (Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2) {
                if (isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}