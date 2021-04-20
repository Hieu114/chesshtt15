/* Hieu Tran.
 * class represents the horizontal, diagonal, and vertical movement */
public abstract class StraightMove extends ChessPiece{
  //choose which piece to play straight move
  public StraightMove(ChessGame.Side side, ChessBoard board, String label){
    super(label, side, board );
  }  
  
  public abstract boolean isLegalMove(int toRow, int toColumn);
}