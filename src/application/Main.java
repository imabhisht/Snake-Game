package application;

import javax.swing.JFrame;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        GamePlay gamePlay = new GamePlay();
        obj.setBounds(10,10,905,700);
        obj.setBackground(Color.DARK_GRAY);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setLocationRelativeTo(null);
        obj.add(gamePlay);


    }
}
