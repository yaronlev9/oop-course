package filesprocessing;
import filters.*;
import java.util.ArrayList;

/**
 * The API Section.
 *  a class for the Section.
 * @author oop
 */
public class Section {

    /** an array list of the error warning messages */
    private ArrayList<String> messages;

    /** the filter */
    private Filter filter;

    /** the order */
    private Order order;

    /**
     * Constructs a new Section object with the given filter and order and error messages.
     *
     * @param filter the filter
     * @param order the order
     * @param messages an array list of the error warning messages
     */
    Section(Filter filter, Order order, ArrayList<String> messages){
        this.filter = filter;
        this.order = order;
        this.messages = messages;
    }

    /**
     * Returns the filter of the section.
     * @return filter
     */
    public Filter getFilter(){
        return filter;
    }

    /**
     * Returns the order of the section.
     * @return order
     */
    Order getOrder(){
        return order;
    }

    /**
     * Returns the error messages.
     * @return messages
     */
    ArrayList<String>  getMessages(){
        return messages;
    }
}
