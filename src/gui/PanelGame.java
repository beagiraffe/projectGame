package gui;

import model.ManagerItem;
import model.Hero;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

public class PanelGame extends JPanel implements KeyListener,Runnable{
    public static int countMap = 0;
    public static int checkSkill;
    public static int checkSpeed;
    public static int checkUlti = 1;
    private Thread thread;
    private boolean isLeft;
    private boolean isRight;
    private boolean isUp;
    private boolean isDown;
    private boolean isFire;
    private boolean isUlti;
    private boolean isRunning = true;
    private BitSet bitSet = new BitSet(256);
    private ManagerItem managerItem;
    public PanelGame(){
        setSize(MyFrame.W_FRAME,MyFrame.H_FRAME);
        setBackground(Color.BLACK);
        setFocusable(true);
        setLocation(0,0);
        managerItem = new ManagerItem();
        managerItem.readMap("map3.txt");
        addKeyListener(this);
        setRequestFocusEnabled(true);
        setFocusable(true);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        managerItem.drawHero(g2d);
        managerItem.drawEnemy(g2d);
        managerItem.drawBulletOfHero(g2d);
        managerItem.drawAllBulletEnemy(g2d);
        managerItem.drawAll(g2d);

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        bitSet.set(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        bitSet.clear(e.getKeyCode());
    }
    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (bitSet.get(KeyEvent.VK_LEFT)){
                managerItem.moveHero(Hero.LEFT);
            }
            else if (bitSet.get(KeyEvent.VK_RIGHT)){
                managerItem.moveHero(Hero.RIGHT);
            }
            else if (bitSet.get(KeyEvent.VK_UP)){
                managerItem.moveHero(Hero.UP);
            }
            else if (bitSet.get(KeyEvent.VK_DOWN)){
                managerItem.moveHero(Hero.DOWN);

            }
            if (bitSet.get(KeyEvent.VK_SPACE)){
                managerItem.fireBullet();
            }
            if (bitSet.get(KeyEvent.VK_CONTROL)){
                if(checkUlti == 1){
                    managerItem.fireUlti();
                }
                checkUlti=1000;
                
            }
            moveHero();
            moveBullet();
            fireOfHero();
            fireOfUlti();
            managerItem.interactBulletOfHero();
            managerItem.moveAllEnemy();
            managerItem.moveAllBulletEnemy();
            managerItem.fireEnemy();
            managerItem.interactBulletOfAllEnemy();
            managerItem.killEnemy();
            
            if (managerItem.checkGameOver()) {
            	bitSet.clear();
                int check =JOptionPane.showConfirmDialog(PanelGame.this,"Your hero is wounded\nDo you wanna try again?","You died",JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION){
                    managerItem = new ManagerItem();
                    checkSkill = 99;
                    checkSpeed = 101;
                    checkUlti = 1;
                    countMap = 0;
                    managerItem.readMap("map1.txt");
                }
                else
                    System.exit(0);
            }
            if (managerItem.sinked()) {
            	bitSet.clear();
                int check =   JOptionPane.showConfirmDialog(PanelGame.this,"Your hero doesn't know to swim\nDo you wanna try again?","You sinked",JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION){
                    managerItem = new ManagerItem();
                    checkSkill = 99;
                    checkSpeed = 101;
                    checkUlti = 1;
                    managerItem.readMap("map1.txt");
                }
                else
                    System.exit(0);
            }            
            
            if (managerItem.bitten()) {
            	bitSet.clear();
                int check =   JOptionPane.showConfirmDialog(PanelGame.this,"Your hero has been bitten\nDo you wanna try again?","You infected",JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION){
                    managerItem = new ManagerItem();
                    checkSkill = 99;
                    checkSpeed = 101;
                    checkUlti = 1;                    
                    managerItem.readMap("map1.txt");
                }
                else
                    System.exit(0);
            }
            
            if (managerItem.checkWin()) {
            	bitSet.clear();
                
                JOptionPane.showConfirmDialog(PanelGame.this, "You Win\nYou want to play ","DONE",JOptionPane.YES_NO_OPTION);
                managerItem= new ManagerItem();
                    checkSkill = 99;
                    checkSpeed = 101;
                    checkUlti = 1;
                    countMap++;
                    if(countMap == 1){
                        managerItem.readMap("map2.txt");
                        countMap++;
                    }else if(countMap > 1){
                        bitSet.clear();
                        
                        managerItem= new ManagerItem();
                        checkSkill = 99;
                        checkSpeed = 101;
                        checkUlti = 1;
                        managerItem.readMap("map3.txt");
                    }
                    
                    
            }
            repaint();
        }
    }

    private void moveBullet() {
        managerItem.moveBulletOfHero();
    }

    void fireOfHero() {
        if (isFire) {
            System.out.println("fire");
            managerItem.fireBullet();
        }
    }

    void fireOfUlti() {
        if (isUlti) {
            System.out.println("ulti");
            managerItem.fireUlti();
        }
    }    

    void moveHero() {
        if (isDown) {
            managerItem.moveHero(Hero.DOWN);

        } else {
            if (isUp) {
                managerItem.moveHero(Hero.UP);

            } else {
                if (isRight) {
                    managerItem.moveHero(Hero.RIGHT);

                } else {
                    if (isLeft) {
                        managerItem.moveHero(Hero.LEFT);
                    }
                }
            }
        }
    }
}

