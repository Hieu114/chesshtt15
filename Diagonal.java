//Hieu Tran.
//The move of diagonal piece
public interface Diagonal {
  
  //retrieve the row
  int getRow();
  
  //retrieve the column
  int getColumn();
  
  ChessBoard getChessBoard();
  
  //Check if the move is legal
  default boolean isLegalMoveD(int toRow, int toColumn) {
    if (getChessBoard().hasPiece(toRow, toColumn)){
      if (Math.abs(getRow() - toRow) == Math.abs(getColumn() - toColumn)){
        if (getRow() - toRow == getColumn() - toColumn){
          for (int i = 1; i < Math.abs(getRow() - toRow); i++){
            if (getChessBoard().hasPiece(Math.min(toRow, getRow()) + i, Math.min(toColumn, getColumn()) + 1))
              return false;
          }
          return true;
        }
        else {
          for (int i = 1; i < Math.abs(getRow() - toRow); i++){
            if (getChessBoard().hasPiece(Math.max(toRow, getRow()) - i, Math.min(toColumn, getColumn()) + 1))
              return false;
          }
          return true;
        }
      }
      else
        return false;
      }
    else
      return false;
  } 
}