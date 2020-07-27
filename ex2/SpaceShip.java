import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public class SpaceShip {

    /** ship direction forward*/
    protected static final int FORWARD = 0;

    /** ship direction right*/
    protected static final int RIGHT = -1;

    /** ship direction left*/
    protected static final int LEFT = 1;

    /** ship's maximal health*/
    protected static final int MAX_HEALTH = 22;

    /** the reduce of health a spaceship receives after getting shot*/
    protected static final int SHOT_DAMAGE = 1;

    /** the reduce of health a spaceship receives after collision*/
    protected static final int COLLISION_DAMAGE = 1;

    /** ship's maximal energy*/
    protected static final int MAXIMAL_ENERGY_LEVEL = 210;

    /** ship's starting energy*/
    protected static final int STARTING_ENERGY_LEVEL = 190;

    /** the energy a spaceship receives after bashing*/
    protected static final int BASHING_ENERGY_GAINED = 18;

    /** the reduce of energy a spaceship receives after collision or getting shot*/
    protected static final int HIT_ENERGY_DAMAGE = 10;

    /** the energy a spaceship receives after every round*/
    protected static final int CHARGE_ENERGY_LEVEL = 1;

    /** the reduce of energy a spaceship receives after firing a shot*/
    protected static final int SHOT_ENERGY_COST = 19;

    /** the reduce of energy a spaceship receives after teleporting*/
    protected static final int TELEPORT_ENERGY_COST = 140;

    /** the reduce of energy a spaceship receives when shield is active*/
    protected static final int SHIELD_ENERGY_COST_PER_ROUND = 3;

    /** the number of rounds a spaceship cannot fire after it fired*/
    protected static final int FIRE_COOL_DOWN_PERIOD = 7;

    /** the status of the shield if true shield on if false shield off*/
    protected boolean shieldStatus = false;

    /** the direction of the turn the spaceship makes*/
    protected int turn = FORWARD;

    /** if true the ship accelerates if false it does not accelerate*/
    protected boolean accelerate = false;

    /** the current health of the spaceship*/
    protected int currentHealth;

    /** the maximal energy level of the spaceship*/
    protected int maximalEnergyLevel;

    /** the current energy level of the spaceship*/
    protected int currentEnergyLevel;

    /** the fire cooldown counter of the spaceship*/
    protected int fireCoolDown = 0;

    /** true if the spaceship fires false if not*/
    protected boolean fired = false;

    /** the physics object of the spaceship*/
    protected SpaceShipPhysics spaceShipPhysics;

    SpaceShip() {
        currentHealth = MAX_HEALTH;
        maximalEnergyLevel = MAXIMAL_ENERGY_LEVEL;
        currentEnergyLevel = STARTING_ENERGY_LEVEL;
        spaceShipPhysics = new SpaceShipPhysics();
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        spaceShipPhysics.move(accelerate, turn);
        if (shieldStatus && currentEnergyLevel >= SHIELD_ENERGY_COST_PER_ROUND) {
            currentEnergyLevel -= SHIELD_ENERGY_COST_PER_ROUND;
        } else if (currentEnergyLevel < SHIELD_ENERGY_COST_PER_ROUND) {
            shieldStatus = false;
        }
        if (fired) {
            fireCoolDown++;
        }
        if (currentEnergyLevel < maximalEnergyLevel) {
            currentEnergyLevel += CHARGE_ENERGY_LEVEL;
        }
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip() {
        if (!shieldStatus) {
            currentHealth -= COLLISION_DAMAGE;
            maximalEnergyLevel -= HIT_ENERGY_DAMAGE;
            if (maximalEnergyLevel < 0) {
                maximalEnergyLevel = 0;
            }
            if (maximalEnergyLevel < currentEnergyLevel) {
                currentEnergyLevel = maximalEnergyLevel;
            }
        } else {
            currentEnergyLevel += BASHING_ENERGY_GAINED;
            maximalEnergyLevel += BASHING_ENERGY_GAINED;
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        if (isDead()) {
            currentEnergyLevel = STARTING_ENERGY_LEVEL;
            maximalEnergyLevel = MAXIMAL_ENERGY_LEVEL;
            currentHealth = MAX_HEALTH;
            spaceShipPhysics = new SpaceShipPhysics();
        }
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (currentHealth <= 0) {
            return true;
        }
            return false;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return spaceShipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shieldStatus) {
            currentHealth -= SHOT_DAMAGE;
            maximalEnergyLevel -= HIT_ENERGY_DAMAGE;
            if (maximalEnergyLevel < 0) {
                maximalEnergyLevel = 0;
            }
            if (maximalEnergyLevel < currentEnergyLevel) {
                currentEnergyLevel = maximalEnergyLevel;
            }
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage() {
        if (shieldStatus) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        } else {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (isFireCoolDownOver() && currentEnergyLevel >= SHOT_ENERGY_COST) {
            game.addShot(spaceShipPhysics);
            currentEnergyLevel -= SHOT_ENERGY_COST;
            fired = true;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (currentEnergyLevel >= SHIELD_ENERGY_COST_PER_ROUND) {
            shieldStatus = true;
        }
    }


    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (currentEnergyLevel >= TELEPORT_ENERGY_COST) {
            currentEnergyLevel -= TELEPORT_ENERGY_COST;
            spaceShipPhysics = new SpaceShipPhysics();

        }
    }

    /**
     * checks if the fire cooldown period is over returns true if is and if not over returns false.
     */
    private boolean isFireCoolDownOver() {
        if (fired && fireCoolDown >= FIRE_COOL_DOWN_PERIOD) {
            fired = false;
            fireCoolDown = 0;
            return true;
        } else if (!fired) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * makes the ship face towards the closest spaceship to it.
     */
    protected void faceTowards(SpaceWars game) {
        SpaceShip closest = game.getClosestShipTo(this);
        if (getPhysics().angleTo(closest.getPhysics()) < 0) {
            turn = RIGHT;
        } else if (getPhysics().angleTo(closest.getPhysics()) > 0) {
            turn = LEFT;
        } else if (getPhysics().angleTo(closest.getPhysics()) == 0){
            turn = FORWARD;
        }

    }

    /**
     * makes the ship run away from the closest spaceship to it.
     *
     * @param game the game object to which this ship belongs.
     */
    protected void turnAway(SpaceWars game){
        SpaceShip closest = game.getClosestShipTo(this);
        if (getPhysics().angleTo(closest.getPhysics())<=0){
            turn = LEFT;
        } else if (getPhysics().angleTo(closest.getPhysics())>0) {
            turn = RIGHT;
        }

    }
}