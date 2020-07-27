import oop.ex3.searchengine.*;
import java.util.*;

/**
 * The API booping site.
 *  a class for the booping site.
 * @author oop
 */
public class BoopingSite{

    /** the maximal latitude */
    private final double MAX_LATITUDE = 90;

    /** the maximal longitude */
    private final double MAX_LONGITUDE = 180;

    /** the array of all the hotels in the dataset given */
    private final Hotel[] hotelArray;

    /**
     * Constructs a new booping site object.
     *
     * @param name the name of the file with the data of the hotels.
     */
    public BoopingSite(String name) {
        hotelArray = HotelDataset.getHotels(name);
    }

    /**
     * gets an array of the hotels in the city given sorted by star rating.
     * @param city the name of the city
     * @return an array of the hotel in the city sorted by star rating
     */
    public Hotel[] getHotelsInCityByRating(String city) {
        if (city == null || hotelArray == null){
            return new Hotel[0];
        }
        int counter = hotelInCityCount(city);
        if (counter == 0){
            return new Hotel[0];
        }
        Hotel [] citySortArray = new Hotel[counter];
        makeArrayByCity(city, citySortArray);
        CompareByStarRating compareByStarRating = new CompareByStarRating();
        Arrays.sort(citySortArray,compareByStarRating);
        return citySortArray;
    }

    /**
     * gets an array of the hotels sorted by the distance from the given position.
     * @param latitude the latitude of the position
     * @param longitude the longitude of the position
     * @return an array of the hotels sorted by the distance from the given position
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude) {
        if (hotelArray == null || latitude>MAX_LATITUDE || latitude<-MAX_LATITUDE || longitude>MAX_LONGITUDE ||
                longitude<-MAX_LONGITUDE){
            return new Hotel[0];
        }
        Hotel [] proximitySortArray = new Hotel[hotelArray.length];
        makeNewArrayByProximity(proximitySortArray);
        CompareByDistance compareByDistance = new CompareByDistance(latitude,longitude);
        Arrays.sort(proximitySortArray,compareByDistance);
        return proximitySortArray;
    }

    /**
     * gets an array of the hotels in the city given sorted by the distance from the given position.
     * @param city the name of the city
     * @param latitude the latitude of the position
     * @param longitude the longitude of the position
     * @return an array of the hotels in the city given sorted by the distance from the given position
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude){
        if (hotelArray == null || latitude>MAX_LATITUDE || latitude<-MAX_LATITUDE || longitude>MAX_LONGITUDE ||
                longitude<-MAX_LONGITUDE || city == null){
             return new Hotel[0];
        }
        int counter = hotelInCityCount(city);
        if (counter == 0){
            return new Hotel[0];
        }
        Hotel [] citySortArray = new Hotel[counter];
        makeArrayByCity(city, citySortArray);
        CompareByDistance compareByDistance = new CompareByDistance(latitude,longitude);
        Arrays.sort(citySortArray,compareByDistance);
        return citySortArray;
    }

    /**
     * counts the number of hotels in the city.
     * @param city the name of the city
     * @return a number of hotels in the city
     */
    private int hotelInCityCount(String city) {
        int counter = 0;
        for (Hotel hotel : hotelArray) {
            if (hotel.getCity().equals(city)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * makes an array of the hotels in the city .
     * @param city the name of the city
     * @param citySortArray an empty array
     * @return an array of the hotels in the city
     */
    private void makeArrayByCity(String city, Hotel [] citySortArray) {
        int j = 0;
        for (int i = 0; i < hotelArray.length; i++) {
            if (hotelArray[i].getCity().equals(city)) {
                citySortArray[j] = hotelArray[i];
                j++;
            }
        }
    }

    /**
     * makes a new array of the hotels.
     * @param proximitySortArray an empty array
     * @return a new array of the hotels
     */
    private void makeNewArrayByProximity(Hotel [] proximitySortArray){
        for (int i = 0; i<proximitySortArray.length; i++){
            proximitySortArray[i] = hotelArray[i];
        }
    }
}
