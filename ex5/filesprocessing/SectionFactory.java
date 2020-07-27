package filesprocessing;
import java.util.ArrayList;
import filters.*;
import exceptions.*;

/**
 * The API SectionFactory.
 *  a class for the SectionFactory.
 * @author oop
 */
public class SectionFactory {

    /** minimum section size */
    private static final int MIN_SECTION_SIZE = 3;

    /** maximum section size */
    private static final int MAX_SECTION_SIZE = 4;

    /** type one error message */
    private static final String WARNING_IN_LINE = "Warning in line ";

    /** missing filter message */
    private static final String MISSING_FILTER = "ERROR: Filter sub-section is missing \n";

    /** missing order message */
    private static final String MISSING_ORDER = "ERROR: ORDER sub-section is missing \n";

    /** bad file message */
    private static final String BAD_FILE = "ERROR: Bad file \n";

    /** string yes */
    private static final String YES = "YES";

    /** string no */
    private static final String NO = "NO";

    /** string not */
    private static final String NOT = "NOT";

    /** string hash tag */
    private static final String HASHTAG = "#";

    /** string filter */
    private static final String FILTER = "FILTER";

    /** minimum filter size */
    private static final int MIN_FILTER_SIZE = 2;

    /** maximum filter size */
    private static final int MAX_FILTER_SIZE = 3;

    /** greater_than filter */
    private static final String GREATER_THAN = "greater_than";

    /** between filter */
    private static final String BETWEEN = "between";

    /** maximum between filter size*/
    private static final int MAX_BETWEEN_SIZE = 4;

    /** minimum between filter size*/
    private static final int MIN_BETWEEN_SIZE = 3;

    /** smaller_than filter */
    private static final String SMALLER_THAN = "smaller_than";

    /** file filter */
    private static final String FILE = "file";

    /** contains filter */
    private static final String CONTAINS = "contains";

    /** prefix filter */
    private static final String PREFIX = "prefix";

    /** suffix filter */
    private static final String SUFFIX = "suffix";

    /** writable filter */
    private static final String WRITABLE = "writable";

    /** executable filter */
    private static final String EXECUTABLE = "executable";

    /** hidden filter */
    private static final String HIDDEN = "hidden";

    /** all filter */
    private static final String ALL = "all";

    /** maximum all filter size*/
    private static final int MAX_ALL_SIZE = 2;

    /** string order */
    private static final String ORDER = "ORDER";

    /** abs order */
    private static final String ABS = "abs";

    /** type order */
    private static final String TYPE = "type";

    /** size order */
    private static final String SIZE = "size";

    /** string reverse */
    private static final String REVERSE = "REVERSE";

    /**
     * Creates an array list of the sections.
     * @param commandsArray the array list of the commands from the commands file
     * @return sectionsArray an array list of the sections
     */
    static ArrayList<Section> createSections(ArrayList<String> commandsArray) throws TypeTwo{
        ArrayList<Section> sectionArray = new ArrayList<>();
        int i = 0;
        while (i < commandsArray.size()) {
            if (commandsArray.size()-i<MIN_SECTION_SIZE){
                throw new TypeTwo(BAD_FILE);
            }
            Filter filter;
            Order order;
            ArrayList<String> messages = new ArrayList<>();
            filter = createFilter(commandsArray, i, messages);
            order = createOrder(commandsArray, i, messages);
            if (i+MIN_SECTION_SIZE<=commandsArray.size()-1 && commandsArray.get(i+MIN_SECTION_SIZE).equals(FILTER)){
                i--;
            }
            i+=MAX_SECTION_SIZE;
           sectionArray.add(new Section(filter,order,messages));
        }
        return sectionArray;
    }

