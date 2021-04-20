import javafx.scene.control.Button;

import java.awt.*;

/**
 * Hieu Tran
 * a class of xianqi display
 */
public class JavaFXXianqiDisplay implements JavaFXChessBoardDisplay {

    /** The primary color of the checkerboard */
    public static Color redColor   = Color.red;

    /** The secondary color of the checkerboard */
    public static Color blackColor = Color.black;

    /* The color of the SOUTH player */
    public final static String southPlayerColor = "-fx-background-color: #ffff00";

    /* The color of the NORTH player */
    public final static String northPlayerColor = "-fx-background-color: #00ff00";

    /* The color of the EAST player */
    public final static String eastPlayerColor = "-fx-background-color: #ffffff";

    /* The color of the WEST player */
    public final static String westPlayerColor = "-fx-background-color: #808080";

    /** The color used to highlight a square */
    public final static String highlightColor = "-fx-background-color: #0000ff";

    /**
     * Display a square that has no piece on it.  Will produce a red/black checkerboard.
     * @param button the button that is used for the chessboard square
     * @param row    the row of this square on the board
     * @param column the column of this square on the board
     */
    public void displayEmptySquare(Button button, int row, int column) {
        button.setStyle("-fx-base: coral");
        button.setText(null);
        button.setGraphic(null);
    }

    /**
     * Display a square that has a piece on it.
     * @param button the button that is used for the chessboard square
     * @param row    the row of this square on the board
     * @param column the column of this square on the board
     * @param piece  the piece that is on this square
     */
    public void displayFilledSquare(Button button, int row, int column, ChessPiece piece) {
        String pieceColor;

        switch (piece.getSide()) {
            case NORTH:   pieceColor = northPlayerColor;
                break;
            case SOUTH:   pieceColor = southPlayerColor;
                break;
            case EAST:    pieceColor = eastPlayerColor;
                break;
            default:      pieceColor = westPlayerColor;
        }

        button.setStyle(pieceColor);
        button.setText(piece.getLabel());
        button.setGraphic(null);
    }

    /**
     * Highlight a square of the board.
     * @param highlight  do we want the highlight on (true) or off (false)?
     * @param button     the button that is used for the chessboard square
     * @param row        the row of this square on the board
     * @param column     the column of this square on the board
     * @param piece      the piece (if any) that is on this square
     */
    public void highlightSquare(boolean highlight, Button button, int row, int column, ChessPiece piece) {
        if (highlight) {
            button.setStyle(highlightColor);
        }
        else if (piece == null)
            displayEmptySquare(button, row, column);
        else
            displayFilledSquare(button, row, column, piece);
    }
}
