package GameState.Part1;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import GameState.GameState;
import GameState.GameStateManager;
import Main.GamePanel;

public class Scene3State extends GameState {

	private double transparency;
	private double textappear;
	private int currentChoice;
	private BufferedImage problem;
	
	private String[] story = {
			"PART 3", "The Library", "", "", "", "", "", "", "", 
			"The map lead you to this library.", 
			"You begin searching the library for any traces left\n"
			+ "by McMaster.",
			"",
			"Looking around, you sight one of McMaster’s followers\n"
			+ "holding a phone.",
			"",
			"Maybe he’s texting McMaster!\n"
			+ "You ambush him from behind and take the phone.",
			"",
			"In a panic, you run up the stairs. What can you do now?",
			"",
			"",
			"You pass by an open window and see a patch of grass\n"
			+ "surrounded by cement.  You jump out, and your height,\n"
			+ "in meters, can be described as s(t)=8 + 1.4t - 4.9t^2\n"
			+ "How fast are you going to hit the ground?"
			};
	private String[] options = {"a. 12.6 m/s", "b. 9.8 m/s", "c. 14.0 m/s", "d. 13.6 m/s"};
	private int lineNum;
	private final int LINE_HEIGHT = 40;
	private final int PADDING_SIZE = 40;
	private boolean isQuestion;
	
	public Scene3State(GameStateManager gsm) {
		this.gsm = gsm;
		transparency = 0;
		lineNum = 0;
		init();
	}

	@Override
	public void init() {
		currentChoice = 0;
		try {
			//problem = ImageIO.read(getClass().getResourceAsStream("/Problems/problem1.GIF"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		if(transparency < 254)	transparency += 1.5;
		if(textappear < 254)	textappear += 5;
	}

	@Override
	public void draw(Graphics2D g) {
		
		// clear screen
		g.setColor(Color.BLACK);
		g.fillRect(0,  0,  GamePanel.WIDTH,  GamePanel.HEIGHT);
		g.setColor(new Color(10, 10, 10, (int) transparency));
		g.fillRect(PADDING_SIZE,  PADDING_SIZE,  GamePanel.WIDTH - PADDING_SIZE * 2,  GamePanel.HEIGHT - PADDING_SIZE * 2);
				
		// draw title
		if(lineNum == 0) {
			g.setColor(new Color(255, 255, 255, (int) transparency));
			g.setFont(new Font("Trebuchet MS", Font.ITALIC, 40));

			g.drawString(story[0], GamePanel.WIDTH/2 - 80, GamePanel.HEIGHT/2 - 60);
			g.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
			g.drawString(story[1], GamePanel.WIDTH/2 - 36, GamePanel.HEIGHT/2 - 20);
			
			g.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
			g.drawString(story[2], GamePanel.WIDTH/2 - 104, GamePanel.HEIGHT/2 + 110);
		}
		
		// draw normal text
		else{
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			
			if(story.length > lineNum) {
				for(int i = lineNum / 9 * 9; i <= lineNum - 1; i++)
					this.drawString(g, story[i], PADDING_SIZE + 20, (i % 9) * LINE_HEIGHT + PADDING_SIZE + 40);
				g.setColor(new Color(255, 255, 255, (int) textappear));
				this.drawString(g, story[lineNum], PADDING_SIZE + 20, (lineNum % 9) * LINE_HEIGHT + PADDING_SIZE + 40);
			}
			else {
				// draw options at end
				isQuestion = true;
				g.setFont(new Font("Kristen ITC", Font.BOLD, 20));
				for(int i  = 0; i < options.length; i++) {
					if(i == currentChoice) {
						g.setColor(Color.WHITE);
					}
					else {
						g.setColor(Color.RED);
					}
					g.drawString(options[i], 200, PADDING_SIZE * 4 + i * 30);
				}
				g.drawImage(problem, PADDING_SIZE * 2, 60, null);
			}
		}
		
	}
	
	public void select() {
		if (isQuestion) {
			if(currentChoice == 0) {
				gsm.setState(GameStateManager.SCENE4STATE);
			}
			if(currentChoice == 1) {
				gsm.setState(GameStateManager.FAIL3STATE);
			}
			if(currentChoice == 2) {
				gsm.setState(GameStateManager.FAIL3STATE);
			}
			if(currentChoice == 3) {
				gsm.setState(GameStateManager.FAIL3STATE);
			}
		}
	}
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER)
		{
			if(lineNum > 0)
			{
				lineNum++;
				while(lineNum < story.length && story[lineNum].length() == 0)
					lineNum++;
				textappear = 0;
			}
			else
				lineNum = 9;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int y = e.getY();
		if(y < 160)
			currentChoice = 0;
		else if(y >= 160 && y < 195)
			currentChoice = 1;
		else if(y >= 195 && y < 225)
			currentChoice = 2;
		else
			currentChoice = 3;
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
