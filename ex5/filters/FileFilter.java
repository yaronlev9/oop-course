package filters;
import java.io.File;

/**
 * The API FileFilter.
 *  a class for the FileFilter that implements Filter.
 * @author oop
 */
public class FileFilter implements Filter {

    /**the string of the file name*/
    private String value;

    /**a boolean instance of activating reverse filter or not */
    private boolean noActivated;

    /**
     * Constructs a new FileFilter object.
     *
     * @param value the string of the file name
     * @param noActivated the boolean instance for activating reverse filter
     */
    public FileFilter(String value ,boolean noActivated){
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
            return file.getName().equals(value);
        } else {
            return !file.getName().equals(value);
        }
    }

}

