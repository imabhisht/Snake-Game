package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener{


    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon snakeImage;

    private int lengthOfSnake = 3;
    private int moves = 0;

    private Timer timer;
    private int delay = 100;
    private ImageIcon titleImage;
    public GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g){

        if(moves == 0){
            snakeXLength[2] = 50;
            snakeXLength[1] = 75;
            snakeXLength[0] = 100;

            snakeYLength[2] = 100;
            snakeYLength[1] = 100;
            snakeYLength[0] = 100;

        }
        //Image Border
        g.setColor(Color.WHITE);
        g.drawRect(24,10,851,55);

        //Title Image
        titleImage = new ImageIcon("src/assets/snaketitle.jpg");
        titleImage.paintIcon(this,g,25,11);

        //Border Area
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,576);

        //Background GamePlay
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);

        rightmouth = new ImageIcon("src/assets/rightmouth.png");
        rightmouth.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);

        for(int a=0; a < lengthOfSnake; a++){
            if(a==0 && right){
                rightmouth = new ImageIcon("src/assets/rightmouth.png");
                rightmouth.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);
            }

            if(a==0 && left){
                leftmouth = new ImageIcon("src/assets/leftmouth.png");
                leftmouth.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);
            }

            if(a==0 && up){
                upmouth = new ImageIcon("src/assets/upmouth.png");
                upmouth.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);
            }

            if(a==0 && down){
                downmouth = new ImageIcon("src/assets/downmouth.png");
                downmouth.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);
            }

            if(a!=0){

                snakeImage = new ImageIcon("src/assets/snakeimage.png");
                snakeImage.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);

            }
        }

        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();
        if(right){
            for(int r = lengthOfSnake-1; r>=0; r--){
                snakeYLength[r+1] = snakeYLength[r];
            }
            for(int r = lengthOfSnake; r>=0;r--){
                if(r==0){
                    snakeXLength[r] = snakeXLength[r] + 25;
                }
                else{
                    snakeXLength[r] = snakeXLength[r-1];
                }

                if(snakeXLength[r] > 850){
                    snakeXLength[r] = 25;
                }
            }
        }
        if(left){

        }
        if(up){

        }
        if(down){

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Right Key Pressed
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            right = true;
            if(!left) {
                right = true;
            }else {
                right=false;
                left=true;
            }
            up = false;
            down = false;
        }

        //Left Key Pressed
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves++;
            left = true;
            if(!right) {
                left = true;
            }else {
                left=false;
                right=true;
            }
            up = false;
            down = false;
        }

        //Up Key Pressed
        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves++;
            up = true;
            if(!down) {
                up = true;
            }else {
                up=false;
                down=true;
            }
            left = false;
            right = false;
        }

        //Down Key Pressed
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            down = true;
            if(!up) {
                down = true;
            }else {
                down=false;
                up=true;
            }
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