    /**
     * Calls the orderCases method and returns an order object.
     * @param commandsArray the array list of the commands from the commands file
     * @param i the index of the start of the section.
     * @param messages the error messages array list
     * @return order an order object
     */
    private static Order createOrder(ArrayList<String> commandsArray, int i, ArrayList<String> messages)
            throws TypeTwo {
        Order order;
        if (commandsArray.get(i+2).equals(ORDER)) {
            if (i+MIN_SECTION_SIZE<=commandsArray.size()-1) {
                try {
                    order = orderCases(commandsArray, i); }
                catch (TypeOne typeOne) {
                    messages.add(typeOne.getLocalizedMessage()+String.valueOf(i+4));
                    order = new Order(ABS, false);
                }
            } else {
                order = new Order(ABS, false);
            }
        } else {
            throw new TypeTwo(MISSING_ORDER);
        }
        return order;
    }

    /**
     * Calls the filterCases method and returns a filter object.
     * @param commandsArray the array list of the commands from the commands file
     * @param i the index of the start of the section.
     * @param messages the error messages array list
     * @return filter a filter object
     */
    private static Filter createFilter(ArrayList<String> commandsArray, int i, ArrayList<String> messages)
            throws TypeTwo {
        Filter filter;
        if (commandsArray.get(i).equals(FILTER)) {
            try {
                filter = filterCases(commandsArray, i);
            }
            catch (TypeOne typeOne){
                messages.add(typeOne.getLocalizedMessage()+String.valueOf(i+2));
                filter = new AllFilter(false);
            }
        } else {
            throw new TypeTwo(MISSING_FILTER);
        }
        return filter;
    }

    /**
     * Creates an order object.
     * @param commandsArray the array list of the commands from the commands file
     * @param i the index of the start of the section.
     * @return order an order object
     */
    private static Order orderCases(ArrayList<String> commandsArray, int i) throws TypeOne {
        String[] parts = commandsArray.get(i + 3).split(HASHTAG);
        String lastPart = parts[parts.length - 1];
        Order order;
        if (commandsArray.get((i+MIN_SECTION_SIZE)).equals(FILTER)){
            order = new Order(ABS, false);
            return order;
        }
        if (parts[0].equals(ABS) || parts[0].equals(TYPE) || parts[0].equals(SIZE)){
            if (lastPart.equals(REVERSE)){
                order = new Order(parts[0], true);
            } else {
                order = new Order(parts[0],false);
            }
            return order;
        } else {
            throw new TypeOne(WARNING_IN_LINE);
        }
    }

