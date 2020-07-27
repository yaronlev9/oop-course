import oop.ex2.GameGUI;

import java.awt.*;

public class HumanShip extends SpaceShip {

    /**
     * Does the actions of the human ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        if (game.getGUI().isTeleportPressed()){
            teleport();
        }
        setDirection(game);
        if (game.getGUI().isUpPressed()){
            accelerate = true;
        }
        super.doAction(game);
        if (game.getGUI().isShieldsPressed()){
            shieldOn();
        } else {
            shieldStatus = false;
        }
        if (game.getGUI().isShotPressed()){
            fire(game);
        }
        turn = FORWARD;
        accelerate = false;
    }

    /**
     * Gets the image of the human ship. This method should return the image of the human
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage(){
        if (shieldStatus){
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        } else {
            return GameGUI.SPACESHIP_IMAGE;
        }
    }

    /**
     * sets the direction the ship will move.
     *
     * @param game the game object to which this ship belongs.
     */
    private void setDirection(SpaceWars game){
        if (game.getGUI().isLeftPressed() && game.getGUI().isRightPressed()){
            turn = FORWARD;
        } else if (game.getGUI().isLeftPressed()){
            turn = LEFT;
        } else if (game.getGUI().isRightPressed()) {
            turn = RIGHT;
        }
    }
}
