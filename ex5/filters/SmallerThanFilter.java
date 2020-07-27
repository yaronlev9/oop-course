package filters;
import java.io.File;

/**
 * The API SmallerThanFilter.
 *  a class for the SmallerThanFilter that implements Filter.
 * @author oop
 */
public class SmallerThanFilter implements Filter {

    /**the number */
    private double number;

    /**a boolean instance of activating reverse filter or not */
    private boolean noActivated;

    /**
     * Constructs a new SmallerThanFilter object.
     *
     * @param number the number
     * @param noActivated the boolean instance for activating reverse filter
     */
    public SmallerThanFilter(double number,boolean noActivated){
        this.number = number;
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
            return file.length() >= 0 && file.length() < number * Filter.CONVERT_KILO_BYTES;
        } else {
            return file.length() >= number * Filter.CONVERT_KILO_BYTES;
        }
    }
}
