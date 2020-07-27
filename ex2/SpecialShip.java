import oop.ex2.Physics;
import oop.ex2.SpaceShipPhysics;

public class SpecialShip extends SpaceShip {

    /** the distance the ship needs to have to the closest ship to fire*/
    private static final double MINIMUM_DISTANCE_TO_FIRE = 0.25;

    /** the distance the ship needs to have to the closest ship to neutralize it*/
    private static final double MINIMUM_DISTANCE_TO_NEUTRALIZE = 0.15;

    /** the distance the ship needs to have to the closest ship to teleport*/
    private static final double MINIMUM_DISTANCE_TO_TELEPORT = 0.20;

    /** the maximal energy regeneration every round*/
    private static final double MAXIMAL_ENERGY_REGENERATION = 1;

    /** the current energy regeneration every round*/
    private static final double CURRENT_ENERGY_REGENERATION = 4;

    /** the fire cool down counter*/
    private static final double FIRE_COOL_DOWN_COUNTER = 7;

    /** the fire cool down counter*/
    private static final double HEALTH_TO_CHANGE_BEHAVIOUR = 7;

    /**
     * Does the actions of the special ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        specialTeleport(game);
        accelerate = true;
        aggresiveOrPassive(game);
        spaceShipPhysics.move(accelerate, turn);
        fire(game);
        neutralizeClosestShip(game);
        if (fired) {
            fireCoolDown += FIRE_COOL_DOWN_COUNTER;
        }
        maximalEnergyLevel += MAXIMAL_ENERGY_REGENERATION;
        if (maximalEnergyLevel>=currentEnergyLevel+CURRENT_ENERGY_REGENERATION){
            currentEnergyLevel += CURRENT_ENERGY_REGENERATION;
        }
    }

    /**
     * Attempts to fire a shot if the closest ship within distance.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game){
        SpaceShip closest = game.getClosestShipTo(this);
        if (currentHealth > HEALTH_TO_CHANGE_BEHAVIOUR &&
            getPhysics().distanceFrom(closest.getPhysics()) < MINIMUM_DISTANCE_TO_FIRE) {
            super.fire(game);
        }
    }

    /**
     * when playing passive if the enemy ship gets within distance the ship attempts to teleport.
     *
     * @param game the game object.
     */
    private void specialTeleport(SpaceWars game) {
        SpaceShip closest = game.getClosestShipTo(this);
        if (currentHealth <= HEALTH_TO_CHANGE_BEHAVIOUR &&
                getPhysics().distanceFrom(closest.getPhysics()) < MINIMUM_DISTANCE_TO_TELEPORT) {
            teleport();
        }
    }

    /**
     * when enemy ship gets within range it can't activate shield and accelerate.
     *
     * @param game the game object.
     */
    private void neutralizeClosestShip(SpaceWars game) {
        SpaceShip closest = game.getClosestShipTo(this);
        if (getPhysics().distanceFrom(closest.getPhysics()) < MINIMUM_DISTANCE_TO_NEUTRALIZE) {
            closest.accelerate = false;
            closest.shieldStatus = false;
        }
    }

    /**
     * when the health bar is bellow the minimum the ship runs away and when above it attacks.
     *
     * @param game the game object.
     */
    private void aggresiveOrPassive(SpaceWars game){
        if (currentHealth >= HEALTH_TO_CHANGE_BEHAVIOUR) {
            faceTowards(game);
        } else {
            turnAway(game);
        }
    }
}