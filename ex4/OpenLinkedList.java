import java.util.LinkedList;

/**
 * The API OpenLinkedList.
 *  a class for the OpenLinkedList.
 * @author oop
 */
public class OpenLinkedList {

    /** the OpenLinkedList */
    private LinkedList<String> strList;

    /**
     * Constructs a new OpenLinkedList object.
     *
     */
    public OpenLinkedList() {
         strList = new LinkedList<String>();
    }

    /**
     * @return the OpenLinkedList
     */
    public LinkedList<String> getLinkedList(){
        return strList;
    }
}
