import oop.ex3.spaceship.*;
import java.util.*;

/**
 * The API long term storage.
 *  a class for the long term storage.
 * @author oop
*/
public class LongTermStorage {

    /** the capacity of the long term storage */
    private final int CAPACITY = 1000;

    /** the of the space */
    private final char SPACE =' ';

    /** the fail code */
    private final int FAIL = -1;

    /** the success code */
    private final int SUCCESS = 0;

    /** the fail message */
    private final String FAIL_MSG = "Error: Your request cannot be completed at this time.";

    /** the first part of the message when there is no room for the item in the locker */
    private final String NO_ROOM_MSG_1 = "Problem: no room for";

    /** the second part of the message when there is no room for the item in the locker */
    private final String NO_ROOM_MSG_2 = "items of type";

    /** the general error message */
    private final String GENERAL_ERROR_MSG = "Error: Your request cannot be completed at this time.";

    /** the inventory of the long term storage */
    private Map <String,Integer>inventory;

    /** the available capacity of the long term storage */
    private int availableCapacity;

    /**
     * Constructs a new long term storage object.
     */
    public LongTermStorage(){
        this.inventory = new HashMap<>();
        this.availableCapacity = CAPACITY;
    }

    /**
     * adds a number of a specific item.
     * @param item the item to be added
     * @param n the number of coppies of the item to add
     * @return a fail or success code
     */
    public int addItem(Item item, int n){
        if (item == null || n<=0){
            System.out.println(GENERAL_ERROR_MSG);
            return FAIL;
        }
        int itemVolume = item.getVolume();
        String itemName = item.getType();
        if (n*itemVolume<=availableCapacity){
            inventory.put(itemName,n+getItemCount(itemName));
            availableCapacity -= itemVolume*n;
            return SUCCESS;
        } else if (n*itemVolume>availableCapacity){
            System.out.println(FAIL_MSG+SPACE+NO_ROOM_MSG_1+SPACE+n+SPACE+NO_ROOM_MSG_2+
                    SPACE+itemName);
            return FAIL;
        }
        System.out.println(GENERAL_ERROR_MSG);
        return FAIL;
    }

    /**
     * clears the inventory.
     */
    public void resetInventory(){
        inventory.clear();
        availableCapacity = CAPACITY;
        }

    /**
     * gets the number of coppies of an item that are in the inventory.
     * @param type the type of item
     * @return the number of coppies of the item or fail code if the item is not in the inventory
     */
    public int getItemCount(String type){
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
    public int getCapacity(){ return CAPACITY; }

    /**
     * gets the available capacity.
     * @return the available capacity
     */
    public int getAvailableCapacity(){
        return availableCapacity;
    }
}


