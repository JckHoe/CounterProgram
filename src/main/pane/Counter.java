package main.pane;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.pane.cache.FakeCache;

public class Counter extends Application {

    //Singleton instance of FakeCache
    public FakeCache cache = new FakeCache();

    //Font Objects
    public Font font0 = Font.font("Arial", FontWeight.SEMI_BOLD, 20);
    public Font font1 = Font.font("Arial", FontWeight.BOLD, 38);
    public Font font2 = Font.font("Helvetica", FontWeight.BOLD, 30);
    public Font font3 = Font.font("Helvetica", FontWeight.BOLD, 40);
    public Font font4 = Font.font("Helvetica", FontWeight.EXTRA_BOLD, 60);

    //Landing Page
    public void mainPage(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setVgap(25);
        pane.setHgap(5);
//        pane.setStyle("-fx-background-color: #add8e6");
        pane.setId("pane");
        Label desc = new Label("No. of Encounters ");
        desc.setFont(font1);
        desc.setStyle("-fx-text-fill: DarkBLue");
        Label lb = new Label("");
        lb.setText(String.valueOf(cache.getCount().get()));
        checkCount(lb);

        Label error = new Label("");
        error.setStyle("-fx-text-fill: Red");
        error.setFont(font0);

        Button add = new Button("Add Count + ");
        add.setId("shinyOrange");
        add.setOnAction(event -> {
            checkCount(lb);
            error.setText("");
            lb.setText(String.valueOf(cache
                    .addCount(Integer.parseInt(lb.getText()))
                    .get()
            ));
        });
        pane.setOnKeyPressed(e -> {
            int labelAmount = Integer.parseInt(lb.getText());
            if (e.getCode() == KeyCode.ENTER) {
                error.setText("");
                checkCount(lb);
                lb.setText(String.valueOf(cache
                        .addCount(Integer.parseInt(lb.getText()))
                        .get()
                ));
            } else if (e.getCode() == KeyCode.BACK_SPACE) {
                if (labelAmount <= 0) {
                    error.setText("You Can't Go Lower than 0!");
                } else {
                    error.setText("");
                    checkCount(lb);
                    lb.setText(String.valueOf(cache
                            .minusCount(labelAmount)
                            .get()
                    ));
                }
            }
        });

        Button minus = new Button("Minus Count - ");
        minus.setId("shinyOrange");
        minus.setOnAction(event -> {
            int labelAmount = Integer.parseInt(lb.getText());
            if (labelAmount <= 0) {
                error.setText("You Can't Go Lower than 0!");
            } else {
                error.setText("");
                checkCount(lb);
                lb.setText(String.valueOf(cache
                        .minusCount(labelAmount)
                        .get()
                ));
            }
        });

        Button reset = new Button("Reset Count");
        reset.setId("iphone");
        reset.setOnAction(e -> {
            lb.setText(String.valueOf(cache
                    .resetCount()
            ));
            checkCount(lb);
        });

        pane.add(desc, 0, 0);
        pane.add(lb, 0, 1);
        pane.add(add, 0, 2);
        pane.add(minus, 0, 3);
        pane.add(reset, 0, 4);
        pane.add(error, 0, 5);

        GridPane.setHalignment(add, HPos.CENTER);
        GridPane.setHalignment(reset, HPos.CENTER);
        GridPane.setHalignment(lb, HPos.CENTER);
        GridPane.setHalignment(minus, HPos.CENTER);
        GridPane.setHalignment(error, HPos.CENTER);

        Scene scene = new Scene(pane, 400, 500);
        scene.getStylesheets().add("main/resource/style.css");
        primaryStage.setTitle("Pokemon Shiny Counter");
        primaryStage.setScene(scene);
        primaryStage.getIcons()
                .add(
                        new Image("main/resource/icon.png")
                );
        primaryStage.show();
    }

    private void checkCount(Label lb) {
        if (Integer.parseInt(lb.getText()) >= 100 && Integer.parseInt(lb.getText()) < 1000) {
            lb.setFont(font3);
        } else if (Integer.parseInt(lb.getText()) >= 1000) {
            lb.setFont(font4);
        } else {
            lb.setFont(font2);
        }
    }

    public void start(Stage primaryStage) {
        mainPage(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
