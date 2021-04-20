//Hieu Tran
//european chess class

public class EuropeanChess implements ChessGame {

    // the side of the player who will go first
    public Side currentSide;

    // the first player that will be playing
    public Side firstPlayer;

    //constructor
    public EuropeanChess(Side side) {
        currentSide = side;
        firstPlayer = side;
    }

    //return the first player
    public Side getFirstPlayer() {
        return firstPlayer;
    }

    //return the number of rows
    @Override
    public int getNumRows() {
        return 8;
    }

    //return the number of columns
    @Override
    public int getNumColumns() {
        return 8;
    }

    //init the game
    @Override
    public void startGame(ChessBoard board) {
        SwingGameMain.initEuropeanChess(Side.SOUTH);
    }

    //return the next player playing
    public void setCurrentSide(Side side) {
        currentSide = side;
    }

    //determine if this is the right piece to play (on the right side)
    public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
        return piece.getChessBoard().hasPiece(row, column) && piece.getSide() == currentSide;
    }

    //make  the move
    public boolean makeMove(ChessPiece p, int toRow, int toColumn) {
        if (p.isLegalMove(toRow, toColumn)) {

            // remove the piece from the old place
            ChessPiece piece = p.getChessBoard().removePiece(p.getRow(), p.getColumn());

            piece.getChessBoard().addPiece(piece, toRow, toColumn);

            if (piece.getLabel() == "K") {
                // return whether the castle is threatened or not
                System.out.println("THREATENED: " + castleThreatened(piece, toRow, toColumn));
                piece.setHasKingMoved(true);
            }
            if (piece.getLabel() == "R") {
                piece.setHasRookMoved(true);
            }
            if (piece.getLabel() == "P") {
                piece.setHasPawnMoved(true);
            }
            setCurrentSide(currentSide == Side.NORTH? Side.SOUTH : Side.NORTH);
            return true;
        }
        return false;
    }

    //return whether the castle move is threatened
    public boolean castleThreatened(ChessPiece piece, int toRow, int toColumn) {
        if (piece.getChessBoard().hasPiece(3,2)
                && piece.getChessBoard().getPiece(3, 2).getSide() != piece.getSide() &&
                piece.getChessBoard().getPiece(3, 2).isLegalMove(toRow, 6)) {
            return true;
        }

        return false;
    }

    //return whether we can change the piece
    public boolean canChangeSelection(ChessPiece piece, int row, int column) {
        return true;
    }
}
