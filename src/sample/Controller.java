package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class Controller implements Initializable {
    static String winPlayerName = "";
     int points_1 = 0, points_2 = 0;
    static Color color, color1 = Color.valueOf("#810541"), color2 = Color.valueOf(
            "#806517");
    static HashSet<String> Memory = new HashSet<>();
    static String ch_1="", ch_2="";
    static Circle[] Point = new Circle[280];
    private int move_1 = 0, move_2 = 0;
    private final FileChooser fileChooser = new FileChooser();
    final Stage BrowseStage = new Stage();
    @FXML
    AnchorPane GameView;
    @FXML
    Circle face_1, face_2;
    @FXML
    Label pointsOfPlayer_1, movesOfPlayer_1, pointsOfPlayer_2,
            movesOfPlayer_2;
    @FXML
    Label player_1Name;
    @FXML
    Label player_2Name;
    @FXML
    AnchorPane user_1, user_2;
    @FXML
    ColorPicker pick_1, pick_2;
    @FXML
    ImageView backImg;
    private double ValueX = 22.0, ValueY = 14.0;
    String localPath = "D:\\";
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IntStream.range(0, 280).forEachOrdered(i -> {
            Point[i] = new Circle(Setting.radius);
            Point[i].setFill(Color.WHITE);
            Point[i].setStroke(Color.BLACK);
            Point[i].setCenterX(ValueX);
            Point[i].setCenterY(ValueY);
            Point[i].setId(String.valueOf(i));
            if ((i + 1) % 20 == 0 && (i + 1) != 280) {
                ValueY = ValueY + 50;
                ValueX = 22;

            } else {
                ValueX = ValueX + 56;
            }
            GameView.getChildren().add(Point[i]);
        });
        face_1.setFill(new ImagePattern(new Image(getClass().getResource("User.png").toString())));
        face_2.setFill(new ImagePattern(new Image(getClass().getResource("User.png").toString())));
        pick_1.setValue(Color.web("#810541"));
        pick_2.setValue(Color.web("#806517"));
        color = Color.web("#810541");
        color1 = Color.valueOf("#810541");
        color2 = Color.valueOf("#806517");
        if (!Setting.backgroundImage.equals(""))
            backImg.setImage(Setting.image);
        backImg.setOpacity(Setting.value);
    }

    @FXML
    protected void onMouseClicked(MouseEvent mouseEvent) {
        try {
            Circle circle = (Circle) mouseEvent.getTarget();
            Hover(Integer.parseInt(circle.getId()));
            click(Integer.parseInt(circle.getId()));
        } catch (Exception ignored) {
        }
    }

    private void Hover(int n) {
        Point[n].setFill(Color.ORANGERED);
    }

    private void click(int n) {
        if (n % 20 == 0) {
            Make_Line(n, n + 1);
        }
        if ((n + 1) % 20 == 0) {
            Make_Line(n, n - 1);
        }
        if ((n - 20) >= 0) {
            Make_Line(n, n - 20);
        }
        if ((n + 20) <= 279) {
            Make_Line(n, n + 20);
        }
        if (n % 20 != 0 && (n + 1) % 20 != 0) {
            Make_Line(n, n - 1);
            Make_Line(n, n + 1);
        }
    }

    @FXML
    protected void resetUser_1() {
        configuringFileChooser(fileChooser);
        String files = fileChooser.showOpenMultipleDialog(BrowseStage).toString();
        files = files.substring(1, files.length() - 1);
        localPath = files.substring(0, files.lastIndexOf("\\"));
        face_1.setFill(new ImagePattern(new Image(new File(files).toURI().toString())));
    }

    @FXML
    protected void resetUser_2() {
        configuringFileChooser(fileChooser);
        String files = fileChooser.showOpenMultipleDialog(BrowseStage).toString();
        files = files.substring(1, files.length() - 1);
        localPath = files.substring(0, files.lastIndexOf("\\"));
        face_2.setFill(new ImagePattern(new Image(new File(files).toURI().toString())));

    }

    @FXML
    protected void colorPicker_1() {
        user_1.setStyle("-fx-background-color:" + toRgb(pick_1.getValue()));
        color1 = pick_1.getValue();
        if (winPlayerName.equals(ch_1))
            color = pick_1.getValue();
        else
            color = pick_2.getValue();
        int bound = GameView.getChildren().size();
        IntStream.range(0, bound).filter(i -> GameView.getChildren().get(i).getId() != null &&
                GameView.getChildren().get(i).getId().contains("P1")).forEachOrdered(i -> ((Line) GameView.getChildren().get(i)).setStroke(color1));
    }

    @FXML
    protected void colorPicker_2() {
        user_2.setStyle("-fx-background-color:" + toRgb(pick_2.getValue()));
        color2 = pick_2.getValue();
        if (winPlayerName.equals(ch_1))
            color = pick_1.getValue();
        else
            color = pick_2.getValue();
        int bound = GameView.getChildren().size();
        IntStream.range(0, bound).filter(i -> GameView.getChildren().get(i).getId() != null &&
                GameView.getChildren().get(i).getId().contains("P2")).forEachOrdered(i -> ((Line) GameView.getChildren().get(i)).setStroke(color2));
    }

    private void Make_Line(int a, int b) {
        checkWin obj = new checkWin();
        Line line = new Line();
        line.setStrokeWidth(Setting.radius-2);
        if (Point[b].getFill() == Color.ORANGERED &&
                !Memory.contains(("" + a + "," + b)) &&
                !Memory.contains(("" + b + "," + a))) {
            if (a - b == 1) {
                line.setStartX(Point[a].getCenterX() - Point[a].getRadius());
                line.setEndX(Point[b].getCenterX() + Point[b].getRadius());
                line.setStartY(Point[a].getCenterY());
                line.setEndY(Point[b].getCenterY());
            }
            if (a - b == -1) {
                line.setStartX(Point[a].getCenterX() + Point[a].getRadius());
                line.setEndX(Point[b].getCenterX() - Point[b].getRadius());
                line.setStartY(Point[a].getCenterY());
                line.setEndY(Point[b].getCenterY());
            }
            if (a - b == 20) {
                line.setStartX(Point[a].getCenterX());
                line.setEndX(Point[b].getCenterX());
                line.setStartY(Point[a].getCenterY() - Point[a].getRadius());
                line.setEndY(Point[b].getCenterY() + Point[b].getRadius());
            }
            if (a - b == -20) {
                line.setStartX(Point[a].getCenterX());
                line.setEndX(Point[b].getCenterX());
                line.setStartY(Point[a].getCenterY() + Point[a].getRadius());
                line.setEndY(Point[b].getCenterY() - Point[b].getRadius());
            }
            Memory.add(("" + a + "," + b));
            if (color.equals(color1)) {
                if (!winPlayerName.equals(ch_1))
                    move_1++;
                else winPlayerName = "";
                movesOfPlayer_1.setText(String.valueOf(move_1));
                line.setId("P1");
                line.setStroke(color);
                color = color2;
            } else {
                if (!winPlayerName.equals(ch_2))
                    move_2++;
                else winPlayerName = "";
                movesOfPlayer_2.setText(String.valueOf(move_2));
                line.setStroke(color);
                line.setId("P2");
                color = color1;
            }
            GameView.getChildren().add(line);
            if (Setting.soundStatus.equals("on"))
                new MediaPlayer(new Media(getClass().getResource("ding.wav").toString())).play();
            IntStream.range(0, 280).forEachOrdered(i -> Point[i].setFill(Color.WHITE));
            obj.win(a, b, GameView, pointsOfPlayer_1, pointsOfPlayer_2);
        }
        if ((points_2+points_1)==247)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dots and Boxes | Information");
            if (points_1 == points_2) {
                if (move_1 > move_2)
                    alert.setContentText(player_2Name.getText() + "  is winner");
                else if (move_1 < move_2)
                    alert.setContentText(player_1Name.getText() + "  is winner");
                else alert.setContentText("Match is draw");

            } else {
                if (points_1 > points_2)
                    alert.setContentText(player_1Name.getText() + "  is winner");
                else alert.setContentText(player_2Name.getText() + " is winner");
            }
            alert.showAndWait();
        }
    }

    String toRgb(Color color) {
        String r = String.valueOf((color.getRed() * 255));
        String g = String.valueOf((color.getGreen() * 255));
        String b = String.valueOf((color.getBlue() * 255));
        return "rgb(" + r + "," + g + "," + b + ")";
    }

    public void configuringFileChooser(FileChooser fileChooser) {
        // Set title for FileChooser
        fileChooser.setTitle("Select Image");

        // Set Initial Directory
        fileChooser.setInitialDirectory(new File(localPath));

        // Add Extension Filters
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPEG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("Icon", "*.ico")
        );
    }
    @FXML
    protected void Option(ActionEvent actionEvent) throws IOException {
        MenuItem menuItem=(MenuItem) actionEvent.getSource();
        if (menuItem.getText().equals("New Game")){
            Memory.clear();
            Main.GameStage.close();
            Main.StartingPage=FXMLLoader.load(getClass().getResource("StartPage.fxml"));
            Main.GameStage.setScene(new Scene(Main.StartingPage));
            Main.GameStage.show();

        }
        if (menuItem.getText().equals("Quit")){
            System.exit(-1);
        }
        if(menuItem.getText().equals("Instructions")){
            Stage helpStage=new Stage();
            helpStage.setTitle("Help");
            helpStage.initModality(Modality.APPLICATION_MODAL);
            helpStage.setMaximized(true);
            helpStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("help.fxml"))));
            helpStage.getIcons().add(new Image(getClass().getResource("icon.png").toString()));
            helpStage.show();
        }
        if(menuItem.getText().equals("About")){
            Stage aboutStage =new Stage();
            aboutStage.setTitle("About");
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("about.fxml"))));
            aboutStage.getIcons().add(new Image(getClass().getResource("icon.png").toString()));
            aboutStage.show();
        }
    }
}
