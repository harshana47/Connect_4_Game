package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public class BoardImpl implements Board{

    Piece[][]pieces;

    BoardUI boardUI;

    BoardController boardController;

    public BoardImpl(BoardController boardController) {
        pieces=new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        this.boardController =boardController;
        boardUI=boardController;

        for (int i = 0; i <NUM_OF_COLS; i++) {
            for (int j = 0; j <NUM_OF_ROWS; j++) {
                pieces[i][j]=Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardController;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = NUM_OF_ROWS - 1; i >= 0; i--) { // outofbound error eka fix karanna -1 damma
            if (pieces[col][i] == Piece.EMPTY) {
                return i;  // 1st empty spot eka return karanwa
            }
        }
        return -1;  // column eka full nam -1 return karnawa
    }


    @Override
    public boolean isLegalMove(int col) {
        int place = findNextAvailableSpot(col);
        if (place==-1){
            return false;
        }
        return true;
    }

    @Override
    public void updateMove(int col, boolean isHuman) {

    }

    @Override
    public void updateMove(int col, boolean isHuman, Piece move) {

    }

    @Override
    public boolean existLegalMove() {
        for (int col = 0; col < NUM_OF_COLS; col++) {
            if (isLegalMove(col)) {
                return true;  // legal move exist da kyala balanwa
            }
        }
        return false;  // nattam meka
    }

    @Override
    public void updateMove(int col,Piece move) {
        int spot = findNextAvailableSpot(col);
        try{
            pieces[col][spot]=move;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("array index out - updateMove");
        }
    }

    public Winner findWinner() {
        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                Piece currentPiece = pieces[i][j];
                if (currentPiece != Piece.EMPTY) {
                    // horizontal check
                    if (i + 3 < NUM_OF_COLS &&
                            currentPiece == pieces[i + 1][j] &&
                            currentPiece == pieces[i + 2][j] &&
                            currentPiece == pieces[i + 3][j]) {
                        return new Winner(currentPiece, i, j, i + 3, j); //spot eka return karanawa
                    }

                    // vertical check
                    if (j + 3 < NUM_OF_ROWS) {
                        if (currentPiece == pieces[i][j + 1] &&
                                currentPiece == pieces[i][j + 2] &&
                                currentPiece == pieces[i][j + 3]) {
                            return new Winner(currentPiece, i, j, i, j + 3);
                        }
                    }
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }

    @Override
    public Piece[][] getPieces(){
        return pieces;
    }
}