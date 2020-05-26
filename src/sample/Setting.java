package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class Setting
{
    private final FileChooser fileChooser = new FileChooser();
    static String backgroundImage="";
    static double value=0.58;
    static double radius=12;
    static String soundStatus="on";
    static Image image;
    static Color textColor=Color.valueOf("#ff8300");
    @FXML
    RadioButton ON,OFF;
    @FXML
    TextField text,val;
    @FXML
    ColorPicker picker;
    @FXML
    Slider slider;
    @FXML
    MenuButton radiusMenu;
    @FXML
    protected void browse(){
        new Controller().configuringFileChooser(fileChooser);
        backgroundImage=fileChooser.showOpenMultipleDialog(new Controller().BrowseStage).toString();
        backgroundImage=backgroundImage.substring(1, backgroundImage.length() - 1);
        image=new Image(new File(backgroundImage).toURI().toString());
        text.setText(backgroundImage);
    }
    @FXML
    protected void opacity(){
          value=slider.getValue()/100;
          val.setText(""+(slider.getValue()/100));
    }
    @FXML
    protected void colorPicker(ActionEvent actionEvent){
        ColorPicker colorPicker= (ColorPicker) actionEvent.getSource();
        colorPicker.setValue(colorPicker.getValue());
        textColor=colorPicker.getValue();
    }
    @FXML
    protected void getRadius(ActionEvent actionEvent) {
        MenuItem menuItem= (MenuItem) actionEvent.getSource();
        if(menuItem.getText().equals("8")){
            radiusMenu.setText("8");
            radius=8;
        }
        if(menuItem.getText().equals("9")){
            radiusMenu.setText("9");
            radius=9;
        }
        if(menuItem.getText().equals("10")){
            radiusMenu.setText("10");
            radius=10;
        }
        if(menuItem.getText().equals("11")){
            radiusMenu.setText("11");
            radius=11;
        }
        if(menuItem.getText().equals("12")){
            radiusMenu.setText("12");
            radius=12;
        }
    }
    @FXML
    protected void apply() throws IOException {
        Main.GameStage.close();
       Main.StartingPage=FXMLLoader.load(getClass().getResource("StartPage.fxml"));
       Main.GameStage.setScene(new Scene(Main.StartingPage));
       Main.GameStage.show();
    }
    @FXML
    protected void sound(ActionEvent actionEvent){
        RadioButton butt= (RadioButton) actionEvent.getSource();
        if (butt.getText().equals("off")){
            soundStatus="off";
           OFF.setSelected(true);
           ON.setSelected(false);
        }
        else {
            soundStatus="on";
            OFF.setSelected(false);
            ON.setSelected(true);
        }
    }
}
