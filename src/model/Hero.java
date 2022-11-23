package model;

import gui.PanelGame;
import java.awt.*;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import static model.Images.buffSpeed_ID;
import sound.Sound;

public class Hero extends Item {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

 
    int orientation;
    ArrayList<doubleBullets> doublebullets; 
    ArrayList<Bullet> bullets;
    ArrayList<Ultimate> ultiii;
    public Hero(int id, int x, int y, int size, int orientation) {
        super(id, x, y, size);
        this.orientation = orientation;
        bullets = new ArrayList<Bullet>();
        doublebullets = new ArrayList<doubleBullets>();
        ultiii = new ArrayList<Ultimate>();
    }
    public void move(int orientation, ArrayList<Item> items,ArrayList<Zombie> zombie,ArrayList<ZombieUD> zombieUD) {
        id = Images.ID_HERO[orientation];
        img = Images.getImage(id);
        this.orientation = orientation;
        boolean isIntesect;
        switch (orientation) {
            case LEFT:
                if(upSpeed()){
                x = x - 2;
                isIntesect = interactWithItems(items);
                if ( isIntesect == true ) {
                    x = x + 2;
                    }                   
                }
                else{
                x = x - 1;
                isIntesect = interactWithItems(items);
                if ( isIntesect == true ) {
                    x = x + 1;
                    }
                }
                break;
            case RIGHT:
                if(upSpeed()){
                x = x + 2;
                isIntesect = interactWithItems(items);
                if ( isIntesect == true ) {
                    x = x - 2;
                    }                   
                }
                else{
                x = x + 1;
                isIntesect = interactWithItems(items);
                if ( isIntesect == true ) {
                    x = x - 1;
                    }
                }
                break;
            case UP:
                if(upSpeed()){
                y = y - 2;
                isIntesect = interactWithItems(items);
                if ( isIntesect == true ) {
                    y = y + 2;
                    }                   
                }
                else{
                y = y - 1;
                isIntesect = interactWithItems(items);
                if ( isIntesect == true ) {
                    y = y + 1;
                    }
                }
                break;
            case DOWN:
                if(upSpeed()){
                y = y + 2;
                isIntesect = interactWithItems(items);
                if ( isIntesect == true ) {
                    y = y - 2;
                    }                   
                }
                else{
                y = y + 1;
                isIntesect = interactWithItems(items);
                if ( isIntesect == true ) {
                    y = y - 1;
                    }
                }
                break;
        }
    }
    boolean interactWithItems(ArrayList<Item> items) {
        Rectangle rect1 =  new Rectangle(x, y, size, size);
        for ( int i = 0; i < items.size(); i++ ) {
            Item item = items.get(i);
            if ( item.id == Images.TREE_ID ) {
                continue;
            }
            if ( item.id != Images.WATER_ID  ){
                Rectangle rect3 = new Rectangle(item.x, item.y, item.size, item.size);
            if ( rect1.intersects(rect3) == true) {
                    return true;
                }
            }

        }
        return false;
    }
    
    public void fireUlti(){
        int sizeC = 50;
        int orC = orientation;
        int xC;
        int yC;
        switch (orientation) {
            case LEFT:
                xC = x -  sizeC;
                yC = y + ( size - sizeC )/2;
                break;
            case RIGHT:
                xC = x +  size;
                yC = y + ( size - sizeC )/2;
                break;
            case UP:
                xC = x +  ( size - sizeC )/2;
                yC = y - sizeC;
                break;
            default:
                xC = x +  ( size - sizeC )/2;
                yC = y + size;
                break;
        }
        Ultimate ulti = new Ultimate(Images.Ultimate_ID, xC, yC, sizeC, orC);
        ultiii.add(ulti);
    }
    
