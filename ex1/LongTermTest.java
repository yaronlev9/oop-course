import oop.ex3.spaceship.*;
import org.junit.*;
import static org.junit.Assert.*;

public class LongTermTest {
    /** the general error message */
    private final String FAIL_TEST_MSG = "test failed";

    /** the capacity of the long term storage */
    private final int CAPACITY = 1000;

    /** the array of the items*/
    private static Item[] items;

    /** the object an item with a type null*/
    private static Item nullItem = null;

    /** the object of the long term storage of the ship*/
    private static LongTermStorage longTermStorage;

    /** the item of the baseball bat*/
    private static Item baseballBat;

    /** the item of the spores engine*/
    private static Item sporesEngine;

    /** the item of the football*/
    private static Item football;

    /** the item of the helmet size 3*/
    private static Item helmetSize3;

    /** the item of the helmet size 1*/
    private static Item helmetSize1;

    /** creates all the objects needed for the test*/
    @BeforeClass
    public static void createTestObjects() {
        ItemFactory itemFactory = new ItemFactory();
        items = itemFactory.createAllLegalItems();
        longTermStorage = new LongTermStorage();
        baseballBat = items[0];
        helmetSize1 = items[1];
        helmetSize3 = items[2];
        sporesEngine = items[3];
        football = items[4];
    }

    /** empties the storage after every test*/
    @After
    public void emptySorage() {
        longTermStorage.resetInventory();
    }

