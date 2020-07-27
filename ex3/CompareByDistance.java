import oop.ex3.searchengine.*;
import java.util.Comparator;

/**
 * The API compare by distance from a given position.
 *  a class for the compareByDistance that implements the comparator interface and sorts by distance from a
 *  given position.
 * @author oop
 */
public class CompareByDistance implements Comparator <Hotel> {

    /** the maximal latitude */
    private final double MAX_LATITUDE = 90;

    /** the maximal longitude */
    private final double MAX_LONGITUDE = 180;

    /** the latitude of the position */
    private double latitude;

    /** the longitude of the position */
    private double longitude;

    /**
     * Constructs a new compareByDistance object.
     *
     * @param latitude the latitude of the position.
     * @param longitude the longitude of the position.
     */
    public CompareByDistance(double latitude,double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    /**
     * overrides the compare method and compares two hotels by their distance from a given position.
     * @param hotel1 the first hotel object
     * @param hotel2 the second hotel object
     * @return 1 if objects need to be switched, -1 if not or 0 if they are the same by distance
     */
    @Override
    public int compare(Hotel hotel1, Hotel hotel2) {
        double distance1 = distanceFromPoint(hotel1, latitude, longitude);
        double distance2 = distanceFromPoint(hotel2, latitude, longitude);
        if (distance1 > distance2) {
            return 1;
        } if (distance1 < distance2) {
            return -1;
        } else {
            return compareByNumberOfPOI(hotel1, hotel2);
        }
    }

    /**
     * compares between to hotels number of POIs.
     * @param hotel1 the first hotel object
     * @param hotel2 the second hotel object
     * @return 1 if objects need to be switched, -1 if not or 0 if they have the same number of POIs
     */
    private int compareByNumberOfPOI(Hotel hotel1, Hotel hotel2) {
        if (hotel1.getNumPOI() < hotel2.getNumPOI()) {
            return 1;
        }
        if (hotel1.getNumPOI() > hotel2.getNumPOI()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * calculates the distance of the hotel from a given position.
     * @param hotel the hotel
     * @param latitude the latitude of the position.
     * @param longitude the longitude of the position.
     * @return the distance
     */
    private double distanceFromPoint(Hotel hotel,double latitude, double longitude){
        double diffLatitude = hotel.getLatitude() - latitude;
        if (Math.abs(diffLatitude)>MAX_LATITUDE){
            diffLatitude = Math.abs(diffLatitude)-(MAX_LATITUDE*2);
        }
        double diffLongitude = hotel.getLongitude() - longitude;
        if (Math.abs(diffLongitude)>MAX_LONGITUDE){
            diffLongitude = Math.abs(diffLongitude)-(MAX_LONGITUDE*2);
        }
        return Math.sqrt((diffLatitude*diffLatitude)+(diffLongitude*diffLongitude));
    }
}
