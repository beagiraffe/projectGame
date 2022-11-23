package model;

import javax.swing.*;
import java.awt.*;


public class Images {
    public static final int BOX_ID = 1;
    public static final int WATER_ID = 2;
    public static final int TREE_ID = 4;
    public static final int ROCK_ID = 5;
    public static final int HOME_ID = 9;
    public static final int BULLET_ID = 22;
    public static final int itemDoubleBullets_ID = 6;
    public static final int buffSpeed_ID = 7;
    public static final int Ultimate_ID = 8;
    
    public static final int[] ID_HERO = new int[] { 10, 11, 12, 13 };
    public static final int[] ID_ZOMBIE = new int[] { 14, 15, 16, 17 };
    public static final int[] ID_ZOMBIEUD = new int[] { 18, 19, 20, 21 };


    public static final Image ultimatee = new ImageIcon(Images.class.getResource("/image/ulti.png")).getImage();   
    
    public static final Image buffSpeed = new ImageIcon(
            Images.class.getResource("/image/map/speed.png")).getImage();
    
    public static final Image itemDoubleBullets = new ImageIcon(
            Images.class.getResource("/image/map/UpLevelWeapons.png")).getImage();
    
    public static final Image TREE = new ImageIcon(
            Images.class.getResource("/image/map/tree2.png")).getImage();

    public static final Image ROCK = new ImageIcon(
            Images.class.getResource("/image/map/rock.png")).getImage();

    public static final Image WATER = new ImageIcon(
            Images.class.getResource("/image/map/water.png")).getImage();

    public static final Image BOX = new ImageIcon(
            Images.class.getResource("/image/map/box.png")).getImage();
    public static final Image HOME = new ImageIcon(
            Images.class.getResource("/image/map/home.png")).getImage();

    public static final Image HERO_LEFT = new ImageIcon(
            Images.class.getResource("/image/player/player_green_left.png")).getImage();
    public static final Image HERO_RIGHT = new ImageIcon(
            Images.class.getResource("/image/player/player_green_right.png")).getImage();
    public static final Image HERO_UP = new ImageIcon(
            Images.class.getResource("/image/player/player_green_up.png")).getImage();
    public static final Image HERO_DOWN = new ImageIcon(
            Images.class.getResource("/image/player/player_green_down.png")).getImage();


    public static final Image ZOMBIE_LEFT = new ImageIcon(
            Images.class.getResource("/image/boss/zombie_left.png")).getImage();
    public static final Image ZOMBIE_RIGHT = new ImageIcon(
            Images.class.getResource("/image/boss/zombie_right.png")).getImage();
    public static final Image ZOMBIE_UP = new ImageIcon(
            Images.class.getResource("/image/boss/zombie_up.png")).getImage();
    public static final Image ZOMBIE_DOWN = new ImageIcon(
            Images.class.getResource("/image/boss/zombie_down.png")).getImage();
    
    
    public static final Image ZOMBIEUD_LEFT = new ImageIcon(
            Images.class.getResource("/image/boss/PVZ_Zombie_Suit.png")).getImage();
    public static final Image ZOMBIEUD_RIGHT = new ImageIcon(
            Images.class.getResource("/image/boss/PVZ_Zombie_Suit.png")).getImage();
    public static final Image ZOMBIEUD_UP = new ImageIcon(
            Images.class.getResource("/image/boss/PVZ_Zombie_Suit.png")).getImage();
    public static final Image ZOMBIEUD_DOWN = new ImageIcon(
            Images.class.getResource("/image/boss/PVZ_Zombie_Suit.png")).getImage();    

    public static final Image BULLET = new ImageIcon(Images.class.getResource("/image/bullet.png")).getImage();

    public static Image getImage(int id) {
        switch (id) {
            case TREE_ID:
                return TREE;
            case ROCK_ID:
                return ROCK;
            case WATER_ID:
                return WATER;
            case BOX_ID:
                return BOX;
            case HOME_ID:
                return HOME;
            case itemDoubleBullets_ID:
                return itemDoubleBullets;
            case buffSpeed_ID:
                return buffSpeed;
            case Ultimate_ID:
                return ultimatee;
            case 10:
                return HERO_LEFT;
            case 11:
                return HERO_RIGHT;
            case 12:
                return HERO_UP;
            case 13:
                return HERO_DOWN;
            case BULLET_ID:
                return BULLET;
            case 14:
                return ZOMBIE_LEFT;
            case 15:
                return ZOMBIE_RIGHT;
            case 16:
                return ZOMBIE_UP;
            case 17:
                return ZOMBIE_DOWN;
            case 18:
                return ZOMBIEUD_LEFT;
            case 19:
                return ZOMBIEUD_RIGHT;
            case 20:
                return ZOMBIEUD_UP;
            case 21:
                return ZOMBIEUD_DOWN;

            default:
                return null;
        }
    }
}
