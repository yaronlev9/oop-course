import oop.ex3.spaceship.*;
import java.util.*;

/**
 * The API locker.
 *  a class for the locker.
 * @author oop
*/
public class Locker{

    /** the of the space */
    private final char SPACE =' ';

    /** the fail code */
    private final int FAIL = -1;

    /** the fail when is able to add to locker but not able to add to long term storage */
    private final int FAIL2 = -2;

    /** the success code */
    private final int SUCCESS = 0;

    /** the success code when successfully enters locker and long term storage */
    private final int SUCCESS2 = 1;

    /** the 50 percent */
    private final double HALF_STORAGE = 0.5;

    /** the 20 percent */
    private final double FIFTH_STORAGE = 0.2;

    /** the item name of the football */
    private final String FOOTBALL = "football";

    /** the item name of the baseball bat */
    private final String BASEBALL_BAT = "baseball bat";

    /** the fail message */
    private final String FAIL_MSG = "Error: Your request cannot be completed at this time.";

    /** the message when given to remove negative number of items */
    private final String NEGATIVE_NUM_MSG = "Problem: cannot remove a negative number of items of type";

    /** the first part of the message when given to remove a higher number of an item than it contains in the locker */
    private final String HIGH_NUM_MSG_1 = "Problem: the locker does not contain";

    /** the second part of the message when given to remove a higher number of an item than it contains in the locker */
    private final String HIGH_NUM_MSG_2 = "items of type";

    /** the first part of the message when there is no room for the item in the locker */
    private final String NO_ROOM_MSG_1 = "Problem: no room for";

    /** the second part of the message when there is no room for the item in the locker */
    private final String NO_ROOM_MSG_2 = "items of type";

    /** the warning message that items were moved to long term storage */
    private final String WARNING_MSG = "Warning: Action successful, but has caused items to be moved to storage";

    /** the general error message */
    private final String GENERAL_ERROR_MSG = "Error: Your request cannot be completed at this time.";

    /** the first part of the fail message when trying to add an item that contradicts an existing one */
    private final String CONTRADICTION_ERROR_MSG1 = "Problem: the locker cannot contain items of type";

    /** the second part of the fail message when trying to add an item that contradicts an existing one */
    private final String CONTRADICTION_ERROR_MSG2 = "as it contains a contradicting item";

    /** the capacity of the locker */
    private final int capacity;

    /** the inventory of the locker */
    private Map <String,Integer>inventory;

    /** the available capacity of the locker */
    private int availableCapacity;

    /** the object of the long term storage */
    protected static LongTermStorage longTermStorage = new LongTermStorage();

    /**
     * Constructs a new locker object.
     *
     * @param capacity the locker capacity.
     */
    public Locker(int capacity){
        this.capacity = capacity;
        this.inventory = new HashMap<>();
        this.availableCapacity = capacity;
    }

    /**
    * adds a number of a specific item.
    * @param item the item to be added
    * @param n the number of coppies of the item to add
    * @return a fail or success code
    */
    public int addItem(Item item, int n){
        if (item != null && checkItemExistence(item.getType())){
            System.out.println(FAIL_MSG+SPACE+CONTRADICTION_ERROR_MSG1+SPACE+item.getType()+SPACE+
                    CONTRADICTION_ERROR_MSG2);
            return FAIL2;
        }
        /**checks wrong input */
        if (item == null || n<=0){
            System.out.println(GENERAL_ERROR_MSG);
            return FAIL;
        }
        String itemName = item.getType();
        int itemVolume = item.getVolume();
        /**checks if the available capacity is smaller than the amount to insert to locker */
        if (availableCapacity<itemVolume*n){
            System.out.println(FAIL_MSG+SPACE+NO_ROOM_MSG_1+SPACE+n+SPACE+
                    NO_ROOM_MSG_2+SPACE+itemName);
            return FAIL;
        /**checks if there is room for the amount to insert to locker */
        } else {
            return tryInsertLockerOrLTS(item, n, itemName, itemVolume);
        }
    }

