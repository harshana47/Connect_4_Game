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
        Piece[][] piece1 = board.getPieces();
        int col;
        for (int i = 0; i < 5; i++) {
            // Horizontal check
            if (piece1[i][0] == piece1[i][1] && piece1[i][1] == piece1[i][2] && piece1[i][0] == Piece.GREEN) {
                if (piece1[i][3] == Piece.EMPTY) {
                    return 3;
                }
            } else if (piece1[i][0] == piece1[i][1] && piece1[i][1] == piece1[i][2] && piece1[i][0] == Piece.BLUE) {
                if (piece1[i][3] == Piece.EMPTY) {
                    return 3;
                }
            }
            else if(piece1[1][i]==piece1[2][i] && piece1[2][i]==piece1[3][i] && piece1[1][i]==Piece.GREEN){
                if(piece1[4][i]==Piece.EMPTY){
                    return 4;
                }
            }
            else if(piece1[1][i]==piece1[2][i] && piece1[2][i]==piece1[3][i] && piece1[1][i]==Piece.BLUE){
                if(piece1[4][i]==Piece.EMPTY){
                    return 4;
                }
            }
            else if(piece1[2][i]==piece1[3][i] && piece1[3][i]==piece1[4][i] && piece1[2][i]==Piece.GREEN){
                if(piece1[5][i]==Piece.EMPTY){
                    return 5;
                }
            }
            else if (piece1[2][i]==piece1[3][i] && piece1[3][i]==piece1[4][i] && piece1[2][i]==Piece.BLUE){
                if(piece1[5][i]==Piece.EMPTY){
                    return 5;
                }
            }
            else if(piece1[3][i]==piece1[2][i] && piece1[2][i]==piece1[1][i] && piece1[3][i]==Piece.GREEN){
                if(piece1[0][i]==Piece.EMPTY){
                    return 0;
                }
            }
            else if(piece1[3][i]==piece1[2][i] && piece1[2][i]==piece1[1][i] && piece1[3][i]==Piece.BLUE){
                if(piece1[0][i]==Piece.EMPTY){
                    return 0;
                }
            }
            else if(piece1[4][i]==piece1[3][i] && piece1[3][i]==piece1[2][i] && piece1[3][i]==Piece.GREEN){
                if(piece1[1][i]==Piece.EMPTY){
                    return 1;
                }
            }
            else if(piece1[4][i]==piece1[3][i] && piece1[3][i]==piece1[2][i] && piece1[3][i]==Piece.BLUE){
                if(piece1[1][i]==Piece.EMPTY){
                    return 1;
                }
            }
            else if(piece1[5][i]==piece1[4][i] && piece1[4][i]==piece1[3][i] && piece1[5][i]==Piece.GREEN){
                if(piece1[2][i]==Piece.EMPTY){
                    return 2;
                }
            }
            else if(piece1[5][i]==piece1[4][i] && piece1[4][i]==piece1[3][i] && piece1[5][i]==Piece.BLUE){
                if(piece1[2][i]==Piece.EMPTY){
                    return 2;
                }
            }
        }
        do {
            col = (int) (Math.random() * 6);
        } while (!board.isLegalMove(col));

        return col;
    }
}