
package model;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Ultimate extends Item {
    public static Image IMG_ULTI = new ImageIcon(Bullet.class.getResource("/image/ulti.png")).getImage();

    int orientation;

    public Ultimate(int id, int x, int y, int size, int orientation) {
        super(id, x, y, size);
        this.orientation = orientation;
    }

    public void move3() {
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
