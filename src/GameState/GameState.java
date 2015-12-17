package GameState;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JLabel;

public abstract class GameState {

	protected GameStateManager gsm;
	protected boolean isCorrect = false;
	public static final Font TITLEFONT = new Font("Trebuchet MS", Font.ITALIC, 40);
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	//public abstract void keyReleased(int k);
	public abstract void mouseMoved(MouseEvent e);
	public abstract void mouseClicked(MouseEvent e);
	public abstract String answer();
	public abstract void select();
	
	  protected String input;
	    protected JLabel jl = new JLabel();
	    protected JTextField jt = new JTextField(10);
	    protected JButton jb = new JButton("Enter");
	    protected JPanel jp = new JPanel();
	    protected JFrame jf = new JFrame();
		
	    protected void addTextField() {
	    	
			jf.setVisible(true);
			jf.setSize(200, 200);
			jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
		    jp.add(jt);
		
		    jt.addActionListener(new ActionListener()
		    {
		           public void actionPerformed(ActionEvent e)
		           {
		                 input = jt.getText();
		                 if(input.length() > 0) {
		                	   gsm.selectAnswer();
		                	   jf.hide();
		                   }
		                 
		           }
		    });
		
		    jp.add(jb);
		    jb.addActionListener(new ActionListener()
		    {
		            public void actionPerformed(ActionEvent e)
		            {
		                   input = jt.getText();
		                   if(input.length() > 0 ) {
		                	   gsm.selectAnswer();
		                	   jf.hide();
		                   }
		                   
		            }
		    });
		    jp.add(jl);
		    jf.add(jp);
		}

	
	protected void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y+= 40);
    }
}
