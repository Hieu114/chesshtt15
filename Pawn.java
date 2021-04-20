/* Hieu Tran.
 * Pawn class */
import javax.swing.*;
public class Pawn extends StraightMove{
  //declare the piece Pawn
  public Pawn(ChessGame.Side side, ChessBoard board){
    super(side, board, "P");
  }  
  
  public boolean isLegalMove(int toRow, int toColumn){
    if (getChessBoard().hasPiece(toRow, toColumn) && toColumn == getColumn()){
        if ((toRow - getRow() == 1) && !getChessBoard().hasPiece(toRow,toColumn)){
          if (getHasPawnMoved() == false)
            setHasPawnMoved(true);
          if (toColumn == 7){
            upgradePawn(toRow, toColumn);
          }
          return true;
        }
        if ((toRow - getRow() == 2) && !getChessBoard().hasPiece(toRow,toColumn) && getHasPawnMoved() == false && !getChessBoard().hasPiece(toRow + 1,toColumn)){
          setHasPawnMoved(true);
          return true;
        }
        else
          return false;
    }
  
  else
    return false;
  }
  
  private void upgradePawn(int toRow, int toColumn) {
        // take the input of the user to update the pawn
        String s = JOptionPane.showInputDialog("Available Options: N B Q R");

        switch (s) {
          case "N" : {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new Knight(getSide(), getChessBoard()), toRow, toColumn);
            }
          case "B" : {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new BishopPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
          case "Q" : {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new QueenPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
          case "R" : {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new RookPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
          default : JOptionPane.showMessageDialog(null, "INVALID PAWN UPDATE");
        }
    }
  public void moveDone(){};
}