package Tic_Tac_Toe;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class chooseMode implements Initializable {
    public Button nextBtn;
    public ComboBox<gameMode> comboBox;

    public enum gameMode {
        AI,Player2
    }
// function for determine that you want to play with AI or with second player
    public void nextAction() throws IOException {
//         pass that you want to play with AI
        if (comboBox.getSelectionModel().getSelectedItem().equals(gameMode.AI))
            gameController.detectPlayWith(gameMode.AI);
        if (comboBox.getSelectionModel().getSelectedItem().equals(gameMode.Player2))
            gameController.detectPlayWith(gameMode.Player2);
//        get the stage
        Stage stage = (Stage) nextBtn.getScene().getWindow();
        stage.close();
//        declare the new stage
        stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Image/tic-tac-toe_39453.png")));
        Parent root = FXMLLoader.load(getClass().getResource("TicTacToe.fxml"));
        stage.setTitle("Tic Tac Toe");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        gameController.getStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.getItems().addAll(gameMode.values());
    }


}
