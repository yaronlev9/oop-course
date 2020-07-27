/**
 * The API ClosedHashSet.
 *  a class for the ClosedHashSet that extends the SimpleHashSet..
 * @author oop
 */
public class ClosedHashSet extends SimpleHashSet {

    /** a string that represents all the deleted places on the ClosedHashSet */
    private final String DELETED = new String("deleted");

    /** the change factor of the capacity of the ClosedHashSet */
    private final int SIZE_CHANGE_FACTOR = 2;

    /** the minimum capacity of the ClosedHashSet */
    private final int MINIMUM_CAPACITY = 1;

    /** an error code that is return when string is not found */
    private final int ERROR_CODE = -1;

    /** the ClosedHashSet */
    private String[] closedHashSet;

    /**
     * Constructs a new ClosedHashSet object with the given load factors.
     *
     * @param upperLoadFactor the upper load factor
     * @param lowerLoadFactor the lower load factor
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {

        super(upperLoadFactor, lowerLoadFactor);
        closedHashSet = new String[capacity];
    }

    /**
     * Constructs a new ClosedHashSet object.
     *
     */
    public ClosedHashSet() {
        super();
        closedHashSet = new String[capacity];
    }

    /**
     * Constructs a new ClosedHashSet object initialized with the given data.
     *
     * @param data the data to add to the ClosedHashSet
     */
    public ClosedHashSet(java.lang.String[] data) {
        super();
        closedHashSet = new String[capacity];
        for (String text : data) {
            this.add(text);
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    public boolean add(java.lang.String newValue) {
        if (this.getItemsIndex(newValue)!=ERROR_CODE) {
            return false;
        } else if (((double)(size() + 1) / (double)capacity()) > getUpperLoadFactor()) {
            doubleArraySize();
        }
        insertEmptyIndex(newValue);
        size++;
        return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(java.lang.String searchVal) {
        return getItemsIndex(searchVal) != ERROR_CODE;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete) {
        int index = getItemsIndex(toDelete);
        if (index == ERROR_CODE) {
            return false;
        } else {
            closedHashSet[index] = DELETED;
            size--;
            if (((double)size() / (double)capacity()) < getLowerLoadFactor() && capacity()>MINIMUM_CAPACITY) {
                halfArraySize();
            }
            return true;
        }
    }

    /**
     * @return The number of elements currently in the set.
     */
    public int size() {
        return size;
    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Doubles the capacity and calls a function that makes a new ClosedHashSet with the new capacity.
     */
    private void doubleArraySize() {
        capacity *= SIZE_CHANGE_FACTOR;
        newClosedHashSet();
    }

    /**
     * Reduces the capacity by half and calls a function that makes a new ClosedHashSet with the new capacity.
     */
    private void halfArraySize() {
        capacity /= SIZE_CHANGE_FACTOR;
        newClosedHashSet();
    }

    /**
     * Makes a new ClosedHashSet with the changed capacity and moves all the values from the old set to the new.
     */
    private void newClosedHashSet() {
        String[] newClosedHashSet = new String[capacity];
        for (String text:closedHashSet) {
           if (text != null && text!=DELETED){
               int hash = text.hashCode();
               for (int i=0; i<capacity();i++){
                   int index = clamp(hash + (i + (i * i)) / SIZE_CHANGE_FACTOR);
                   if (newClosedHashSet[index]==null) {
                       newClosedHashSet[index] = text;
                       break;
                   }
               }
           }
        }
        closedHashSet = newClosedHashSet;
    }

    /**
     * Finds an empty index using the hash and quadratic functions and inserts the text given.
     * @param text the string that needs to be added
     */
    private void insertEmptyIndex(String text) {
        int hash = text.hashCode();
        for (int i=0; i<capacity();i++){
            int index = clamp(hash + (i + (i * i)) / SIZE_CHANGE_FACTOR);
            if (closedHashSet[index]==null || closedHashSet[index]==DELETED) {
                closedHashSet[index] = text;
                break;
            }
        }
    }

    /**
     * Checks if the item is in the ClosedHashSet if not returns ERROR CODE if yes returns the index.
     * @param searchVal the string that is checked
     * @return ERROR CODE if not contained and the index if contained
     */
    private int getItemsIndex(String searchVal){
        int hash = searchVal.hashCode();
        for (int i = 0; i < capacity; i++) {
            int index = clamp(hash + (i + (i * i)) / SIZE_CHANGE_FACTOR);
            if (closedHashSet[index] == null) {
                return ERROR_CODE;
            } else if (closedHashSet[index].equals(searchVal)&&
                    closedHashSet[index]!=DELETED){
                return index;
            }
        }
        return ERROR_CODE;
    }
}