    public void fireBullet() {
        int sizeB = 15;
        int orB = orientation;
        int xB;
        int yB;
        switch (orientation) {
            case LEFT:
                xB = x -  sizeB;
                yB = y + ( size - sizeB )/2;
                break;
            case RIGHT:
                xB = x +  size;
                yB = y + ( size - sizeB )/2;
                break;
            case UP:
                xB = x +  ( size - sizeB )/2;
                yB = y - sizeB;
                break;
            default:
                xB = x +  ( size - sizeB )/2;
                yB = y + size;
                break;
        }
        Bullet bullet = new Bullet(Images.BULLET_ID, xB, yB, sizeB, orB);
        doubleBullets doublebullet = new doubleBullets(Images.BULLET_ID, xB, yB, sizeB, orB);
        bullets.add(bullet);
        if(upSkill()) doublebullets.add(doublebullet);
    }
    public void drawAllBullet(Graphics2D g2d) {
        for ( int i = 0; i < bullets.size(); i++ ) {
            Bullet bullet = bullets.get(i);
            bullet.draw(g2d);
        }
        for ( int i = 0; i < doublebullets.size(); i++ ) {
            doubleBullets doublebullet1 = doublebullets.get(i);
            doublebullet1.draw(g2d);
        }
        for ( int i = 0; i < ultiii.size(); i++ ) {
            Ultimate ulti1 = ultiii.get(i);
            ulti1.draw(g2d);
        }
        
    }
    public void moveAllBullet() {
        for ( int i = 0; i< bullets.size(); i++ ) {
            Bullet bullet = bullets.get(i);
            bullet.move();
        }
        for ( int i = 0; i< doublebullets.size(); i++ ) {
            doubleBullets doublebullet1 = doublebullets.get(i);
            doublebullet1.move1();
        }

    }
    public void moveUlti(){
        for ( int i = 0; i< ultiii.size(); i++ ) {
            Ultimate ulti1 = ultiii.get(i);
            ulti1.move3();
        } 
    }
    public void interactdoubleBullet(ArrayList<Item> items) {
        for ( int i = 0; i < doublebullets.size(); i++ ) {
            doubleBullets doublebullet = doublebullets.get(i);
            Rectangle rect1 =new Rectangle(doublebullet.x, doublebullet.y,  doublebullet.size, doublebullet.size);
            for ( int j = 0 ; j < items.size(); j++ ){
                Item item = items.get(j);
                if ( item.id == Images.TREE_ID ) {
                    continue;
                }
                if ( item.id == Images.WATER_ID ) {
                    continue;
                }
                Rectangle rect2 =  new Rectangle(item.x, item.y,item.size, item.size);
                if ( rect1.intersects(rect2) == true ) {
                    if ( item.id == Images.ROCK_ID ) {
                        doublebullets.remove(i);
                        return;
                    }
                    if ( item.id == Images.BOX_ID ) {
                        doublebullets.remove(i);
                        items.remove(j);
                        return;
                    }
                    if ( item.id == Images.buffSpeed_ID) {
                        bullets.remove(i);
                        items.remove(j);
                        PanelGame.checkSpeed = 102;
                        return;
                    } 
                }
            }
        }

    }
    public void interactBullet(ArrayList<Item> items) {
        for ( int i = 0; i < bullets.size(); i++ ) {
            Bullet bullet = bullets.get(i);
            Rectangle rect1 =new Rectangle(bullet.x, bullet.y,  bullet.size, bullet.size);
            for ( int j = 0 ; j < items.size(); j++ ){
                Item item = items.get(j);
                if ( item.id == Images.TREE_ID ) {
                    continue;
                }
                if ( item.id == Images.WATER_ID ) {
                    continue;
                }
                Rectangle rect2 =  new Rectangle(item.x, item.y,item.size, item.size);
                if ( rect1.intersects(rect2) == true ) {
                    if ( item.id == Images.ROCK_ID ) {
                        bullets.remove(i);
                        return;
                    }
                    if ( item.id == Images.BOX_ID ) {
                        bullets.remove(i);
                        items.remove(j);
                        return;
                    }
                    if ( item.id == Images.itemDoubleBullets_ID) {
                        bullets.remove(i);
                        items.remove(j);
                        PanelGame.checkSkill = 100;
                        return;
                    }
                    if ( item.id == Images.buffSpeed_ID) {
                        bullets.remove(i);
                        items.remove(j);
                        PanelGame.checkSpeed = 102;
                        return;
                    }
                }
            }
        }

    }
   
    public boolean upSkill() {
        int check = PanelGame.checkSkill;
        if(check == 100){
            return true;
        }
        return false;
    }
    public boolean upSpeed() {
        int check = PanelGame.checkSpeed;
        if(check == 102) {
            return true;
        }
        return false;
    } 


