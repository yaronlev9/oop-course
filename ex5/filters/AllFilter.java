package filters;
import java.io.File;

/**
 * The API AllFilter.
 *  a class for the AllFilter that implements Filter.
 * @author oop
 */
public class AllFilter implements Filter{

    /**a boolean instance of activating reverse filter or not */
    private boolean noActivated;

    /**
     * Constructs a new AllFilter object.
     *
     * @param noActivated the boolean instance for activating reverse filter
     */
    public AllFilter(boolean noActivated){
        this.noActivated = noActivated;
    }

    /**
     * Checks if the file given passes the filter.
     * @param file the file
     * @return true if the file passes the filter false if not.
     */
    @Override
    public boolean isPass(File file){
        return !noActivated;
    }
}
