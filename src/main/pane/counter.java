package main.pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class counter extends Application{

	public static String dataFilePath = "src/main/cache/.data.txt";

	public void start(Stage primaryStage){
		File file = new File(dataFilePath);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String amount = "";
		while ( input.hasNext()){
			amount = input.next();
			
		}
		input.close();
		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(20,20,20,20));
		pane.setVgap(30);
		pane.setHgap(5);
		Label desc = new Label("Number of Resets");
		Label lb = new Label("");
		lb.setFont(Font.font("Arial",FontWeight.BOLD,30));
		lb.setText(amount);
		Button create = new Button("Count + ");
		create.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				int tempAmount = 0;
				File file = new File(dataFilePath);
				Scanner input = null;
				try {
					input = new Scanner(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				int amount = 0;;
				while ( input.hasNext()){
					amount = input.nextInt();
					
				}
				input.close();
				
				tempAmount = amount + 1;
				try (
					java.io.PrintWriter output = new java.io.PrintWriter(file);
				) {
					output.println(tempAmount);
					output.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	

				input = null;
				try {
					input = new Scanner(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String temp = "";
				while ( input.hasNext()){
					temp = input.next();
					
				}
				input.close();
				lb.setText(temp);
			}
		});
		pane.add(desc, 0, 0);
		pane.add(lb, 0, 1);
		pane.add(create, 0, 2);
		
		GridPane.setHalignment(create, HPos.CENTER);
		GridPane.setHalignment(lb, HPos.CENTER);
		
		Scene scene = new Scene(pane,200,200);
		primaryStage.setTitle("Counter Program");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main (String [] args){
		Application.launch(args);
	}
}
