package filters;
import java.io.File;

/**
 * The API GreaterThanFilter.
 *  a class for the GreaterThanFilter that implements Filter.
 * @author oop
 */
public class GreaterThanFilter implements Filter {

    /**the number */
    private double number;

    /**a boolean instance of activating reverse filter or not */
    private boolean noActivated;

    /**
     * Constructs a new GreaterThanFilter object.
     *
     * @param number the number
     * @param noActivated the boolean instance for activating reverse filter
     */
    public GreaterThanFilter(double number,boolean noActivated){
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
            return file.length() > number * Filter.CONVERT_KILO_BYTES;
        } else {
            return file.length() >= 0 && file.length() <= number * Filter.CONVERT_KILO_BYTES;
        }
    }

}
