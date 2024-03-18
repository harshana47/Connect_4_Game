package lk.ijse.dep.service;

public interface Board {
    //public
    int NUM_OF_ROWS=5;
    //public
    int NUM_OF_COLS=6;

    BoardUI getBoardUI();
    int findNextAvailableSpot(int col);

    boolean isLegalMove(int col);

    void updateMove(int col, boolean isHuman);

    void updateMove(int col, boolean isHuman, Piece move);

    boolean existLegalMove();
    void updateMove(int col,Piece move);
    Winner findWinner();

    Piece[][] getPieces();
}