package GameState;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import GameState.Failure.*;
import GameState.Part1.*;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;
	private int beforeState;
	
	public static final int MENUSTATE = 0;
	public static final int HELPSTATE = 1;
	
	//PART 1
	public static final int SCENE1STATE = 2;
	public static final int FAIL1STATE = 3;
	public static final int SCENE2STATE = 4;
	public static final int FAIL2STATE = 5;
	public static final int SCENE3STATE = 6;
	public static final int FAIL3STATE = 7;
	public static final int SCENE4STATE = 8;
	public static final int FAIL4STATE = 9;
	public static final int SCENE5STATE = 10;
	public static final int FAIL5STATE = 11;
	public static final int SCENE6STATE = 12;
	public static final int FAIL6STATE = 13;
	public static final int SCENE7STATE = 14;
	public static final int FAIL7STATE = 15;
	public static final int SCENE8STATE = 16;
	public static final int FAIL8STATE = 17;
	public static final int SCENE9STATE = 18;
	public static final int FAIL9STATE = 19;
	public static final int SCENE10STATE = 20;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new HelpState(this));
		gameStates.add(new Scene1State(this));
		gameStates.add(new Fail1State(this));
		gameStates.add(new Scene2State(this));
		gameStates.add(new Fail2State(this));
		gameStates.add(new Scene3State(this));
		gameStates.add(new Fail3State(this));
		gameStates.add(new Scene4State(this));
		gameStates.add(new Fail4State(this));
		gameStates.add(new Scene5State(this));
		gameStates.add(new Fail5State(this));
		gameStates.add(new Scene6State(this));
		gameStates.add(new Fail6State(this));
		gameStates.add(new Scene7State(this));
		gameStates.add(new Fail7State(this));
		gameStates.add(new Scene8State(this));
		gameStates.add(new Fail8State(this));
		gameStates.add(new Scene9State(this));
		gameStates.add(new Fail9State(this));
		gameStates.add(new Scene10State(this));
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public int getState() {
		return currentState;
	}
	
	public String getAnswer() {
		return gameStates.get(currentState).answer();
	}
	
	public void selectAnswer() {
		gameStates.get(currentState).select();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void mouseMoved(MouseEvent e) {
		gameStates.get(currentState).mouseMoved(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		gameStates.get(currentState).mouseClicked(e);
	}
	
	public void newGame() {
		currentState = MENUSTATE;
		for(int i = gameStates.size() - 1; i > 0; i--)
		{
			gameStates.remove(i);
		}
		//UNFINISHED - add all the states after menu back in
	}
}
