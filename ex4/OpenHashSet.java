
/**
 * The API OpenHashSet.
 *  a class for the OpenHashSet that extends the SimpleHashSet.
 * @author oop
 */
public class OpenHashSet extends SimpleHashSet{

    /** the change factor of the capacity of the ClosedHashSet */
    private final int SIZE_CHANGE_FACTOR = 2;

    /** the OpenHashSet */
    private OpenLinkedList[] openHashSet;

    /**
     * Constructs a new OpenHashSet object with the given load factors.
     *
     * @param upperLoadFactor the upper load factor
     * @param lowerLoadFactor the lower load factor
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor,lowerLoadFactor);
        openHashSet = new OpenLinkedList[capacity];
       makeLinkedLists(openHashSet);
    }

    /**
     * Constructs a new OpenHashSet object.
     *
     */
    public OpenHashSet(){
        super();
        openHashSet = new OpenLinkedList[capacity];
        makeLinkedLists(openHashSet);
    }

    /**
     * Constructs a new OpenHashSet object initialized with the given data.
     *
     * @param data the data to add to the OpenHashSet
     */
    public OpenHashSet(java.lang.String[] data) {
       super();
        openHashSet = new OpenLinkedList[capacity];
        makeLinkedLists(openHashSet);
        for(String text: data){
            this.add(text);
        }

    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    public boolean add(java.lang.String newValue){
        if (this.contains(newValue)){
            return false;
        } else if((((double)size()+1)/(double)capacity())>getUpperLoadFactor()) {
            makeArrayBigger();
        }
        int index = clamp(newValue.hashCode());
        openHashSet[index].getLinkedList().add(newValue);
        size++;
        return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(java.lang.String searchVal){
        int index = clamp(searchVal.hashCode());
        return openHashSet[index].getLinkedList().contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete) {
        if (!this.contains(toDelete)) {
            return false;
        } else {
            int index = clamp(toDelete.hashCode());
            openHashSet[index].getLinkedList().remove(toDelete);
            size--;
            if (((double)size() / (double)capacity()) < getLowerLoadFactor() && capacity()>1) {
                makeArraySmaller();
            }
            return true;
        }
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size(){
        return size;
    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity(){
        return capacity;
    }

    /**
     * Doubles the capacity and calls a function that makes a new OpenHashSet with the new capacity.
     */
    private void makeArrayBigger(){
        capacity *=SIZE_CHANGE_FACTOR;
        makeNewHashSet();
    }

    /**
     * Reduces the capacity by half and calls a function that makes a new OpenHashSet with the new capacity.
     */
    private void makeArraySmaller(){
        capacity /=SIZE_CHANGE_FACTOR;
        makeNewHashSet();
    }

    /**
     * creates new linked lists in every index on the OpenHashSet.
     * @param openHashSet the OpenHashSet
     */
    private void makeLinkedLists(OpenLinkedList[] openHashSet){
        for (int i=0; i<capacity; i++){
            openHashSet[i] = new OpenLinkedList();
        }
    }

    /**
     * Makes a new OpenHashSet with the changed capacity and moves all the values from the old set to the new.
     */
    private void makeNewHashSet(){
        OpenLinkedList[] newOpenHashSet = new OpenLinkedList[capacity];
        makeLinkedLists(newOpenHashSet);
        for (OpenLinkedList lst:openHashSet){
            for (String text: lst.getLinkedList()){
                int index = clamp(text.hashCode());
                newOpenHashSet[index].getLinkedList().add(text);
            }
        }
        openHashSet = newOpenHashSet;
    }
}

