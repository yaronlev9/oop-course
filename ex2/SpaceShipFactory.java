import oop.ex2.*;

/**
* This class receives the input of the ships and makes objects for every ship.
*
* @author oop
*/
public class SpaceShipFactory {

    /** the human controlled spaceship string*/
    private static final char HUMAN = 'h';

    /** the runner spaceship string*/
    private static final char RUNNER = 'r';

    /** the basher spaceship string*/
    private static final char BASHER = 'b';

    /** the aggresive spaceship string*/
    private static final char AGGRESSIVE = 'a';

    /** the drunk spaceship string*/
    private static final char DRUNKARD = 'd';

    /** the special spaceship string*/
    private static final char SPECIAL = 's';

    /**
     * This method is used by the StarWars game object to create objects of every ship of every type
     * using the input given
     *
     * @param args a list of strings representing evert spaceship.
     * @return a list of objects of the spaceships.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip [] spaceShipArray = new SpaceShip[args.length];
        for (int i = 0; i<args.length; i++){
            char ship = args[i].charAt(0);
            if (ship == HUMAN){
                spaceShipArray[i] = new HumanShip();
                continue;
            }else if (ship == RUNNER){
                spaceShipArray[i] = new RunnerShip();
                continue;
            }else if (ship == BASHER){
                spaceShipArray[i] = new BasherShip();
                continue;
            }else if (ship == AGGRESSIVE){
                spaceShipArray[i] = new AggressiveShip();
                continue;
            }else if (ship == DRUNKARD){
                spaceShipArray[i] = new DrunkardShip();
                continue;
            }else if (ship == SPECIAL){
                spaceShipArray[i] = new SpecialShip();
            }
        }
        return spaceShipArray;
    }
}
