package filesprocessing;
import exceptions.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * The API DirectoryProcessor.
 *  a class for the DirectoryProcessor.
 * @author oop
 */
public class DirectoryProcessor {

    /** the number of inputs */
    private static final int ARGS_NUM = 2;

    /** a string for the wring input error */
    private static final String WRONG_INPUT = "ERROR: wrong input \n";

    /** the directory given */
    private File sourcedir;

    /** the command file given */
    private File commandfile;

    /** the array of files in directory */
    private File[] filesArray;

    /** an array list of the lines in the command file */
    private ArrayList<String> commandsArray = new ArrayList<>();

    /** an array list of all the sections in the command file */
    private ArrayList<Section> sections;

    /**
     * Constructs a new DirectoryProcessor object with the given source directory and command file.
     *
     * @param sourcedir a string of the directory
     * @param commandfile a string of the command file
     */
    public DirectoryProcessor(String sourcedir, String commandfile)  {
        this.commandfile = new File(commandfile);
        this.sourcedir = new File(sourcedir);
        makeArrayOfFiles();
        try{
            makeArrayofComands();}
        catch(Exception e){
            System.err.println(WRONG_INPUT);
            sections = null;
            return;
        }
        try{
            sections = SectionFactory.createSections(commandsArray);
        } catch (TypeTwo typeTwo) {
            sections = null;
            System.err.print(typeTwo.getLocalizedMessage());
        }
    }

    /**
     * Creates an array of all the files in the given directory.
     */
    private void makeArrayOfFiles(){
        if (sourcedir.isDirectory()){
            filesArray = sourcedir.listFiles();
        }
        int counter = 0;
        for (File file : filesArray) {
            if (!file.isDirectory()) {
                counter++;
            }
        }
        File[] newFilesArray = new File[counter];
        int j = 0;
        for (File file : filesArray) {
            if (!file.isDirectory()) {
                newFilesArray[j] = file;
                j++;
            }
        }
        filesArray = newFilesArray;
    }

    /**
     * Creates an array of all lines in the command file.
     */
    private void makeArrayofComands() throws FileNotFoundException {
        Scanner scanner = new Scanner(commandfile);
        while (scanner.hasNextLine()) {
            commandsArray.add(scanner.nextLine());
        }
    }

    /**
     * Returns the section array list.
     * @return sections an array list of all the sections in the command file
     */
    private ArrayList<Section> getSections(){
        return sections;
    }

    /**
     * Returns an array of all the files.
     * @return filesArray the array of all the files
     */
    private File[] getFiles(){
        return filesArray;
    }

    /**
     * The main method that runs the program.
     * @param args the input of the source directory and the command file
     */
    public static void main(String[] args) {
        if (args.length!=ARGS_NUM){
            System.err.println(WRONG_INPUT);
            return; }
        DirectoryProcessor processor = new DirectoryProcessor(args[0],args[1]);
        if (processor.getSections() == null){
            return; }
        Manager manager = new Manager(processor.getSections(),processor.getFiles());
        manager.printAllSections();
    }

}
