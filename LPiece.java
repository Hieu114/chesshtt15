/**Hieu Tran 
  * a class similar to LMove
 */

public abstract class LPiece extends ChessPiece {

    //constructor
    public LPiece(String label, ChessGame.Side side, ChessBoard board) {
        super(label, side, board);
    }

    //return if the horse is moving right
    private boolean isLegalHorse(int toRow, int toColumn) {
        if (getChessBoard().getPiece(getRow(), getColumn()).getLabel().equals("H")) {
            if (Math.max(getRow(), toRow) - Math.min(getRow(), toRow) == 2) {
                if (getRow() < toRow) {
                    if (getChessBoard().hasPiece(getRow() + 1, getColumn())) {
                        return false;
                    }
                }
                else {
                    if (getChessBoard().hasPiece(getRow() - 1, getColumn())) {
                        return false;
                    }
                }
            }
            else if (Math.max(getColumn(), toColumn) - Math.min(getColumn(), toColumn) == 2) {
                if (getColumn() > toColumn) {
                    if (getChessBoard().hasPiece(getRow(), getColumn() - 1)) {
                        return false;
                    }
                }
                else {
                    if (getChessBoard().hasPiece(getRow(), getColumn() + 1)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

   //is it the legal move
    public boolean isLegalMove(int toRow, int toColumn) {

        if (!isLegalHorse(toRow, toColumn)) {
            return false;
        }

        // check if there is a piece of the same side at the designated position
        if (getChessBoard().hasPiece(toRow, toColumn)) {
            if (getChessBoard().getPiece(toRow, toColumn).getSide() == this.getSide()) {
                return false;
            }
        }

        if (getColumn() + 1 == toColumn && (getRow() + 2 == toRow || getRow() - 2 == toRow)) {
            return true;
        }

        if (getColumn() + 2 == toColumn && (getRow() + 1 == toRow || getRow() - 1 == toRow)) {
            return true;
        }

        if (getColumn() - 1 == toColumn && (getRow() + 2 == toRow || getRow() - 2 == toRow)) {
            return true;
        }

        if (getColumn() - 2 == toColumn && (getRow() + 1 == toRow || getRow() - 1 == toRow)) {
            return true;
        }

        return false;
    }

    public void moveDone() {}
}
