import java.util.Random;

public class DrunkardShip extends SpaceShip {

    /** the number of randoms to choose to activate shield*/
    private  static final int RANDOM_SHIELD = 400;

    /** the number of randoms to choose to activate fire*/
    private  static final int RANDOM_FIRE = 100;

    /** the minimum percent to activate either shield or fire*/
    private  static final double PERCENT_TO_ACTIVATE = 0.9;

    /** the number of rounds the ship will turn before it changes directions*/
    private static final int ROUNDS_TO_TURN = 250;

    /** the number of rounds the shield can be active after being turned on.*/
    private static final int ROUNDS_TO_SHIELD = 1;

    /** the count of the number of rounds the ship is turning in the same direction*/
    private static int turnCount = 0;

    /** the count of the number of rounds the ship can have the shield turned on.*/
    private static int shieldCount = 0;


    /** sets the turn the ship starts with*/
    DrunkardShip() {
        chooseRandomStart();
    }

    /**
     * Does the actions of the drunkard ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        accelerate = true;
        drunkTurn();
        super.doAction(game);
        randomShield();
        randomFire(game);
        turnCount++;
        shieldCount++;
    }

    /**
     * changes the side the ship is turning.
     *
     */
    private void drunkTurn() {
        if (turnCount > ROUNDS_TO_TURN) {
            turn = turn * -1;
            turnCount = 0;
        }
    }

    /**
     * chooses randomly the direction the drunk ship will start moving in circles at the start of the round.
     *
     */
    private void chooseRandomStart() {
        Random rand = new Random();
        int n = rand.nextInt(2);
        if (n == 0) {
            turn = -1;
        } else {
            turn = 1;
        }
    }

    /**
     * if requierments are met turns on the shield and after one round with the shield on it deactivates it.
     *
     */
    private void drunkShield() {
        if (shieldStatus && shieldCount > ROUNDS_TO_SHIELD) {
            shieldStatus = false;
            shieldCount = 0;
        } else if (!shieldStatus && shieldCount > ROUNDS_TO_SHIELD){
            shieldStatus = true;
            shieldCount = 0;
        }
    }

    /**
     * the spaceship turnes on shield randomly.
     *
     */
    private void randomShield(){
        Random rand = new Random();
        int n = rand.nextInt(RANDOM_SHIELD);
        if (n>PERCENT_TO_ACTIVATE*RANDOM_SHIELD){
            drunkShield();
        }
    }

    /**
     * the spaceship fires randomly.
     *
     * @param game the game object to which this ship belongs.
     */
    private  void randomFire(SpaceWars game){
        Random rand = new Random();
        int n = rand.nextInt(RANDOM_FIRE);
        if (n>PERCENT_TO_ACTIVATE*RANDOM_FIRE){
            fire(game);
        }
    }
}