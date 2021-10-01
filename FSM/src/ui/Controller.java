/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 1/10/2021
 */

package ui;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.Automaton;
import model.State;

public class Controller {

	// --------------------------------------------------------------------------------
	
	// Attributes of the JAVAFX in the UI

	@FXML
	private TextField statesTextField;

	@FXML
	private TextField inputTextField;

	@FXML
	private TextField outputTextField;

	@FXML
	private RadioButton mooreRadioButton;

	@FXML
	private RadioButton mealyRadioButton;

	@FXML
	private ToggleGroup option;

	@FXML
	private Button createButton;

	@FXML
	private Button resetButton;

	@FXML
	private Button saveButton;

	@FXML
	private Button reduceButton;

	@FXML
	private StackPane machinePane;

	// --------------------------------------------------------------------------------
	
	// Relation with the Automaton class

	private Automaton automaton;

	// --------------------------------------------------------------------------------
	
	// Special attributes of the Controller class

	private GridPane gtf; 

	private GridPane glp;

	private TextField texFieldInformation;

	// --------------------------------------------------------------------------------
	
	// Normal attributes of the Controller class

	private String[] states;

	private String[] inputs;

	private int click;
	
	// --------------------------------------------------------------------------------
	
	// Initialize method
	
	/**
	 * Method to initialize some information of the system.
	 */

	public void initialize() {

		automaton = new Automaton();

		click = -1;

		resetButton.setDisable(true);

		saveButton.setDisable(true);

		reduceButton.setDisable(true);

		machinePane.setDisable(true);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Create method
	
	/**
	 * Method to create an automaton in the system
	 * @param event JavaFX information
	 */

	@FXML
	public void create(ActionEvent event) {

		try {

			if(statesTextField.getText() != null && inputTextField.getText() != null && outputTextField.getText() != null) {

				String s = statesTextField.getText();

				states = s.split(",");

				String i = inputTextField.getText();

				inputs = i.split(",");

				statesTextField.setEditable(false);

				inputTextField.setEditable(false);

				outputTextField.setEditable(false);

				String[][] matrix = new String[states.length + 1][inputs.length + 1];

				automaton.setMatrix(matrix);

				if(mooreRadioButton.isSelected()) {

					String[][] finalMatrix = new String[automaton.getMatrix().length][automaton.getMatrix()[0].length+1];
					
					automaton.setMatrix(finalMatrix);
					
					structure(states, inputs);
					
					int row = inputs.length + 1;
					
					for (int a = 0 ; a < states.length ; a++) {
						
						TextField ntf = new TextField();
						
						ntf.setPrefWidth(30);
						
						ntf.setEditable(true);
						
						gtf.add(ntf, row, a + 1);
						
					}
					
					click = 2;

					resetButton.setDisable(false);

					machinePane.setDisable(false);

					saveButton.setDisable(false);

				} else if(mealyRadioButton.isSelected()) {

					structure(states, inputs);

					click = 1;

					resetButton.setDisable(false);

					machinePane.setDisable(false);

					saveButton.setDisable(false);

				} else {

					throw new NullPointerException();

				}

			} else {

				throw new NullPointerException();

			}

		} catch(NullPointerException e1) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Important information");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in all required data");

			alert.showAndWait();

		} catch(NumberFormatException e2) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Important information");
			alert.setHeaderText(null);
			alert.setContentText("Please validate that the data is written correctly.");

			alert.showAndWait();

		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Structure method
	
	/**
	 * Method to take the information of the principal structure of the system.
	 * @param states Information from the user
	 * @param inputs Information from the user
	 */

