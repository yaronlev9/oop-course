package filters;
import java.io.File;

/**
 * The API BetweenFilter.
 *  a class for the BetweenFilter that implements Filter.
 * @author oop
 */
public class BetweenFilter implements Filter {

    /**the smaller number */
    private double smallNumber;

    /**the bigger number */
    private double bigNumber;

    /**a boolean instance of activating reverse filter or not */
    private boolean noActivated;

    /**
     * Constructs a new BetweenFilter object.
     *
     * @param smallNumber the smaller number
     * @param bigNumber the bigger number
     * @param noActivated the boolean instance for activating reverse filter
     */
    public BetweenFilter(double smallNumber,double bigNumber,boolean noActivated){
        this.smallNumber = smallNumber;
        this.bigNumber = bigNumber;
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
            return file.length() >= smallNumber * Filter.CONVERT_KILO_BYTES &&
                    file.length()<= bigNumber* Filter.CONVERT_KILO_BYTES;
        } else {
            return file.length() >= 0 && (file.length() < smallNumber * Filter.CONVERT_KILO_BYTES ||
                    file.length()> bigNumber* Filter.CONVERT_KILO_BYTES);
        }
    }

}
