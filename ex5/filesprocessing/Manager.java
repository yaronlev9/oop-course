package filesprocessing;
import java.io.File;
import java.util.ArrayList;

/**
 * The API Manager.
 *  a class for the Manager.
 * @author oop
 */
public class Manager {

    /** an array list of all the sections */
    ArrayList<Section> sectionArray;

    /** an array of all the files */
    File[] filesArray;

    /**
     * Constructs a new Manager object with the given section array list and files array.
     *
     * @param sectionArray an array list of all the sections
     * @param filesArray an array of all the files
     */
    public Manager(ArrayList<Section> sectionArray, File[] filesArray){
        this.sectionArray = sectionArray;
        this.filesArray = filesArray;
    }

    /**
     * Iterates over all the sections and calls the printSection method.
     */
    public void printAllSections(){
        for (Section section : sectionArray){
                printSection(section);
        }
    }

    /**
     * Prints all the filtered files in the given order and error messages if exist.
     */
    private void printSection(Section section){
        ArrayList<File> filteredFilesArray = new ArrayList<>();
        for (String message: section.getMessages()){
            System.err.println(message);
        }
        for (File file: filesArray){
            if (section.getFilter().isPass(file)){
                filteredFilesArray.add(file);
            }
        }
        if (filteredFilesArray.isEmpty()){
            return;
        }
        File[] newFilesArray = section.getOrder().sortByOrder(createFilteredArray(filteredFilesArray));
        for (File file:newFilesArray){
            System.out.println(file.getName());
        }
    }

    /**
     * Gets a filtered array list of files and creates a new array.
     * @param filteredFilesArray the filtered array list of files
     * @return filesArray an array of filtered files
     */
    private File[] createFilteredArray( ArrayList<File> filteredFilesArray){
        File[] filesArray = new File[filteredFilesArray.size()];
        int i = 0;
        for (File file:filteredFilesArray){
            filesArray[i] = file;
            i++;
        }
        return filesArray;
    }
}
