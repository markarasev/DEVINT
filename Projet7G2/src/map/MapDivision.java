package map;

import main.Game;
import objects.GameObject;
import objects.ObjectType;
import objects.Rock;
import org.newdawn.slick.Graphics;
import util.Preferences;

import java.util.ArrayList;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV
 */
public class MapDivision {

    private ArrayList<GameObject> objects;
    private boolean[][] accessMap;

    public MapDivision() {
        objects = new ArrayList<GameObject>();
        initAccessMap();
    }

    public void render(Graphics g) {
        g.setBackground(Preferences.getBackgroundColor());
        for(GameObject obj: objects) {
            obj.draw(g);
        }
    }

    public boolean isTileAccessible(int x, int y) {
        if (x < 0 || y < 0 || x > Game.XTILEMAX-1 || y > Game.YTILEMAX-1) {
            return false;
        }
        return accessMap[x][y];
    }

    public void addObject(GameObject obj) {
        objects.add(obj);
        updateAccessMap(obj);
    }

    private void initAccessMap() {
        accessMap = new boolean[Game.XTILEMAX][Game.YTILEMAX];
        for(int i = 0; i < Game.XTILEMAX; i++) {
            for(int j = 0; j < Game.YTILEMAX; j++) {
                accessMap[i][j] = true;
            }
        }
    }

    private void updateAccessMap(GameObject obj) {
        for (int i = Game.toTile(obj.getX()); i < Game.toTile(obj.getWidth()); i++) {
            for(int j = Game.toTile(obj.getY()); j < Game.toTile(obj.getHeight()); j++) {
                accessMap[i][j] = false;
            }
        }
    }
}