    /**
     * Creates a filter object.
     * @param commandsArray the array list of the commands from the commands file
     * @param i the index of the start of the section.
     * @return filter a filter object
     */
    private static Filter filterCases(ArrayList<String> commandsArray, int i) throws TypeOne {
        String[] parts = commandsArray.get(i + 1).split(HASHTAG);
        String lastPart = parts[parts.length - 1];
        Filter filter;
        switch (parts[0]) {
            case GREATER_THAN:
                checkWrongCommand(parts, lastPart);
                if (Double.parseDouble(parts[1]) >= 0) {
                    if (lastPart.equals(NOT)) {
                        filter = new GreaterThanFilter(Double.parseDouble(parts[1]), true);
                    } else {
                        filter = new GreaterThanFilter(Double.parseDouble(parts[1]), false);
                    }
                } else {
                    throw new TypeOne(WARNING_IN_LINE);
                }
                break;
            case BETWEEN:
                checkWrongCommandBetweenFilter(parts, lastPart);
                if (Double.parseDouble(parts[1]) >= 0 && Double.parseDouble(parts[2]) >= 0 &&
                        Double.parseDouble(parts[1])<=Double.parseDouble(parts[2])) {
                    if (lastPart.equals(NOT)) {
                        filter = new BetweenFilter(Double.parseDouble(parts[1]),
                                Double.parseDouble(parts[2]), true);
                    } else {
                        filter = new BetweenFilter(Double.parseDouble(parts[1]),
                                Double.parseDouble(parts[2]), false);
                    }
                } else {
                    throw new TypeOne(WARNING_IN_LINE);
                }
                break;
            case SMALLER_THAN:
                checkWrongCommand(parts, lastPart);
                if (Double.parseDouble(parts[1]) >= 0) {
                    if (lastPart.equals(NOT)) {
                        filter = new SmallerThanFilter(Double.parseDouble(parts[1]), true);
                    } else {
                        filter = new SmallerThanFilter(Double.parseDouble(parts[1]), false);
                    }
                } else {
                    throw new TypeOne(WARNING_IN_LINE);
                }
                break;
            case FILE:
                checkWrongCommand(parts, lastPart);
                if (lastPart.equals(NOT)) {
                    filter = new FileFilter(parts[1], true);
                } else {
                    filter = new FileFilter(parts[1], false);
                }
                break;
            case CONTAINS:
                checkWrongCommand(parts, lastPart);
                if (lastPart.equals(NOT)) {
                    filter = new ContainsFilter(parts[1], true);
                } else {
                    filter = new ContainsFilter(parts[1], false);
                }
                break;
            case PREFIX:
                checkWrongCommand(parts, lastPart);
                if (lastPart.equals(NOT)) {
                    filter = new PrefixFilter(parts[1], true);
                } else {
                   filter = new PrefixFilter(parts[1], false);
                }
                break;
            case SUFFIX:
                checkWrongCommand(parts, lastPart);
                if (lastPart.equals(NOT)) {
                   filter = new SuffixFilter(parts[1], true);
                } else {
                    filter = new SuffixFilter(parts[1], false);
                }
                break;
            case WRITABLE:
                checkWrongCommand(parts, lastPart);
                filter = permissionsFiltersCases(parts, lastPart);
                break;
            case EXECUTABLE:
                checkWrongCommand(parts, lastPart);
                filter = permissionsFiltersCases(parts, lastPart);
                break;
            case HIDDEN:
                checkWrongCommand(parts, lastPart);
                filter = permissionsFiltersCases(parts, lastPart);
                break;
            case ALL:
                if (parts.length > MAX_ALL_SIZE ||(parts.length == MAX_ALL_SIZE && !lastPart.equals(NOT))) {
                    throw new TypeOne(WARNING_IN_LINE);
                }
                if (lastPart.equals(NOT)) {
                    filter = new AllFilter(true);
                } else {
                    filter = new AllFilter(false);
                }
                break;
            default:
                throw new TypeOne(WARNING_IN_LINE);
        }
        return filter;
    }
    /**
     * Checks if the command is wrong and if yes throws an exception.
     * @param parts the parts of the command line
     * @param lastPart the last value in the parts array
     */
    private static void checkWrongCommandBetweenFilter(String[] parts, String lastPart) throws TypeOne {
        if (parts.length > MAX_BETWEEN_SIZE || parts.length < MIN_BETWEEN_SIZE ||
                (parts.length == MAX_BETWEEN_SIZE && !lastPart.equals(NOT))) {
            throw new TypeOne(WARNING_IN_LINE);
        }
    }

    /**
     * Checks if the command is wrong and if yes throws an exception.
     * @param parts the parts of the command line
     * @param lastPart the last value in the parts array
     */
    private static void checkWrongCommand(String[] parts, String lastPart) throws TypeOne {
        if (parts.length > MAX_FILTER_SIZE || parts.length < MIN_FILTER_SIZE ||
                (parts.length == MAX_FILTER_SIZE && !lastPart.equals(NOT))) {
            throw new TypeOne(WARNING_IN_LINE);
        }
    }

    /**
     * Sorts which type of filter object to create and creates it.
     * @param parts the parts of the command line
     * @param lastPart the last value in the parts array
     * @return filter a filter object
     */
    private static Filter permissionsFiltersCases(String[] parts, String lastPart) throws TypeOne {
        if (parts[1].equals(YES) && lastPart.equals(NOT)) {
            return new PermissionsFilter(parts[0], true);
        } else if (parts[1].equals(YES)) {
            return new PermissionsFilter(parts[0], false);
        } else if (parts[1].equals(NO) && lastPart.equals(NOT)) {
            return new PermissionsFilter(parts[0], false);
        } else if (parts[1].equals(NO)) {
            return new PermissionsFilter(parts[0], true);
        } else {
            throw new TypeOne(WARNING_IN_LINE);
        }

    }
}