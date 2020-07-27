/**
 * The API SimpleHashSet.
 *  an abstract class for the SimpleHashSet that implements the SimpleSet..
 * @author oop
 */
public abstract class SimpleHashSet implements SimpleSet{

    /** the default upper load factor */
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;

    /** the default lower load factor */
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;

    /** the initial capacity of the SimpleHashSet */
    protected static final int INITIAL_CAPACITY = 16;

    /** the upper load factor */
    protected float upperLoadFactor;

    /** the lower load factor */
    protected float lowerLoadFactor;

    /** the capacity of the SimpleHashSet */
    protected int capacity;

    /** the size of the SimpleHashSet*/
    protected int size;

    /**
     * Constructs a new SimpleHashSet object.
     *
     */
    protected SimpleHashSet(){
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
    }

    /**
     * Constructs a new SimpleHashSet object with the given load factors.
     *
     * @param upperLoadFactor the upper load factor
     * @param lowerLoadFactor the lower load factor
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    public abstract int capacity();

    /**
     * Clamps the integer given to an index between 0 and the length of the SimpleHashSet.
     * @param index Value to delete
     * @return the index between 0 to the length of the SimpleHashSet
     */
    protected int clamp(int index){
        return index &(capacity() -1);
    }

    /**
     * @return The lower load factor of the SimpleHashSet
     */
    protected float getLowerLoadFactor(){
        return lowerLoadFactor;
    }

    /**
     * @return The upper load factor of the SimpleHashSet
     */
    protected float getUpperLoadFactor(){
        return upperLoadFactor;
    }
}
