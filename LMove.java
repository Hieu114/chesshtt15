/* Hieu Tran.
 * class represents the move in L-shape */
public class LMove extends ChessPiece{
  //choose which piece to play
  public LMove(String label, ChessGame.Side side, ChessBoard board){
    super(label, side, board);
  }
  
  //return whether the move is legal
  public boolean isLegalMove(int toRow, int toColumn){
    if (getChessBoard().hasPiece(toRow, toColumn)){
      if (Math.abs(getRow() - toRow) == 2 && Math.abs(getColumn() - toColumn) == 1)
        return true;
      if (Math.abs(getRow() - toRow) == 1 && Math.abs(getColumn() - toColumn) == 2)
        return true;
      else
        return false;
    }
    else
      return false;
  }
  public void moveDone(){};
}