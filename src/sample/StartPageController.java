package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StartPageController {
    String s1, s2;
    @FXML
    TextField Player_1, Player_2;

    @FXML
    protected void onAction() {
        try {
            Main.MainPage=FXMLLoader.load(getClass().getResource("sample.fxml"));
            ((Label) Main.MainPage.lookup("#player_1Name")).setText(Player_1.getText());
            ((Label) Main.MainPage.lookup("#player_2Name")).setText(Player_2.getText());
            s1 = Player_1.getText().toUpperCase();
            s2 = Player_2.getText().toUpperCase();
            String min;
            int i;
            if (s1.length() < s2.length())
                min = s1;
            else
                min = s2;
            for (i = 0; i < min.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    Controller.ch_1 = String.valueOf(s1.charAt(i));
                    Controller.ch_2 = String.valueOf(s2.charAt(i));
                    break;
                }
            }
            if (Controller.ch_1.equals("") && Controller.ch_2.equals("") && (s1.length() != s2.length())) {
                if (s1.equals(min)) {
                    Controller.ch_1 = "" + s1.charAt(i - 1);
                    Controller.ch_2 = "" + s2.charAt(i);
                } else {
                    Controller.ch_1 = ("" + s1.charAt(i)).toUpperCase();
                    Controller.ch_2 = ("" + s2.charAt(i - 1)).toUpperCase();
                }
                Main.GameStage.setScene(new Scene(Main.MainPage));
            } else
                {
                if ((s1.length() == s2.length()) && s1.equals(s2)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setContentText("Player name can not be same");
                    Player_1.setText("");
                    Player_2.setText("");
                    alert.showAndWait();
                } else {
                    Main.GameStage.setScene(new Scene(Main.MainPage));
                }
            }}
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter the name of both the players");
            alert.showAndWait();
        }
    }

    @FXML
    protected void setting() throws IOException {
        Main.GameStage.close();
        Main.SettingPage=FXMLLoader.load(getClass().getResource("setting.fxml"));
        Main.GameStage.setScene(new Scene(Main.SettingPage));
        Main.GameStage.show();
    }
}

