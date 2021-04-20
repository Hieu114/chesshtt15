/**
 * Hieu Tran
 * a class representing Xianqi
 */

public class Xianqi implements ChessGame {

    // the side of the player who plays first
    public Side currentSide;

    // the first player that will be playing
    public Side firstPlayer;

   //constructor
    public Xianqi(Side currentSide) {
        this.currentSide = currentSide;
        this.firstPlayer = currentSide;
    }

    //return whether the piece is legal to play
    @Override
    public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
        return true;
    }

    //return if the kings are facing each other
    private static boolean facingKing(ChessPiece piece) {

        // initialize positions of north king
        int northCol = -1;
        int northRow = -1;

        // initialize positions of south king
        int southCol = -1;
        int southRow = -1;

        // get the board
        ChessBoard board = piece.getChessBoard();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.hasPiece(i, j) && board.getPiece(i, j).getLabel().equals("X")) {
                    ChessPiece king = board.getPiece(i, j);

                    if (king.getSide() == Side.NORTH) {
                        northCol = king.getColumn();
                        northRow = king.getRow();
                    }

                    if (king.getSide() == Side.SOUTH) {
                        southCol = king.getColumn();
                        southRow = king.getRow();
                    }
                }
            }
        }

        if (northCol == -1 && northRow == -1) {
            northCol = piece.getColumn();
            northRow = piece.getRow();
        }

        if (southCol== -1 && southRow == -1) {
            southCol = piece.getColumn();
            southRow = piece.getRow();
        }

        // see whether a piece preveting the king seeing each other
        if (northCol == southCol) {
            for (int i = Math.min(southRow, northRow) + 1; i < Math.max(southRow, northCol); i++) {
                if (piece.getChessBoard().hasPiece(i, northCol)) {
                    return false;
                }
            }
        }

        return true;
    }

    //make the move
    @Override
    public boolean makeMove(ChessPiece p, int toRow, int toColumn) {
        if (p.isLegalMove(toRow, toColumn)) {

            // remove the piece from the current position
            ChessPiece save = p.getChessBoard().removePiece(p.getRow(), p.getColumn());

            //make the move
            if (p.getColumn() == toColumn) {
                p.getChessBoard().addPiece(save, toRow, toColumn);
            }
            else {
                if (!facingKing(p)) {
                    p.getChessBoard().addPiece(save, toRow, toColumn);
                }
                else {
                    p.getChessBoard().addPiece(save, p.getRow(), p.getColumn());
                }
            }

            setCurrentSide(currentSide == Side.NORTH? Side.SOUTH : Side.NORTH);

            return true;
        }
        return false;
    }

    //return first player
    @Override
    public Side getFirstPlayer() {
        return firstPlayer;
    }

    //return the total number of rows
    @Override
    public int getNumRows() {
        return 10;
    }

    //return the number of columns
    @Override
    public int getNumColumns() {
        return 9;
    }

    //transfer the turn the the next player
    public void setCurrentSide(Side currentSide) {
        this.currentSide = currentSide;
    }

    //start
    @Override
    public void startGame(ChessBoard board) {
        SwingGameMain.initXianqi(Side.SOUTH);
    }

    //return if the the selection of a piece can be changed
    public boolean canChangeSelection(ChessPiece piece, int row, int column) {
        return true;
    }

    //MAIN
    public static void main(String[] args) {

        //side of the first player
        ChessGame.Side firstPlayer = Side.SOUTH;

        //side of the second player
        ChessGame.Side secondPlayer = Side.NORTH;

        // initialize the game
        Xianqi xianqi = new Xianqi(firstPlayer);

        // create a display
        SwingEuropeanChessDisplay swingEuropeanChessDisplay = new SwingEuropeanChessDisplay();

        // create the xianqi board
        SwingChessBoard swingChessBoard = new SwingChessBoard(10, 9, swingEuropeanChessDisplay, xianqi);

        // add KingPiece
        swingChessBoard.addPiece(new XianqiKingPiece(secondPlayer, swingChessBoard), 0, 4);
        swingChessBoard.addPiece(new XianqiKingPiece(firstPlayer, swingChessBoard), 9, 4);

        // add HorsePiece
        swingChessBoard.addPiece(new HorsePiece(firstPlayer, swingChessBoard),5, 4);
        swingChessBoard.addPiece(new HorsePiece(firstPlayer, swingChessBoard),6, 4);
    }
}
