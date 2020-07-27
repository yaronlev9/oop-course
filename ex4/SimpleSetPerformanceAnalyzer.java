import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer {

    /** contained in print message */
    private static final String CONTAINED = " contained in ";

    /** the string hi */
    private static final String STRING1 = "hi";

    /** the string -13170890158 */
    private static final String STRING2 = "-13170890158";

    /** the string 23 */
    private static final String STRING3 = "23";

    /** the dataset data1.txt */
    private static final String DATA1 = "data1.txt";

    /** the dataset data2.txt */
    private static final String DATA2 = "data2.txt";

    /** an array of the names of all the collections that are in the test */
    private static final String[] COLLECTION_NAMES = {"OpenHashSet:","ClosedHashSet:","TreeSet:",
            "LinkedList:","HashSet:"};

    /** the index of the OpenHashSet in the array */
    private static final int OPEN_HASH_SET = 0;

    /** the index of the closedHashSet in the array */
    private static final int CLOSED_HASH_SET = 1;

    /** the index of the TreeSet in the array */
    private static final int TREE_SET = 2;

    /** the index of the LinkedList in the array */
    private static final int LINKED_LIST = 3;

    /** the index of the HashSet in the array */
    private static final int HASH_SET = 4;

    /** the size of the collection array */
    private static final int ARRAY_SIZE = 5;

    /** the number of iterations made for each collection */
    private static final int ITERATIONS = 70000;

    /** the number of iterations made for the LinkedList */
    private static final int LINKED_LIST_ITERATIONS = 7000;

    /** the change factor from ns to ms */
    private static final int MILLION = 1000000;

    /** the data1 */
    private static String[] data1 = Ex4Utils.file2array(DATA1);

    /** the data2 */
    private static String[] data2 = Ex4Utils.file2array(DATA2);

    /** the collection array */
    private static SimpleSet[] collectionArray = new SimpleSet[ARRAY_SIZE];

    /**
     * empties all the objects needed for the test before every test.
     *
     */
    private static void createTestObjects() {
        collectionArray[OPEN_HASH_SET] = new OpenHashSet();
        collectionArray[CLOSED_HASH_SET] = new ClosedHashSet();
        collectionArray[TREE_SET] = new CollectionFacadeSet(new TreeSet<>());
        collectionArray[LINKED_LIST] = new CollectionFacadeSet(new LinkedList<>());
        collectionArray[HASH_SET] = new CollectionFacadeSet(new HashSet<>());
    }

    /**
     * adding data1.txt to a collection array.
     *
     */
    private static void testAddData1ToAll(){
        System.out.println("results for adding data1:");
        createTestObjects();
        for (int i=0; i<collectionArray.length; i++){
            addData(data1,i);
        }
    }

    /**
     * adding data2.txt to a collection array.
     *
     */
    private static void testAddData2ToAll(){
        System.out.println("results for adding data2:");
        createTestObjects();
        for (int i=0; i<collectionArray.length; i++){
            addData(data2,i);
        }
    }

    /**
     * check if hi is contained in collection array that contains data1.txt.
     *
     */
    private static void testContainsString1Data1() {
        System.out.println("results for contains hi in data1:");
        createTestObjects();
        addDataNoPrint(data1);
        for (int i = 0; i < collectionArray.length; i++) {
            containsString(STRING1, i);
        }
    }

    /**
     * check if -13170890158 is contained in collection array that contains data1.txt.
     *
     */
    private static void testContainsString2Data1() {
        System.out.println("results for contains -13170890158 in data1:");
        createTestObjects();
        addDataNoPrint(data1);
        for (int i = 0; i < collectionArray.length; i++) {
            containsString(STRING2, i);
        }
    }


    /**
     * check if 23 is contained in collection array that contains data2.txt.
     *
     */
    private static void testContainsString3Data2() {
        System.out.println("results for contains 23 in data2:");
        createTestObjects();
        addDataNoPrint(data2);
        for (int i = 0; i < collectionArray.length; i++) {
            containsString(STRING3, i);
        }
    }

    /**
     * check if hi is contained in collection array that contains data2.txt.
     *
     */
    private static void testContainsString1Data2() {
        System.out.println("results for contains hi in data2:");
        createTestObjects();
        addDataNoPrint(data2);
        for (int i = 0; i < collectionArray.length; i++) {
            containsString(STRING1, i);
        }
    }

    /**
     * runs all the contains tests.
     *
     */
    private static void runAllContainsTests(){
        testContainsString1Data1();
        testContainsString2Data1();
        testContainsString3Data2();
        testContainsString1Data2();
    }

    /**
     * For every collection in the array it adds the data, calculates and prints the time it took.
     * @param data the dataset
     * @param index the index of the collection in the collection array
     */
    private static void addData(String[] data, int index){
            long timeBefore = System.nanoTime();
            for (String text : data) {
                collectionArray[index].add(text);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(COLLECTION_NAMES[index]+" "+difference/MILLION);
            System.out.println();
    }

    /**
     * For every collection in the array it adds the data.
     * @param data the dataset
     */
    private static void addDataNoPrint(String[] data){
        for (SimpleSet collection : collectionArray) {
            for (String text : data) {
                collection.add(text);
            }
        }
    }

    /**
     * For every collection in the array it checks if the string is contained calculates and print the time it took.
     * @param text the string that needs to be checked if contains
     * @param index the index of the collection in the collection array
     */
    private static void containsString(String text,int index){
        int firstIterations = ITERATIONS;
        int secondIterations = ITERATIONS;
        if (index == LINKED_LIST){
           firstIterations = 0;
           secondIterations = LINKED_LIST_ITERATIONS;
        }
        for (int j=0; j<firstIterations; j++) {
            collectionArray[index].contains(text);
        }
        long timeBefore = System.nanoTime();
        for (int j=0; j<secondIterations; j++) {
            collectionArray[index].contains(text);
        }
        long difference = System.nanoTime() - timeBefore;
        System.out.println(text+CONTAINED+COLLECTION_NAMES[index]+" "+difference/secondIterations);
        System.out.println();
    }

    /**
     * the main method that runs all the tests in the exercise.
     */
    public static void main(String[] args){
        testAddData1ToAll();
        testAddData2ToAll();
        runAllContainsTests();
    }
}