    public boolean killZombie(Zombie zombie) {
        Rectangle rect1 = new Rectangle(zombie.x, zombie.y, zombie.size, zombie.size);
        for ( int i = 0; i < bullets.size(); i++ ) {
            Bullet bullet = bullets.get(i);
            Rectangle rect2 = new Rectangle(bullet.x, bullet.y, bullet.size, bullet.size);
            if ( rect1.intersects(rect2)) {
                bullets.remove(i);                
                return true;
            }
        }
        
        for ( int i = 0; i < doublebullets.size(); i++ ) {
            doubleBullets doublebullet = doublebullets.get(i);
            Rectangle rect2 = new Rectangle(doublebullet.x, doublebullet.y, doublebullet.size, doublebullet.size);
            if ( rect1.intersects(rect2)) {
                doublebullets.remove(i);
                return true;
            }
        }
        for ( int i = 0; i < ultiii.size(); i++ ) {
            Ultimate ulti = ultiii.get(i);
            Rectangle rect2 = new Rectangle(ulti.x, ulti.y, ulti.size, ulti.size);
            if ( rect1.intersects(rect2)) {                
                return true;
            }
        }        
        return false;
    }
    public boolean killZombieUD(ZombieUD zombieUD) {
        Rectangle rect1 = new Rectangle(zombieUD.x, zombieUD.y, zombieUD.size, zombieUD.size);
        for ( int i = 0; i < bullets.size(); i++ ) {
            Bullet bullet = bullets.get(i);
            Rectangle rect2 = new Rectangle(bullet.x, bullet.y, bullet.size, bullet.size);
            if ( rect1.intersects(rect2)) {
                bullets.remove(i);
                return true;
            }
        }        
        
        for ( int i = 0; i < doublebullets.size(); i++ ) {
            doubleBullets doublebullet = doublebullets.get(i);
            Rectangle rect2 = new Rectangle(doublebullet.x, doublebullet.y, doublebullet.size, doublebullet.size);
            if ( rect1.intersects(rect2)) {
                doublebullets.remove(i);
                return true;
            }
        }

        for ( int i = 0; i < ultiii.size(); i++ ) {
            Ultimate ulti = ultiii.get(i);
            Rectangle rect2 = new Rectangle(ulti.x, ulti.y, ulti.size, ulti.size);
            if ( rect1.intersects(rect2)) {                
                return true;
            }
        }         
        return false;
    }    
    public boolean killHome(Item home) {
        Rectangle rect1 = new Rectangle(home.x, home.y, home.size, home.size);
        for ( int i = 0; i < bullets.size(); i++ ) {
            Bullet bullet = bullets.get(i);
            Rectangle rect2 = new Rectangle(bullet.x, bullet.y, bullet.size, bullet.size);
            if ( rect1.intersects(rect2) ) {
                return true;
            }
        }
        return false;
    }
    public boolean sinked(ArrayList<Item> items) {
        Rectangle rect1 =  new Rectangle(x, y, size, size);
       for ( int i = 0; i < items.size(); i++ ) {
            Item item = items.get(i);
            if ( item.id == Images.WATER_ID  ){
                Rectangle rect2 = new Rectangle(item.x, item.y, item.size, item.size);
                if ( rect1.intersects(rect2) == true) {
                  return true;
                }
            }
        }
        return false;
    }
     public boolean bitten(ArrayList<Zombie> zombie,ArrayList<ZombieUD> zombieUD) {
        Rectangle rect1 =  new Rectangle(x, y, size, size);
        for ( int i = 0; i < zombie.size(); i++ ) {
            Zombie z  = zombie.get(i);
            Rectangle rect2 = new Rectangle(z.x,z.y,z.size, z.size);
            if ( rect1.intersects(rect2) == true) {
                return true;
            }
        }
        for ( int i = 0; i < zombieUD.size(); i++ ) {
            ZombieUD zUD  = zombieUD.get(i);
            Rectangle rect3 = new Rectangle(zUD.x,zUD.y,zUD.size, zUD.size);
            if ( rect1.intersects(rect3) == true) {
              return true;
            }
        }
        return false;
    }
}
