package model;

import javax.sound.sampled.Clip;
import javax.swing.*;

import sound.Sound;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;


public class ManagerItem {
    private ArrayList<Item> items;
    
    Hero hero;
    long currentTimeFireHero = 0;
    ArrayList<Zombie> zombie;
    int a[] = {0,0,0,0};
    ArrayList<ZombieUD> zombieUD;
    long currentMoveEnemy;
    long currentfireEnemy;
    Item home;
    public ManagerItem() {
        items = new ArrayList<>();
        int x = 200;
        int y = 500;
        int orientation = Hero.UP;
        int id = Images.ID_HERO[orientation];
        hero = new Hero(id, x, y, 35, orientation);
        zombie = new ArrayList<Zombie>();
        zombieUD = new ArrayList<ZombieUD>();
        createEnemy();
    }

    void createEnemy(){ //nen sinh o 2,3x va 12,13y se k bi dinh map
        int x1 = 3*20;
        int y1 = 4*20;
        int ori1 = Hero.DOWN;
        int size1 = 40;
        ZombieUD z1 = new ZombieUD(Images.ID_ZOMBIEUD[ori1],x1, y1, size1, ori1);
        zombieUD.add(z1);

        int x2 = 4*20;
        int y2 = 3*20;
        int ori2 = Hero.DOWN;
        int size2 = 36;
        Zombie z2 = new Zombie(Images.ID_ZOMBIE[ori2], x2, y2, size2, ori2);
        zombie.add(z2);

        int x3 = 2*20;
        int y3 = 6*20;
        int ori3 = Hero.DOWN;
        int size3 = 40;
        ZombieUD z3 = new ZombieUD(Images.ID_ZOMBIEUD[ori3],  x3, y3, size3, ori3);
        zombieUD.add(z3);

        int x4 = 23*20;
        int y4 = 4*20;
        int ori4 = Hero.DOWN;
        int size4 = 36;
        Zombie z4 = new Zombie(Images.ID_ZOMBIE[ori4], x4, y4, size4, ori4);
        zombie.add(z4);
        
        int x5 = 24*20;
        int y5 = 4*20;
        int ori5 = Hero.RIGHT;
        int size5 = 40;
        ZombieUD z5 = new ZombieUD(Images.ID_ZOMBIEUD[ori5],x5, y5, size5, ori5);
        zombieUD.add(z5);

        int x6 = 23*20;
        int y6 = 12*20;
        int ori6 = Hero.RIGHT;
        int size6 = 36;
        Zombie z6 = new Zombie(Images.ID_ZOMBIE[ori6], x6, y6, size6, ori6);
        zombie.add(z6);

        int x7 = 6*20;
        int y7 = 14*20;
        int ori7 = Hero.RIGHT;
        int size7 = 40;
        ZombieUD z7 = new ZombieUD(Images.ID_ZOMBIEUD[ori7],  x7, y7, size7, ori7);
        zombieUD.add(z7);

        int x8 = 22*20;
        int y8 = 13*20;
        int ori8 = Hero.RIGHT;
        int size8 = 36;
        Zombie z8 = new Zombie(Images.ID_ZOMBIE[ori8], x8, y8, size8, ori8);
        zombie.add(z8);
    }

