/**Hieu Tran
 */
public class SwingGameMain {

    //place all the pieces
    public static SwingChessBoard initEuropeanChess(ChessGame.Side firstPlayer) {
        ChessGame.Side secondPlayer = firstPlayer == ChessGame.Side.SOUTH ? ChessGame.Side.NORTH : ChessGame.Side.SOUTH;
        EuropeanChess europeanChess = new EuropeanChess(firstPlayer);
        SwingEuropeanChessDisplay swingEuropeanChessDisplay = new SwingEuropeanChessDisplay();
        SwingChessBoard swingChessBoard = new SwingChessBoard(8, 8, swingEuropeanChessDisplay, europeanChess);

        // add pawn
        for (int i = 0; i < 8; ++i) {
            swingChessBoard.addPiece(new PawnPiece(secondPlayer, swingChessBoard), 1, i);
            swingChessBoard.addPiece(new PawnPiece(firstPlayer, swingChessBoard), 6, i);
        }
        // add king
        swingChessBoard.addPiece(new KingPiece(secondPlayer, swingChessBoard), 0, 4);
        swingChessBoard.addPiece(new KingPiece(firstPlayer, swingChessBoard), 7, 4);

        // add Queen
        swingChessBoard.addPiece(new QueenPiece(secondPlayer, swingChessBoard), 0, 3);
        swingChessBoard.addPiece(new QueenPiece(firstPlayer, swingChessBoard), 7, 3);

        // add Bishop
        swingChessBoard.addPiece(new BishopPiece(secondPlayer, swingChessBoard), 0, 2);
        swingChessBoard.addPiece(new BishopPiece(secondPlayer, swingChessBoard), 0, 5);
        swingChessBoard.addPiece(new BishopPiece(firstPlayer, swingChessBoard), 7, 2);
        swingChessBoard.addPiece(new BishopPiece(firstPlayer, swingChessBoard), 7, 5);

        // add Knight
        swingChessBoard.addPiece(new KnightPiece(secondPlayer, swingChessBoard), 0, 1);
        swingChessBoard.addPiece(new KnightPiece(secondPlayer, swingChessBoard), 0, 6);
        swingChessBoard.addPiece(new KnightPiece(firstPlayer, swingChessBoard), 7, 1);
        swingChessBoard.addPiece(new KnightPiece(firstPlayer, swingChessBoard), 7, 6);

        // add Rook
        swingChessBoard.addPiece(new RookPiece(secondPlayer, swingChessBoard), 0, 0);
        swingChessBoard.addPiece(new RookPiece(secondPlayer, swingChessBoard), 0, 7);
        swingChessBoard.addPiece(new RookPiece(firstPlayer, swingChessBoard), 7, 0);
        swingChessBoard.addPiece(new RookPiece(firstPlayer, swingChessBoard), 7, 7);

        return swingChessBoard;
    }

    //place all the pieces of xianqi bpard
    public static SwingChessBoard initXianqi(ChessGame.Side firstPlayer) {
        ChessGame.Side secondPlayer = firstPlayer == ChessGame.Side.SOUTH ? ChessGame.Side.NORTH : ChessGame.Side.SOUTH;
        Xianqi xianqi = new Xianqi(firstPlayer);
        SwingEuropeanChessDisplay swingEuropeanChessDisplay = new SwingEuropeanChessDisplay();
        SwingChessBoard swingChessBoard = new SwingChessBoard(10, 9, swingEuropeanChessDisplay, xianqi);

        // add Soldier
        for (int i = 0; i < 9; ++i) {
            if (i % 2 == 0) {
                swingChessBoard.addPiece(new SoldierPiece(secondPlayer, swingChessBoard), 3, i);
                swingChessBoard.addPiece(new SoldierPiece(firstPlayer, swingChessBoard), 6, i);
            }
        }

        // add King
        swingChessBoard.addPiece(new XianqiKingPiece(secondPlayer, swingChessBoard), 0, 4);
        swingChessBoard.addPiece(new XianqiKingPiece(firstPlayer, swingChessBoard), 9, 4);

        // add Guard
        swingChessBoard.addPiece(new GuardPiece(secondPlayer, swingChessBoard), 0, 3);
        swingChessBoard.addPiece(new GuardPiece(secondPlayer, swingChessBoard), 0, 5);
        swingChessBoard.addPiece(new GuardPiece(firstPlayer, swingChessBoard), 9, 3);
        swingChessBoard.addPiece(new GuardPiece(firstPlayer, swingChessBoard), 9, 5);

        // add Elephant
        swingChessBoard.addPiece(new ElephantPiece(secondPlayer, swingChessBoard), 0, 2);
        swingChessBoard.addPiece(new ElephantPiece(secondPlayer, swingChessBoard), 0, 6);
        swingChessBoard.addPiece(new ElephantPiece(firstPlayer, swingChessBoard), 9, 2);
        swingChessBoard.addPiece(new ElephantPiece(firstPlayer, swingChessBoard), 9, 6);

        // add Horse
        swingChessBoard.addPiece(new HorsePiece(secondPlayer, swingChessBoard), 0, 1);
        swingChessBoard.addPiece(new HorsePiece(secondPlayer, swingChessBoard), 0, 7);
        swingChessBoard.addPiece(new HorsePiece(firstPlayer, swingChessBoard), 9, 1);
        swingChessBoard.addPiece(new HorsePiece(firstPlayer, swingChessBoard), 9, 7);

        // add Rook
        swingChessBoard.addPiece(new RookPiece(secondPlayer, swingChessBoard), 0, 0);
        swingChessBoard.addPiece(new RookPiece(secondPlayer, swingChessBoard), 0, 8);
        swingChessBoard.addPiece(new RookPiece(firstPlayer, swingChessBoard), 9, 0);
        swingChessBoard.addPiece(new RookPiece(firstPlayer, swingChessBoard), 9, 8);

        // add Cannon
        swingChessBoard.addPiece(new CannonPiece(secondPlayer, swingChessBoard), 2, 1);
        swingChessBoard.addPiece(new CannonPiece(secondPlayer, swingChessBoard), 2, 7);
        swingChessBoard.addPiece(new CannonPiece(firstPlayer, swingChessBoard), 7, 1);
        swingChessBoard.addPiece(new CannonPiece(firstPlayer, swingChessBoard), 7, 7);

        return swingChessBoard;
    }

    //init the board
    public static SwingChessBoard init(String input, ChessGame.Side firstPlayer) {
        if (input.toUpperCase().equals("CHESS")) {
            return SwingGameMain.initEuropeanChess(firstPlayer);
        }
        else if (input.toUpperCase().equals("XIANQI")) {
            return SwingGameMain.initXianqi(firstPlayer);
        }
        return null;
    }

}
