package GameState.Part1;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import GameState.GameState;
import GameState.GameStateManager;
import Main.GamePanel;

public class Scene8State extends GameState {

    private double transparency;
    private double textappear;
    private int currentChoice;
    private BufferedImage problem;
    
    private String[] story = {
            "PART 8", "Base 2", "", "", "", "", "", "", "", 
            "The guard in front stomps on the ground.", 
            "\"Correct! You may pass.\"",
            "The guards move out of the way. It's time to enter Binary.",
            "There are 4 hallways in the Base 2 and there is a derivative\n"
            + "posted above the 4 entrances.",
            "",
            "An answer corresponds to each hallway.",
            "Solve the problem correctly and enter the corresponding\n",
            "pathway or risk entering a dangerous path."
            };
    private String[] options = {"Path 1. \u03C0", "Path 2. 0", "Path 3. -\u03C0", "Path 4. -1"};
    private int lineNum;
    private final int LINE_HEIGHT = 40;
    private final int PADDING_SIZE = 40;
    private boolean isQuestion;
    
    public Scene8State(GameStateManager gsm) {
        this.gsm = gsm;
        transparency = 0;
        lineNum = 0;
        init();
    }

    @Override
    public void init() {
        currentChoice = 0;
        try {
            problem = ImageIO.read(getClass().getResourceAsStream("/Problems/problem6.jpg"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if(transparency < 254)  transparency += 1.5;
        if(textappear < 254)    textappear += 5;
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
                gsm.setState(GameStateManager.FAIL8STATE);
            }
            if(currentChoice == 1) {
                gsm.setState(GameStateManager.FAIL8STATE);
            }
            if(currentChoice == 2) {
                gsm.setState(GameStateManager.SCENE9STATE);
            }
            if(currentChoice == 3) {
                gsm.setState(GameStateManager.FAIL8STATE);
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