    /**
     * removes a number of a specific item.
     * @param item the item to be removed
     * @param n the number of coppies of the item to remove
     * @return a fail or success code
     */
    public int removeItem(Item item, int n){
        if (item == null || n==0){
            System.out.println(GENERAL_ERROR_MSG);
            return FAIL;
        }
        String itemName = item.getType();
        int itemVolume = item.getVolume();
        if (n<0){
            System.out.println(FAIL_MSG+SPACE+NEGATIVE_NUM_MSG+SPACE+itemName);
            return FAIL;
        } else if ( n>getItemCount(itemName)) {
            System.out.println(FAIL_MSG + SPACE + HIGH_NUM_MSG_1 + SPACE + n + SPACE +
                    HIGH_NUM_MSG_2 + SPACE + itemName);
            return FAIL;
        } else if (n<getItemCount(itemName)){
            inventory.put(itemName, getItemCount(itemName) - n);
            availableCapacity += n*itemVolume;
            return SUCCESS;
        } else if (n == getItemCount(itemName)){
            inventory.remove(itemName);
            availableCapacity += n*itemVolume;
            return SUCCESS;
        }
        System.out.println(GENERAL_ERROR_MSG);
        return FAIL;
    }

    /**
     * gets the number of coppies of an item that are in the inventory.
     * @param type the type of item
     * @return the number of coppies of the item or fail code if the item is not in the inventory
     */
    public int getItemCount(String type) {
        if (!inventory.isEmpty() && inventory.containsKey(type)){
            return inventory.get(type);
        }
        return 0;
    }

    /**
     * gets the inventory.
     * @return a Map of the inventory
     */
    public Map<String, Integer> getInventory(){
        return inventory;
    }

    /**
     * gets the capacity.
     * @return the capacity
     */
    public int getCapacity(){
        if (capacity>=0){
            return capacity;
        }
        System.out.println(GENERAL_ERROR_MSG);
        return FAIL;
    }

    /**
     * gets the available capacity.
     * @return the available capacity
     */
    public int getAvailableCapacity(){
        return availableCapacity;
    }

    /**
     * tries to move some amount of the item to long term storage.
     * @param item the item
     * @param itemName the name of the item
     * @param moveToLongTerm the number of coppies of the item to move to long term storage
     * @param addToLocker the number of coppies of the item to add to locker
     * @return true if success or false if fail
     */
    private boolean MoveToStorage(Item item, String itemName,int moveToLongTerm,int addToLocker) {
        int itemVolume = item.getVolume();
        if (longTermStorage.addItem(item, moveToLongTerm) == SUCCESS) {
            availableCapacity += (getItemCount(itemName)-addToLocker)*itemVolume;
            if (addToLocker == 0){
                return true;
            }
            inventory.put(itemName, addToLocker);
            return true;
        }
        return false;
    }

    /**
     * checks if the contradicting item exists in inventory.
     * @param itemName the name of the item to be added
     * @return true if exists and false if not
     */
    private boolean checkItemExistence(String itemName) {
        if ((itemName.equals(FOOTBALL) && inventory.containsKey(BASEBALL_BAT)) ||
                (itemName.equals(BASEBALL_BAT) && inventory.containsKey(FOOTBALL))){
            return true;
        }
        return false;
    }

    /**
     * tries to add a number of a specific item to the locker ot the long term storage or both.
     * @param item the item to be added
     * @param n the number of coppies of the item to add
     * @param itemName the name of the item
     * @param itemVolume the volume of the item
     * @return a fail or success code
     */
    private Integer tryInsertLockerOrLTS(Item item, int n, String itemName, int itemVolume) {
        double itemStorageUnits = (n+getItemCount(itemName))*itemVolume;
        if (itemStorageUnits>HALF_STORAGE*capacity) {
            int addToLocker = (int) (FIFTH_STORAGE * capacity) / itemVolume;
            int moveToLongTerm = n+getItemCount(itemName) - addToLocker;
            /**tries to insert to long term storage */
            if (MoveToStorage(item, itemName, moveToLongTerm, addToLocker)) {
                System.out.println(WARNING_MSG);
                return SUCCESS2;
            } else {
                return FAIL;
            }
            /**if no need to put inside long term storage it enters to locker */
        } else {
            inventory.put(itemName, n + getItemCount(itemName));
            availableCapacity -= n * itemVolume;
            return SUCCESS;
        }
    }
}
