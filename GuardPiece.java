/**Hieu Tran
  * class of the guard piece
 */
public class GuardPiece extends SPiece {

    /**
     * GuardPiece constructor with default label "G"
     * @param side the side of the piece
     * @param board the associated board
     */
    public GuardPiece(ChessGame.Side side, ChessBoard board) {
        super("G", side, board);
    }
}
