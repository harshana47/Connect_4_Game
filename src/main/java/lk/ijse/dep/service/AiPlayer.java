package lk.ijse.dep.service;

//Inheritance ai player player gen extend wenawa
public class AiPlayer extends Player {
    private Winner winner;

    //Board object parameter eka gannawa
    public AiPlayer(Board newBoard) {
        super(newBoard);
    }

    @Override
    public void movePiece(int col) {
        col = AiCOl(); // find the best column to make a move
        System.out.println("AI thinking: " + col);

        board.updateMove(col, Piece.GREEN); // move ekata anuwa board eka update karanawa
        board.getBoardUI().update(col, false); // ui eka update karanawa
        winner = board.findWinner(); // winner kenek innawada balnawa
        if (winner.getWinningPiece() != Piece.EMPTY) { // innawanm
            board.getBoardUI().notifyWinner(winner); // notify karanawa
        } else if (!board.existLegalMove()) { //winner knek nattam
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY)); //tie karanawa
        }
    }

    //best move eka hoyana part eka
    private int AiCOl() {
        Piece[][] pieces = board.getPieces();
        int col;

        //winnind moves check karnawa
        for (col = 0; col < Board.NUM_OF_COLS; col++) {
            if (board.isLegalMove(col)) {
                int row = board.findNextAvailableSpot(col); // next available spot eka check karanawa
                // e spot ekata green simulate karanawa
                pieces[col][row] = Piece.GREEN;
                Winner winner = board.findWinner();

                // e column ekata piece eka dala ai ta dinanna puluwannam e spot eka return karanawa
                if (winner.getWinningPiece() == Piece.GREEN) {
                    pieces[col][row] = Piece.EMPTY; // simulated move aka undo karnawa
                    return col;
                }

                // if eken eliyen giyorh undo karanne methanin
                pieces[col][row] = Piece.EMPTY;
            }
        }

        // enamy eka win karana ewa balala block karanawa
        for (col = 0; col < Board.NUM_OF_COLS; col++) {
            if (board.isLegalMove(col)) {
                int row = board.findNextAvailableSpot(col);
                // e spot ekata green simulate karanawa
                pieces[col][row] = Piece.BLUE;
                Winner opponentWinner = board.findWinner();

                // enemy eka ilaga move eken win karanwanam block karanawa
                if (opponentWinner.getWinningPiece() == Piece.BLUE) {
                    pieces[col][row] = Piece.EMPTY; // undo simulated move
                    return col;
                }

                // if eken out unot undo karanawa
                pieces[col][row] = Piece.EMPTY;
            }
        }

        // wining move or block akaranna naththam random move ekak danawa
        do {
            col = (int) (Math.random() * Board.NUM_OF_COLS);
        } while (!board.isLegalMove(col));

        return col;
    }
}