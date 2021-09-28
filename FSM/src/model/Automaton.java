package model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;

public class Automaton {
	private ArrayList<State> states;
	private char[] stimuli;
	private char[] outputs;
	private String[][] matrix;
	private HashMap<State, Integer> ind;
	
	public Automaton(ArrayList<State> states, char[] stimuli, char[] outputs) {
		this.states = states;
		this.stimuli = stimuli;
		this.outputs = outputs;
		ind = new HashMap<>();
		generateIndex();
	}
	public Automaton() {
	
	}
	
	private void generateIndex(){
		for (int i = 0; i < states.size(); i++) {
			ind.put(states.get(i), i);
		}
	}

	public ArrayList<State> getStates(){
		return states;
	}

	public char[] getStimuli(){
		return stimuli;
	}

	public char[] getOutputs(){
		return outputs;
	}

	public String[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(String[][] matrix) {
		this.matrix = matrix;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	public void setStimuli(char[] stimuli) {
		this.stimuli = stimuli;
	}

	public void setOutputs(char[] outputs) {
		this.outputs = outputs;
	}
	
	public ArrayList<State> getLinked(){
		dfs();
		ArrayList<State> connectedStates = new ArrayList<>();
		for (State s : states) {
			if (s.isVisited()) {
				connectedStates.add(s);
			}
		}
		return connectedStates;
	}
	
	private void dfs(){

		for (State s : states) {
			s.setVisited(false);
		}

		Stack<State> st = new Stack<State>();
		boolean[] visited = new boolean[states.size()];
		State begin = states.get(0);
		st.push(begin);

		while (!st.isEmpty()){
			State curr = st.pop();
			int i = ind.get(curr);
			visited[i] = true;
			states.get(i).setVisited(true);

			for (State s : curr.getSuState()) {
				if (!visited[ind.get(s)]) {
					st.push(s);
				}
			}

		}
	}
	
	private ArrayList<ArrayList<State>> getFstPartition(){ 
		ArrayList<State> tSta = getLinked();

		for (State state : tSta) {
			state.setVisited(false);
		}

		ArrayList<ArrayList<State>> list = new ArrayList<>();
		ArrayList<State> block;
		String c;
		String c1;
		int enumerate = 0;


		for (int i = 0; i < tSta.size()-1; i++) {
			
			if (!tSta.get(i).isVisited()) {
				tSta.get(i).setVisited(true);
				tSta.get(i).setCurrent(enumerate);
				tSta.get(i).changeCurrent();
				block = new ArrayList<>();
				block.add(tSta.get(i));
				
				for (int j = i+1; j < tSta.size(); j++) {
					if (!tSta.get(j).isVisited()) {
						
						c = String.valueOf(tSta.get(i).getResult());
						c1 = String.valueOf(tSta.get(j).getResult());

						if (c.equals(c1)) {
							tSta.get(j).setVisited(true);
							tSta.get(j).setCurrent(enumerate);
							tSta.get(j).changeCurrent();
							block.add(tSta.get(j));
						}

					}
				}
				list.add(block);
				enumerate++;
			}
		}
		
		return list;
	}
	
	private ArrayList<ArrayList<State>> lastPartition(){
		ArrayList<ArrayList<State>> list = getFstPartition();
		ArrayList<ArrayList<State>> list1 = new ArrayList<>();
		ArrayList<State> nCurrent;
		boolean con = true;

		while (con) {
			int c = 0;
			for (ArrayList<State> a : list) {
				for (State state : a){
					state.setVisited(false);
					state.changeCurrent();
				}
			}
			for (ArrayList<State> current : list) {
				for (int i = 0; i < current.size(); i++) {			
					if (current.get(i).isVisited() == false) {
						current.get(i).setVisited(true);
						current.get(i).setCurrent(c);
						nCurrent = new ArrayList<>();
						nCurrent.add(current.get(i));
						for (int j = i+1; j < current.size(); j++) {
							if (current.get(j).isVisited() == false) { 
								if (samePlace(current.get(i), current.get(j))) {
									current.get(j).setCurrent(c);
									current.get(j).setVisited(true);
									nCurrent.add(current.get(j));
								}
							}
						}
						list1.add(nCurrent);
						c++;
					}
				}
			}
			if (!list.equals(list1)) {
				list = new ArrayList<>(list1);
				list1 = new ArrayList<>();
			}else{
				con = false;
			}
		}
		return list1;
	} 
	
	private boolean samePlace(State s1, State s2){
		boolean ans = true;
		int m1, m2;
		for (int i = 0; i < s1.getSuState().size() && ans; i++) {
			m1 = s1.getSuState().get(i).getPrevC();
			m2 = s2.getSuState().get(i).getPrevC();

			if (m1 != m2) {
				ans = false;
			}
		}
		return ans;
	}
	
	public ArrayList<State> getMinimizedAutomaton(){
		ArrayList<ArrayList<State>> list = lastPartition();
		ArrayList<State> newA = new ArrayList<>();	
		State s;
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			String name = "Q" + index;
			index++;
			char[] arr = list.get(i).get(0).getResult();
			s = new State(name, arr);
			newA.add(s);
		}
		for (int i = 0; i < newA.size(); i++) {
			for (int j = 0; j < stimuli.length; j++) {
				
				int n = list.get(i).get(0).getSuState().get(j).getCurrent();
				newA.get(i).addsuState(newA.get(n));
			}	
		}
		return newA;
	}
	
	public ArrayList<State> moore() {
		ArrayList<State> list = new ArrayList<State>();
		HashMap<String, State> map = new HashMap<>();
		
		for (int i = 1; i < matrix.length; i++) {
			String c = matrix[i][matrix[i].length-1];
			char ch = c.charAt(0);
			char[] cc = new char[] {ch};
			String name = matrix[i][0];
			State s = new State(name, cc);
			map.put(name, s);
			list.add(s);
		}

		for (int i = 0; i < matrix.length-1; i++) {
			for (int j = 1; j < matrix[i].length-1; j++) {
				String name = matrix[i+1][j];
				State s = map.get(name);
				list.get(i).addsuState(s);
			}
		}
		
		return list;
	}
	
	public ArrayList<State> mealy(int size) {
		ArrayList<State> list = new ArrayList<State>();
		HashMap<String, State> map = new HashMap<>();
		for (int i = 1; i < matrix.length; i++) {
			String[] c = new String[size];
			for (int j = 1; j < matrix[i].length; j++) {
				String[] array = matrix[i][j].split(",");
				c[j-1] = array[1];
			}
			char[] cc = convertArray(c);
			String name = matrix[i][0];
			State s = new State(name, cc);
			map.put(name, s);
			list.add(s);
		}
		for (int i = 0; i < matrix.length-1; i++) {
			for (int j = 0; j < matrix[i].length-1; j++) {
				String[] array = matrix[i+1][j+1].split(",");
				String name = array[0];
				State s = map.get(name);
				list.get(i).addsuState(s);
			}
		}
		return list;
	}
	
	public char[] convertArray(String[] array) {

		char[] chars = new char[array.length];

		for (int i = 0; i < array.length; i++) {
			char c = array[i].charAt(0);
			chars[i] = c;
		}

		return chars;

	}

}
