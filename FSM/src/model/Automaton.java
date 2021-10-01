/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 1/10/2021
 */

package model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;

public class Automaton {
	
	// --------------------------------------------------------------------------------
	
	// Special attributes of the Automaton class
	
	private ArrayList<State> states;
	
	private HashMap<State, Integer> ind;
	
	// --------------------------------------------------------------------------------
	
	// Normal attributes of the Automaton class
	
	private char[] inputs;
	
	private char[] outputs;
	
	private String[][] matrix;
	
	// --------------------------------------------------------------------------------
	
	// Constructor method  - 1
	
	/**
	 * Constructor method 1 of the Automaton class.
	 * @param states ArrayList of the states
	 * @param stimuli Char array with the input
	 * @param outputs Char array with the output
	 */
	
	public Automaton(ArrayList<State> states, char[] inputs, char[] outputs) {
		
		this.states = states;
		
		this.inputs = inputs;
		
		this.outputs = outputs;
		
		ind = new HashMap<>();
		
		generateIndex();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Constructor method  - 2
	
	/**
	 * Constructor method 2 of the Automaton class.
	 */
	
	public Automaton() {
	
	}
	
	// --------------------------------------------------------------------------------
	
	// GenerateIndex method
	
	/**
	 * Method to generate index using a "for" as a principal structure.
	 */
	
	private void generateIndex(){
		
		for (int i = 0; i < states.size(); i++) {
			
			ind.put(states.get(i), i);
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetStates method
	
	/**
	 * Method to have all the states of the system.
	 * @return ArrayList of the states
	 */

	public ArrayList<State> getStates(){
		
		return states;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetStimuli method
	
	/**
	 * Method to have all the inputs of the system.
	 * @return Char Array with the inputs
	 */

	public char[] getInputs(){
		
		return inputs;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetOutputs method
	
	/**
	 * Method to have all the outputs of the system.
	 * @return Char Array with the outputs
	 */

	public char[] getOutputs(){
		
		return outputs;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetMatrix method
	
	/**
	 * Method to have the matrix of the system.
	 * @return String matrix with the information that we needed
	 */

	public String[][] getMatrix() {
		
		return matrix;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetMatrix method
	
	/**
	 * Method to change the matrix with a new information.
	 * @param matrix String matrix with the information
	 */

	public void setMatrix(String[][] matrix) {
		
		this.matrix = matrix;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetStates method
	
	/**
	 * Method to change the states with a new information.
	 * @param states ArrayList of states with some information
	 */

	public void setStates(ArrayList<State> states) {
		
		this.states = states;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetStimuli method
	
	/**
	 * Method to change the inputs with a new information.
	 * @param inputs Char Array with a new information of the inputs
	 */

	public void setInputs(char[] inputs) {
		
		this.inputs = inputs;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetOutputs method
	
	/**
	 * Method to change the outputs with a new information.
	 * @param ouputs Char Array with a new information of the outputs
	 */

	public void setOutputs(char[] outputs) {
		
		this.outputs = outputs;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetLinked method
	
	/**
	 * Method to have the information of the linked list.
	 * @return ArrayList of states with all the information
	 */
	
	public ArrayList<State> getLinked(){
		
		dfs();
		
		ArrayList<State> cs = new ArrayList<>();
		
		for (State s : states) {
			
			if (s.isVisited()) {
				
				cs.add(s);
				
			}
			
		}
		
		return cs;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// DFS method
	
	/**
	 * Method to search in depth.
	 */
	
	private void dfs(){

		for (State s : states) {
			
			s.setVisited(false);
			
		}

		Stack<State> stackStates = new Stack<State>();
		
		boolean[] visited = new boolean[states.size()];
		
		State start = states.get(0);
		
		stackStates.push(start);

		while (!stackStates.isEmpty()){
			
			State current = stackStates.pop();
			
			int i = ind.get(current);
			
			visited[i] = true;
			
			states.get(i).setVisited(true);

			for (State s : current.getSuState()) {
				
				if (!visited[ind.get(s)]) {
					
					stackStates.push(s);
					
				}
				
			}

		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetFirstPartition method
	
	/**
	 * Method to do the first partition of the system.
	 * @return ArrayList of states with the information that we needed
	 */
	
	private ArrayList<ArrayList<State>> getFstPartition(){ 
		
		ArrayList<State> listStates = getLinked();

		for (State state : listStates) {
			
			state.setVisited(false);
			
		}

		ArrayList<ArrayList<State>> lPartition = new ArrayList<>();
		
		ArrayList<State> block;
		
		String case1;
		
		String case2;
		
		int number = 0;

		for (int i = 0; i < listStates.size()-1; i++) {
			
			if (!listStates.get(i).isVisited()) {
				
				listStates.get(i).setVisited(true);
				
				listStates.get(i).setCurrent(number);
				
				listStates.get(i).changeCurrent();
				
				block = new ArrayList<>();
				
				block.add(listStates.get(i));
				
				for (int j = i + 1 ; j < listStates.size() ; j ++) {
					
					if (!listStates.get(j).isVisited()) {
						
						case1 = String.valueOf(listStates.get(i).getResult());
						
						case2 = String.valueOf(listStates.get(j).getResult());

						if (case1.equals(case2)) {
							
							listStates.get(j).setVisited(true);
							
							listStates.get(j).setCurrent(number);
							
							listStates.get(j).changeCurrent();
							
							block.add(listStates.get(j));
							
						}

					}
					
				}
				
				lPartition.add(block);
				
				number ++ ;
				
			}
			
		}
		
		return lPartition;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// LastPartition method
	
	/**
	 * Method to do the last partition of the system.
	 * @return ArrayList of states with the information that we needed
	 */
	
	private ArrayList<ArrayList<State>> lastPartition(){
		
		ArrayList<ArrayList<State>> listFirstPartition = getFstPartition();
		
		ArrayList<ArrayList<State>> listLastPartition = new ArrayList<>();
		
		ArrayList<State> currentState;
		
		boolean bePartition = true;

		while (bePartition) {
			
			int count = 0;
			
			for (ArrayList<State> a : listFirstPartition) {
				
				for (State state : a){
					
					state.setVisited(false);
					
					state.changeCurrent();
					
				}
				
			}
			
			for (ArrayList<State> current : listFirstPartition) {
				
				for (int i = 0; i < current.size(); i ++) {	
					
					if (current.get(i).isVisited() == false) {
						
						current.get(i).setVisited(true);
						
						current.get(i).setCurrent(count);
						
						currentState = new ArrayList<>();
						
						currentState.add(current.get(i));
						
						for (int j = i + 1 ; j < current.size(); j ++) {
							
							if (current.get(j).isVisited() == false) { 
								
								if (samePlace(current.get(i), current.get(j))) {
									
									current.get(j).setCurrent(count);
									
									current.get(j).setVisited(true);
									
									currentState.add(current.get(j));
									
								}
								
							}
							
						}
						
						listLastPartition.add(currentState);
						
						count ++ ;
						
					}
					
				}
				
			}
			
			if (!listFirstPartition.equals(listLastPartition)) {
				
				listFirstPartition = new ArrayList<>(listLastPartition);
				
				listLastPartition = new ArrayList<>();
				
			}else{
				
				bePartition = false;
				
			}
			
		}
		
		return listLastPartition;
		
	} 
	
	// --------------------------------------------------------------------------------
	
	// SamePlace method
	
	/**
	 * Method to know if two states are in the same place.
	 * @param state1 Object 1 type state
	 * @param state2 Object 2 type state
	 * @return Boolean data where true represents same place, and false not
	 */
	
	private boolean samePlace(State state1, State state2){
		
		boolean isSamePlace = true;
		
		int posititon1, posititon2;
		
		for (int i = 0; i < state1.getSuState().size() && isSamePlace; i ++) {
			
			posititon1 = state1.getSuState().get(i).getPrevC();
			
			posititon2 = state2.getSuState().get(i).getPrevC();
			
			if (posititon1 != posititon2) {
				
				isSamePlace = false;
				
			}
			
		}
		
		return isSamePlace;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetMinimedAutomaton method
	
	/**
	 * Method to minimized the automaton in the system.
	 * @return ArrayList of states with the information that we needed
	 */
	
	public ArrayList<State> getMinimizedAutomaton(){
		
		ArrayList<ArrayList<State>> listStates = lastPartition();
		
		ArrayList<State> emptyDataState = new ArrayList<>();	
		
		State state;
		
		int position = 0;
		
		for (int i = 0; i < listStates.size(); i++) {
			
			String n = "Q" + position;
			
			position ++ ;
			
			char[] atc = listStates.get(i).get(0).getResult();
			
			state = new State(n, atc);
			
			emptyDataState.add(state);
			
		}
		
		for (int i = 0; i < emptyDataState.size(); i++) {
			
			for (int j = 0; j < inputs.length; j++) {
				
				int index = listStates.get(i).get(0).getSuState().get(j).getCurrent();
				
				emptyDataState.get(i).addsuState(emptyDataState.get(index));
				
			}	
			
		}
		
		return emptyDataState;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// MOORE method
	
	/**
	 * Method to do the structure of MOORE in the automaton of the system.
	 * @return ArrayList of states with the information that we needed
	 */
	
	public ArrayList<State> moore() {
		
		ArrayList<State> listStates = new ArrayList<State>();
		
		HashMap<String, State> hashMap = new HashMap<>();
		
		for (int i = 1; i < matrix.length; i ++) {
			
			String position = matrix[i][matrix[i].length - 1];
			
			char dimension = position.charAt(0);
			
			char[] result = new char[] {dimension};
			
			String name = matrix[i][0];
			
			State stateN = new State(name, result);
			
			hashMap.put(name, stateN);
			
			listStates.add(stateN);
			
		}

		for (int i = 0 ; i < matrix.length-1 ; i ++) {
			
			for (int j = 1 ; j < matrix[i].length - 1 ; j ++) {
				
				String name = matrix[i + 1][j];
				
				State mooreState = hashMap.get(name);
				
				listStates.get(i).addsuState(mooreState);
				
			}
			
		}
		
		return listStates;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// MEALY method
	
	/**
	 * Method to do the structure of MEALY in the automaton of the system.
	 * @param dimension Size of the automaton that we need to validate
	 * @return ArrayList of states with the information that we needed
	 */
	
	public ArrayList<State> mealy(int dimension) {
		
		ArrayList<State> listStates = new ArrayList<State>();
		
		HashMap<String, State> hashMap = new HashMap<>();
		
		for (int i = 1 ; i < matrix.length ; i ++) {
			
			String[] result = new String[dimension];
			
			for (int j = 1; j < matrix[i].length; j++) {
				
				String[] structure = matrix[i][j].split(",");
				
				result[j - 1] = structure[1];
				
			}
			
			char[] finalResult = convertArray(result);
			
			String name = matrix[i][0];
			
			State secondState = new State(name, finalResult);
			
			hashMap.put(name, secondState);
			
			listStates.add(secondState);
			
		}
		
		for (int i = 0 ; i < matrix.length - 1 ; i ++) {
			
			for (int j = 0 ; j < matrix[i].length - 1 ; j ++) {
				
				String[] secondStructure = matrix[i + 1][j + 1].split(",");
				
				String name = secondStructure[0];
				
				State NState = hashMap.get(name);
				
				listStates.get(i).addsuState(NState);
				
			}
			
		}
		
		return listStates;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// ConverArray method
	
	/**
	 * Method to take a string array and create a new char array.
	 * @param information Original information that we need to take
	 * @return Char array with the new information
	 */
	
	public char[] convertArray(String[] information) {

		char[] finalResult = new char[information.length];

		for (int i = 0 ; i < information.length ; i ++) {
			
			char position = information[i].charAt(0);
			
			finalResult[i] = position;
			
		}

		return finalResult;

	}
	
	// --------------------------------------------------------------------------------

}
