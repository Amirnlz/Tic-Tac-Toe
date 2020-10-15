package Tic_Tac_Toe;

public class  Artificial_Intelligence {
    public static Board.CellState human = Board.CellState.X;
    public static Board.CellState ai = Board.CellState.O;

// calculate the best move of AI with miniMax algorithm
    public static Integer[] bestMove() {
        Integer[] bestMove = null;
        int bestScore = -100;
        for (Integer[] position : Board.unTakenMoves()) {
            Board.setCell(position, ai);
            int score = miniMax();
            Board.setCell(position, Board.CellState.EMPTY);
            if (score > bestScore) {
                bestScore = score;
                bestMove = position;
            }
        }
        return bestMove;
    }

// the miniMax algorithm
    public static int miniMax () {
        Board.CellState winner = Board.gameState();
        int score;
        if (winner != null) {
            if (winner == ai) {
                score = 1;
                return score;
            } else if (winner == Board.CellState.EMPTY) {
                score = 0;
                return score;
            } else {
                score = -1;
                return score;
            }
        }
        int aiCount = 0;
        int humanCount = 0;
        for (int i = 0; i < Board.getBoard().length; i++) {
            for (int j = 0; j < Board.getBoard()[i].length; j++) {
                if (Board.getBoard()[i][j] == ai) {
                    aiCount++;
                } else if (Board.getBoard()[i][j] == human) humanCount++;
            }
        }
        int bestScore;
        if (humanCount > aiCount)
            bestScore = -1;
        else
            bestScore = 1;

        for (Integer[] position : Board.unTakenMoves()) {
            Board.setCell(position, humanCount > aiCount ? ai : human);
            int currentScore = miniMax();
            Board.setCell(position, Board.CellState.EMPTY);
            if (humanCount > aiCount ? currentScore > bestScore : currentScore < bestScore)
                bestScore = currentScore;
        }
        return bestScore;
    }
}