    /** tests the reset inventory method*/
    @Test
    public void testResetInventory(){
        longTermStorage.resetInventory();
        /** checks if the inventory is empty after calling the method*/
        assertEquals(FAIL_TEST_MSG,"{}",longTermStorage.getInventory().toString());
        /** checks if the available capacity is maximum after calling the method*/
        assertEquals(FAIL_TEST_MSG,CAPACITY,longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(baseballBat,3);
        /** checks if the item is added*/
        assertEquals(FAIL_TEST_MSG,3,longTermStorage.getItemCount(baseballBat.getType()));
        longTermStorage.resetInventory();
        /** checks if the inventory is empty after calling the method*/
        assertEquals(FAIL_TEST_MSG,"{}",longTermStorage.getInventory().toString());
        /** checks if the available capacity is maximum after calling the method*/
        assertEquals(FAIL_TEST_MSG,CAPACITY,longTermStorage.getAvailableCapacity());
    }

    /** tests the reset get item count method*/
    @Test
    public void testGetItemCount(){
        /** calling an item that is not in inventory*/
        assertEquals(FAIL_TEST_MSG,0,longTermStorage.getItemCount(baseballBat.getType()));
        longTermStorage.addItem(baseballBat,3);
        longTermStorage.addItem(helmetSize1,5);
        /** calling an item that is in inventory*/
        assertEquals(FAIL_TEST_MSG,3,longTermStorage.getItemCount(baseballBat.getType()));
        /** calling a second item that is in inventory*/
        assertEquals(FAIL_TEST_MSG,5,longTermStorage.getItemCount(helmetSize1.getType()));
        longTermStorage.resetInventory();
        /** calling an item that was in inventory and then removed*/
        assertEquals(FAIL_TEST_MSG,0,longTermStorage.getItemCount(baseballBat.getType()));
    }

    /** tests the get inventory method*/
    @Test
    public void testGetInventory() {
        /** calling an empty inventory*/
        assertEquals(FAIL_TEST_MSG, "{}",longTermStorage.getInventory().toString());
        longTermStorage.addItem(baseballBat,3);
        longTermStorage.addItem(helmetSize1,5);
        /** calling an inventory after adding items*/
        assertEquals(FAIL_TEST_MSG, "{helmet, size 1=5, baseball bat=3}",
                longTermStorage.getInventory().toString());
        longTermStorage.resetInventory();
        /** calling the inventory after removing all items*/
        assertEquals(FAIL_TEST_MSG, "{}",longTermStorage.getInventory().toString());
    }

    /** tests the get capacity method*/
    @Test
    public void testGetCapacity(){
        /** calling the capacity*/
        assertEquals(FAIL_TEST_MSG, CAPACITY,longTermStorage.getCapacity());
        longTermStorage.addItem(helmetSize1,3);
        /** calling the capacity after adding an item*/
        assertEquals(FAIL_TEST_MSG, CAPACITY,longTermStorage.getCapacity());
        longTermStorage.resetInventory();
        /** calling the capacity after removing the item*/
        assertEquals(FAIL_TEST_MSG, CAPACITY,longTermStorage.getCapacity());
    }

    /** test1 tests the get available capacity method*/
    @Test
    public void test1GetAvailableCapacity(){
        System.out.println("long term storage test1 getAvailableCapacity");
        /** calling the available capacity*/
        assertEquals(FAIL_TEST_MSG, CAPACITY,longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(sporesEngine,5);
        /** calling the available capacity after adding an item*/
        assertEquals(FAIL_TEST_MSG, 950,longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(sporesEngine,95);
        /** calling the available capacity after adding more of this item*/
        assertEquals(FAIL_TEST_MSG, 0,longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(sporesEngine,1);
        /** calling the available capacity after adding more if this item when available capacity is 0*/
        assertEquals(FAIL_TEST_MSG, 0,longTermStorage.getAvailableCapacity());
        System.out.println();
    }

    /** test2 tests the get available capacity method*/
    @Test
    public void test2GetAvailableCapacity(){
        System.out.println("long term storage test2 getAvailableCapacity");
        longTermStorage.resetInventory();
        /** calling the available capacity after removing all items*/
        assertEquals(FAIL_TEST_MSG, CAPACITY,longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(sporesEngine,10);
        /** calling the available capacity after adding an item*/
        assertEquals(FAIL_TEST_MSG, 900,longTermStorage.getAvailableCapacity());
        longTermStorage.addItem(helmetSize1,10);
        /** calling the available capacity after adding a different item*/
        assertEquals(FAIL_TEST_MSG, 870,longTermStorage.getAvailableCapacity());
        System.out.println();
    }

    @Test
    /** test for wrong inputs*/
    public void test1AddItem(){
        System.out.println("long term storage test1 addItem");
        /** adding an item with negative number*/
        assertEquals(FAIL_TEST_MSG, -1,longTermStorage.addItem(football,-3));
        /** adding an item with type null*/
        assertEquals(FAIL_TEST_MSG, -1,longTermStorage.addItem(nullItem,3));
        /** adding an item with number 0*/
        assertEquals(FAIL_TEST_MSG, -1,longTermStorage.addItem(football,0));
        System.out.println();
    }

    /** test for different outputs*/
    @Test
    public void test2AddItem(){
        System.out.println("long term storage test2 addItem");
        /** adding an item*/
        assertEquals(FAIL_TEST_MSG, 0,longTermStorage.addItem(sporesEngine,50));
        /** adding the same item filling storage*/
        assertEquals(FAIL_TEST_MSG, 0,longTermStorage.addItem(sporesEngine,50));
        /** adding an item when storage is full*/
        assertEquals(FAIL_TEST_MSG, -1,longTermStorage.addItem(sporesEngine,1));
        System.out.println();
    }

    /** test for different outputs*/
    @Test
    public void test3AddItem(){
        System.out.println("long term storage test3 addItem");
        /** adding an item*/
        assertEquals(FAIL_TEST_MSG, 0,longTermStorage.addItem(baseballBat,150));
        /** adding a different item*/
        assertEquals(FAIL_TEST_MSG, 0,longTermStorage.addItem(helmetSize1,200));
        /** adding another item when storage is full*/
        assertEquals(FAIL_TEST_MSG, -1,longTermStorage.addItem(helmetSize3,30));
        System.out.println();
    }
}


