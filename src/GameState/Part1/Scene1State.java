package GameState.Part1;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import GameState.GameState;
import GameState.GameStateManager;
import Main.GamePanel;

import javax.imageio.ImageIO;

public class Scene1State extends GameState {

	private double transparency;
	private double textappear;
	private int currentChoice;
	private BufferedImage problem;
	
	private String[] story = {
			"PART 1", "The Crime Scene", "\"The McMasterful Murder\"", "", "", "", "", "", "", 
			"Mr. McMaster has masterfully murdered Dr. Le Hospital\n"
			+ "and has fled the crime scene!",
			"",
			"Mr. McMaster has left a calculus related code\n"
			+ "for those who wish to be his followers.",
			"",
			"Luckily, we have calculus knowledge as well!\n",
			"",
			"Our goal is to answer his calculus clues\n"
			+ "to locate where Mr. McMaster is hiding!",
			"",
			"",
			"",
			"You pick up a note and it reads:\n"
			+ "Solve this derivative for the location of the next clue.",
			};
	private String[] options = {"Bar. -0.5", "Park. 0.5", "School. -2", "Bell Square. 2"};
	private int lineNum;
	private final int LINE_HEIGHT = 40;
	private final int PADDING_SIZE = 10;
	private boolean isQuestion = false;
	
	public Scene1State(GameStateManager gsm) {
		this.gsm = gsm;
		transparency = 0;
		lineNum = 0;
		init();
	}

	@Override
	public void init() {
		currentChoice = 0;
		try {
			problem = ImageIO.read(getClass().getResourceAsStream("/Problems/problem1.GIF"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		if(transparency < 254)	transparency += 1.5;
		if(textappear < 255)	textappear += 5;
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
					g.drawString(options[i], 200, PADDING_SIZE * 15 + i * 30);
				}
				g.drawImage(problem, PADDING_SIZE * 4, 60, null);
			}
		}
		
	}
	
	public void select() {
		if (isQuestion) {
			if(currentChoice == 0) {
				gsm.setState(GameStateManager.SCENE2STATE);
			}
			if(currentChoice == 1) {
				gsm.setState(GameStateManager.FAIL1STATE);
			}
			if(currentChoice == 2) {
				gsm.setState(GameStateManager.FAIL1STATE);
			}
			if(currentChoice == 3) {
				gsm.setState(GameStateManager.FAIL1STATE);
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

	
	public void mouseMoved(MouseEvent e) {
		
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
