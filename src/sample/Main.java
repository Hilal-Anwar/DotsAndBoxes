package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage GameStage=new Stage();
    static Parent StartingPage,MainPage,SettingPage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        StartingPage =FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        MainPage = FXMLLoader.load(getClass().getResource("sample.fxml"));
        SettingPage= FXMLLoader.load(getClass().getResource("setting.fxml"));
        GameStage.setTitle("Dots and Boxes");
        GameStage.setScene(new Scene(StartingPage));
        GameStage.setMaximized(true);
        GameStage.getIcons().add(new Image(getClass().getResource("icon.png").toString()));
        GameStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}