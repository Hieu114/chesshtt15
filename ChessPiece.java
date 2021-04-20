/**
 * Hieu Tran
 * an abstract class representing all pieces on both boards
 */

public abstract class ChessPiece {

    // the game board of the piece
    private ChessBoard board;

    // the player's side of each piece
    private ChessGame.Side side;

    // the label of the piece
    private final String label;

    // the icon of the piece
    public Object icon = null;

    // the row of the piece
    private int row;

    // the column of the piece
    private int column;

    // the boolean hasPawnMoved set to true only for pawns making first move
    private boolean hasPawnMoved = false;

    // the boolean hasKingMoved set to true only for kings making first move
    private boolean hasKingMoved = false;

    // the boolean rookFirstMove set to true only for rooks making first move
    private boolean hasRookMoved = false;

    // the boolean change to true when the soldier has pass middle boundary
    private boolean soldierPassBound = false;

    // ChessPiece constructor that takes in label, side, and board associated with the piece
    public ChessPiece(String label, ChessGame.Side side, ChessBoard board) {
        this.side = side;
        this.label = label;
        this.board = board;
    }

     //return the side associated with the piece
    public ChessGame.Side getSide() {
        return side;
    }

     // return the label of the piece
    public String getLabel() {
        return label;
    }


     // return the icon of the piece
    public Object getIcon() {
        return icon;
    }

    /**
     * move the piece to a new location
     * given the number of row and column
     */
    public void setLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * return whether the move is legal
     * given the row and column of destination
     */
    public abstract boolean isLegalMove(int toRow, int toColumn);

    /**
     * return the board of the piece
     */
    public ChessBoard getChessBoard() {
        return board;
    }

     //return the row of the piece
    public int getRow() {
        return row;
    }

    //return the column of the piece
    public int getColumn() {
        return column;
    }

    /**
     * determine whether it is a legal non-capture move
     * @param row destination row
     * @param column destination column
     * @return whether it is a legal non-capture move
     */
    public boolean isLegalNonCaptureMove(int row, int column) {
        return isLegalMove(row, column) && !getChessBoard().hasPiece(row, column);
    }

    /**
     * determine whether it is a legal capture move
     * @param row destination row
     * @param column destination column
     * @return whether it is a legal capture move
     */
    public boolean isLegalCaptureMove(int row, int column) {
        return getChessBoard().hasPiece(row, column)
                && isLegalMove(row, column)
                &&  getChessBoard().getPiece(row, column).getSide() != this.getSide();
    }

    //the move is done
    public abstract void moveDone();

     //return the boolean pawnFirstMove associated with the piece
    public boolean getHasPawnMoved() {
        return hasPawnMoved;
    }

    /**
     * update the boolean hasPawnMoved associated with the piece
     */
    public void setHasPawnMoved(boolean hasPawnMoved) {
        this.hasPawnMoved = hasPawnMoved;
    }

    //return whether the king has moved
    public boolean getHasKingMoved() {
        return hasKingMoved;
    }

    /**
     * update the boolean hasKingMoved associated with the piece
     */
    public void setHasKingMoved(boolean hasKingMoved) {
        this.hasKingMoved = hasKingMoved;
    }

    //return whether has the rook moved;
    public boolean getHasRookMoved() {
        return hasRookMoved;
    }

    //update whether the rook has moved
    public void setHasRookMoved(boolean hasRookMoved) {
        this.hasRookMoved = hasRookMoved;
    }

