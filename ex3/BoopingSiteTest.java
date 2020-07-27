import oop.ex3.searchengine.*;
import org.junit.*;
import static org.junit.Assert.*;


public class BoopingSiteTest {

    /** the general error message */
    private final String FAIL_TEST_MSG = "test failed";

    /** the maximal latitude */
    private final double MAX_LATITUDE = 90;

    /** the maximal longitude */
    private final double MAX_LONGITUDE = 180;

    /** the object of the searching site*/
    private static BoopingSite boopingSite1;

    /** the object of the searching site*/
    private static BoopingSite boopingSite2;

    /** the object of the searching site*/
    private static BoopingSite boopingSite3;

    /** a dataset of hotels that contains all the hotels*/
    private static final String BIG_DATASET = "hotels_dataset.txt";

    /** a dataset of hotels that contains all the hotels in manali*/
    private static final String MANALI_CITY_DATASET = "hotels_tst1.txt";

    /** an empty dataset*/
    private static final String EMPTY_DATASET = "hotels_tst2.txt";

    /** the array of all the hotels in the dataset given */
    private static final Hotel[] emptyArray = new Hotel[0];

    /** creates all the objects needed for the test*/
    @BeforeClass
    public static void createTestObjects() {
        boopingSite1 = new BoopingSite(BIG_DATASET);
        boopingSite2 = new BoopingSite(MANALI_CITY_DATASET);
        boopingSite3 = new BoopingSite(EMPTY_DATASET);
    }
    /** test for wrong inputs*/
    @Test
    public void test1GetHotelsInCityByRating(){
        /** boopingSite 1 checks when the city name is a null*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsInCityByRating(null));
        /** boopingSite 1 checks when the city name is not a real city*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsInCityByRating("not existing city"));

        /** boopingSite 2 checks when the city name is a null*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite2.getHotelsInCityByRating(null));
        /** boopingSite 2 checks when the city name is not a real city*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite2.getHotelsInCityByRating("not existing city"));

        /** boopingSite 3 checks when the city name is a null*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsInCityByRating(null));
        /** boopingSite 3 checks when a real city is given to an empty array*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsInCityByRating("manali"));
        /** boopingSite 3 checks when the city name is not a real city*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsInCityByRating("not existing city"));
    }

    /** checks the output of the method*/
    @Test
    public void test2GetHotelsInCityByRating(){
        /** checks if all hotels are in delhi*/
        assertTrue(checkHotelsInCity(boopingSite1.getHotelsInCityByRating("delhi"),"delhi"));
        /** checks if all hotels are sorted by star rating*/
        assertTrue(checkHotelsSortedByRating(boopingSite1.getHotelsInCityByRating("delhi")));
        /** checks if all hotels are sorted by star rating and alphabetical order*/
        assertTrue(checkHotelsSortedByAlphabet(boopingSite1.getHotelsInCityByRating("delhi")));

        /** checks if all hotels are in manali*/
        assertTrue(checkHotelsInCity(boopingSite1.getHotelsInCityByRating("manali"),"manali"));
        /** checks if all hotels are sorted by star rating*/
        assertTrue(checkHotelsSortedByRating(boopingSite1.getHotelsInCityByRating("manali")));
        /** checks if all hotels are sorted by star rating and alphabetical order*/
        assertTrue(checkHotelsSortedByAlphabet(boopingSite1.getHotelsInCityByRating("manali")));
    }

    /** test for wrong inputs with a full dataset*/
    @Test
    public void test1GetHotelsByProximity() {
        /** check when latitude is above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsByProximity(100,50));
        /** check when latitude is bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsByProximity(-100,50));
        /** check when longitude is above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsByProximity(50,200));
        /** check when longitude is bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsByProximity(50,-200));
        /** check when both latitude and longitude are above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsByProximity(100,200));
        /** check when both latitude and longitude are bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsByProximity(-100,-200));
    }

    /** test for wrong inputs with an empty dataset*/
    @Test
    public void test2GetHotelsByProximity() {
        /** check when latitude is above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsByProximity(100,50));
        /** check when latitude is bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsByProximity(-100,50));
        /** check when longitude is above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsByProximity(50,200));
        /** check when longitude is bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsByProximity(50,-200));
        /** check when both latitude and longitude are above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsByProximity(100,200));
        /** check when both latitude and longitude are bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsByProximity(-100,-200));
    }

    /** checks the output of the method*/
    @Test
    public void test3GetHotelsByProximity() {
        /** check if the array is sorted from the smallest distance to the highest*/
        assertTrue(checkHotelsSortedByDistance(
                boopingSite1.getHotelsByProximity(50,50),50,50));
        /** check if the array is sorted from the highest num of poi to the lowest*/
        assertTrue(checkHotelsSortedByNumberPOI(
                boopingSite1.getHotelsByProximity(50,50),50,50));

    }

    /** test1 for wrong inputs*/
    @Test
    public void test1GetHotelsInCityByProximity(){
        /** boopingSite 1 checks when the city name is a null*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsInCityByProximity
                (null,50,50));
        /** boopingSite 1 checks when the city name is not a real city*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsInCityByProximity(
                "not existing city",50,50));

        /** boopingSite 2 checks when the city name is a null*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite2.getHotelsInCityByProximity(
                null,50,50));
        /** boopingSite 2 checks when the city name is not a real city*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite2.getHotelsInCityByProximity(
                "not existing city",50,50));

        /** boopingSite 3 checks when the city name is a null*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsInCityByProximity(
                null,50,50));
        /** boopingSite 3 checks when a real city is given to an empty array*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsInCityByProximity(
                "manali",50,50));
        /** boopingSite 3 checks when the city name is not a real city*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsInCityByProximity(
                "not existing city",50,50));
    }

    /** test2 for wrong inputs with a full dataset*/
    @Test
    public void test2GetHotelsInCityByProximity(){
        /** check when latitude is above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsInCityByProximity(
                "delhi",100,50));
        /** check when latitude is bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsInCityByProximity(
                "delhi",-100,50));
        /** check when longitude is above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsInCityByProximity(
                "delhi",50,200));
        /** check when longitude is bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsInCityByProximity(
                "delhi",50,-200));
        /** check when both latitude and longitude are above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsInCityByProximity(
                "delhi",100,200));
        /** check when both latitude and longitude are bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite1.getHotelsInCityByProximity(
                "delhi",100,200));
    }

    /** test3 for wrong inputs with an empty dataset*/
    @Test
    public void test3GetHotelsInCityByProximity(){
        /** check when latitude is above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsInCityByProximity(
                "delhi",100,50));
        /** check when latitude is bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsInCityByProximity(
                "delhi",-100,50));
        /** check when longitude is above maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsInCityByProximity(
                "delhi",50,200));
        /** check when longitude is bellow maximum*/
        assertArrayEquals(FAIL_TEST_MSG,emptyArray,boopingSite3.getHotelsInCityByProximity(
                "delhi",50,-200));
    }

    /** checks the output of the method*/
    @Test
    public void test4GetHotelsInCityByProximity(){
        /** checks if all hotels are in delhi*/
        assertTrue(checkHotelsInCity(boopingSite1.getHotelsInCityByProximity(
                "delhi",50,50),"delhi"));
        /** checks if all hotels are sorted by distance*/
        assertTrue(checkHotelsSortedByDistance(boopingSite1.getHotelsInCityByProximity(
                "delhi",50,50),50,50));
        /** checks if all hotels are sorted by distance and number of POI*/
        assertTrue(checkHotelsSortedByNumberPOI(boopingSite1.getHotelsInCityByProximity(
                "delhi",50,50),50,50));

        /** checks if all hotels are in manali*/
        assertTrue(checkHotelsInCity(boopingSite1.getHotelsInCityByProximity(
                "manali",50,50),"manali"));
        /** checks if all hotels are sorted by distance*/
        assertTrue(checkHotelsSortedByDistance(boopingSite1.getHotelsInCityByProximity(
                "manali",50,50),50,50));
        /** checks if all hotels are sorted by distance and number of POI*/
        assertTrue(checkHotelsSortedByNumberPOI(boopingSite1.getHotelsInCityByProximity(
                "manali",50,50),50,50));
    }

    /**
     * check if all hotels are in the city.
     * @param hotels the hotels
     * @param city the city
     * @return true if all hotels are in the city or false otherwise
     */
    private boolean checkHotelsInCity(Hotel [] hotels,String  city) {
        for (Hotel hotel : hotels) {
            if (!hotel.getCity().equals(city)) {
                return false;
            }
        }
        return true;
    }

    /**
     * check if all hotels are organized from high star rating to low.
     * @param hotels the hotels
     * @return true if the array is organized by star rating and false if not
     */
    private boolean checkHotelsSortedByRating(Hotel [] hotels) {
        for (int i = 0; i<hotels.length-1; i++) {
            if (hotels[i+1].getStarRating()>hotels[i].getStarRating()) {
                return false;
            }
        }
        return true;
    }

    /**
     * check if all hotels are organized from high star rating to low and by alphabetical order.
     * @param hotels the hotels
     * @return true if the array is organized by star rating and alphabetical order false if not
     */
    private boolean checkHotelsSortedByAlphabet(Hotel [] hotels) {
        for (int i = 0; i<hotels.length-1; i++) {
            int compare = hotels[i].getPropertyName().compareTo(hotels[i+1].getPropertyName());
            if (hotels[i+1].getStarRating()==hotels[i].getStarRating() && compare>0) {
                return false;
            }
        }
        return true;
    }

    /**
     * calculates the distance of the hotel from a given position.
     * @param hotel the hotel
     * @param latitude the latitude
     * @param longitude the longitude
     * @return the distance of the hotel from the position
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

    /**
     * check if the hotels are sorted from smallest distance to highest.
     * @param hotels the hotels
     * @param latitude the latitude
     * @param longitude the longitude
     * @return true if the hotels are organized from smallest distance to highest false otherwise
     */
    private boolean checkHotelsSortedByDistance(Hotel [] hotels,double latitude, double longitude) {
        for (int i = 0; i<hotels.length-1; i++) {
            double distance1 = distanceFromPoint(hotels[i],latitude,longitude);
            double distance2 = distanceFromPoint(hotels[i+1],latitude,longitude);
            if (distance1>distance2) {
                return false;
            }
        }
        return true;
    }

    /**
     * check if the hotels are sorted from smallest distance to highest and from highest num of poi to lowest.
     * @param hotels the hotels
     * @param latitude the latitude
     * @param longitude the longitude
     * @return true if the hotels are organized from smallest distance to highest and from highest num of poi to
     * lowest and false otherwise
     */
    private boolean checkHotelsSortedByNumberPOI(Hotel [] hotels,double latitude, double longitude) {
        for (int i = 0; i<hotels.length-1; i++) {
            double distance1 = distanceFromPoint(hotels[i],latitude,longitude);
            double distance2 = distanceFromPoint(hotels[i+1],latitude,longitude);
            if (distance1==distance2 && hotels[i].getNumPOI() < hotels[i+1].getNumPOI()) {
                return false;
            }
        }
        return true;
    }

}
