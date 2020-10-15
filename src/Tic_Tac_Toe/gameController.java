package Tic_Tac_Toe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.*;
import static Tic_Tac_Toe.Artificial_Intelligence.bestMove;

public class gameController {
    public GridPane gridPane;
    public  Label lbl0;
    public  Label lbl1;
    public  Label lbl2;
    public  Label lbl3;
    public  Label lbl4;
    public  Label lbl5;
    public  Label lbl6;
    public  Label lbl7;
    public  Label lbl8;
    private static Stage stage ;
    private boolean nextTurn =false;
    private static boolean  isSinglePlayer;
    private boolean isPlayer2=true;

//    get stage to use it when wanna restart game
    public static void  getStage(Stage getStage){
        stage = getStage;
    }
//    determine game is with two player or with AI
    public static void detectPlayWith(chooseMode.gameMode mode){
        isSinglePlayer = mode.equals(chooseMode.gameMode.AI);
    }

    @FXML
    public void handleMouseClicked(MouseEvent event) {
        Label tempLabel = (Label) event.getSource();
        if (isSinglePlayer)  setSinglePlayer(tempLabel); else player2(tempLabel);
    }

    private void setSinglePlayer(Label tempLabel) {
//        check the selected cell be an empty
        if (Board.isCellEmpty(getPosition(tempLabel)) && !nextTurn) {
            setLabels(getPosition(tempLabel), Board.CellState.X);
            Board.setCell(getPosition(tempLabel), Board.CellState.X);
            nextTurn = true;
        }else
//         message for show that selected cell is has been taken
            JOptionPane.showMessageDialog(null, "This cell already taken!", "Help", JOptionPane.INFORMATION_MESSAGE);
//        if it not null it means player has won the game
        if (Board.gameState() != null) message(Board.gameState());
//        if it is true artificial intelligence will move
        if (nextTurn) {
//            it will do the AI turns in the game
            AiTurn();
            if (Board.gameState() != null) message(Board.gameState());
            nextTurn = false;
        }
    }

//   do the Ai turn at the game
    private void  AiTurn() {
        Integer[] bestMoveAI = bestMove();
        Board.setCell(bestMoveAI, Board.CellState.O);
        setLabels(bestMoveAI, Board.CellState.O);
    }

//    get position of the selected label
    private Integer[] getPosition(Label selectedLabel) {
        Integer[] selected = new Integer[2];
        selected[0] = GridPane.getColumnIndex(selectedLabel);
        selected[1] = GridPane.getRowIndex(selectedLabel);
        return selected;
    }

//   message for the when game end
        private void message(Board.CellState cellState) {
            int answer = 0;

            if (!isSinglePlayer) {
//                messages for single player game
                if (cellState == Board.CellState.O)
                    answer = JOptionPane.showConfirmDialog(null, "AI won the game :P \n" +
                            "Play again?!", "Question", JOptionPane.YES_NO_OPTION);
                else if (cellState == Board.CellState.X)
                    answer = JOptionPane.showConfirmDialog(null, "You won the game :D \n" +
                            "Play again?!", "Question", JOptionPane.YES_NO_OPTION);
                else if (Board.isTie() == 9)
                    answer = JOptionPane.showConfirmDialog(null, "The game is Tie :D \n" +
                            "Play again?!", "Question", JOptionPane.YES_NO_OPTION);
            }
//            messages that is for 2 player game
            else {
                if (cellState == Board.CellState.O)
                    answer = JOptionPane.showConfirmDialog(null, "Player1 won the game :P \n" +
                            "Play again?!", "Question", JOptionPane.YES_NO_OPTION);
                if (cellState == Board.CellState.X)
                    answer = JOptionPane.showConfirmDialog(null, "Player2 won the game :P \n" +
                            "Play again?!", "Question", JOptionPane.YES_NO_OPTION);
                if (Board.isTie() == 9)
                    answer = JOptionPane.showConfirmDialog(null, "The game is Tie :D \n" +
                            "Play again?!", "Question", JOptionPane.YES_NO_OPTION);
            }
            responseCode(answer);
        }
//    control game by selected option when game end
        private void responseCode(int response) {
            if (response == 0) {
                clearData();
                restartGame();
            }
            if (response == 1) System.exit(0);
        }

//    clear saved data
        public void clearData() {
            Label[][] labels = getLabels();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Board.state[i][j] = Board.CellState.EMPTY;
                    labels[i][j].setText(" ");
                }
            }
        }

//    restart the game and show new stage
        private void restartGame() {
            stage.show();
        }

//    return the labels to detect which label should be taken
        private   Label[][] getLabels() {
            return new Label[][]{{lbl0, lbl1, lbl2}, {lbl3, lbl4, lbl5}, {lbl6, lbl7, lbl8}};
        }

//    print the X or O at the game
        private void setLabels(Integer[] position, Board.CellState player) {
            Label[][] labels = getLabels();
            if (player == Board.CellState.X)
                labels[position[1]][position[0]].setText(String.valueOf(player));
            if (player == Board.CellState.O)
                labels[position[1]][position[0]].setText(String.valueOf(player));
        }

//    function is for 2 player game
        public void player2(Label selected) {
//            first player turn
            if (isPlayer2) {
//                if selected cell is unTaken the game will continue
                if (Board.isCellEmpty(getPosition(selected))) {
                    setLabels(getPosition(selected), Board.CellState.O);
                    Board.setCell(getPosition(selected), Board.CellState.O);
                    isPlayer2 = false;
                } else
                    JOptionPane.showMessageDialog(null, "This cell already taken!", "Help", JOptionPane.INFORMATION_MESSAGE);
                if (Board.gameState() != null) message(Board.gameState());
            }
//            for the next turn player
            else {
                if (Board.isCellEmpty(getPosition(selected))) {
                    setLabels(getPosition(selected), Board.CellState.X);
                    Board.setCell(getPosition(selected), Board.CellState.X);
                    isPlayer2 = true;
                    if (Board.gameState() != null) message(Board.CellState.X);
                }else
                    JOptionPane.showMessageDialog(null, "This cell already taken!", "Help", JOptionPane.INFORMATION_MESSAGE);
            }
        }
}
