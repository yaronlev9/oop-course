import oop.ex3.searchengine.*;
import java.util.Comparator;

/**
 * The API compare by star rating.
 *  a class for the compareByStarRating that implements the comparator interface and sorts by star rating.
 * @author oop
*/
public class CompareByStarRating implements Comparator<Hotel> {

    /**
     * Constructs a new compareByStarRating object.
     *
     */
    public CompareByStarRating(){

    }

    /**
     * overrides the compare method and compares two hotels by their star rating.
     * @param firstHotel the first hotel object
     * @param secondHotel the second hotel object
     * @return 1 if objects need to be switched, -1 if not or 0 if they are the same by rating
     */
    @Override
    public int compare(Hotel firstHotel, Hotel secondHotel) {
        if (firstHotel.getStarRating() < secondHotel.getStarRating()) {
            return 1;
        }
        if (firstHotel.getStarRating() > secondHotel.getStarRating()) {
            return -1;
        } else {
            return firstHotel.getPropertyName().compareTo(secondHotel.getPropertyName());
        }
    }
}
