/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 1/10/2021
 */

package model;

import java.util.ArrayList;

public class State {
	
	// --------------------------------------------------------------------------------
	
	// Normal attributes of the state class
	
	private boolean visited;
	
	private String name;
	
	private char[] result;
	
	private int current;
	
	private int prevC;
	
	// --------------------------------------------------------------------------------
	
	// Special attributes of the state class
	
	private ArrayList<State> suState;
	
	// --------------------------------------------------------------------------------
	
	// Constructor method
	
	/**
	 * Constructor method of the state class to create a object in another part of the system.
	 * @param name String data with a original information of the state
	 * @param result Char array where is going to save every part of the String
	 */
	
	public State(String name, char[] result) {
		
		this.name= name;
		
		visited= false;
		
		this.result= result;
		
		suState= new ArrayList<State>();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// IsVisited method
	
	/**
	 * Method to know if a state is visited.
	 * @return Boolean data where true represent visited, and false not
	 */

	public boolean isVisited() {
		
		return visited;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetVisited method
	
	/**
	 * Method to set the information of the visited.
	 * @param visited Boolean data with a new information
	 */

	public void setVisited(boolean visited) {
		
		this.visited = visited;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetName method
	
	/**
	 * Method to have the name of the state.
	 * @return String data with the information that we needed
	 */

	public String getName() {
		
		return name;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetName method
	
	/**
	 * Method to set the information of the name.
	 * @param name String data with a new information
	 */

	public void setName(String name) {
		
		this.name = name;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetResult method
	
	/**
	 * Method to have the result of the state.
	 * @return Char array with the information that we needed
	 */

	public char[] getResult() {
		
		return result;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetResult method
	
	/**
	 * Method to set the information of the result.
	 * @param result Char array with a new information
	 */

	public void setResult(char[] result) {
		
		this.result = result;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetCurrent method
	
	/**
	 * Method to have the current state.
	 * @return Integer data with the information that we needed
	 */

	public int getCurrent() {
		
		return current;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetCurrent method
	
	/**
	 * Method to set the current state.
	 * @param current Integer data with the index of the new current state
	 */

	public void setCurrent(int current) {
		
		this.current = current;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetPrevC method
	
	/**
	 * Method to have the index of the state current state -1.
	 * @return Integer data with the information that we needed
	 */

	public int getPrevC() {
		
		return prevC;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetPrevC method
	
	/**
	 * Method to set the current state -1.
	 * @param prevC Integer data with the new current state -1
	 */

	public void setPrevC(int prevC) {
		
		this.prevC = prevC;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetSuState method
	
	/**
	 * Method to have the state.
	 * @return State ArrayList with the information that we needed
	 */
	
	public ArrayList<State> getSuState() {
		
		return suState;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetSuState method
	
	/**
	 * Method to set the information of the state.
	 * @param suState State ArrayLis with a new information
	 */

	public void setSuState(ArrayList<State> suState) {
		
		this.suState = suState;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AddSuState method
	
	/**
	 * Method to add a SuState into the system.
	 * @param state New state that we want to include
	 */

	public void addsuState(State state) {
		
		suState.add(state);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// ChangeCurrent
	
	/**
	 * Method to change the current state of the system.
	 */
	
	public void changeCurrent() {
		
		prevC = current;
		
	}
	
	// --------------------------------------------------------------------------------
	
}
