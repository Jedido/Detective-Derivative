package GameState.Failure;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import GameState.GameState;
import GameState.GameStateManager;
import Main.GamePanel;

public class Fail9State extends GameState {

	private double textappear;
	private int currentChoice;
	
	private String[] story = {
			"HA you just got ganked by my problem!!",
			"Muahahahaha.",
			"You’re lucky i’m so benevolent to allow you to attempt\n"
			+ "this problem again!",
			"",
			"Thank me later!",
			"You have dishonored McMaster’s Masterful teachings\n"
			+ "and must review how to solve this problem.",
			"",
			"",
			"",
			"",
			"Hints:",
			"Good skill -McMaster",
			};
	private String[] options = {"a. 1", "b. 2", "c. 3", "d. 4"};
	private int lineNum;
	private final int LINE_HEIGHT = 40;
	private final int PADDING_SIZE = 40; 
	
	public Fail9State(GameStateManager gsm) {
		this.gsm = gsm;
		lineNum = 0;
		init();
	}

	@Override
	public void init() {
		currentChoice = 0;
	}

	@Override
	public void update() {
		if(textappear < 255)	textappear += 5;
	}

	@Override
	public void draw(Graphics2D g) {
		
		// clear screen
		g.setColor(Color.BLACK);
		g.fillRect(0,  0,  GamePanel.WIDTH,  GamePanel.HEIGHT);
		g.setColor(new Color(10, 10, 10));
		g.fillRect(PADDING_SIZE,  PADDING_SIZE,  GamePanel.WIDTH - PADDING_SIZE * 2,  GamePanel.HEIGHT - PADDING_SIZE * 2);
		
		// draw normal text
		g.setColor(new Color(255, 255, 255));
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		if(lineNum < story.length) {
			for(int i = lineNum / 9 * 9; i <= lineNum - 1; i++)
				this.drawString(g, story[i], PADDING_SIZE + 20, (i % 9) * LINE_HEIGHT + PADDING_SIZE + 40);
			g.setColor(new Color(255, 255, 255, (int) textappear));
			this.drawString(g, story[lineNum], PADDING_SIZE + 20, (lineNum % 9) * LINE_HEIGHT + PADDING_SIZE + 40);
		}
		else {
			gsm.setState(GameStateManager.SCENE9STATE);
		}
	}
	
	public void select() {
		if(currentChoice == 0) {
			//
		}
		if(currentChoice == 1) {
			gsm.setState(GameStateManager.SCENE2STATE);
		}
		if(currentChoice == 2) {
			//
		}
		if(currentChoice == 3) {
			//
		}
	}
	
	@Override
	public void keyPressed(int k) {
/*		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}*/
		if(k == KeyEvent.VK_ENTER)
		{
			lineNum++;
			while(lineNum < story.length && story[lineNum].length() == 0)
				lineNum++;
			textappear = 0;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		select();
		e.consume();
	}

	@Override
	public String answer() {
		// TODO Auto-generated method stub
		return null;
	}

}
