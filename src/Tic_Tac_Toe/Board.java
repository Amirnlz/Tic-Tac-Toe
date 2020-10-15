package Tic_Tac_Toe;

import java.util.ArrayList;

public class Board {
    public enum CellState{
        X,O,EMPTY
    }
//    declare the states of the game
    public static CellState[][] state = new CellState[][]{
            {CellState.EMPTY, CellState.EMPTY, CellState.EMPTY},
            {CellState.EMPTY, CellState.EMPTY, CellState.EMPTY},
            {CellState.EMPTY, CellState.EMPTY, CellState.EMPTY}
    };

//    return the game cellState
    public static CellState[][] getBoard() {
        return state;
    }

//    change the selected cell to player state
    public  static void setCell(Integer[] selected, CellState player){
        state[selected[0]][selected[1]] = player;
    }

//     check winner
    public static CellState gameState() {
        CellState checkingPlayer;
//        check Columns
        for (int j = 0; j < state.length; j++) {
            checkingPlayer = state[0][j];
            if (checkingPlayer == CellState.EMPTY)
                continue;
            for (int i = 0; i < state[j].length; i++) {
                if (state[i][j] != checkingPlayer) {
                    checkingPlayer = null;
                    break;
                }
            }
            if (checkingPlayer != null) return checkingPlayer;
        }
//        check Rows
        for (int i = 0; i < 3; i++) {
            checkingPlayer = state[i][0];
            if (checkingPlayer == CellState.EMPTY)
                continue;
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] != checkingPlayer) {
                    checkingPlayer = null;
                    break;
                }
            }
            if (checkingPlayer != null) return checkingPlayer;
        }

        //check Diagonals
        checkingPlayer = state[0][0];
        if (checkingPlayer == state[1][1] && checkingPlayer == state[2][2] && checkingPlayer != CellState.EMPTY)
            return checkingPlayer;

        checkingPlayer = state[2][0];
        if (checkingPlayer == state[1][1] && checkingPlayer == state[0][2] && checkingPlayer != CellState.EMPTY)
            return checkingPlayer;

//        check empty cells
        for (CellState[] states : state) {
            for (CellState cellState : states) {
                if (cellState == CellState.EMPTY)
                    return null;
            }
        }
        return CellState.EMPTY;
    }

// check the game if it is Tie or not
    public static int isTie() {
        int checker = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[j][i] == CellState.O || state[j][i] == CellState.X) checker++;
            }
        }
        return checker;
    }

// returns the available cells to play
    public static ArrayList<Integer[]> unTakenMoves() {
        ArrayList<Integer[]> unTakenMoves = new ArrayList<>();
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i][j] == CellState.EMPTY) {
                    unTakenMoves.add(new Integer[]{i, j});
                }
            }
        }
        return unTakenMoves;
    }

//    check the selected cell is empty or not
    public static boolean isCellEmpty(Integer[] position){
        return state[position[0]][position[1]] == CellState.EMPTY;
    }
}