	public void structure(String[] states, String[] inputs) {
		
		machinePane.getChildren().clear();
		
		gtf = new GridPane();
		
		gtf.setAlignment(Pos.CENTER);
		
		texFieldInformation = new TextField("/");
		
		texFieldInformation.setDisable(true);
		
		texFieldInformation.setPrefWidth(45);
		
		gtf.add(texFieldInformation, 0, 0);

		for (int i = 0 ; i < states.length ; i ++) {
			
			TextField newTextField = new TextField(states[i]);
			
			newTextField.setDisable(true);
			
			newTextField.setPrefWidth(45);
			
			gtf.add(newTextField, 0, i + 1);
			
		}

		for (int i = 0 ; i < inputs.length ; i ++) {
			
			TextField newTextField2 = new TextField(inputs[i]);
			
			newTextField2.setDisable(true);
			
			newTextField2.setPrefWidth(45);
			
			gtf.add(newTextField2, i + 1, 0);
			
		}

		for (int i = 0 ; i < states.length ; i ++) {
			
			for (int j = 0 ; j < inputs.length ; j ++) {
				
				TextField newTextField3 = new TextField();
				
				newTextField3.setPrefWidth(45);
				
				newTextField3.setEditable(true);
				
				gtf.add(newTextField3, j + 1, i + 1);
				
			}
			
		}

		machinePane.getChildren().add(gtf);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Reduce method
	
	/**
	 * Method to reduce the automaton from the system.
	 * @param event JavaFX information
	 */

	@FXML
	public void reduce(ActionEvent event) {

		ArrayList<State> listState = automaton.getMinimizedAutomaton();

		machinePane.getChildren().clear();
		
		glp = new GridPane();
		
		glp.setAlignment(Pos.CENTER);
		
		TextField differentUI = new TextField("/"); 
		
		differentUI.setDisable(true);
		
		differentUI.setPrefWidth(30);
		
		glp.add(differentUI, 0, 0);

		for (int i = 0 ; i < automaton.getInputs().length ; i ++) {
			
			TextField tfni1 = new TextField("" + automaton.getInputs()[i]);
			
			tfni1.setDisable(true);
			
			tfni1.setPrefWidth(30);
			
			glp.add(tfni1, i + 1, 0);
			
		}

		for (int i = 0 ; i < listState.size() ; i ++) {
			
			TextField tfni2 = new TextField(listState.get(i).getName());
			
			tfni2.setDisable(true);
			
			tfni2.setPrefWidth(30);
			
			glp.add(tfni2, 0, i + 1);
			
		}

		if (click == 2) {
			
			for (int i = 0 ; i < listState.size() ; i ++) {
				
				for (int j = 0 ; j < automaton.getInputs().length ; j ++) {
					
					State sucessorState = listState.get(i).getSuState().get(j);
					
					String name = sucessorState.getName();
					
					TextField ftf = new TextField(name);
					
					glp.add(ftf, j + 1, i + 1);
					
				}
				
				TextField oftm = new TextField(listState.get(i).getResult()[0] + "");
				
				oftm.setDisable(true);
				
				oftm.setPrefWidth(30);
				
				glp.add(oftm, automaton.getInputs().length + 2, i + 1);
				
			}
			
		}else {
			
			for (int i = 0 ; i < listState.size() ; i ++) {
				
				for (int j = 0 ; j < automaton.getInputs().length ; j ++) {

					State finalSucessorState = listState.get(i).getSuState().get(j);
					
					char onePoint = listState.get(i).getResult()[j];
					
					String name = finalSucessorState.getName();
					
					TextField tf = new TextField(name + "," + onePoint);
					
					glp.add(tf, j + 1, i + 1);
					
				}
				
			}
			
		}
		
		machinePane.getChildren().add(glp);

	}
	
	// --------------------------------------------------------------------------------
	
	// Reset method
	
	/**
	 * Method to reset the information of the system.
	 * @param event JavaFx information
	 */

	@FXML
	public void reset(ActionEvent event) {

		statesTextField.setEditable(true);

		statesTextField.setText(null);

		inputTextField.setEditable(true);

		inputTextField.setText(null);

		outputTextField.setEditable(true);

		outputTextField.setText(null);

		mooreRadioButton.setSelected(false);

		mealyRadioButton.setSelected(false);

		resetButton.setDisable(true);

		machinePane.setDisable(true);

		machinePane.getChildren().clear();

		saveButton.setDisable(true);

		reduceButton.setDisable(true);

	}
	
	// --------------------------------------------------------------------------------
	
	// Save method
	
	/**
	 * Method to save the information of the automaton into the system.
	 * @param event JavaFX information
	 */

	@FXML
	public void save(ActionEvent event) {

		reduceButton.setDisable(false);

		takeState();

		char[] informationAutomaton1 = automaton.convertArray(inputs);
		
		char[] informationAutomaton2 = automaton.convertArray(outputTextField.getText().split(","));

		ArrayList<State> finalInformationStates = null;

		if (click == 1) {
			
			finalInformationStates = automaton.mealy(informationAutomaton1.length);
			
		}else {
			
			finalInformationStates = automaton.moore();
			
		}

		String[][] matrix = automaton.getMatrix();
		
		automaton = new Automaton(finalInformationStates, informationAutomaton1, informationAutomaton2);
		
		automaton.setMatrix(matrix);

	}
	
	// --------------------------------------------------------------------------------
	
	// CreateStates method
	
	/**
	 * Method to create a personally state
	 */

	private void takeState() {
		
		String[][] matrix = automaton.getMatrix();
		
		for (Node node : gtf.getChildren()) {

			int positionX = GridPane.getColumnIndex(node);
			
			int positionY = GridPane.getRowIndex(node);

			TextField finalTF = (TextField) node;
			
			String currentString = finalTF.getText();

			matrix[positionY][positionX] = currentString;

		}
		
		automaton.setMatrix(matrix);
		
	}
	
	// --------------------------------------------------------------------------------

}