    //check if the piece is moving diagonally
    public boolean legalDiagonalPath(int toRow, int toColumn) {
        if (getLabel().equals("C")) {
            return false;
        }

        if (getLabel().equals("R")) {
            return false;
        }

        // the guard is out of the box
        if (getLabel().equals("G")) {
            if (Math.abs(getRow() - toRow) == 1 && Math.abs((getColumn() - toColumn)) == 1) {
                if (toColumn < 3 || toColumn > 5) {
                    return false;
                } else {
                    ChessGame.Side side = getChessBoard().getPiece(getRow(), getColumn()).getSide();
                    if (side == ChessGame.Side.NORTH) {
                        return toRow < 3;
                    }
                    else if (side == ChessGame.Side.SOUTH) {
                        return toRow > 6;
                    }
                }
            }
            else 
                return false;
        }

        // The elephant is crossing the river
        if (getLabel().equals("E")) {
            if (Math.abs(getRow() - toRow) == 2 && Math.abs((getColumn() - toColumn)) == 2) {
                ChessGame.Side side = getChessBoard().getPiece(getRow(), getColumn()).getSide();
                if ((side == ChessGame.Side.NORTH && toRow > 4)
                        || (side == ChessGame.Side.SOUTH && toRow < 5)) {
                    return false;
                }
            } 
            else 
                return false;
            
        }

        // the condition for a path to be diagonal
        if (Math.abs(toRow - getRow()) == Math.abs(toColumn - getColumn())) {

        if (getRow() - toRow == getColumn() - toColumn){
          for (int i = 1; i < Math.abs(getRow() - toRow); i++){
            if (getChessBoard().hasPiece(Math.min(toRow, getRow()) + i, Math.min(toColumn, getColumn()) + i))
              return false;
          }
          return true;
        }
        else {
          for (int i = 1; i < Math.abs(getRow() - toRow); i++){
            if (getChessBoard().hasPiece(Math.max(toRow, getRow()) - i, Math.min(toColumn, getColumn()) - i))
              return false;
          }
          return true;
        }
      }

        return true;
    }

    //check if the piece is moving in straight lines
    public boolean legalAxisPath(int toRow, int toColumn) {
        if (getLabel().equals("G")) {
            return false;
        }

        if (getLabel().equals("E")) {
            return false;
        }

        if (getLabel().equals("H")) {
            return false;
        }

        if (getLabel().equals("B")) {
            return false;
        }

            // check if there is any piece in the path
            if (!getLabel().equals("C")) {
                for (int i = Math.min(toColumn, getColumn()) + 1; i < Math.max(toColumn, getColumn()); ++i) {
                    if (getChessBoard().hasPiece(getRow(), i)) {
                        return false;
                    }
                }
            }
            else {
                if (getChessBoard().hasPiece(toRow, toColumn)) {
                    if (getChessBoard().getPiece(toRow, toColumn).getSide() == getChessBoard().getPiece(getRow(), getColumn()).getSide()) {
                        return false;
                    }
                    else {
          //horizontal case
          if(toRow == getRow()){
            if (toColumn > getColumn()){
            for (int i = 1; i < Math.abs(toColumn - getColumn()); i ++){
              if (getChessBoard().hasPiece(toRow, Math.min(toColumn, getColumn()) + i))
                return false;
            }
            return true;
            }
             else{
            for (int i = 1; i < Math.abs(toColumn - getColumn()); i ++){
              if (getChessBoard().hasPiece(toRow, Math.min(toColumn, getColumn()) - i))
                return false;
            }
            return true;
            }
          }
          //verticle case
          if(toColumn == getColumn()){
            if (toRow > getRow()){
            for (int i = 1; i <= Math.abs(toRow - getRow()); i ++){
              if (getChessBoard().hasPiece(toColumn, Math.min(toRow, getRow()) + i))
                return false;
            } 
            return true;
          }
            else{
            for (int i = 1; i <= Math.abs(toRow - getRow()); i++){
              if (getChessBoard().hasPiece(toColumn, Math.max(toRow, getRow()) - i))
                return false;
            } 
            return true;
          }
          }
          else
            return false;
        }
                    }
                }

        return true;
    }

    

    //return whether the soldier piece is crossing halfway of the board
    public boolean getSoldierPassBound() {
        return soldierPassBound;
    }

    //update whether the soldier piece is crossing halfway of the board
    public void setSoldierPassBound(boolean soldierPassBound) {
        this.soldierPassBound = soldierPassBound;
    }
}
