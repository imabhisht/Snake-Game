
package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javafx.embed.swing.JFXPanel;

import javax.swing.Timer;


public class Gameplay extends JFXPanel implements KeyListener, ActionListener {

    private Random rand = new Random();
    private Snake s = new Snake();
    private Enemy e = new Enemy();
    private gameImages gi = new gameImages();
    
    private int score;
    private Timer timer;
    private int delay = 100;
    private int savedDelay;

    public Gameplay() {

        init();
        initTimer(delay);
        
        //KeyListener & Timer
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    private void initTimer(int delay) {
        timer = new Timer(delay, this);
        timer.start();
    }

    private void init() {

        s = new Snake();
        e = new Enemy();
        e.setXposEnemy(25 + 425);
        e.setYposEnemy(75 + 325);
        score = 0;

        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0,0, getParent().getWidth(), getParent().getHeight());

        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);

        g.drawImage(gi.getTitleImage(), 25, 11, null);

        g.drawRect(24, 74, 851, 576);
        g.drawImage(gi.getBackgroundImage(), 25, 75, null);

        g.drawImage(s.getRightMouth(), s.getSnakeXLength(0), s.getSnakeYLength(0), null);

        if (!s.isMoves()) {
            g.drawImage(gi.getMenuImage(), 25, 100, null);
        }
        s.paintSnake(g);

        if (e.getXposEnemy() == s.getSnakeXLength(0) && e.getYposEnemy() == s.getSnakeYLength(0)) {
            score++;
            s.setLengthOfSnake(s.getLengthOfSnake() + 1);
            e.setXposEnemy(25 + (rand.nextInt(34) * 25));
            e.setYposEnemy(75 + (rand.nextInt(23) * 25));

            if (score % 10 == 0 && delay > 50) {
                delay -= 20;
                timer.stop();
                initTimer(delay);
            }
        }
        g.drawImage(e.getEnemyImage(), e.getXposEnemy(), e.getYposEnemy(), null);

        for (int i = 1; i < s.getLengthOfSnake(); i++) {
            if (s.getSnakeXLength(0) == s.getSnakeXLength(i) && s.getSnakeYLength(0) == s.getSnakeYLength(i)) {
                s.setDead(true);

                g.drawImage(gi.getDiedImage(), 25, 100, null);
                delay = 150;
                timer.stop();
                initTimer(delay);

            }
        }
        

        g.setColor(Color.white);
        g.setFont(new Font("", Font.PLAIN, 20));
        g.drawString("Score: " + score, 780, 45);

        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!s.isDead()) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && !s.isLeft()) {
                s.setRight(true);
                s.setUp(false);
                s.setDown(false);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT && !s.isRight()) {
                s.setLeft(true);
                s.setUp(false);
                s.setDown(false);
            } else if (e.getKeyCode() == KeyEvent.VK_UP && !s.isDown()) {
                s.setRight(false);
                s.setUp(true);
                s.setLeft(false);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN && !s.isUp()) {
                s.setRight(false);
                s.setLeft(false);
                s.setDown(true);
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                init();

                
            }
        }

        if (!s.isDead()) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                savedDelay = delay;
                delay = 50;
                timer.stop();
                initTimer(delay);
                
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!s.isDead()) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                delay = savedDelay;
                timer.stop();
                initTimer(delay);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (s.isMoves() && !s.isDead()) {
            if (s.isRight()) {
                for (int i = s.getLengthOfSnake() - 1; i >= 0; i--) {
                    s.setSnakeYLength(i + 1, s.getSnakeYLength(i));
                }
                for (int i = s.getLengthOfSnake(); i >= 0; i--) {
                    if (i == 0) {
                        s.setSnakeXLength(i, s.getSnakeXLength(i) + 25);
                    } else {
                        s.setSnakeXLength(i, s.getSnakeXLength(i - 1));
                    }
                    if (s.getSnakeXLength(i) > 850) {
                        s.setSnakeXLength(i, 25);
                    }
                }

            } else if (s.isLeft()) {
                for (int i = s.getLengthOfSnake() - 1; i >= 0; i--) {
                    s.setSnakeYLength(i + 1, s.getSnakeYLength(i));
                }
                for (int i = s.getLengthOfSnake(); i >= 0; i--) {
                    if (i == 0) {
                        s.setSnakeXLength(i, s.getSnakeXLength(i) - 25);
                    } else {
                        s.setSnakeXLength(i, s.getSnakeXLength(i - 1));
                    }
                    if (s.getSnakeXLength(i) < 25) {
                        s.setSnakeXLength(i, 850);
                    }
                }

            } else if (s.isUp()) {
                for (int i = s.getLengthOfSnake() - 1; i >= 0; i--) {
                    s.setSnakeXLength(i + 1, s.getSnakeXLength(i));
                }
                for (int i = s.getLengthOfSnake(); i >= 0; i--) {
                    if (i == 0) {
                        s.setSnakeYLength(i, s.getSnakeYLength(i) - 25);
                    } else {
                        s.setSnakeYLength(i, s.getSnakeYLength(i - 1));
                    }
                    if (s.getSnakeYLength(i) < 75) {
                        s.setSnakeYLength(i, 625);
                    }
                }

            } else if (s.isDown()) {
                for (int i = s.getLengthOfSnake() - 1; i >= 0; i--) {
                    s.setSnakeXLength(i + 1, s.getSnakeXLength(i));
                }
                for (int i = s.getLengthOfSnake(); i >= 0; i--) {
                    if (i == 0) {
                        s.setSnakeYLength(i, s.getSnakeYLength(i) + 25);
                    } else {
                        s.setSnakeYLength(i, s.getSnakeYLength(i - 1));
                    }
                    if (s.getSnakeYLength(i) > 625) {
                        s.setSnakeYLength(i, 75);
                    }
                }
            }
        } else if (s.isLeft() | s.isRight() | s.isUp() | s.isDown()) {
            s.setMoves(true);
        }
        repaint();
    }
}