    public void drawEnemy(Graphics2D g2d) {
        for ( int i = 0; i < zombie.size(); i++ ) {
            Zombie z = zombie.get(i);
            z.draw(g2d);
        }
        for ( int i = 0; i < zombieUD.size(); i++ ) {
            ZombieUD zUD = zombieUD.get(i);
            zUD.draw(g2d);
        }
    }
    public void readMap(String map) {
        File file = new File("src/map/"+map);
        try {
            RandomAccessFile rd =  new RandomAccessFile(file, "rw");
            String content = rd.readLine();
            int index = 0;
            while (content != null ){
                for ( int i = 0; i < content.length(); i++ ) {
                    int id = content.charAt(i) - '0';
                    if ( id == 0 ) {
                        continue;
                    }
                    int x = i * 20;
                    int y = index * 20;
                    Item item;
                    if ( id == 9 ) {
                        item = new Item(id, x, y, 40);
                        home = item;
                    }else {
                        item = new Item(id, x, y, 20);
                    }
                    items.add(item);
                }
                index++;
                content = rd.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawAll(Graphics2D g2d ) {
        for ( int i = 0; i < items.size(); i++ ) {
            Item item = items.get(i);
            item.draw(g2d);
        }
    }

    public void drawHero(Graphics2D g2d) {
        hero.draw(g2d);
    }

    public void moveHero(int orientation) {
        hero.move(orientation, items,zombie,zombieUD);
    }

    public void fireBullet() {
        long time = System.currentTimeMillis();
        if ( time - currentTimeFireHero > 500 ) {
            hero.fireBullet();
            currentTimeFireHero = time;
        }
    }
    
    public void fireUlti() {
        long time = System.currentTimeMillis();
        if ( time - currentTimeFireHero > 600 ) {
            hero.fireUlti();
            currentTimeFireHero = time;
        }
    }    

    public void drawBulletOfHero(Graphics2D g2d) {
        hero.drawAllBullet(g2d);
    }

    public void moveBulletOfHero() {
        hero.moveAllBullet();
        hero.moveUlti();
    }
    public void interactBulletOfHero() {
        hero.interactBullet( items);     // dan 1 gap tuong thi mat
        hero.interactdoubleBullet(items); // dan 2 gap tuong thi mat
    }

    public void interactBulletOfAllEnemy(){
        for ( int i = 0; i < zombie.size(); i++ ) {
            Zombie z = zombie.get(i);
            z.interactBullet(items);
        }
        for ( int i = 0; i < zombieUD.size(); i++ ) {
            ZombieUD zUD = zombieUD.get(i);
            zUD.interactBullet(items);
        }
    }
    public void moveAllEnemy() {
        long current = System.currentTimeMillis();
        if ( current - currentMoveEnemy < 25 ) {
            return;
        }
        currentMoveEnemy = current;
        for ( int i = 0; i < zombie.size(); i++ ) {
            Zombie z = zombie.get(i);
            z.move(items);
        }
        for ( int i = 0; i < zombieUD.size(); i++ ) {
            ZombieUD zUD = zombieUD.get(i);
            zUD.move(items);
        }          
    }
    public void fireEnemy() {
        long time = System.currentTimeMillis();
        if  ( time - currentfireEnemy < 3500 ) {
            return;
        }
        currentfireEnemy = time;

        for ( int i = 0; i < zombieUD.size(); i++ ) {
            ZombieUD zUD = zombieUD.get(i);
            zUD.fireBullet();
        }  
         for ( int i = 0; i < zombie.size(); i++ ) {
            Zombie z = zombie.get(i);
            z.fireBullet();
        }       
    }
    public void drawAllBulletEnemy(Graphics2D g2d) {
        for ( int i = 0; i < zombie.size(); i++ ) {
            Zombie z = zombie.get(i);
            z.drawAllBullet(g2d);
        }
        for ( int i = 0; i < zombieUD.size(); i++ ) {
            ZombieUD zUD = zombieUD.get(i);
            zUD.drawAllBullet(g2d);
        }
    }   
    public void moveAllBulletEnemy() {
        for ( int i = 0; i < zombie.size(); i++ ) {
            Zombie z = zombie.get(i);
            z.moveAllBullet();
        }
        for ( int i = 0; i < zombieUD.size(); i++ ) {
            ZombieUD zUD = zombieUD.get(i);
            zUD.moveAllBullet();
        }       
    }

    public void killEnemy() {
        for ( int i = 0; i < zombie.size(); i++ ) {
            Zombie z = zombie.get(i);
            boolean isKill = hero.killZombie(z);
            if ( isKill ) {
                zombie.remove(i);
                i--;
                Clip clip = Sound.getSound(getClass().getResource("/sound/explosion.wav"));
                clip.start();
            }
        }
        for ( int i = 0; i < zombieUD.size(); i++ ) {
            ZombieUD zUD = zombieUD.get(i);
            boolean isKill = hero.killZombieUD(zUD);
            if ( isKill ) {
                if(a[i]<2){
                    a[i]++;
                }
                if(a[i]==2) {
                    zombieUD.remove(i);
                    i--;
                    Clip clip = Sound.getSound(getClass().getResource("/sound/explosion.wav"));
                    clip.start();
                }
            }
            
        }
    }

    public boolean checkGameOver(){
        int isTrue = 1;
        if(isTrue > 0){
            for ( int i = 0; i < zombie.size(); i++ ) {
            Zombie z = zombie.get(i);
            boolean isKill = z.killHero(hero);
            if ( isKill ) {
                return true;
                }
            }
            for ( int i = 0; i < zombieUD.size(); i++ ) {
            ZombieUD zUD = zombieUD.get(i);
            boolean isKill = zUD.killHero(hero);
            if ( isKill ) {
                return true;
                }
            }
            if ( killHome() ) {
            Clip clip = Sound.getSound(getClass().getResource("/sound/explosion_hero.wav"));
            clip.start();
            return true;
                }        
            }
        return false;
    }
    
    public boolean checkWin() {
        if ( zombie.size() == 0 && zombieUD.size() == 0 ) {
            return true;
        }else {
            return false;
        }
    }
    public boolean sinked() {
        boolean kill = hero.sinked(items);
        if (kill ) {
            Clip clip = Sound.getSound(getClass().getResource("/sound/explosion_hero.wav"));
            clip.start();
            return true;
        }
         return false;
    }
    public boolean bitten() {
        boolean kill = hero.bitten(zombie,zombieUD );
        if (kill ) {
            Clip clip = Sound.getSound(getClass().getResource("/sound/explosion_hero.wav"));
            clip.start();
            return true;
        }
         return false;
    }

    
    public boolean killHome() {
        boolean killFromHero = hero.killHome(home);
        if (killFromHero ) {
            return true;
        }
        for ( int i = 0; i < zombie.size(); i++ ) {
            Zombie z = zombie.get(i);
            if ( z.killHome(home) ) {
                Clip clip = Sound.getSound(getClass().getResource("/sound/explosion_hero.wav"));
                clip.start();
                return true;
            }
        }
        for ( int i = 0; i < zombieUD.size(); i++ ) {
            ZombieUD zUD = zombieUD.get(i);
            if ( zUD.killHome(home) ) {
                Clip clip = Sound.getSound(getClass().getResource("/sound/explosion_hero.wav"));
                clip.start();
                return true;
            }
        }
        return false;
    }
}
