
public class AggressiveShip extends SpaceShip {

    /** the angle the ship needs to have to the closest ship to fire*/
    private static final double ANGLE_TO_SHOOT= 0.21;

    /**
     * Does the actions of the aggresive ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        accelerate = true;
        faceTowards(game);
        super.doAction(game);
        shootOnRequirements(game);
    }

    /**
     * checks if the requierements the ship needs to have in order to fire are met.
     *
     * @param game the game object to which this ship belongs.
     */
    private void shootOnRequirements(SpaceWars game){
        SpaceShip closest = game.getClosestShipTo(this);
        if (getPhysics().angleTo(closest.getPhysics())<ANGLE_TO_SHOOT){
            fire(game);
        }
    }
}
