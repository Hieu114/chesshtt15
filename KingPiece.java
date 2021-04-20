/**Hieu Tran
  * a class of pieces moving straight
 */
public class KingPiece extends SPiece {

    /**
     * KingPiece constructor with default label "K"
     * @param side the side of the piece
     * @param board the associated board
     */
    public KingPiece(ChessGame.Side side, ChessBoard board) {
        super("K", side, board);
    }

}
