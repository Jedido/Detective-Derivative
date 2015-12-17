package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class HelpState extends GameState{

	private double transparency;
	private double textappear;
	
	private String image;
	private int counter = 1;
	private BufferedImage rules;
	private final int PADDING_SIZE = 10;
	GameStateManager gsm;
	
	public HelpState(GameStateManager gsm) {
		this.gsm = gsm;
		transparency = 0;
		init();
	}
	
	public void draw(Graphics2D g) {
		// clear screen
		g.setColor(Color.WHITE);
		g.fillRect(0,  0,  GamePanel.WIDTH,  GamePanel.HEIGHT);
		g.setColor(new Color(10, 10, 10, (int) transparency));
		g.fillRect(PADDING_SIZE,  PADDING_SIZE,  GamePanel.WIDTH - PADDING_SIZE * 2,  GamePanel.HEIGHT - PADDING_SIZE * 2);
		//g.setColor(Color.BLACK);
		g.setColor(new Color(0, 0, 0, (int) textappear));
		g.setFont(new Font("Trebuchet MS", Font.ITALIC, 40));
		g.drawString("Derivative Rules", 145, 75);
		g.drawImage(rules, PADDING_SIZE *14, 120, null);
	}
	
	@Override
	public void init() {
		counter = 1;
		try {
			rules = ImageIO.read(getClass().getResourceAsStream("/Problems/derivativerule1.GIF"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//if(transparency < 254)	transparency += 1.5;
		if(textappear < 255)	textappear += 5;
		image = "/Problems/derivativerule" + counter + ".GIF";
		try {
			rules = ImageIO.read(getClass().getResourceAsStream(image));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if(k == KeyEvent.VK_LEFT) {
			counter--;
			if(counter > 4 || counter < 1) {
				gsm.setState(GameStateManager.MENUSTATE);
			}
		}
		if(k == KeyEvent.VK_RIGHT) {
			counter++;
			if(counter > 4 || counter < 1) {
				gsm.setState(GameStateManager.MENUSTATE);
			}
		}
		if(k == KeyEvent.VK_ENTER)
		{
			counter++;
			if(counter > 4 || counter < 1) {
				gsm.setState(GameStateManager.MENUSTATE);
			}
		}
		if(k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String answer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}
}
