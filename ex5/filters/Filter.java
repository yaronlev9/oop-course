package filters;
import java.io.File;

public interface Filter {

    /**the converting number from kilobytes to bytes */
    double CONVERT_KILO_BYTES = 1024;

    /**
     * Checks if the file given passes the filter.
     * @param f the file
     * @return true if the file passes the filter false if not.
     */
    boolean isPass(File f);
}
