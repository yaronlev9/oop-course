package filters;
import java.io.File;

/**
 * The API SuffixFilter.
 *  a class for the SuffixFilter that implements Filter.
 * @author oop
 */
public class SuffixFilter implements Filter {

    /**the string that the file name ends with*/
    private String value;

    /**a boolean instance of activating reverse filter or not */
    private boolean noActivated;

    /**
     * Constructs a new SuffixFilter object.
     *
     * @param value the string that the file name ends with
     * @param noActivated the boolean instance for activating reverse filter
     */
    public SuffixFilter(String value, boolean noActivated){
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
            return file.getName().endsWith(value);
        } else {
            return !file.getName().endsWith(value);
        }
    }
}
