package filesprocessing;
import java.io.File;

/**
 * The API Order.
 *  a class for the Order.
 * @author oop
 */
public class Order {

    /** the string of the dot */
    private final String  DOT = ".";

    /** the string of the abs order */
    private final String ABS = "abs";

    /** the string of the type order */
    private final String TYPE = "type";

    /** the string of the size order */
    private final String SIZE = "size";

    /** the name of the order */
    private String sortType;

    /**a boolean instance of activating reverse order or not */
    private boolean reversed;

    /**
     * Constructs a new Order object with the given order name.
     *
     * @param sortType the name of the order
     * @param reversed a boolean instance of activating reverse order or not
     */
    public Order(String sortType, boolean reversed){
        this.sortType = sortType;
        this.reversed = reversed;
    }

    /**
     * Checks the order name of the object and calls the correct order function and returns sorted array
     * @param filesArray the array of the files
     * @return filesArray the sorted array of the files
     */
    File[] sortByOrder(File[] filesArray){
        switch (sortType) {
            case ABS:
                sortByAbs(filesArray, 0, filesArray.length - 1);
                return filesArray;
            case SIZE:
                sortBySize(filesArray,0, filesArray.length - 1);
                return filesArray;
            case TYPE:
                sortByType(filesArray,0, filesArray.length - 1);
                return filesArray;
        }
        return filesArray;
    }

    /**
     * Sorts the array by abs using quick sort algorithm.
     * @param filesArray the array of the files
     * @param low the lowest index of the array
     * @param high the highest index of the array
     */
    private void sortByAbs(File[] filesArray,int low, int high){
        if (low<high){
            File pivot = filesArray[high];
            int i = (low-1);
            for (int j = low; j<high; j++){
                i = compareByAbs(filesArray,pivot, i, j);
            }
            File temp = filesArray[i+1];
            filesArray[i+1] = filesArray[high];
            filesArray[high] = temp;
            int partitionIndex = i+1;
            sortByAbs(filesArray,low, partitionIndex-1);
            sortByAbs(filesArray,partitionIndex+1, high);
        }
    }

    /**
     * Compares two files in the array and sorts them by abs.
     * @param filesArray the array of the files
     * @param pivot a file in the array
     * @param i an index of a file in the array
     * @param j an index of a file in the array
     * @return i an index in the array
     */
    private int compareByAbs(File[] filesArray, File pivot, int i, int j) {
        if (!reversed && filesArray[j].getAbsolutePath().compareTo(pivot.getAbsolutePath())<0){
            i++;
            switchObjects(filesArray,i, j);
        } else if (reversed && filesArray[j].getAbsolutePath().compareTo(pivot.getAbsolutePath())>0){
            i++;
            switchObjects(filesArray,i, j);
        }
        return i;
    }

    /**
     * Sorts the array by size using quick sort algorithm.
     * @param filesArray the array of the files
     * @param low the lowest index of the array
     * @param high the highest index of the array
     */
    private void sortBySize(File[] filesArray, int low, int high){
        if (low<high){
            File pivot = filesArray[high];
            int i = (low-1);
            for (int j = low; j<high; j++){
                i = compareBySize(filesArray,pivot, i, j);
            }
            File temp = filesArray[i+1];
            filesArray[i+1] = filesArray[high];
            filesArray[high] = temp;
            int partitionIndex = i+1;
            sortBySize(filesArray,low, partitionIndex-1);
            sortBySize(filesArray,partitionIndex+1, high);
        }
    }

    /**
     * Compares two files in the array and sorts them by size.
     * @param filesArray the array of the files
     * @param pivot a file in the array
     * @param i an index of a file in the array
     * @param j an index of a file in the array
     * @return i an index in the array
     */
    private int compareBySize(File[] filesArray, File pivot, int i, int j) {
        if (filesArray[j].length()==pivot.length()){
            i = compareByAbs(filesArray,pivot, i, j);
        } else if (!reversed && filesArray[j].length()<pivot.length()) {
            i++;
            switchObjects(filesArray,i, j);
        } else if (reversed && filesArray[j].length()>pivot.length()){
            i++;
            switchObjects(filesArray,i, j);
        }
        return i;
    }

    /**
     * Sorts the array by type using quick sort algorithm.
     * @param filesArray the array of the files
     * @param low the lowest index of the array
     * @param high the highest index of the array
     */
    private void sortByType(File[] filesArray, int low, int high){
        if (low<high){
            File pivot = filesArray[high];
            int i = (low-1);
            for (int j = low; j<high; j++){
                i = compareByType(filesArray,pivot, i, j);
            }
            File temp = filesArray[i+1];
            filesArray[i+1] = filesArray[high];
            filesArray[high] = temp;
            int partitionIndex = i+1;
            sortByType(filesArray,low, partitionIndex-1);
            sortByType(filesArray,partitionIndex+1, high);
        }
    }

    /**
     * Compares two files in the array and sorts them by type.
     * @param filesArray the array of the files
     * @param pivot a file in the array
     * @param i an index of a file in the array
     * @param j an index of a file in the array
     * @return i an index in the array
     */
    private int compareByType(File[] filesArray, File pivot, int i, int j) {
        if (getType(filesArray[j]).compareTo(getType(pivot))==0){
            i = compareByAbs(filesArray,pivot, i, j);
        } else if (!reversed && getType(filesArray[j]).compareTo(getType(pivot))<0) {
            i++;
            switchObjects(filesArray,i, j);
        } else if (reversed && getType(filesArray[j]).compareTo(getType(pivot))>0){
            i++;
            switchObjects(filesArray,i, j);
        }
        return i;
    }

    /**
     * Switches places of two files in the array.
     * @param filesArray the array of the files
     * @param i an index of a file in the array
     * @param j an index of a file in the array
     */
    private void switchObjects(File[] filesArray, int i, int j) {
        File temp = filesArray[i];
        filesArray[i] = filesArray[j];
        filesArray[j] = temp;
    }

    /**
     * Returns the type of the file.
     * @param file a file
     * @return the type of the file
     */
    private String getType(File file){
        String name = file.getName();
        if (!name.contains(DOT)){
            return "";
        } else if (name.startsWith(DOT) && name.lastIndexOf(DOT)==name.indexOf(DOT)){
            return "";
        }
        int index = name.lastIndexOf(DOT);
        return name.substring(index+1);
    }
}
