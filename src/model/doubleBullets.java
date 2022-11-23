
package model;

import java.awt.Image;
import javax.swing.ImageIcon;


public class doubleBullets extends Item {
    public static Image IMG_DOUBLEBULLET = new ImageIcon(doubleBullets.class.getResource("/image/bullet.png")) .getImage();

    int orientation;

    public doubleBullets(int id, int x, int y, int size, int orientation) {
        super(id, x, y, size);
        this.orientation = orientation;
    }

    public void move1() {
        switch (orientation) { //toc do dan bay + huong bay
            case Hero.LEFT:
                x = x - 4;
                break;
            case Hero.RIGHT:
                x = x + 4;
                break;
            case Hero.UP:
                y = y - 4;
                break;
            case Hero.DOWN:
                y = y + 4;
                break;
            default:
                break;
        }

    }    


}
