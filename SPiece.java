/**Hieu Tran
  * a class similar to straight move
 */

import javax.swing.*;

public abstract class SPiece extends ChessPiece {

    //constructor
    public SPiece(String label, ChessGame.Side side, ChessBoard board) {
        super(label, side, board);
    }

    //return whether the right king
    private boolean legalKing(ChessPiece p, int toRow, int toColumn) {
        // European North South Game
        if (p.getLabel().equals("K")) {
            // check if the movement of the king exceeds 1 step
            if (Math.max(toRow, getRow()) > Math.min(toRow, getRow()) + 1 || Math.max(toColumn, getColumn()) > Math.min(toColumn, getColumn()) + 1) {
                if (legalCastle(toColumn)) {
                    // castle to the left
                    if (getColumn() > toColumn) {
                        // remove the king from the board
                        ChessPiece king = getChessBoard().removePiece(getRow(), getColumn());

                        getChessBoard().addPiece(king, toRow, toColumn);

                        // remove the rook at column 0 from the board
                        ChessPiece rook = getChessBoard().removePiece(getRow(), 0);

                        getChessBoard().addPiece(rook, getRow(), toColumn + 1);
                    } 
                    else {
                        // remove the king from the board
                        ChessPiece king = getChessBoard().removePiece(getRow(), getColumn());

                        getChessBoard().addPiece(king, toRow, toColumn);

                        // remove the rook at column 7 from the board
                        ChessPiece rook = getChessBoard().removePiece(getRow(), 7);

                        getChessBoard().addPiece(rook, getRow(), toColumn - 1);
                    }
                    return true;
                }
                return false;
            } 
            else 
                return true;
            
        }

        return true;
    }

    //return if it is a legal move
    private boolean legalXianqiKing(ChessPiece p, int toRow, int toColumn) {
        // Xianqi North South Game
        if (p.getLabel().equals("X")) {

            // check if out of palace
            if (toColumn < 3 || toColumn > 5) {
                return false;
            }

            if (p.getSide() == ChessGame.Side.NORTH && toRow > 2) {
                return false;
            }

            if (p.getSide() == ChessGame.Side.SOUTH && toRow < 7) {
                return false;
            }

            // check if the movement of the xianqi king exceeds 1 step
            if (Math.max(toRow, getRow()) > Math.min(toRow, getRow()) + 1 || Math.max(toColumn, getColumn()) > Math.min(toColumn, getColumn()) + 1) {
                return false;
            }
        }

        return true;
    }

    //is it a legal move for the soldier
    private boolean legalSoldier(ChessPiece p, int toRow, int toColumn) {
        // side of the piece
        ChessGame.Side side = p.getSide();

        if (p.getLabel().equals("S")) {
            if (Math.abs(getRow() - toRow) > 1 || Math.abs(getColumn() - toColumn) > 1) {
                return false;
            }
            if (side == ChessGame.Side.NORTH) {
                if (toRow < getRow()) {
                    return false;
                }
            }
            else if (side == ChessGame.Side.SOUTH) {
                if (toRow > getRow()) {
                    return false;
                }
            }
            if (!p.getSoldierPassBound()) {
                if (Math.abs(getRow() - toRow) != 1 && Math.abs(getColumn() - toColumn) != 0) {
                    return false;
                }
            }

            // determine whether the soldier has pass boundary
            if ((side == ChessGame.Side.NORTH && toRow >= 5)
                    || (side == ChessGame.Side.SOUTH && toRow <= 4)) {
                p.setSoldierPassBound(true);
            }
        }

        return true;
    }

    //is it the legal move for pawn
    private boolean legalPawn(ChessPiece p, int toRow, int toColumn) {
        if (p.getLabel().equals("P")) {
            // check whether the movement of the pawn exceeds one step
            if (Math.max(toRow, getRow()) > Math.min(toRow, getRow()) + 1) {
                if (p.getHasPawnMoved()) {
                    return false;
                } else {
                    p.setHasPawnMoved(true);
                    return Math.max(toRow, getRow()) == Math.min(toRow, getRow()) + 2;
                }
            } else {
                // check if the pawn movement is a capture move or not
                if ((p.getSide() == ChessGame.Side.SOUTH && getRow() > toRow)
                        || (p.getSide() == ChessGame.Side.NORTH && getRow() < toRow)) {
                    if (toColumn == getColumn() - 1 || toColumn == getColumn() + 1) {
                        return getChessBoard().hasPiece(toRow, toColumn)
                                && getChessBoard().getPiece(toRow, toColumn).getSide() != this.getSide();
                    }
                } else {
                    return false;
                }
                return true;
            }
        } else {
            return true;
        }
    }

    //upgrade the pawn
    private void upgradePawn(int toRow, int toColumn) {
        String s = JOptionPane.showInputDialog("Choose among: N B Q R", "input");
        char label = s != null ? s.charAt(0) : ' ';

        switch (label) {
          case 'N' : {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new KnightPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
          case 'B' : {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new BishopPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
          case 'Q' : {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new QueenPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
          case 'R' : {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new RookPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
          default : JOptionPane.showMessageDialog(null, "cannot update pawn");
        }
    }

    //is it legal to castle
    private boolean legalCastle(int toColumn) {
        if (!getHasKingMoved()) {
            if (getRow() == 0 || getRow() == 7) {
                boolean left = getColumn() - 2 == toColumn && getChessBoard().getPiece(getRow(), 0).getHasRookMoved();
                boolean right = getColumn() + 2 == toColumn && getChessBoard().getPiece(getRow(), 7).getHasRookMoved();
                return !(left || right);
            }
        }
        return false;
    }

    //return if it is a legal move
    public boolean isLegalMove(int toRow, int toColumn) {
        if (getRow() == toRow && getColumn() == toColumn) {
            return false;
        }

        if ((getChessBoard().getPiece(getRow(), getColumn()).getSide() == ChessGame.Side.SOUTH
                && toRow == 0 && getRow() == 1)
                || (getChessBoard().getPiece(getRow(), getColumn()).getSide() == ChessGame.Side.NORTH
                && toRow == 7 && getRow() == 6)) {
            if (toColumn == getColumn() - 1 || toColumn == getColumn() + 1 || toColumn == getColumn()) {
                upgradePawn(toRow, toColumn);
            }
        }

        if (getChessBoard().hasPiece(toRow, toColumn)) {
            if (getChessBoard().getPiece(toRow, toColumn).getSide() == this.getSide()) {
                return false;
            }
        }

        return legalSoldier(this, toRow, toColumn) && legalXianqiKing(this, toRow, toColumn) && legalPawn(this, toRow, toColumn) && legalKing(this, toRow, toColumn) && (legalDiagonalPath(toRow, toColumn) || legalAxisPath(toRow, toColumn));
    }

    public void moveDone() {}
}
