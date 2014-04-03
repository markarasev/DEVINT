package map;

import main.Game;
import org.newdawn.slick.Graphics;
import util.Position;
import util.Preferences;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV
 */
public class MapDivision {
    private ArrayList<MapObject> objects;
    private boolean[][] accessMap;

    public MapDivision() {
        //TODO
    }

    public void render(Graphics g) {
        /*for(MapObject obj: objects) {
            obj.draw(g);
        }*/
        g.setBackground(Preferences.getBackgroundColor());
        g.setColor(Preferences.getItemColor());
        g.fillRect(0, 0, Game.FRAMEWIDTH / 2, Game.FRAMEHEIGHT / 6);
        g.fillRect(3 * Game.FRAMEWIDTH / 4, 0, Game.FRAMEWIDTH, Game.FRAMEHEIGHT / 6);
        g.fillRect(0, Game.FRAMEHEIGHT / 2, Game.FRAMEWIDTH / 4, Game.FRAMEHEIGHT);
        g.fillRect(3 * Game.FRAMEWIDTH / 4, 5 * Game.FRAMEHEIGHT / 6, Game.FRAMEWIDTH, Game.FRAMEHEIGHT);
    }

    public boolean isTileAccessible(Position p) {
        return accessMap[p.getX()][p.getY()];
    }
}
