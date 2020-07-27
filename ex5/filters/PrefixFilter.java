package filters;
import java.io.File;

/**
 * The API PrefixFilter.
 *  a class for the PrefixFilter that implements Filter.
 * @author oop
 */
public class PrefixFilter implements Filter{

    /**the string that the file name starts with*/
    private String value;

    /**a boolean instance of activating reverse filter or not */
    private boolean noActivated;

    /**
     * Constructs a new PrefixFilter object.
     *
     * @param value the string that the file name starts with
     * @param noActivated the boolean instance for activating reverse filter
     */
    public PrefixFilter(String value, boolean noActivated){

        this.value = value;
        this.noActivated = noActivated;
    }

    /**
     * Checks if the file given passes the filter.
     * @param file the file
     * @return true if the file passes the filter false if not.
     */
    @Override
    public boolean isPass(File file){
        if (!noActivated) {
            return file.getName().startsWith(value);
        } else {
            return !file.getName().startsWith(value);
        }
    }
}
