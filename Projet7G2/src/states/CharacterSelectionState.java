package states;

import main.Game;
import objects.Sprite;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.Preferences;

/**
 * Created by Marc KARASSEV on 10/05/2014.
 */
public class CharacterSelectionState extends BasicGameState {

    private static final int ID = 5, DARKSLIME = 0, ANGELSLIME = 1, FANGSLIME = 2, SLIME = 3;

    private static int currentCharacter;
    private AppGameContainer container;
    private main.Game game;
    private Image darkslime, angelslime, fangslime, slime;

    public CharacterSelectionState() {
        super();
        try {
            darkslime = new Image(Sprite.DARKSLIME.getSheet(), Sprite.TRANSP);
            angelslime = new Image(Sprite.ANGELSLIME.getSheet(), Sprite.TRANSP);
            fangslime = new Image(Sprite.FANGSLIME.getSheet(), Sprite.TRANSP);
            slime = new Image(Sprite.SLIME.getSheet(), Sprite.TRANSP);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        if (gameContainer instanceof AppGameContainer) {
            container = (AppGameContainer) gameContainer;
        } else throw new SlickException("init containter");
        if (stateBasedGame instanceof Game) {
            game = (Game) stateBasedGame;
        } else throw new SlickException("init game");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        short[] coords = Sprite.DARKSLIME.getSouthCoords(), dims = Sprite.DARKSLIME.getSouthDims();

        g.drawImage(darkslime, 50, 50, 50 + 2 * dims[0], 50 + 2 * dims[1], coords[0], coords[1],
                coords[0] + dims[0], coords[1] + dims[1]);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        currentCharacter = DARKSLIME;
        Preferences.getVoice().stop();
        Preferences.getVoice().playShortText("Choisissez votre personnage.");
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_ENTER:
                onEnter();
                break;
            case Input.KEY_UP:
                onUpDown();
                break;
            case Input.KEY_LEFT:
                onLeftRight();
                break;
            case Input.KEY_RIGHT:
                onLeftRight();
                break;
            case Input.KEY_DOWN:
                onUpDown();
                break;
            case Input.KEY_ESCAPE:
                onEscape();
                break;
            case Input.KEY_F3:
                onF3();
                break;
            case Input.KEY_F4:
                onF4();
                break;
            default:
        }
    }

    private void onEnter() {
        switch (currentCharacter) {
            case DARKSLIME:

                break;
            case ANGELSLIME:
                break;
            case FANGSLIME:
                break;
            case SLIME:
                break;
            default:
                System.err.println("personnage incorrect");
        }
        try {
            leave(container, game);
            game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private void onEscape() {
        try {
            leave(container, game);
            game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private void onF3() {
        Preferences.changeColors();
    }

    private void onF4() {
        Preferences.changeVoice();
    }

    private void onUpDown() {
        if(currentCharacter < 2) {
            currentCharacter += 2;
        }
        else {
            currentCharacter -= 2;
        }
    }

    private void onLeftRight() {
        if(currentCharacter % 2 == 0) {
            currentCharacter += 1;
        }
        else {
            currentCharacter -= 1;
        }
    }

    public static Sprite getCharacter() {
        switch(currentCharacter) {
            case DARKSLIME:
                return Sprite.DARKSLIME;
            case ANGELSLIME:
                return Sprite.ANGELSLIME;
            case FANGSLIME:
                return Sprite.FANGSLIME;
            case SLIME:
                return Sprite.SLIME;
            default:
                return Sprite.DARKSLIME;
        }
    }
}
