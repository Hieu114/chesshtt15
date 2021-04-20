/**
 * Hieu Tran
 * a class representing javafx chess board
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Scanner;

public class JavaFXChessBoard extends Application implements ChessBoard {

    // the game board
    private Scene board;

    // the squares of the board
    private Button[][] squares;

    // the number of rows
    private int squaresRow;

    // the number of columns
    private int squaresCol;

    // stores the pieces
    private ChessPiece[][] pieces;

    // global rules for this particular game
    private ChessGame gameRules;

    // rules for how to draw the chess board
    private JavaFXChessBoardDisplay boardDisplay;

    /**
     * Returns the rules of the game.
     * @return the rules of the game
     */
    public ChessGame getGameRules() {
        return gameRules;
    }

    /**
     * Changes the rules of the game
     * @param newRules the new rules for the game
     */
    public void setGameRules(ChessGame newRules) {
        this.gameRules = newRules;
    }

    /**
     * Returns the number of rows in the board.
     * @return the number of rows
     */
    public final int numRows() {
        return squares.length;
    }

    /**
     * Returns the number of columns in the board.
     * @return the number of columns
     */
    public final int numColumns() {
        return squares[0].length;
    }

    /**
     *  Adds a piece to the board at the desired location.  Any piece currently
     *  at that location is lost.
     *  @param piece   the piece to add
     *  @param row     the row for the piece
     *  @param col     the column for the piece
     */
    public void addPiece(final ChessPiece piece, final int row, final int col) {
        // set the piece on the board, tell the piece where it is, and then use the display rules to display the square
        // run the display code on the event dispatch thread

        pieces[row][col] = piece;
        piece.setLocation(row, col);
        boardDisplay.displayFilledSquare(squares[row][col], row, col, piece);
    }

    /**
     *  Removes a piece from the board
     *  @param row  the row of the piece
     *  @param col  the column of the piece
     *  @return  the piece removed of null if there was no piece at that square
     */
    public ChessPiece removePiece(final int row, final int col) {
        // remove the piece from the board, use the display rules to show an empty square,
        // and run the display code on the event dispatch thread
        ChessPiece save = pieces[row][col];
        pieces[row][col] = null;

        boardDisplay.displayEmptySquare(squares[row][col], row, col);

        return save;
    }

    /**
     *  Returns true if there is a piece at a specific location of the board.
     *  @param row   the row to examine
     *  @param col   the column to examine
     *  @return   true if there is a piece a this row and column and false
     *            if the square is empty
     */
    public boolean hasPiece(int row, int col) {
        return (pieces[row][col] != null);
    }

    /**
     *  Returns the chess piece at a specific location on the board.
     *  @param row   the row for the piece
     *  @param col   the column for the piece
     *  @return      the piece at the row and column or null if there is no piece there.
     */
    public ChessPiece getPiece(int row, int col) {
        return pieces[row][col];
    }

    /**
     * set the display of the board
     * @param boardDisplay the type of display
     */
    public void setBoardDisplay(JavaFXChessBoardDisplay boardDisplay) {
        this.boardDisplay = boardDisplay;
    }

    /**
     *
     * @param board the type of board
     */
    public void setBoard(Scene board) {
        this.board = board;
    }

    /** The code the responds when the user clicks on the game board */
    private class ChessAction implements EventHandler<ActionEvent> {
        private boolean firstPick = true;  // if true, we a selecting a piece
        private int pieceRow;              // remember row of selected piece
        private int pieceCol;              // remember column of selected piece

        /**
         * What we do when the user chooses the piece to move.
         * @param row the row of the chosen piece
         * @param col the column of the chosen piece
         */
        private void processFirstSelection(int row, int col) {
            if ((pieces[row][col] != null) &&
                    (getGameRules() == null || getGameRules().legalPieceToPlay(pieces[row][col], row, col))) {
                /*
                 * if this is the first pick and a square with a piece was picked,
                 * remember the piece's location and highlight the square.
                 */
                pieceRow = row;
                pieceCol = col;
                boardDisplay.highlightSquare(true, squares[row][col], row, col, pieces[row][col]);
                firstPick = false;
            }
        }

        /**
         * What we do when the user chooses the square to move the piece to.
         * @param row the row the piece will move to
         * @param col the column the piece will move to
         */
        private void processSecondSelection(int row, int col) {
            if (row == pieceRow && col == pieceCol)
                return;

            boolean moveMade = getGameRules().makeMove(pieces[pieceRow][pieceCol], row, col);

            // if the move was made or if it was not made and the user select a new piece, then reset to choose a new move
            if (moveMade || getGameRules().canChangeSelection(pieces[pieceRow][pieceCol], pieceRow, pieceCol)) {
                boardDisplay.highlightSquare(false, squares[pieceRow][pieceCol], pieceRow, pieceCol, pieces[pieceRow][pieceCol]);
                firstPick = true;
            }
        }

        /**
         *  Handle a button click.  The method alternates between selecting a piece
         *  and selecting any square.  After both are selected, the piece's
         *  legalMove is called, and if the move is legal, the piece is moved.
         *  @param e   the event that triggered the method
         */
        @Override
        public void handle(ActionEvent e) {
//            System.out.println("HANDLE IS CALLED");

            // get the button
            Button b = (Button) e.getSource();

            int col = -1;
            int row = -1;

            // first find which button (board square) was clicked.
            for (int i = 0; i < squares.length; i++) {
                for (int j = 0; j < squares[i].length; j++) {
                    if (squares[i][j].equals(b)) {
                        row = i;
                        col = j;
                    }
                }
            }

            if (firstPick) {
                processFirstSelection(row, col);
            }
            else {
                processSecondSelection(row, col);
            }
        }
    }

    /**
     * Returns true if a particular square is threatened by an opposing piece.
     * @param row     the row of the square
     * @param column  the column of the square
     * @param piece   a piece of the game
     * @return  true if the square can be attacked by a piece of an opposing side as the parameter piece
     */
    public boolean squareThreatened(int row, int column, ChessPiece piece) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (hasPiece(i,j) && getPiece(i, j).getSide() != piece.getSide() &&
                        getPiece(i, j).isLegalMove(row, column))
                    return true;
            }
        }
        return false;
    }

    private Button[][] init(int row, int col) {
        Button[][] buttons = new Button[row][col];
        pieces = new ChessPiece[row][col];

        ChessAction action = new ChessAction();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setOnAction(action);
                buttons[i][j].setPrefSize(60, 60);
            }
        }

        return buttons;
    }

    /**
     * Initialize the game
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        // command line processor
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        // create north south game
        ChessGame.Side first = ChessGame.Side.SOUTH;
        ChessGame.Side second = ChessGame.Side.NORTH;

        // initialize row and column
        if (input.toUpperCase().equals("CHESS")) {
            squaresRow = 8;
            squaresCol = 8;
        }
        else if (input.toUpperCase().equals("XIANQI")) {
            squaresRow = 10;
            squaresCol = 9;
        }

        // initialize square board
        squares = init(squaresRow, squaresCol);

        // add pieces to the game
        if (input.toUpperCase().equals("CHESS")) {
            setGameRules(new EuropeanChess(ChessGame.Side.SOUTH));
            setBoardDisplay(new JavaFXEuropeanChessDisplay());

            // display empty squares
            for (int i = 0; i < squaresRow; i++) {
                for (int j = 0; j < squaresCol; j++) {
                    boardDisplay.displayEmptySquare(squares[i][j], squaresRow, squaresCol);
                }
            }

            // add PawnPiece
            for (int i = 0; i < 8; ++i) {
                addPiece(new PawnPiece(second, this), 1, i);
                addPiece(new PawnPiece(first, this), 6, i);
            }

            // add KingPiece
            addPiece(new KingPiece(second, this), 0, 4);
            addPiece(new KingPiece(first, this), 7, 4);

            // add QueenPiece
            addPiece(new QueenPiece(second, this), 0, 3);
            addPiece(new QueenPiece(first, this), 7, 3);

            // add BishopPiece
            addPiece(new BishopPiece(second, this), 0, 2);
            addPiece(new BishopPiece(second, this), 0, 5);
            addPiece(new BishopPiece(first, this), 7, 2);
            addPiece(new BishopPiece(first, this), 7, 5);

            // add KnightPiece
            addPiece(new KnightPiece(second, this), 0, 1);
            addPiece(new KnightPiece(second, this), 0, 6);
            addPiece(new KnightPiece(first, this), 7, 1);
            addPiece(new KnightPiece(first, this), 7, 6);

            // add RookPiece
            addPiece(new RookPiece(second, this), 0, 0);
            addPiece(new RookPiece(second, this), 0, 7);
            addPiece(new RookPiece(first, this), 7, 0);
            addPiece(new RookPiece(first, this), 7, 7);
        }
        else if (input.toUpperCase().equals("XIANQI")) {
            setGameRules(new Xianqi(ChessGame.Side.SOUTH));
            setBoardDisplay(new JavaFXXianqiDisplay());

            // display empty squares
            for (int i = 0; i < squaresRow; i++) {
                for (int j = 0; j < squaresCol; j++) {
                    boardDisplay.displayEmptySquare(squares[i][j], squaresRow, squaresCol);
                }
            }

            // add SoldierPiece
            for (int i = 0; i < 9; ++i) {
                if (i % 2 == 0) {
                    addPiece(new SoldierPiece(second, this), 3, i);
                    addPiece(new SoldierPiece(first, this), 6, i);
                }
            }

            // add KingPiece
            addPiece(new XianqiKingPiece(second, this), 0, 4);
            addPiece(new XianqiKingPiece(first, this), 9, 4);

            // add GuardPiece
            addPiece(new GuardPiece(second, this), 0, 3);
            addPiece(new GuardPiece(second, this), 0, 5);
            addPiece(new GuardPiece(first, this), 9, 3);
            addPiece(new GuardPiece(first, this), 9, 5);

            // add ElephantPiece
            addPiece(new ElephantPiece(second, this), 0, 2);
            addPiece(new ElephantPiece(second, this), 0, 6);
            addPiece(new ElephantPiece(first, this), 9, 2);
            addPiece(new ElephantPiece(first, this), 9, 6);

            // add HorsePiece
            addPiece(new HorsePiece(second, this), 0, 1);
            addPiece(new HorsePiece(second, this), 0, 7);
            addPiece(new HorsePiece(first, this), 9, 1);
            addPiece(new HorsePiece(first, this), 9, 7);

            // add RookPiece
            addPiece(new RookPiece(second, this), 0, 0);
            addPiece(new RookPiece(second, this), 0, 8);
            addPiece(new RookPiece(first, this), 9, 0);
            addPiece(new RookPiece(first, this), 9, 8);

            // add CannonPiece
            addPiece(new CannonPiece(second, this), 2, 1);
            addPiece(new CannonPiece(second, this), 2, 7);
            addPiece(new CannonPiece(first, this), 7, 1);
            addPiece(new CannonPiece(first, this), 7, 7);
        }

        GridPane pane = new GridPane();
        pane.setPrefSize(squaresCol * 60,squaresRow * 60);

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                pane.add(squares[i][j], j, i);
            }
        }

        // create new scene
        Scene scene = new Scene(pane);

        // set scene to board
        setBoard(scene);
        board.setRoot(pane);

        // show the game
        stage.setScene(board);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
