package exceptions;
/**
 * The API TypeTwo.
 *  a class for the TypeTwo exception that extends the Exception.
 * @author oop
 */
public class TypeTwo extends Exception {

    /**
     * Constructs a new TypeTwo object with the error message.
     *
     * @param errorMessage the error message that is to be printed
     */
    public TypeTwo(String errorMessage){
        super(errorMessage);
    }
}
