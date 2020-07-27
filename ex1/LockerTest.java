import oop.ex3.spaceship.*;
import org.junit.*;
import static org.junit.Assert.*;

public class LockerTest {
    /** the general error message */
    private final String FAIL_TEST_MSG = "test failed";

    /** the array of the items*/
    private static Item[] items;

    /** the object of locker 1*/
    private static Locker locker1;

    /** the object of locker 2*/
    private static Locker locker2;

    /** the object of locker 3*/
    private static Locker locker3;

    /** locker3 capacity*/
    private static final int LOCKER3_CAPACITY = 700;

    /** locker1 capacity*/
    private static final int LOCKER1_CAPACITY = 100;

    /** the object an item with a type null*/
    private static Item nullItem = null;

    /** the long term storage*/
    private static LongTermStorage longTermStorage = Locker.longTermStorage;

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
        baseballBat = items[0];
        helmetSize1 = items[1];
        helmetSize3 = items[2];
        sporesEngine = items[3];
        football = items[4];
        locker1 = new Locker(LOCKER1_CAPACITY);
        locker2 = new Locker(0);
        locker3 = new Locker(LOCKER3_CAPACITY);
    }

    /** empties the lockers after every test*/
    @After
    public void emptyLockers() {
        emptyLocker1();
        emptyLocker3();
        longTermStorage.resetInventory();
    }

    /** empties locker1*/
    private void emptyLocker1() {
        if (locker1.getInventory().isEmpty()){
            return;
        }
        for (Item item:items){
            if (locker1.getInventory().containsKey(item.getType())){
                locker1.removeItem(item,locker1.getInventory().get(item.getType()));
            }
        }
    }

    /** empties locker3*/
    private void emptyLocker3() {
        if (locker3.getInventory().isEmpty()){
            return;
        }
        for (Item item:items){
            if (locker3.getInventory().containsKey(item.getType())){
                locker3.removeItem(item,locker3.getInventory().get(item.getType()));
            }
        }
    }

    /** tests the reset get item count method*/
    @Test
    public void testGetItemCount(){
        /** calling an item that is not in inventory*/
        assertEquals(FAIL_TEST_MSG,0,locker1.getItemCount(baseballBat.getType()));
        locker1.addItem(baseballBat,3);
        locker1.addItem(helmetSize1,5);
        /** calling an item that is in inventory*/
        assertEquals(FAIL_TEST_MSG,3,locker1.getItemCount(baseballBat.getType()));
        /** calling a second item that is in inventory*/
        assertEquals(FAIL_TEST_MSG,5,locker1.getItemCount(helmetSize1.getType()));
        locker1.removeItem(baseballBat,2);
        /** checks that some copies of the item were removed*/
        assertEquals(FAIL_TEST_MSG,1,locker1.getItemCount(baseballBat.getType()));
        locker1.removeItem(helmetSize1,5);
        /** checks that the item was removed from inventory*/
        assertEquals(FAIL_TEST_MSG,0,locker1.getItemCount(helmetSize1.getType()));
        locker1.removeItem(baseballBat,1);
        /** checks that the item was removed from inventory*/
        assertEquals(FAIL_TEST_MSG,0,locker1.getItemCount(baseballBat.getType()));
    }

    /** tests the get inventory method*/
    @Test
    public void testGetInventory() {
        /** calling an empty inventory*/
        assertEquals(FAIL_TEST_MSG, "{}",locker1.getInventory().toString());
        locker1.addItem(baseballBat,3);
        locker1.addItem(helmetSize1,5);
        /** calling an inventory after adding items*/
        assertEquals(FAIL_TEST_MSG, "{helmet, size 1=5, baseball bat=3}",locker1.getInventory().toString());
        locker1.removeItem(baseballBat,2);
        /** calling the inventory after removing some copies of the item*/
        assertEquals(FAIL_TEST_MSG, "{helmet, size 1=5, baseball bat=1}",locker1.getInventory().toString());
        locker1.removeItem(helmetSize1,5);
        /** calling the inventory after removing an item completely*/
        assertEquals(FAIL_TEST_MSG, "{baseball bat=1}",locker1.getInventory().toString());
        locker1.removeItem(baseballBat,1);
        /** calling the inventory after removing the second item completely*/
        assertEquals(FAIL_TEST_MSG, "{}",locker1.getInventory().toString());
    }

    /** tests the get capacity method for locker1*/
    @Test
    public void test1GetCapacity(){
        /** calling the capacity*/
        assertEquals(FAIL_TEST_MSG, 100,locker1.getCapacity());
        locker1.addItem(baseballBat,3);
        /** calling the capacity after adding an item*/
        assertEquals(FAIL_TEST_MSG, 100,locker1.getCapacity());
        locker1.removeItem(baseballBat,2);
        /** calling the capacity after removing some copies of the item*/
        assertEquals(FAIL_TEST_MSG, 100,locker1.getCapacity());
        locker1.removeItem(baseballBat,1);
        /** calling the capacity after removing the item completely*/
        assertEquals(FAIL_TEST_MSG, 100,locker1.getCapacity());
    }

    /** tests the get capacity method for locker2*/
    @Test
    public void test2GetCapacity(){
        System.out.println("locker test2 getCapacity locker2");
        /** calling the capacity*/
        assertEquals(FAIL_TEST_MSG, 0,locker2.getCapacity());
        locker2.addItem(helmetSize1,3);
        /** calling the capacity after adding an item*/
        assertEquals(FAIL_TEST_MSG, 0,locker2.getCapacity());
        locker2.removeItem(helmetSize1,2);
        /** calling the capacity after removing some copies of the item*/
        assertEquals(FAIL_TEST_MSG, 0,locker2.getCapacity());
        locker2.removeItem(helmetSize1,1);
        /** calling the capacity after removing the item completely*/
        assertEquals(FAIL_TEST_MSG, 0,locker2.getCapacity());
        System.out.println();
    }

    /** tests the get available capacity method for locker2*/
    @Test
    public void test1GetAvailableCapacity(){
        System.out.println("locker test1 getAvailableCapacity locker2");
        /** calling the available capacity*/
        assertEquals(FAIL_TEST_MSG, 0,locker2.getAvailableCapacity());
        locker2.addItem(helmetSize1,3);
        /** calling the available capacity after adding 3 copies of an item*/
        assertEquals(FAIL_TEST_MSG, 0,locker2.getAvailableCapacity());
        locker2.removeItem(helmetSize1,2);
        /** calling the available capacity after removing 2 copies of an item*/
        assertEquals(FAIL_TEST_MSG, 0,locker2.getAvailableCapacity());
        locker2.removeItem(helmetSize1,1);
        /** calling the available capacity after removing 1 copies of an item*/
        assertEquals(FAIL_TEST_MSG, 0,locker2.getAvailableCapacity());
        System.out.println();
    }

    /** tests the get available capacity method for locker1*/
    @Test
    public void test2GetAvailableCapacity(){
        System.out.println("locker test2 getAvailableCapacity locker1");
        /** calling the available capacity*/
        assertEquals(FAIL_TEST_MSG, 100,locker1.getAvailableCapacity());
        locker1.addItem(sporesEngine,5);
        /** calling the available capacity after adding 5 copies of an item*/
        assertEquals(FAIL_TEST_MSG, 50,locker1.getAvailableCapacity());
        locker1.addItem(sporesEngine,5);
        /** calling the available capacity after adding 5 more copies of the same item causing only 2 items
         * to enter the locker and 3 to enter long term storage*/
        assertEquals(FAIL_TEST_MSG, 80,locker1.getAvailableCapacity());
        /** checking that 3 items of spores engine entered the long term storage*/
        assertEquals(FAIL_TEST_MSG, "{spores engine=8}",longTermStorage.getInventory().toString());
        locker1.addItem(sporesEngine,1);
        /** calling the available capacity after adding 1 more copy of the same item*/
        assertEquals(FAIL_TEST_MSG, 70,locker1.getAvailableCapacity());
        locker1.removeItem(sporesEngine,2);
        /** calling the available capacity after removing 8 copies of the item*/
        assertEquals(FAIL_TEST_MSG, 90,locker1.getAvailableCapacity());
        System.out.println();
    }

    @Test
    /** test for wrong inputs*/
    public void test1AddItem(){
        System.out.println("locker test1 addItem");
        /** adding an item with negative number*/
        assertEquals(FAIL_TEST_MSG, -1,locker1.addItem(football,-3));
        /** adding an item with type null*/
        assertEquals(FAIL_TEST_MSG, -1,locker1.addItem(nullItem,3));
        /** adding an item with number 0*/
        assertEquals(FAIL_TEST_MSG, -1,locker1.addItem(football,0));
        System.out.println();

    }

    /** test for different outputs*/
    @Test
    public void test2AddItem(){
        System.out.println("locker test2 addItem");
        /** adding the baseball bat*/
        assertEquals(FAIL_TEST_MSG, 0,locker1.addItem(baseballBat,1));
        /** adding the football*/
        assertEquals(FAIL_TEST_MSG, -2,locker1.addItem(football,1));
        locker1.removeItem(baseballBat,1);
        /** adding the football after the baseball bat is removed*/
        assertEquals(FAIL_TEST_MSG, 0,locker1.addItem(football,1));
        /** adding the baseball bat*/
        assertEquals(FAIL_TEST_MSG, -2,locker1.addItem(baseballBat,1));
        /** adding 25 copies of football (100) resulting in adding an amount larger that the available capacity*/
        assertEquals(FAIL_TEST_MSG, -1,locker1.addItem(football,25));
        /** adding 20 copies of helmet, size 3 (100) resulting in adding an amount larger that the available capacity*/
        assertEquals(FAIL_TEST_MSG, -1,locker1.addItem(helmetSize3,20));
        System.out.println();
    }

    /** test for different outputs*/
    @Test
    public void test3AddItem(){
        System.out.println("locker test3 addItem");
        /** adding 70 copies of the spores engine (700) to locker 3 causing 14 units (140) to enter locker3
         * and 56 (560) to enter the long term storage*/
        assertEquals(FAIL_TEST_MSG, 1,locker3.addItem(sporesEngine,70));
        /** checking that 56 items of spores engine entered the long term storage*/
        assertEquals(FAIL_TEST_MSG, "{spores engine=56}",longTermStorage.getInventory().toString());
        /** adding 56 copies of the spores engine (560) to locker 3 causing 14 units (140) to enter locker3
         * and 56 (560) to enter the long term storage but long term storage cant receive 56 units*/
        assertEquals(FAIL_TEST_MSG, -1,locker3.addItem(sporesEngine,56));
        /** adding 11 copies of helmet, size 3 (55) to locker 1 causing 2 units (10) to enter locker1
         * and 9 (45) to enter the long term storage*/
        assertEquals(FAIL_TEST_MSG, 1,locker1.addItem(helmetSize3,11));
        /** checking that 7 items of of helmet, size 3 entered the long term storage*/
        assertEquals(FAIL_TEST_MSG, "{helmet, size 3=7, spores engine=56}",
                longTermStorage.getInventory().toString());
        /** checking that 4 items of of helmet, size 3 entered the locker1*/
        assertEquals(FAIL_TEST_MSG, "{helmet, size 3=4}", locker1.getInventory().toString());
        /** adding 16 copies of helmet, size 3 (80) to locker 1 causing 4 units (20) to enter locker1
         * and 12 (60) to enter the long term storage*/
        assertEquals(FAIL_TEST_MSG, 1,locker1.addItem(helmetSize3,16));
        /** checking that 16+7=13 items of of helmet, size 3 entered the long term storage*/
        assertEquals(FAIL_TEST_MSG, "{helmet, size 3=23, spores engine=56}",
                longTermStorage.getInventory().toString());
        /** checking that 4 items of of helmet, size 3 entered the locker1*/
        assertEquals(FAIL_TEST_MSG, "{helmet, size 3=4}", locker1.getInventory().toString());
        System.out.println();
    }

    /** test for different outputs*/
    @Test
    public void test4AddItem(){
        System.out.println("locker test4 addItem");
        /** adding 10 copies of helmet size 3*/
        assertEquals(FAIL_TEST_MSG, 0,locker1.addItem(helmetSize3,10));
        /** adding 5 copies of baseball bat*/
        assertEquals(FAIL_TEST_MSG, 0,locker1.addItem(baseballBat,5));
        /** checking that items are in inventory*/
        assertEquals(FAIL_TEST_MSG, "{baseball bat=5, helmet, size 3=10}",locker1.getInventory().toString());
        /** checking the available capacity*/
        assertEquals(FAIL_TEST_MSG, 40,locker1.getAvailableCapacity());
        /** adding 1 copies of helmet size 3 causing 4 items to be in locker and 7 to move to long term storage*/
        assertEquals(FAIL_TEST_MSG, 1,locker1.addItem(helmetSize3,1));
        /** checking that items are in inventory in locker*/
        assertEquals(FAIL_TEST_MSG, "{baseball bat=5, helmet, size 3=4}",locker1.getInventory().toString());
        /** checking the available capacity*/
        assertEquals(FAIL_TEST_MSG, 70,locker1.getAvailableCapacity());
        /** checking that items are in inventory in long term storage*/
        assertEquals(FAIL_TEST_MSG, "{helmet, size 3=7}",
                longTermStorage.getInventory().toString());
        /** checking the available capacity*/
        assertEquals(FAIL_TEST_MSG, 965,longTermStorage.getAvailableCapacity());



        System.out.println();
    }

    @Test
    /** test for wrong inputs*/
    public void test1RemoveItem(){
        System.out.println("locker test1 removeItem");
        /** removing an item with type null*/
        assertEquals(FAIL_TEST_MSG, -1,locker1.removeItem(nullItem,3));
        /** removing an item with number 0*/
        assertEquals(FAIL_TEST_MSG, -1,locker1.removeItem(football,0));
        /** removing an item with negative number*/
        assertEquals(FAIL_TEST_MSG, -1,locker1.removeItem(football,-3));
        System.out.println();
    }

    /** test for wrong inputs*/
    @Test
    public void test2RemoveItem(){
        System.out.println("locker test2 removeItem");
        locker1.addItem(baseballBat,3);
        /** removing 1 copy of the item*/
        assertEquals(FAIL_TEST_MSG, 0,locker1.removeItem(baseballBat,1));
        /** checking that 1 was removed and 2 are left*/
        assertEquals(FAIL_TEST_MSG, 2,locker1.getItemCount(baseballBat.getType()));
        /** removing more copies than the number that is in the inventory*/
        assertEquals(FAIL_TEST_MSG, -1,locker1.removeItem(baseballBat,3));
        /** removing 2 copy of the item*/
        assertEquals(FAIL_TEST_MSG, 0,locker1.removeItem(baseballBat,2));
        /** checking that the item was removed completely from inventory*/
        assertEquals(FAIL_TEST_MSG, "{}",locker1.getInventory().toString());
        locker1.addItem(baseballBat,5);
        locker1.addItem(helmetSize1,3);
        /** removing 2 copies of baseball bat*/
        assertEquals(FAIL_TEST_MSG, 0,locker1.removeItem(baseballBat,2));
        /** removing 2 copies of helmet, size 1*/
        assertEquals(FAIL_TEST_MSG, 0,locker1.removeItem(helmetSize1,2));
        /** checking that 2 copies of  baseball bat and 2 copies of helmet, size 1 were removed */
        assertEquals(FAIL_TEST_MSG, "{helmet, size 1=1, baseball bat=3}",locker1.getInventory().toString());
        System.out.println();
    }
}