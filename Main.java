package application;

import java.awt.Image;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
			Parent root = FXMLLoader.load(getClass().getResource("intro.fxml"));
			
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("NASA API");
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
			}
	
	public static void main(String[] args) {
		
		Main.launch(args);
		
	}
	
}
