package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

public class checkWin extends Controller {
    private int Value_1 = 0, Value_2 = 0;
    private Label L1,L2;
    void win(int a, int b, AnchorPane gameView, Label p1, Label p2)
    {
        //for vertical line
        L1=p1;L2=p2;
        if ((b - a) == 20 || (b - a) == -20)
        {
            //Right side up upward and downward
            if ((Memory.contains("" + a + "," + (a + 1)) || Memory.contains("" + (a + 1) + "," + a))
                    && (Memory.contains("" + b + "," + (b + 1)) || Memory.contains("" + (b + 1) + "," + b)) &&
                    (Memory.contains("" + (a + 1) + "," + (b + 1)) || Memory.contains("" + (b + 1) + "," + (a + 1)))) {
                Text Won = new Text();
                Won.setStyle("-fx-font: normal bold 35px '20th Century Font'");
                Won.setFill(Setting.textColor);
                Won.setY(((Point[a].getCenterY()+Point[b].getCenterY())/2)+10);
                Won.setX(Point[a].getCenterX() + 18);
                System.out.println("1");
                if (color.equals(color1)) {
                    Value_1 = 1;
                    Won.setText("" + ch_2);
                    applyWinPlayer_2();
                    color = color2;
                }
                else
                {
                    Won.setText(ch_1);
                    applyWinPlayer_1();
                    color = color1;
                    Value_2 = 1;
                }
                gameView.getChildren().add(Won);
            }
            //left side upward and downward
            if ((Memory.contains("" + a + "," + (a - 1)) || Memory.contains("" + (a - 1) + "," + a))
                    && (Memory.contains("" + b + "," + (b - 1)) || Memory.contains("" + (b - 1) + "," + b)) &&
                    (Memory.contains("" + (a - 1) + "," + (b - 1)) || Memory.contains("" + (b - 1) + "," + (a - 1)))) {
                Text Won = new Text();
                Won.setStyle("-fx-font: normal bold 35px '20th Century Font'");
                Won.setFill(Setting.textColor);
                Won.setY(((Point[a].getCenterY()+Point[b].getCenterY())/2)+10);
                System.out.println("2");
                Won.setX(Point[a].getCenterX() - 38);
                if (((color.equals(color1) || Value_1 == 1)) && Value_2 == 0){
                    Won.setText(ch_2);
                    applyWinPlayer_2();
                    color = color2;
                } else {
                    Won.setText(ch_1);
                    applyWinPlayer_1();
                    color = color1;
                }
                gameView.getChildren().add(Won);
            }
        }
        //for horizontal line
        if (((b - a) == 1) || ((b - a) == -1)) {
            //right side backward and forward
            if ((Memory.contains("" + a + "," + (a + 20)) || Memory.contains("" + (a + 20) + "," + a))
                    && (Memory.contains("" + b + "," + (b + 20)) || Memory.contains("" + (b + 20) + "," + b)) &&
                    (Memory.contains("" + (a + 20) + "," + (b + 20)) || Memory.contains("" + (b + 20) + "," + (a + 20)))) {
                Text Won = new Text();
                Won.setStyle("-fx-font: normal bold 35px '20th Century Font'");
                Won.setFill(Setting.textColor);
                Won.setX(((Point[a].getCenterX()+Point[b].getCenterX())/2)-10);
                Won.setY(Point[a].getCenterY()+ 35);
                if (color.equals(color1)) {
                    Value_1 = 1;
                    Won.setText("" + ch_2);
                    applyWinPlayer_2();
                    color = color2;
                } else {
                    Won.setText(ch_1);
                    color = color1;
                    applyWinPlayer_1();
                    Value_2 = 1;
                }
                gameView.getChildren().add(Won);
            }
            //left side backward and forward
            if ((Memory.contains("" + a + "," + (a - 20)) || Memory.contains("" + (a - 20) + "," + a))
                    && (Memory.contains("" + b + "," + (b - 20)) || Memory.contains("" + (b - 20) + "," + b)) &&
                    (Memory.contains("" + (a - 20) + "," + (b - 20)) || Memory.contains("" + (b - 20) + "," + (a - 20)))) {
                Text Won = new Text();
                Won.setStyle("-fx-font: normal bold 35px '20th Century Font'");
                Won.setFill(Setting.textColor);
                Won.setX(((Point[a].getCenterX()+Point[b].getCenterX())/2)-10);
                Won.setY(Point[a].getCenterY() - 15);
                if (((color.equals(color1) || Value_1 == 1)) && Value_2 == 0) {
                    Won.setText("" + ch_2);
                    applyWinPlayer_2();
                    color = color2;
                } else {
                    applyWinPlayer_1();
                    Won.setText(ch_1);
                    color =color1;
                }
                gameView.getChildren().add(Won);
            }
        }
    }
    private void applyWinPlayer_1(){
        winPlayerName=ch_1;
        points_1++;
        L1.setText(String.valueOf(points_1));
        if (Setting.soundStatus.equals("on"))
            new MediaPlayer(new Media(getClass().getResource("player_1.wav").toString())).play();
    }
    private void applyWinPlayer_2(){
        winPlayerName=ch_2;
        points_2++;
        L2.setText(String.valueOf(points_2));
        if (Setting.soundStatus.equals("on"))
            new MediaPlayer(new Media(getClass().getResource("player_2.wav").toString())).play();
    }
}