package mypacman;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Game implements KeyListener {

    Board board = new Board();
    Timer timer;
    char direction = 'L';
    static boolean flag = true;

    public Game() {
        JFrame frame = new JFrame();
        frame.setSize(420, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(board, BorderLayout.CENTER);
        frame.setTitle("PAC-MAN");
        frame.setVisible(true);
        frame.addKeyListener(this);
        timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!board.title && board.lives > 0) {

                    if (flag) {
                        try {
                            Thread.sleep(2000);
                            flag = false;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    board.ghost1.move();
                    if (board.ghost1.getShape().intersects(board.pacman.getShape())) {
                        board.reset();
                    }
                    board.ghost2.move();
                    if (board.ghost2.getShape().intersects(board.pacman.getShape())) {
                        board.reset();
                    }
                    board.ghost3.move();
                    if (board.ghost3.getShape().intersects(board.pacman.getShape())) {
                        board.reset();
                    }
                    board.ghost4.move();
                    if (board.ghost4.getShape().intersects(board.pacman.getShape())) {
                        board.reset();
                    }

                    board.ghost2.move();
                    board.ghost3.move();
                    board.ghost4.move();

                    board.ghost1.updateState(board.states);
                    board.ghost2.updateState(board.states);
                    board.ghost3.updateState(board.states);
                    board.ghost4.updateState(board.states);
                    board.pacman.move(direction);
                    if (board.balls[board.pacman.x / 20][board.pacman.y / 20]) {
                        board.balls[board.pacman.x / 20][board.pacman.y / 20] = false;
                        board.score++;
                    }

                    board.pacman.updateState(board.states);
                }
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        Game game = new Game();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 'L';
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 'R';
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = 'U';
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 'D';
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                
            board.title = false;
           
            board.lives = 2; 
            board.init(); 
            board.score = 0; 
            board.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
       if (board.check()) {
            board.lives = 2; 
            board.init(); 
            board.score = 0;
            board.repaint(); 
        }
        
        }
    }
@Override
public void keyReleased(KeyEvent e) {

    }
 
}
