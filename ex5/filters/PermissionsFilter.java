package filters;
import java.io.File;

/**
 * The API PermissionsFilter.
 *  a class for the PermissionsFilter that implements Filter.
 * @author oop
 */
public class PermissionsFilter implements Filter {

    /**the writable filter name*/
    private final String WRITABLE = "writable";

    /**the executable filter name*/
    private final String EXECUTABLE = "executable";

    /**the hidden filter name*/
    private final String HIDDEN = "hidden";

    /**a boolean instance of activating reverse filter or not */
    private boolean noActivated;

    /**the string of the filter name*/
    private String filter;

    /**
     * Constructs a new PermissionsFilter object.
     *
     * @param filter the string of the filter name
     * @param noActivated the boolean instance for activating reverse filter
     */
    public PermissionsFilter(String filter, boolean noActivated){
        this.filter = filter;
        this.noActivated = noActivated;
    }

    /**
     * Checks if the file given passes the filter.
     * @param file the file
     * @return true if the file passes the filter false if not.
     */
    @Override
    public boolean isPass(File file){
        switch (filter) {
            case WRITABLE:
                if (!noActivated) {
                    return file.canWrite();
                } else {
                    return !file.canWrite();
                }
            case EXECUTABLE:
                if (!noActivated) {
                    return file.canExecute();
                } else {
                    return !file.canExecute();
                }
            case HIDDEN:
                if (!noActivated) {
                    return file.isHidden();
                } else {
                    return !file.isHidden();
                }
        }
        return false;
    }
}
