package model;

import javax.swing.*;
import java.awt.*;

public class Bullet extends Item {
    public static Image IMG_BULLET = new ImageIcon(Bullet.class.getResource("/image/bullet.png")).getImage();

    int orientation;

    public Bullet(int id, int x, int y, int size, int orientation) {
        super(id, x, y, size);
        this.orientation = orientation;
    }

    public void move() {
        switch (orientation) { //toc do dan bay + huong bay
            case Hero.LEFT:
                x = x - 3;

                break;
            case Hero.RIGHT:
                x = x + 3;

                break;
            case Hero.UP:
                y = y - 3;

                break;
            case Hero.DOWN:
                y = y + 3;

                break;
            default:
                break;
        }

    }

}
