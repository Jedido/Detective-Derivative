package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuState extends GameState {
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = {"Start", "Help", "Quit"};
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/mathbg.gif", 1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) {
		
		//draw bg
		bg.draw(g);
		
		// draw menu options
		g.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		for(int i  = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 20, 400 + i * 30);
		}
		g.setFont(new Font("Impact", Font.ITALIC, 18));
		g.setColor(Color.BLACK);
		g.drawString("Jed C., Dexter C., Justin I. -2014", 420, 470);
	}

	public void select() {
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.SCENE1STATE);
		}
		if(currentChoice == 1) {
			gsm.setState(GameStateManager.HELPSTATE);
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
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
		}
		if(k == KeyEvent.VK_K){
			gsm.setState(GameStateManager.SCENE3STATE);
		}
	}

	public int getIndex() {
		return GameStateManager.MENUSTATE;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int y = e.getY();
		if(y < 405)
			currentChoice = 0;
		else if(y >= 405 && y < 435)
			currentChoice = 1;
		else
			currentChoice = 2;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		select();
		e.consume();
	}

	@Override
	public String answer() {
		// TODO Auto-generated method stub
		return null;
	}
}
