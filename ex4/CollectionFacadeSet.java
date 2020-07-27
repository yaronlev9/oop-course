
/**
 * The API CollectionFacadeSet.
 *  a class for the CollectionFacadeSet that implements the SimpleSet.
 * @author oop
 */
public class CollectionFacadeSet implements SimpleSet {

    /** the collection */
    protected java.util.Collection<java.lang.String> collection;

    /**
     * Constructs a new CollectionFacadeSet object with the given collection.
     *
     * @param collection the upper load factor
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection){
        this.collection = collection;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    public boolean add(java.lang.String newValue){
        if (collection.contains(newValue)) {
            return false;
        }
        collection.add(newValue);
        return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(java.lang.String searchVal){
        return collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete){
        if (collection.contains(toDelete)){
            collection.remove(toDelete);
            return true;
        }
        return false;
    }

    /**
     * @return The number of elements currently in the set.
     */
    public int size(){
        return collection.size();
    }
}
