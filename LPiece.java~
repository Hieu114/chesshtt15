/**
 * @author: Lam Nguyen
 * an abstract class representing all L-type pieces (or we can say the KnightPiece)
 */

public abstract class LPiece extends ChessPiece {

    /**
     * LPiece constructor that takes in label, side, and board associated with the piece
     * @param label the label for the piece
     * @param side the side of the piece
     * @param board piece's associated board
     */
    public LPiece(String label, ChessGame.Side side, ChessBoard board) {
        super(label, side, board);
    }

    /**
     * determine whether the horse's move is legal
     * @param toRow destination row
     * @param toColumn destination column
     * @return whether the horse's move is legal
     */
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

    /**
     * check whether the knight piece has made a legal move
     * @param toRow destination row
     * @param toColumn destination column
     * @return whether the knight piece has made a legal move
     */
    public boolean isLegalMove(int toRow, int toColumn) {
        System.out.println("L-type");

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

    /**
     * deal with actions after the move is done
     */
    public void moveDone() {}
}
