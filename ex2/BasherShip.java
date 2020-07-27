public class BasherShip extends SpaceShip {

    /** the distance the ship needs to have to the closest ship to turn on shield*/
    private static final double DISTANCE_TO_SHIELD_ON = 0.19;

    /**
     * Does the actions of the basher ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
    */
    public void doAction(SpaceWars game){
        accelerate = true;
        faceTowards(game);
        super.doAction(game);
        shieldOnRequirements(game);
    }

    /**
     * checks if the requierements the ship needs to have in order to turn on the shield are met.
     *
     * @param game the game object to which this ship belongs.
     */
    private void shieldOnRequirements(SpaceWars game){
        SpaceShip closest = game.getClosestShipTo(this);
        if (getPhysics().distanceFrom(closest.getPhysics())<=DISTANCE_TO_SHIELD_ON){
            shieldOn();
        }
    }
}

