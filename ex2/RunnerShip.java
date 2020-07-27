
public class RunnerShip extends SpaceShip {

    /** the distance the ship needs to have to the closest ship to teleport*/
    private static final double DISTANCE_TO_TELEPORT = 0.25;

    /** the angle the attacking ship needs to have to the ship to teleport for the ship to teleport*/
    private static final double ANGLE_TO_TELEPORT = 0.23;

    /**
     * Does the actions of the runner ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        teleportRequirements(game);
        accelerate = true;
        turnAway(game);
        super.doAction(game);
    }

    /**
     * checks if the requierements the ship needs to have in order to teleport are met.
     *
     * @param game the game object to which this ship belongs.
     */
    private void teleportRequirements(SpaceWars game){
        SpaceShip closest = game.getClosestShipTo(this);
        if (getPhysics().distanceFrom(closest.getPhysics())<DISTANCE_TO_TELEPORT &&
                closest.getPhysics().angleTo(this.getPhysics())<ANGLE_TO_TELEPORT &&
                closest.getPhysics().angleTo(this.getPhysics())>-ANGLE_TO_TELEPORT){
            teleport();
        }
    }
}

