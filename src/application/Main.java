package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("ASSIGNMENT5.fxml"));
			Scene scene = new Scene(root, 1000, 820);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Project 11 FXML");
			primaryStage.show();
			
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) 
	{
		launch(args);
		
	}
}
