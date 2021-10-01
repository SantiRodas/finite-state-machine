/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 1/10/2021
 */

package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	// --------------------------------------------------------------------------------
	
	// Relation with the Controller Class
	
	Controller controller;
	
	// --------------------------------------------------------------------------------
	
	// Constructor method 
	
	/**
	 * Constructor method of the principal class of the system.
	 */
	
	public Main() {
		
		controller = new Controller();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Start method
	
	/**
	 * Original method from JavaFx to start the system.
	 * @param arg0 Original information of the JavaFx
	 * @throws Excepcion (All the exceptions)
	 */

	@Override
	public void start(Stage arg0) throws Exception {
		
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Window.fxml"));

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
	
	// --------------------------------------------------------------------------------
	
	// Main method
	
	/**
	 * Principal method of the system (All the system).
	 * @param args Original information of Java
	 */

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		launch(args);

	}
	
	// --------------------------------------------------------------------------------

}
