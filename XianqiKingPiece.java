/**Hieu Tran
  * a class representing the xiangqi piece
 */
public class XianqiKingPiece extends SPiece {

    /**
     * XianqiKingPiece constructor with default label "X"
     * @param side the side of the piece
     * @param board the associated board
     */
    public XianqiKingPiece(ChessGame.Side side, ChessBoard board) {
        super("X", side, board);
    }
}
