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
    
    private Automaton automaton;
    
    private GridPane gridToFill; 
    
    private GridPane gridLastPartition;
    
    private TextField txt;
    
    private String[] states;
    
    private String[] inputs;
    
    private int wichIsLastCliked;
    
    public void initialize() {
    	
    	automaton = new Automaton();
    	
    	wichIsLastCliked = -1;
    	
    	resetButton.setDisable(true);
    	
    	saveButton.setDisable(true);
    	
    	reduceButton.setDisable(true);
    	
    	machinePane.setDisable(true);
    }

    @FXML
    void create(ActionEvent event) {
    	
    	try {
    		
    		if(statesTextField.getText() != null && inputTextField.getText() != null && outputTextField.getText() != null) {
    			
    			String s = statesTextField.getText();
    			
    			states = s.split(",");
    			
    			String i = inputTextField.getText();
    			
    			inputs = i.split(",");
    			
    			statesTextField.setEditable(false);
    			
    			inputTextField.setEditable(false);
    			
    			outputTextField.setEditable(false);
    			
    			String[][] m = new String[states.length+1][inputs.length+1];
    			
    			automaton.setMatrix(m);
    			
    			if(mooreRadioButton.isSelected()) {
    				
    		    	String[][] ma = new String[automaton.getMatrix().length][automaton.getMatrix()[0].length+1];
    		    	automaton.setMatrix(ma);
    		    	scheme(states, inputs);
    		    	int j = inputs.length + 1;
    				for (int a = 0; a < states.length; a++) {
    					TextField tf = new TextField();
    					tf.setPrefWidth(30);
    					tf.setEditable(true);
    					gridToFill.add(tf, j, a + 1);
    				}
    				wichIsLastCliked = 2;
    				
    				resetButton.setDisable(false);
    				
    				machinePane.setDisable(false);
    				
    				saveButton.setDisable(false);
    				
    			} else if(mealyRadioButton.isSelected()) {
    				
    		    	scheme(states, inputs);
    		    	
    		    	wichIsLastCliked = 1;
    		    	
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
    
    public void scheme(String[] states, String[] inputs) {
    	machinePane.getChildren().clear();
		gridToFill = new GridPane();
		gridToFill.setAlignment(Pos.CENTER);
		txt = new TextField("/");
		txt.setDisable(true);
		txt.setPrefWidth(45);
		gridToFill.add(txt, 0, 0);

		for (int i = 0; i < states.length; i++) {
			TextField txtN = new TextField(states[i]);
			txtN.setDisable(true);
			txtN.setPrefWidth(45);
			gridToFill.add(txtN, 0, i+1);
		}

		for (int i = 0; i < inputs.length; i++) {
			TextField txtN = new TextField(inputs[i]);
			txtN.setDisable(true);
			txtN.setPrefWidth(45);
			gridToFill.add(txtN, i+1, 0);
		}

		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < inputs.length; j++) {
				TextField txtN = new TextField();
				txtN.setPrefWidth(45);
				txtN.setEditable(true);
				gridToFill.add(txtN, j+1, i+1);
			}
		}
		
		machinePane.getChildren().add(gridToFill);
	}

    @FXML
    void reduce(ActionEvent event) {
    	
    	ArrayList<State> list = automaton.getMinimizedAutomaton();

		machinePane.getChildren().clear();
		gridLastPartition = new GridPane();
		gridLastPartition.setAlignment(Pos.CENTER);
		TextField txt = new TextField("/"); 
		txt.setDisable(true);
		//btnReset.setDisable(false);
		txt.setPrefWidth(30);
		gridLastPartition.add(txt, 0, 0);

		for (int i = 0; i < automaton.getStimuli().length; i++) {
			TextField txtN = new TextField(""+automaton.getStimuli()[i]);
			txtN.setDisable(true);
			txtN.setPrefWidth(30);
			gridLastPartition.add(txtN, i+1, 0);
		}

		for (int i = 0; i < list.size(); i++) {
			TextField txtN = new TextField(list.get(i).getName());
			txtN.setDisable(true);
			txtN.setPrefWidth(30);
			gridLastPartition.add(txtN, 0, i+1);
		}

		if (wichIsLastCliked == 2) {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < automaton.getStimuli().length; j++) {
					State successor = list.get(i).getSuState().get(j);
					String name = successor.getName();
					TextField tf = new TextField(name);
					gridLastPartition.add(tf, j+1, i+1);
				}
				TextField tfm = new TextField(list.get(i).getResult()[0]+"");
				tfm.setDisable(true);
				tfm.setPrefWidth(30);
				gridLastPartition.add(tfm, automaton.getStimuli().length+2, i+1);
			}
		}else {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < automaton.getStimuli().length; j++) {

					State successor = list.get(i).getSuState().get(j);
					char c = list.get(i).getResult()[j];
					String name = successor.getName();
					TextField tf = new TextField(name+","+c);
					gridLastPartition.add(tf, j+1, i+1);
				}
			}
		}
		machinePane.getChildren().add(gridLastPartition);
    	
    }

    @FXML
    void reset(ActionEvent event) {
    	
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

    @FXML
    void save(ActionEvent event) {
    	
    	reduceButton.setDisable(false);

    	createStates();
    	
    	char[] i = automaton.convertArray(inputs);
    	char[] o = automaton.convertArray(outputTextField.getText().split(","));

    	ArrayList<State> s = null;
    	
    	if (wichIsLastCliked == 1) {
			s = automaton.mealy(i.length);
		}else {
			s = automaton.moore();
		}
    	/*a.setOutputs(o);
    	a.setStates(s);
    	a.setStimuli(i);
    	a.setType(t);*/
    	
    	String[][] m = automaton.getMatrix();
    	automaton = new Automaton(s, i, o);
    	automaton.setMatrix(m);
    	
    }
    
    private void createStates() {
    	String[][] m = automaton.getMatrix();
		for (Node node : gridToFill.getChildren()) {

			int c = GridPane.getColumnIndex(node);
			int r = GridPane.getRowIndex(node);

			TextField txt = (TextField)node;
			String s = txt.getText();
			
			m[r][c] = s;

		}
		automaton.setMatrix(m);
	}

}
