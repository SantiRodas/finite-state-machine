package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	Controller controller;
	
	public Main() {
		controller = new Controller();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(""));

			fxmlLoader.setController(controller);

			Parent root = fxmlLoader.load();

			Scene scene = new Scene(root);
			
			Stage primaryStage = new Stage();

			primaryStage.setScene(scene);

			primaryStage.setTitle("Finite state machine");

			primaryStage.show();

		} catch(Exception e) {
			
			e.printStackTrace();
			
		}

	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		launch(args);

	}

}
